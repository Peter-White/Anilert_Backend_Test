package weeb.power;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
		
		Map<String, JSONObject> animes = new HashMap<>();
		
		try {
			JSONArray myZip = JSONArrayTest.getMovieTheaterJSONArray("02127");
			JSONArray myLatLng = JSONArrayTest.getMovieTheaterJSONArray(42.3600825, -71.0588801);
			JSONArray myZipLatLng = JSONArrayTest.getMovieTheaterJSONArray("02127", 42.3600825, -71.0588801);
			JSONArray myZipRad = JSONArrayTest.getMovieTheaterJSONArray("02127", 50);
			JSONArray myLatLngRad = JSONArrayTest.getMovieTheaterJSONArray(42.3600825, -71.0588801, 50);
			JSONArray myZipLatLngRad = JSONArrayTest.getMovieTheaterJSONArray("02127", 42.3600825, -71.0588801, 50);
			
			JSONArray myLocation = myZip;
			
			int count = 0;
			while (count < myLocation.length()) {
				
				JSONObject currentMovie = (JSONObject) myLocation.get(count);
				String title = currentMovie.getString("title");

				if(currentMovie.has("animation") && currentMovie.get("animation").equals("anime")) {
					animes.put(title, currentMovie);
				}
				else if(currentMovie.has("genres")) {
					String genres = currentMovie.get("genres").toString();
					if(genres.indexOf("Anime") != -1 
//							|| genres.indexOf("Animated") != -1
					) {
						animes.put(title, currentMovie);
					}
				}
				else {
					String description = null;
					if(currentMovie.has("shortDescription")) {
						description = (String) currentMovie.get("shortDescription");
						if(description.indexOf("Anime") != -1 || description.indexOf("anime") != -1) {
							animes.put(title, currentMovie);
						}
					} else if(currentMovie.has("longDescription")) {
						description = (String) currentMovie.get("longDescription");
						if(description.indexOf("Anime") != -1 || description.indexOf("anime") != -1) {
							animes.put(title, currentMovie);
						}
					} else {
						System.out.println(currentMovie.get("title") + " *");
					}
				}
				
				count++;
				
			}
			
			for (Entry<String, JSONObject> entry : animes.entrySet()) {
				System.out.println(entry.getKey());
				System.out.println(entry.getValue().getJSONArray("showtimes"));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
