package weeb.power;

import weeb.data.APIKeys;

public class Main {

	public static final String DB_NAME = "testjava.db";
	public static final String CONNECTION_STRING = "jdbc:sqlite:/home/leafcoder/eclipse-workspace/TestDB/" + DB_NAME;
	
	public static final String TABLE_CONTACTS = "contacts";
	
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_PHONE = "phone";
	public static final String COLUMN_EMAIL = "email";
	
	public static void main(String[] args) {

//		JSONArrayTest.getMovieTheaterJSONArray("02127");
		System.out.println(APIKeys.getGracenoteAPIKey());
		
	}

}
