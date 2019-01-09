package weeb.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONObjectTest {
	
	 private static StringBuilder urlPath;
	 private static final String movieDBStart = "https://api.themoviedb.org/3/search/multi?api_key=";
	 private static final String googlePlacesStart = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?";
	 
	  public static JSONObject getMovieDBSearchJSONObject(String title) throws IOException, JSONException {
		  	title = title.replaceAll(" ", "%20");
			urlPath = new StringBuilder(movieDBStart);
			urlPath.append(APIKeys.getMovieDBAPIKey());
			urlPath.append("&language=en&query=");
			urlPath.append(title);
			urlPath.append("&page=1&include_adult=true");
			
			return JSONReader.readJsonObjectFromUrl(urlPath.toString());
	  }
	  
	  public static JSONObject getGoogleFindPlaceFromTextJSONObject(String place) throws IOException, JSONException {
		  	place = place.replaceAll(" ", "%20");
		  	urlPath = new StringBuilder(googlePlacesStart);
		  	urlPath.append("input=" + place);
		  	urlPath.append("&inputtype=textquery&fields=place_id,photos,formatted_address,name,rating,opening_hours,geometry");
		  	urlPath.append("&key=" + APIKeys.getGooglePlacesAPIKey());
		  	
		  	return JSONReader.readJsonObjectFromUrl(urlPath.toString());
	  }
	  
	  public static Boolean isAnime(String title) throws JSONException, IOException {	
		  JSONArray results = getMovieDBSearchJSONObject(title).getJSONArray("results");
		  
		  for(int i = 0; i < results.length(); i++) {
			  JSONObject currentMovie = (JSONObject) results.get(i);
			  if(currentMovie.has("original_language")) {
				 String originalLanguage = currentMovie.getString("original_language"); 
				 String genreIds = currentMovie.get("genre_ids").toString();
			  
				  if(originalLanguage.equals("ja") && genreIds.contains("16")) {
					  return true;
				  }
			  }
		  }
		  
		  return false;
	  }

}
