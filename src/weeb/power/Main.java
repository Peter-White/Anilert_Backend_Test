package weeb.power;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import weeb.data.JSONArrayTest;
import weeb.data.JSONObjectTest;

public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {

		try {
			Map<Integer, JSONObject> placeCandidates = null;
			Map<Integer, JSONObject> animes = null;
			Map<Integer, JSONObject> showtimes = null;
			boolean quit = false;
			while (!quit) {
				System.out.println("Enter your place (or 'quit' to leave):");
				String myPlace = scanner.nextLine();
				if(!myPlace.equals("quit")) {
					placeCandidates = new HashMap<>();
					JSONArray canidates = (JSONArray) JSONObjectTest
							.getGoogleFindPlaceFromTextJSONObject(myPlace).get("candidates");
					System.out.println("Results:");
					for (int i = 0; i < canidates.length(); i++) {
						JSONObject place = (JSONObject) canidates.get(i);
						System.out.println((i + 1) + ": " + place.getString("formatted_address"));
						placeCandidates.put(i, place);
					}
				
					boolean backToMain = false;
					while (!backToMain) {
						System.out.println("Enter your loaction number or 0 if it is not present");
						int choice = scanner.nextInt();
						scanner.nextLine();
						if(choice == 0) {
							System.out.println("Try to be more specific.");
							backToMain = true;
						} else if (placeCandidates.containsKey(choice - 1)) {
							JSONObject locationData = placeCandidates.get(choice - 1).getJSONObject("geometry").getJSONObject("location");
							
							boolean backToLocation = false;
							while (!backToLocation) {
								System.out.println("Enter your search radius (km):");
								double radius = scanner.nextDouble();
								scanner.nextLine();
								if(radius > 0) {
									animes = new HashMap<>();
									JSONArray movies = JSONArrayTest.getMovieTheaterJSONArray(locationData.getDouble("lat"), locationData.getDouble("lng"), radius);
									int count = 0;
									int animePlace = 0;
									while (count < movies.length()) {
										
										JSONObject currentMovie = (JSONObject) movies.get(count);
						
										if(currentMovie.has("animation") && currentMovie.get("animation").equals("anime")) {
											animePlace++;
											animes.put(animePlace, currentMovie);
										}
										else if(currentMovie.has("genres")) {
											String genres = currentMovie.get("genres").toString();
											if(genres.indexOf("Anime") != -1 
//													|| genres.indexOf("Animated") != -1
											) {
												animePlace++;
												animes.put(animePlace, currentMovie);
											}
										}
										else {
											String description = null;
											if(currentMovie.has("shortDescription")) {
												description = (String) currentMovie.get("shortDescription");
												if(description.indexOf("Anime") != -1 || description.indexOf("anime") != -1) {
													animePlace++;
													animes.put(animePlace, currentMovie);
												}
											} else if(currentMovie.has("longDescription")) {
												description = (String) currentMovie.get("longDescription");
												if(description.indexOf("Anime") != -1 || description.indexOf("anime") != -1) {
													animePlace++;
													animes.put(animePlace, currentMovie);
												}
											} else {
												if(JSONObjectTest.isAnime(currentMovie.getString("title"))) {
													animePlace++;
													animes.put(animePlace, currentMovie);
												}
											}
										}
										
										count++;
										
									}
									
									if(animes.size() > 0) {
										
										boolean backToRadius = false;
										while (!backToRadius) {
											System.out.println("Here are some titles playing near you:");
											for (Entry<Integer, JSONObject> entry : animes.entrySet()) {
												System.out.println((entry.getKey()) + ": " + entry.getValue().getString("title"));
											}
											System.out.println("Choose which one you want to open or hit 0 to got back to location:");
											int animeChoice = scanner.nextInt();
											scanner.nextLine();
											if(animeChoice == 0) {
												backToLocation = true;
												backToMain = true;
											} else if (animes.containsKey(animeChoice)) {
												
												JSONArray showTimesArray = animes.get(animeChoice).getJSONArray("showtimes");
												showtimes = new HashMap<>();
												for (int i = 0; i < showTimesArray.length(); i++) {
													showtimes.put(i, (JSONObject) showTimesArray.get(i));
												}
												
												boolean backToAnimeSelect = false;
												while (!backToAnimeSelect) {
													System.out.println("\n" + animes.get(animeChoice).getString("title"));
													
													if(animes.get(animeChoice).has("shortDescription")) {
														System.out.println("\n" + animes.get(animeChoice).getString("shortDescription") + "\n");
													}
													
													System.out.println("Showtimes:");
													
													for (Entry<Integer, JSONObject> show : showtimes.entrySet()) {
														System.out.println((show.getKey() + 1) + ": " + show.getValue().getJSONObject("theatre").getString("name"));
														System.out.println(convertDate(show.getValue().getString("dateTime")));
														System.out.println(show.getValue().getString("ticketURI"));
														System.out.println();
													}
													
													backToAnimeSelect = true;
												}
											} else {
												System.out.println("Not acceptable. Try Again.");
											}
										}
										
									} else {
										System.out.println("Sorry no anime titles are playing near you. Try another location.");
										backToLocation = true;
										backToMain = true;
									}
									
								} else {
									System.out.println("Not acceptable. Try Again.");
								}
							}
							
							animes = new HashMap<>();
						} else {
							System.out.println("Selection not available. Please try again.");
						}
					}					
				} else {
					System.out.println("Buh-Bye");
					quit = true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String convertDate(String apiDate) {
		Pattern regExPattern = Pattern.compile("([0-9]{4})-([0-9]{2})-([0-9]{2})T([0-9]{2}):([0-9]{2})");
		Matcher matcher = regExPattern.matcher(apiDate);
		Date date = null;
		while (matcher.find()) {
			date = new Date();
			date.setYear(Integer.parseInt(matcher.group(1)) - 1900);
			date.setMonth(Integer.parseInt(matcher.group(2))-1);
			date.setDate(Integer.parseInt(matcher.group(3)));
			date.setHours(Integer.parseInt(matcher.group(4)));
			date.setMinutes(Integer.parseInt(matcher.group(5)));
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm aaa");
		return dateFormat.format(date);
	}
}
