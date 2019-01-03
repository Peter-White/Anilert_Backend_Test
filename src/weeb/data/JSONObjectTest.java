package weeb.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONObjectTest {
	
	 private static StringBuilder urlPath;
	 private static final String movieDBStart = "https://api.themoviedb.org/3/search/movie?api_key=";
	 private static URL urlConvert;
	 
	  public static JSONObject createJSON(String url) throws IOException, JSONException {
	
		  JSONObject json = JSONReader.readJsonObjectFromUrl(url);
		  return json;
		  
	  }
	  
	  public static JSONObject getMovieDBSearchJSONObject(String title) throws IOException, JSONException {
		  	title = title.replaceAll(" ", "%20");
			urlPath = new StringBuilder(movieDBStart);
			String key = APIKeys.getMovieDBAPIKey();
			urlPath.append(key);
			urlPath.append("&language=en&query=");
			urlPath.append(title);
			urlPath.append("&page=1&include_adult=true");
			
			urlConvert = new URL(urlPath.toString());
			InputStream is = urlConvert.openStream();
			InputStreamReader reader = new InputStreamReader(is, Charset.forName("UTF-8"));
			BufferedReader rd = new BufferedReader(reader);
			String jsonText = JSONReader.readJSONData(rd);
			
			return new JSONObject(jsonText);
	  }

}
