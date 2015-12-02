package dropgrade.dropgrade;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;


public class DataBaseHelper extends SQLiteOpenHelper
{
	private static DataBaseHelper sInstance;
	static final String DATABASE_NAME = "dropgrade.db";
	static final int DATABASE_VERSION = 3;
	public DataBaseHelper(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase _db) 
	{
		_db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE_TABLE_USER);
		_db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE_TABLE_COURSE);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) 
	{
		// Log the version upgrade.
		Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");
		_db.execSQL("DROP TABLE IF EXISTS " + "USER");
		_db.execSQL("DROP TABLE IF EXISTS " + "COURSE");
		onCreate(_db);
	}
	public static synchronized DataBaseHelper getInstance(Context context) {

	    // Use the application context, which will ensure that you 
	    // don't accidentally leak an Activity's context.
	    // See this article for more information: http://bit.ly/6LRzfx
	    if (sInstance == null) {
	      sInstance = new DataBaseHelper(context.getApplicationContext());
	    }
	    return sInstance;
	  }
}
