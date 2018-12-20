package weeb.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;

public class JSONArrayTest {

	public static JSONArray getMovieTheaterJSONArray(String zipcode, Double lat, Double lng, int radius) {
		
	}
	
	public static JSONArray getMovieTheaterJSONArray(String zipcode, Double lat, Double lng) {
		
	}
	
	public static JSONArray getMovieTheaterJSONArray(String zipcode) {
		
	}
	
	public static JSONArray getMovieTheaterJSONArray(String zipcode, int radius) {
		
	}
	
	public static JSONArray getMovieTheaterJSONArray(Double lat, Double lng) {
		
	}
	
	public static JSONArray getMovieTheaterJSONArray(Double lat, Double lng, int radius) {
		
	}
	
	public static String currentDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return df.format(date).toString();
	}
}
