package dropgrade.dropgrade;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Markus on 12.09.2014.
 */
public class DatabaseManager extends SQLiteOpenHelper
{
	public static final String TABLE_NAME_STUDENT = "student";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_FNAME = "fname";
	public static final String KEY_LNAME = "lname";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_PASSWORD = "password";
	
	private static final String DATABASE_NAME = "dropgrade";
	
	private static DatabaseManager instance;

	//private static final String DB_NAME = "gradesmanager.db";
	private static final int DB_VERSION = 1;

	private static final String[] DB_STRUCTURE =  new String[]
			{
					"CREATE TABLE `student` (" +
							" `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
							" `username` TEXT NOT NULL" +
							" 'fname' TEXT NOT NULL" +
							" 'lname' TEXT NOT NULLL"+
							" 'email' TEXT NOT NULL"+
							" 'password' TEXT NOT NULL"+
							");",
/*
					"CREATE TABLE `subject` (" +
							" `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
							" `name` TEXT NOT NULL" +
							");",

					"CREATE TABLE `course_work` (" +
							" `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
							" `course` INTEGER NOT NULL," +
							" `subject` INTEGER NOT NULL" +
							");",

					"CREATE TABLE `student` (" +
							" `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
							" `weight_group` INTEGER NOT NULL," +
							" `has_subject` INTEGER NOT NULL," +
							" `weighting` NUMERIC NOT NULL" +
							");",

					"CREATE TABLE `courses_taken' (" +
							" `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
							" `has_group` INTEGER," +
							" `has_type` INTEGER NOT NULL," +
							" `grade` NUMERIC NOT NULL," +
							" `note` TEXT" +
							");",

					"CREATE TABLE `courses` (" +
							" `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
							" `title` TEXT NOT NULL," +
							" `points` INTEGER NOT NULL DEFAULT '0'" +
							");",

					"CREATE TABLE `professor` (" +
							" `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
							" `has_subject` INTEGER NOT NULL," +
							" `type` INTEGER NOT NULL," +
							" `has_group` INTEGER NOT NULL," +
							" `multiplicator` INTEGER NOT NULL" +
							");",

					"CREATE TABLE `review` (" +
							" `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
							" `name` TEXT NOT NULL UNIQUE" +
							");"*/
			};


	public static SQLiteDatabase getInstance(Context context)
	{
		if(instance == null)
		{
			instance = new DatabaseManager(context);
		}

		return instance.getReadableDatabase();
	}

	private DatabaseManager(Context context)
	{
		super(context, DATABASE_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		for (String aDB_STRUCTURE : DB_STRUCTURE)
		{
			db.execSQL(aDB_STRUCTURE);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		//TODO
	}
}
