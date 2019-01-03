package weeb.power;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import weeb.data.JSONArrayTest;
import weeb.data.JSONObjectTest;

public class Main {
	
	public static void main(String[] args) {

//			try {
//				JSONObject spiritedAway = JSONObjectTest.getMovieDBSearchJSONObject("batman superman");
//				System.out.println(spiritedAway);
//			} catch (IOException | JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		
		try {
			JSONArray myZip = JSONArrayTest.getMovieTheaterJSONArray("02127");
			
			int descount = 0;
			int count = 0;
			while (count < myZip.length()) {
				
				JSONObject currentMovie = (JSONObject) myZip.get(count);
				String title = null;
				if(currentMovie.has("animation") && currentMovie.get("animation").equals("anime")) {
					title = (String) currentMovie.get("title");
				}
				else if(currentMovie.has("genres")) {
					String genres = currentMovie.get("genres").toString();
					if(genres.indexOf("Anime") != -1 
//							|| genres.indexOf("Animated") != -1
					) {
						title = (String) currentMovie.get("title");
					}
				}
				else {
					String description = null;
					if(currentMovie.has("shortDescription")) {
						description = (String) currentMovie.get("shortDescription");
						if(description.indexOf("Anime") != -1 || description.indexOf("anime") != -1) {
							title = (String) currentMovie.get("title");
						}
					} else if(currentMovie.has("longDescription")) {
						description = (String) currentMovie.get("longDescription");
						if(description.indexOf("Anime") != -1 || description.indexOf("anime") != -1) {
							title = (String) currentMovie.get("title");
						}
					} else {
						System.out.println(currentMovie.get("title") + " *");
					}
				}
				
				if(title != null) {
					System.out.println(title);
				}
				
				count++;
				
			}
			
			System.out.println(count);
			System.out.println(descount);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
