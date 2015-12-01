package dropgrade.dropgrade;


import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class LoginDataBaseAdapter {

	static final String DATABASE_NAME = "login.db";
	static final int DATABASE_VERSION = 1;
	public static final int NAME_COLUMN = 3;

	static final String DATABASE_CREATE = "create table "+"USER"+
			"( " +"ID integer primary key autoincrement,"+ "USERNAME text,"+ "FNAME text,"+ "LNAME text,"+ "EMAIL text,"+ "PASSWORD  text,"+"REPASSWORD text) ";

	public  SQLiteDatabase db;
	private final Context context;
	private DataBaseHelper dbHelper;

	public  LoginDataBaseAdapter(Context _context) 
	{
		context = _context;
		dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

	}
	public  LoginDataBaseAdapter open() throws SQLException 
	{
		db = dbHelper.getWritableDatabase();
		return this;
	}
	public void close() 
	{
		db.close();
	}

	public  SQLiteDatabase getDatabaseInstance()
	{
		return db;
	}

	public void insertEntry(String username, String fname, String lname, String email, String password,String repassword)
	{
		ContentValues newValues = new ContentValues();
		newValues.put("PASSWORD", password);
		newValues.put("REPASSWORD",repassword);
		newValues.put("USERNAME", username);
		newValues.put("FNAME", fname);
		newValues.put("LNAME", lname);
		newValues.put("EMAIL", email);
		

		db.insert("USER", null, newValues);
	}

	public int deleteEntry(String password)
	{
		String where="PASSWORD=?";
		int numberOFEntriesDeleted= db.delete("USER", where, new String[]{password}) ;
		return numberOFEntriesDeleted;
	}	

	public String getSinlgeEntry(String username)
	{
		Cursor cursor=db.query("USER", null, " PASSWORD=?", new String[]{username}, null, null, null);
		if(cursor.getCount()<1) // UserName Not Exist
		{
			cursor.close();
			return "NOT EXIST";
		}
		cursor.moveToFirst();
		String repassword= cursor.getString(cursor.getColumnIndex("REPASSWORD"));
		cursor.close();
		return repassword;				
	}

//	public String getAllTags(String a) {
//
//
//		Cursor c = db.rawQuery("SELECT * FROM " + "USER" + " where SECURITYHINT = '" +a + "'" , null);
//		String str = null;
//		if (c.moveToFirst()) {
//			do {
//				str = c.getString(c.getColumnIndex("PASSWORD"));
//			} while (c.moveToNext());
//		}
//		return str;
//	}


	public void  updateEntry(String password,String repassword, String username, String fname, String lname, String email)
	{
		ContentValues updatedValues = new ContentValues();
		updatedValues.put("PASSWORD", password);
		updatedValues.put("REPASSWORD",repassword);
		updatedValues.put("USERNAME",username);
		updatedValues.put("FNAME",fname);
		updatedValues.put("LNAME",lname);
		updatedValues.put("EMAIL",email);

		String where="USERNAME = ?";
		db.update("USER",updatedValues, where, new String[]{password});			   
	}	



	public HashMap<String, String> getAllInfo(String id) {
		HashMap<String, String> wordList = new HashMap<String, String>();
		String selectQuery = "SELECT * FROM USER where ID='"+id+"'";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				wordList.put("PASSWORD", cursor.getString(1));
			} while (cursor.moveToNext());
		}				    
		return wordList;
	}	
}
