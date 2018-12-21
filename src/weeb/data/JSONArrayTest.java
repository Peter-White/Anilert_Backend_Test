package weeb.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONArrayTest {
	
	private static final String graceNoteURLStart = "http://data.tmsapi.com/v1.1/movies/showings?startDate=" + currentDate();
	public static final String numDays = "&numDays=60";
	private static StringBuilder urlPath;
	private static JSONArray jsonArray;

//	public static JSONArray getMovieTheaterJSONArray(String zipcode, Double lat, Double lng, int radius) {
//		
//	}
//	
//	public static JSONArray getMovieTheaterJSONArray(String zipcode, Double lat, Double lng) {
//		
//	}
//	
	
//	http://data.tmsapi.com/v1.1/movies/showings?startDate=2018-12-21&numDays=60&zip=02110&lat=42.35222&lng=-71.0552&radius=15&api_key={API_KEY}
	public static JSONArray getMovieTheaterJSONArray(String zipcode) {
		urlPath = new StringBuilder(graceNoteURLStart);
		urlPath.append(numDays);
		urlPath.append("&zip=" + zipcode);
		System.out.println(urlPath.toString());
		return null;
	}
//	
//	public static JSONArray getMovieTheaterJSONArray(String zipcode, int radius) {
//		
//	}
//	
//	public static JSONArray getMovieTheaterJSONArray(Double lat, Double lng) {
//		
//	}
//	
//	public static JSONArray getMovieTheaterJSONArray(Double lat, Double lng, int radius) {
//		
//	}
	
	public static JSONArray readJsonFromUrl(String urlPath) throws IOException, JSONException {
		URL url = new URL(urlPath.toString());
		InputStream is = url.openStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		String jsonText = readAll(rd);
		JSONArray jsonArray = new JSONArray(jsonText);
		return jsonArray;
	}
	
	public static String currentDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return df.format(date).toString();
	}
	
	  private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
	  }
}
