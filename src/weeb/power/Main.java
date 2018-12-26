package weeb.power;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

import weeb.data.JSONObjectTest;

public class Main {
	
	public static void main(String[] args) {

			try {
				JSONObject spiritedAway = JSONObjectTest.getMovieDBSearchJSONObject("fury road");
				System.out.println(spiritedAway);
			} catch (IOException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
