package weeb.power;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Set;

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
									while (count < movies.length()) {
										
										JSONObject currentMovie = (JSONObject) movies.get(count);
						
										if(currentMovie.has("animation") && currentMovie.get("animation").equals("anime")) {
											animes.put(count, currentMovie);
										}
										else if(currentMovie.has("genres")) {
											String genres = currentMovie.get("genres").toString();
											if(genres.indexOf("Anime") != -1 
//													|| genres.indexOf("Animated") != -1
											) {
												animes.put(count, currentMovie);
											}
										}
										else {
											String description = null;
											if(currentMovie.has("shortDescription")) {
												description = (String) currentMovie.get("shortDescription");
												if(description.indexOf("Anime") != -1 || description.indexOf("anime") != -1) {
													animes.put(count, currentMovie);
												}
											} else if(currentMovie.has("longDescription")) {
												description = (String) currentMovie.get("longDescription");
												if(description.indexOf("Anime") != -1 || description.indexOf("anime") != -1) {
													animes.put(count, currentMovie);
												}
											} else {
												if(JSONObjectTest.isAnime(currentMovie.getString("title"))) {
													animes.put(count, currentMovie);
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
												System.out.println((entry.getKey() + 1) + ": " + entry.getValue().getString("title"));
											}
											System.out.println("Choose which one you want to open or hit 0 to got back to location:");
											int animeChoice = scanner.nextInt();
											scanner.nextLine();
											if(animeChoice == 0) {
												backToLocation = true;
												backToMain = true;
											} else if (animes.containsKey(animeChoice - 1)) {
												System.out.println(animes.get(animeChoice - 1));
											} else {
												System.out.println("Not applicable. Try again.");
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
		
//		Map<String, JSONObject> animes = new HashMap<>();
//		
//		try {
////			JSONArray myZip = JSONArrayTest.getMovieTheaterJSONArray("02127");
//			JSONArray myLatLng = JSONArrayTest.getMovieTheaterJSONArray(42.3600825, -71.0588801);
////			JSONArray myZipLatLng = JSONArrayTest.getMovieTheaterJSONArray("02127", 42.3600825, -71.0588801);
////			JSONArray myZipRad = JSONArrayTest.getMovieTheaterJSONArray("02127", 50);
////			JSONArray myLatLngRad = JSONArrayTest.getMovieTheaterJSONArray(42.3600825, -71.0588801, 50);
////			JSONArray myZipLatLngRad = JSONArrayTest.getMovieTheaterJSONArray("02127", 42.3600825, -71.0588801, 50);
//			
//			JSONArray myLocation = myLatLng;
//			
//			int count = 0;
//			while (count < myLocation.length()) {
//				
//				JSONObject currentMovie = (JSONObject) myLocation.get(count);
//				String title = currentMovie.getString("title");
//
//				if(currentMovie.has("animation") && currentMovie.get("animation").equals("anime")) {
//					animes.put(title, currentMovie);
//				}
//				else if(currentMovie.has("genres")) {
//					String genres = currentMovie.get("genres").toString();
//					if(genres.indexOf("Anime") != -1 
//							|| genres.indexOf("Animated") != -1
//					) {
//						animes.put(title, currentMovie);
//					}
//				}
//				else {
//					String description = null;
//					if(currentMovie.has("shortDescription")) {
//						description = (String) currentMovie.get("shortDescription");
//						if(description.indexOf("Anime") != -1 || description.indexOf("anime") != -1) {
//							animes.put(title, currentMovie);
//						}
//					} else if(currentMovie.has("longDescription")) {
//						description = (String) currentMovie.get("longDescription");
//						if(description.indexOf("Anime") != -1 || description.indexOf("anime") != -1) {
//							animes.put(title, currentMovie);
//						}
//					} else {
//						if(JSONObjectTest.isAnime(title)) {
//							animes.put(title, currentMovie);
//						}
//					}
//				}
//				
//				count++;
//				
//			}
//			
//			for (Entry<String, JSONObject> entry : animes.entrySet()) {
//				System.out.println(entry.getKey());
//				System.out.println(entry.getValue().getJSONArray("showtimes"));
//				System.out.println();
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
	}

}
