package weeb.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteException;

public class APIKeys {

	private static final String DB_NAME = "Anilert.db";
	private static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;
	
	
	private static final String TABLE_API_KEYS = "API_KEYS";
	private static final String COLUMN_TITLE = "title";
	private static final String COLUMN_KEY = "key";
	
	private static final String GRACENOTE = "OnConnect_API_Key";
	private static final String GOOGLE_PLACES = "Google_Places_API_Key";
			
	private static String getAPIKey(String api) {
		
		String key = null;
		
		try (
				Connection conn = DriverManager.getConnection(CONNECTION_STRING);
				Statement statement = conn.createStatement();	
			) {
			
			String query = "SELECT " + COLUMN_KEY + " FROM " + TABLE_API_KEYS;
			query += " WHERE title = ";
			query += '"' + api + '"';
			
			ResultSet results = statement.executeQuery(query);
			while (results.next()) {
				key = results.getString(COLUMN_KEY);
			}
			
			results.close();
			statement.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return key;
	}
	
	public static String getGracenoteAPIKey() {
		return getAPIKey(GRACENOTE);
	}
	
	public static String getGooglePlacesAPIKey() {
		return getAPIKey(GOOGLE_PLACES);
	}
}
