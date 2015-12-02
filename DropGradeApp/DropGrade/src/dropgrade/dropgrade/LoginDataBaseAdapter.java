package dropgrade.dropgrade;


import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class LoginDataBaseAdapter {


	public static final int NAME_COLUMN = 3;

	static final String DATABASE_CREATE_TABLE_USER = "CREATE TABLE "+"USER"+
			"( " +"ID integer primary key autoincrement,"+ "USERNAME text,"+ "FNAME text,"+ "LNAME text,"+ "EMAIL text,"+ "PASSWORD  text,"+"REPASSWORD text)";

	static final String DATABASE_CREATE_TABLE_COURSE = "CREATE TABLE "+"COURSE"+
	"( " +"CID integer primary key autoincrement,"+ "CourseName text,"+ "DeptName text,"+ "UserID integer,"+ "Professor text"+")";
	
	public  SQLiteDatabase db;
	private final Context context;
	private DataBaseHelper dbHelper;

	public  LoginDataBaseAdapter(Context _context) 
	{
		context = _context;
		dbHelper = DataBaseHelper.getInstance(context);

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

	public void insertUserEntry(String username, String fname, String lname, String email, String password,String repassword)
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

	public int deleteUserEntry(String password)
	{
		String where="PASSWORD=?";
		int numberOFEntriesDeleted= db.delete("USER", where, new String[]{password}) ;
		return numberOFEntriesDeleted;
	}	
	public String getUserID(String username){
		Cursor cursor=db.query("USER", null, " USERNAME=?", new String[]{username}, null, null, null);
		if(cursor.getCount()<1) // UserName Not Exist
		{
			cursor.close();
			return "User Does Not Exist";
		}
		cursor.moveToFirst();
		String id= cursor.getString(cursor.getColumnIndex("ID"));
		cursor.close();
		return id;	
	}

	public String getPassword(String pw)
	{
		Cursor cursor=db.query("USER", null, " PASSWORD=?", new String[]{pw}, null, null, null);
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


	public void  updateUserEntry(String password,String repassword, String username, String fname, String lname, String email)
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



	public HashMap<String, String> getAllUserInfo(String id) {
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
	
	public void insertCourseEntry(String CourseName, String DeptName, String UserID, String ProfessorName)
	{
		ContentValues newValues = new ContentValues();
		newValues.put("UserID", UserID);
		newValues.put("DeptName",DeptName);
		newValues.put("CourseName", CourseName);
		newValues.put("Professor", ProfessorName);
		//newValues.put("ProfessorLName", ProfessorLName);
		//newValues.put("Semester", Semester);

		db.insert("COURSE", null, newValues);
	}

	public void deleteCourseEntry(String CID)
	{
		 db.execSQL("delete from COURSE where CID='"+CID+"'");
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


	public void  updateCourseEntry(String CourseName, String DeptName, Integer UserID,String CID, String ProfessorF){

		ContentValues updatedValues = new ContentValues();
		updatedValues.put("UserID", UserID);
		updatedValues.put("DeptName",DeptName);
		updatedValues.put("CourseName", CourseName);
		updatedValues.put("ProfessorName", ProfessorF);
		//updatedValues.put("ProfessorFName", ProfessorL);
		//updatedValues.put("Semester", Semester);

		String where="CID = ?";
		db.update("COURSE",updatedValues, where, new String[]{CID});			   
	}	



	public ArrayList<String> getAllCourseInfo(String UserID) {
		ArrayList<String> wordList = new ArrayList<String>();
		String selectQuery = "SELECT * FROM COURSE where UserID='"+UserID+"'";
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor.moveToFirst()) {
			
			do {
				wordList.add(cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+ " " + cursor.getString(3) + " " + cursor.getString(4) );
			} while (cursor.moveToNext());
			
		}				    
		return wordList;
	}	

}

