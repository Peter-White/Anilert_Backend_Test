package weeb.power;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyStore.SecretKeyEntry;
import java.util.Arrays;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import weeb.data.JSONArrayTest;
import weeb.data.JSONObjectTest;

public class Main {

	public static void main(String[] args) {

////		JSONArray jsonArray;
//		String url = "http://data.tmsapi.com/v1.1/movies/showings?startDate=";
//		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//		System.out.println(today);
//		try {
//
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			URL urlConvert = new URL("http://data.tmsapi.com/v1.1/movies/showings?startDate=2018-12-21&numDays=60&zip=02110&lat=42.35222&lng=-71.0552&radius=15&api_key={API_KEY}");
//			InputStream is = urlConvert.openStream();
//			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//			String jsonText = readAll(rd);
//			JSONArray jsonArray = new JSONArray(jsonText);
//			
//			for (int i = 0; i < jsonArray.length(); i++) {
//				JSONObject jObject = (JSONObject) jsonArray.get(i);
//				if(jObject.has("genres")) {
//					String[] genresArray = (jObject.get("genres").toString().replace("[", "").replace("]", "")).split(",");
//					if(Arrays.asList(genresArray).indexOf("\"Anime\"") != -1) {
//						System.out.println(jObject.get("title"));
//						JSONArray showtimes = (JSONArray) jObject.get("showtimes");
//						for (int j = 0; j < showtimes.length(); j++) {
//							JSONObject showtime = (JSONObject) showtimes.get(j);
//							System.out.println(showtime);
//						}
//					}
//				}
//			}
//			
//		} catch (IOException | JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		JSONArrayTest.getMovieTheaterJSONArray("02127");
		
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
