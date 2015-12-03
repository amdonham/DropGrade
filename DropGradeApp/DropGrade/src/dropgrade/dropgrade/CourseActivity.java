package dropgrade.dropgrade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Created By: Madison McHam
 * Course Activity that lists all the course that the user is enrolled in 
 * 
 */

public class CourseActivity extends Activity {
	
	Button AddCourse;
	final private static int DIALOG_LOGIN = 1;
	final Context context = this;
	LoginDataBaseAdapter loginDataBaseAdapter;
	String UserID;
	private ArrayAdapter<String> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classes);
//		CoursesDB = new CoursesDatabaseAdapter(this);
//		CoursesDB = CoursesDB.open();
		AddCourse = (Button) findViewById(R.id.addCButton);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    UserID = extras.getString("UID");
		}
		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		ArrayList<Course> image_details = getCourseData();
		final ListView lv1 = (ListView) findViewById(R.id.classList);
		lv1.setAdapter(new CustomListAdapter(this, image_details));
		lv1.setOnItemClickListener(new OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Object o = lv1.getItemAtPosition(position);
				Course newsData = (Course) o;
				Intent nextScreen = new Intent(v.getContext(),CourseWorkActivity.class);
				nextScreen.putExtra("UID",UserID);
				nextScreen.putExtra("courseName",newsData.getCourseName());
				nextScreen.putExtra("courseDept",newsData.getCourseSubject());
				nextScreen.putExtra("courseID",newsData.getCourseNum().toString());
				startActivityForResult(nextScreen, 0);
			}

		});
		    //Add Course Button
		    AddCourse.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					showDialog(DIALOG_LOGIN);
				}
		    });
		}
////CID integer primary key autoincrement,"+ "CourseName text,"+ "DeptName text,"+ "UserID integer," + "Professor text"
	private ArrayList<Course> getCourseData() {
		ArrayList<Course> results = new ArrayList<Course>();
		ArrayList<String> courses = loginDataBaseAdapter.getAllCourseInfo(UserID);
		for(int i = 0; i < courses.size(); i++){
			Course courseInfo = new Course();

			String[] splitCourse = courses.get(i).split("~");
			String id = splitCourse[0];
			String name = splitCourse[1];
			String dept = splitCourse[2];
			String fprof = splitCourse[4];
			
			courseInfo.setCourseName(name);
			courseInfo.setCourseNum(Integer.parseInt(id));
			courseInfo.setCourseSubject(dept);
			courseInfo.setProfessorFName(fprof);
			
			results.add(courseInfo);
		}
		return results;
	}
	
@Override
 protected Dialog onCreateDialog(int id) {
  AlertDialog dialogDetails = null;
  switch (id) {
  case DIALOG_LOGIN:
   LayoutInflater inflater = LayoutInflater.from(this);
   View dialogview = inflater.inflate(R.layout.add_course, null);
   AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
   dialogbuilder.setTitle("New Course");
   dialogbuilder.setView(dialogview);
   dialogDetails = dialogbuilder.create();
   break;
  }
  return dialogDetails;
 }

@Override
 protected void onPrepareDialog(int id, Dialog dialog) {

	switch (id) {
    case DIALOG_LOGIN:
  final AlertDialog alertDialog = (AlertDialog) dialog;
   Button loginbutton = (Button) alertDialog
     .findViewById(R.id.btn_login2);
   Button cancelbutton = (Button) alertDialog
     .findViewById(R.id.btn_cancel2);
   
   final TextView text = (TextView) dialog.findViewById(R.id.text);
   text.setText("Enter new course information and press Submit!");
   final EditText coursename = (EditText) alertDialog
     .findViewById(R.id.txt_name);
   final EditText coursesub = (EditText) alertDialog
	.findViewById(R.id.coursesub);
   final EditText professorf = (EditText) alertDialog
	.findViewById(R.id.professsorf);
   
//   final EditText semester = (EditText) alertDialog
//			.findViewById(R.id.semester);

   loginbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    	alertDialog.dismiss();
	    if(coursename.getText().length() > 0 && coursesub.getText().length() > 0 && professorf.getText().length() > 0){
	     loginDataBaseAdapter.insertCourseEntry(
	    		 coursename.getText().toString(), 
	    		 coursesub.getText().toString(), 
	    		 UserID, 
	    		 professorf.getText().toString()
	    		 );
	     loginDataBaseAdapter.insertProfessorEntry(professorf.getText().toString(), coursesub.getText().toString());
	    }
     
     finish();
     startActivity(getIntent());

	}
    

   });
   cancelbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
     alertDialog.dismiss();
    }
   });
   break;
  }
 }
}