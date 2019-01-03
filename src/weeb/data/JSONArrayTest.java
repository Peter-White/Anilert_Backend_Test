package weeb.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;

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
		urlPath.append("&api_key=");
		urlPath.append(APIKeys.getGracenoteAPIKey());
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
	
	public static String currentDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return df.format(date).toString();
	}
	
}
