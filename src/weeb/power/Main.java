package weeb.power;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import weeb.data.JSONObjectTest;

public class Main {

	public static void main(String[] args) {

//		JSONArray jsonArray;
		String url = "http://data.tmsapi.com/v1.1/movies/showings?startDate=";
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		System.out.println(today);
		try {
			JSONObject jsonObject = new JSONObject("http://data.tmsapi.com/v1.1/movies/showings?startDate=2018-12-20&numDays=60&zip=02127&api_key=6zta76smsf9nmnx75vvrmnt");
			JSONArray jsonArray = new JSONArray("http://data.tmsapi.com/v1.1/movies/showings?startDate=2018-12-20&numDays=60&zip=02127&api_key=6zta76smsf9nmnx75vvrmntf");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
