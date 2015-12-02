package dropgrade.dropgrade;


import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class CoursesDatabaseAdapter {

	static final String DATABASE_NAME = "dropgrade.db";
	static final int DATABASE_VERSION = 1;
	public static final int NAME_COLUMN = 3;

	static final String DATABASE_CREATE = "create table "+"COURSE"+
			"( " +"CID integer primary key autoincrement,"+ "CourseName text,"+ "DeptName text,"+ "UserID integer) ";

	public  SQLiteDatabase db;
	private final Context context;
	private DataBaseHelper dbHelper;

	public  CoursesDatabaseAdapter(Context _context) 
	{
		context = _context;
		dbHelper = new DataBaseHelper(context);

	}
	public  CoursesDatabaseAdapter open() throws SQLException 
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

	public void insertCourseEntry(String CourseName, String DeptName, String UserID)
	{
		ContentValues newValues = new ContentValues();
		newValues.put("UserID", UserID);
		newValues.put("DeptName",DeptName);
		newValues.put("CourseName", CourseName);

		db.insert("COURSE", null, newValues);
	}

	public int deleteCourseEntry(String CID)
	{
		String where="CID=?";
		int numberOFEntriesDeleted= db.delete("COURSE", where, new String[]{CID}) ;
		return numberOFEntriesDeleted;
	}	

	public String getSinlgeCourseEntry(String CourseID)
	{
		Cursor cursor=db.query("COURSE", null, " CID=?", new String[]{CourseID}, null, null, null);
		if(cursor.getCount()<1) // UserName Not Exist
		{
			cursor.close();
			return "NOT EXIST";
		}
		cursor.moveToFirst();
		String CourseName= cursor.getString(cursor.getColumnIndex("CourseName"));
		cursor.close();
		return CourseName;				
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


	public void  updateCourseEntry(String CourseName, String DeptName, Integer UserID,String CID){

		ContentValues updatedValues = new ContentValues();
		updatedValues.put("UserID", UserID);
		updatedValues.put("DeptName",DeptName);
		updatedValues.put("CourseName", CourseName);

		String where="CID = ?";
		db.update("COURSE",updatedValues, where, new String[]{CID});			   
	}	



	public HashMap<String, String> getAllCourseInfo(String UserID) {
		HashMap<String, String> wordList = new HashMap<String, String>();
		String selectQuery = "SELECT * FROM COURSE where UserID='"+UserID+"'";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				wordList.put(UserID, cursor.getString(1));
			} while (cursor.moveToNext());
		}				    
		return wordList;
	}	
}
