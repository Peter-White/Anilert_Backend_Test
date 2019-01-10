package weeb.data;

import java.io.IOException;
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

//	http://data.tmsapi.com/v1.1/movies/showings?startDate=2018-12-21&numDays=60&zip=02110&lat=42.35222&lng=-71.0552&radius=15&api_key={API_KEY}
	public static JSONArray getMovieTheaterJSONArray(String zipcode, Double lat, Double lng, double radius) throws IOException, JSONException {
		urlPath = new StringBuilder(graceNoteURLStart);
		urlPath.append(numDays);
		urlPath.append("&zip=" + zipcode);
		urlPath.append("&lat=" + lat);
		urlPath.append("&lng=" + lng);
		urlPath.append("&radius=" + radius);
		urlPath.append("&units=km");
		urlPath.append("&api_key=");
		urlPath.append(APIKeys.getGracenoteAPIKey());
		
		return JSONReader.readJsonArrayFromUrl(urlPath.toString());
	}

//	http://data.tmsapi.com/v1.1/movies/showings?startDate=2018-12-21&numDays=60&zip=02110&lat=42.35222&lng=-71.0552&api_key={API_KEY}
	public static JSONArray getMovieTheaterJSONArray(String zipcode, Double lat, Double lng) throws IOException, JSONException {
		urlPath = new StringBuilder(graceNoteURLStart);
		urlPath.append(numDays);
		urlPath.append("&zip=" + zipcode);
		urlPath.append("&lat=" + lat);
		urlPath.append("&lng=" + lng);
		urlPath.append("&api_key=");
		urlPath.append(APIKeys.getGracenoteAPIKey());
		
		return JSONReader.readJsonArrayFromUrl(urlPath.toString());
	}

//	http://data.tmsapi.com/v1.1/movies/showings?startDate=2018-12-21&numDays=60&zip=02110&api_key={API_KEY}
	public static JSONArray getMovieTheaterJSONArray(String zipcode) throws IOException, JSONException {
		urlPath = new StringBuilder(graceNoteURLStart);
		urlPath.append(numDays);
		urlPath.append("&zip=" + zipcode);
		urlPath.append("&api_key=");
		urlPath.append(APIKeys.getGracenoteAPIKey());

		return JSONReader.readJsonArrayFromUrl(urlPath.toString());
	}

//	http://data.tmsapi.com/v1.1/movies/showings?startDate=2018-12-21&numDays=60&zip=02110&radius=15&api_key={API_KEY}
	public static JSONArray getMovieTheaterJSONArray(String zipcode, int radius) throws IOException, JSONException {
		urlPath = new StringBuilder(graceNoteURLStart);
		urlPath.append(numDays);
		urlPath.append("&zip=" + zipcode);
		urlPath.append("&radius=" + radius);
		urlPath.append("&units=km");
		urlPath.append("&api_key=");
		urlPath.append(APIKeys.getGracenoteAPIKey());
		
		return JSONReader.readJsonArrayFromUrl(urlPath.toString());
	}

//	http://data.tmsapi.com/v1.1/movies/showings?startDate=2018-12-21&numDays=60&lat=42.35222&lng=-71.0552&api_key={API_KEY}
	public static JSONArray getMovieTheaterJSONArray(Double lat, Double lng) throws IOException, JSONException {
		urlPath = new StringBuilder(graceNoteURLStart);
		urlPath.append(numDays);
		urlPath.append("&lat=" + lat);
		urlPath.append("&lng=" + lng);
		urlPath.append("&api_key=");
		urlPath.append(APIKeys.getGracenoteAPIKey());
		
		return JSONReader.readJsonArrayFromUrl(urlPath.toString());
	}

//	http://data.tmsapi.com/v1.1/movies/showings?startDate=2018-12-21&numDays=60&lat=42.35222&lng=-71.0552&radius=15&api_key={API_KEY}	
	public static JSONArray getMovieTheaterJSONArray(Double lat, Double lng, double radius) throws IOException, JSONException {
		urlPath = new StringBuilder(graceNoteURLStart);
		urlPath.append(numDays);
		urlPath.append("&lat=" + lat);
		urlPath.append("&lng=" + lng);
		urlPath.append("&radius=" + radius);
		urlPath.append("&units=km");
		urlPath.append("&api_key=");
		urlPath.append(APIKeys.getGracenoteAPIKey());
		
		return JSONReader.readJsonArrayFromUrl(urlPath.toString());
	}
	
//	public static JSONArray testFileJSON() throws IOException, JSONException {
//		
//		String path = "/home/Desktop/JSON_Data/gracenote_jsonarray.json";
//		
//		JSON
//		
//		return JSONReader.readJsonArrayFromUrl(path);
//		
//	}
	
	public static String currentDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return df.format(date).toString();
	}
	
}
