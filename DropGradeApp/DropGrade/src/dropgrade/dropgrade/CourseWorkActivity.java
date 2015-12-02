package dropgrade.dropgrade;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class CourseWorkActivity extends AppCompatActivity {

	final private static int DIALOG_LOGIN = 1;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    LoginDataBaseAdapter loginDataBaseAdapter;
    List<String> listHeader;
    HashMap<String, List<CourseWork>> listItems;
    Button AddAssignment;
    String courseID;
    String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_work_activity);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    courseID = extras.getString("courseID");
		    UserID = extras.getString("UID");
		}
        Button deleteButton = (Button) findViewById(R.id.deleteCourse);
        AddAssignment = (Button) findViewById(R.id.addAssign);
		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.listView10);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listHeader, listItems);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Showing a group as already expanded
        expListView.expandGroup(0);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on item click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int itemPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listHeader.get(groupPosition)
                                + " : "
                                + listItems.get(
                                listHeader.get(groupPosition)).get(
                                itemPosition).getName(), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
        
        AddAssignment.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				showDialog(DIALOG_LOGIN);

			}
	    });
        deleteButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				loginDataBaseAdapter.deleteCourseEntry(courseID,UserID);
				Intent nextScreen = new Intent(v.getContext(),CourseActivity.class);
				nextScreen.putExtra("UID",UserID);
				startActivityForResult(nextScreen, 0);
				

			}
	    });
	    
	}

    

    /*
     * Preparing the list data
     */

    private void prepareListData() {

        listHeader = new ArrayList<String>();
        listItems = new HashMap<String, List<CourseWork>>();

        
        // A list of Headers. I've added the Headers as simple Strings.. However, you can also change them to Objects of POJO. The process which I've applied to the itemsList can also be applied here.
        listHeader.add("Homework");
        listHeader.add("Projects");
        listHeader.add("Midterm");
        listHeader.add("Final");
        listHeader.add("Other");
        
        ArrayList<CourseWork> work = new ArrayList<CourseWork>();
        ArrayList<String> grades = loginDataBaseAdapter.getAllGradeInfo(UserID,courseID);
		for(int i = 0; i < grades.size(); i++){
			CourseWork item = new CourseWork();
			String[] splitInfo = grades.get(i).split(" ");
			String AID = splitInfo[0];
			String Name = splitInfo[1];
			String Type = splitInfo[2];
			Integer Grade = Integer.parseInt(splitInfo[3]);
			Integer Weight = Integer.parseInt(splitInfo[4]);
			item.setCategory(Type);
			item.setName(Name);
			item.setGrade(Grade);
			item.setWeight(Weight);
			work.add(item);
		}
        
        List<CourseWork> homeworks = new ArrayList<CourseWork>();
        List<CourseWork> midterms = new ArrayList<CourseWork>();
        List<CourseWork> finals = new ArrayList<CourseWork>();
        List<CourseWork> projects = new ArrayList<CourseWork>();
        List<CourseWork> other = new ArrayList<CourseWork>();
        
        for (int i = 0; i < work.size(); i++){
        	
        	
        	if (work.get(i).category.equals("Homework")){
        		homeworks.add(work.get(i));
        	}
        	
        	else if(work.get(i).category.equals("Midterm")){
        		midterms.add(work.get(i));
        	}
        	else if(work.get(i).category.equals("Final")){
        		finals.add(work.get(i));
        	}
        	
        	else if(work.get(i).category.equals("Projects")){
        		projects.add(work.get(i));
        	}
        	else if(work.get(i).category.equals("Other")){
        		other.add(work.get(i));
        	}
        }
        
        listItems.put(listHeader.get(0), homeworks);
        listItems.put(listHeader.get(1), projects);
        listItems.put(listHeader.get(2), midterms);
        listItems.put(listHeader.get(3), finals);
        listItems.put(listHeader.get(4), other);

        }
    
    @Override
    protected Dialog onCreateDialog(int id) {
     AlertDialog dialogDetails = null;
     switch (id) {
     case DIALOG_LOGIN:
      LayoutInflater inflater = LayoutInflater.from(this);
      View dialogview = inflater.inflate(R.layout.add_course_work, null);
      AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
      dialogbuilder.setTitle("New Assignment");
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
        .findViewById(R.id.btn_login3);
      Button cancelbutton = (Button) alertDialog
        .findViewById(R.id.btn_cancel3);
      
      final TextView text = (TextView) dialog.findViewById(R.id.text);
      text.setText("Enter new assignment information and press Submit!");
      final EditText assignname = (EditText) alertDialog
        .findViewById(R.id.txt_name);
      final RadioGroup category = (RadioGroup) alertDialog
        .findViewById(R.id.radioCategory);
      final EditText assignweight = (EditText) alertDialog
   	.findViewById(R.id.assignmentweight);
      final EditText grade = (EditText) alertDialog
   		   .findViewById(R.id.assignmentgrade);
     

      loginbutton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
        alertDialog.dismiss();
        int selectedID = category.getCheckedRadioButtonId();
        View radioButton = category.findViewById(selectedID);
        int radioId = category.indexOfChild(radioButton);
        RadioButton btn = (RadioButton) category.getChildAt(radioId);
        String selection = (String) btn.getText();
        loginDataBaseAdapter.insertAssignmentEntry(
       		 assignname.getText().toString(), 
       		 selection , 
       		 UserID, 
       		 assignweight.getText().toString(),
       		 grade.getText().toString(),
       		 courseID
       		 
       		 );
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


//package dropgrade.dropgrade;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ExpandableListView;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Random;
//
//
//public class CourseWorkActivity extends AppCompatActivity {
//
//	final private static int DIALOG_LOGIN = 1;
//    ExpandableListAdapter listAdapter;
//    ExpandableListView expListView;
//    LoginDataBaseAdapter loginDataBaseAdapter;
//    List<String> listHeader;
//    HashMap<String, List<CourseWork>> listItems;
//    Button AddAssignment;
//    String courseID;
//    String UserID;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.course_work_activity);
//        AddAssignment = (Button) findViewById(R.id.addAssign);
//        Bundle extras = getIntent().getExtras();
//		if (extras != null) {
//		    courseID = extras.getString("courseID");
//		    UserID = extras.getString("UID");
//		}
//        Button deleteButton = (Button) findViewById(R.id.deleteCourse);
//        AddAssignment = (Button) findViewById(R.id.addAssign);
//		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
//		loginDataBaseAdapter=loginDataBaseAdapter.open();
//        
//        // get the listview
//        expListView = (ExpandableListView) findViewById(R.id.listView10);
//
//        // preparing list data
//        prepareListData();
//
//        listAdapter = new ExpandableListAdapter(this, listHeader, listItems);
//
//        // setting list adapter
//        expListView.setAdapter(listAdapter);
//
//        // Showing a group as already expanded
//        expListView.expandGroup(0);
//
//        // Listview Group click listener
//        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                // Toast.makeText(getApplicationContext(),
//                // "Group Clicked " + listDataHeader.get(groupPosition),
//                // Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//
//        // Listview Group expanded listener
//        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // Listview Group collasped listener
//        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        // Listview on item click listener
//        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int itemPosition, long id) {
//                Toast.makeText(
//                        getApplicationContext(),
//                        listHeader.get(groupPosition)
//                                + " : "
//                                + listItems.get(
//                                listHeader.get(groupPosition)).get(
//                                itemPosition).getName(), Toast.LENGTH_SHORT)
//                        .show();
//                return false;
//            }
//        });
//        
//        AddAssignment.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//
//				showDialog(DIALOG_LOGIN);
//
//			}
//	    	
//	    });
//        deleteButton.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				loginDataBaseAdapter.deleteCourseEntry(courseID);
////				Toast.makeText(CourseWorkActivity.this, courseID, Toast.LENGTH_LONG).show();
//				Intent nextScreen = new Intent(v.getContext(),CourseActivity.class);
//				nextScreen.putExtra("UID",UserID);
//				startActivityForResult(nextScreen, 0);
//				
//
//			}
//	    });
//	    
//	    
//	}
//
//    
//
//    /*
//     * Preparing the list data
//     */
//
//    private void prepareListData() {
//
//        listHeader = new ArrayList<String>();
//        listItems = new HashMap<String, List<CourseWork>>();
//
//        // A list of Headers. I've added the Headers as simple Strings.. However, you can also change them to Objects of POJO. The process which I've applied to the itemsList can also be applied here.
//        listHeader.add("Assignment");
//        listHeader.add("Projects");
//        listHeader.add("Midterm");
//        listHeader.add("Final");
//        listHeader.add("Quizzes");
//        listHeader.add("Miscellaneous");
//
//        ArrayList<CourseWork> work = new ArrayList<CourseWork>();
//        CourseWork item = new CourseWork();
//        item.setCategory("Assignment");
//        item.setName("Assignment 1");
//        item.setGrade(100);
//        item.setWeight(6);
//        work.add(item);
//        
//        item = new CourseWork();
//        item.setCategory("Assignment");
//        item.setName("Assignment 2");
//        item.setGrade(100);
//        item.setWeight(6);
//        work.add(item);
//        
//        item = new CourseWork();
//        item.setCategory("Assignment");
//        item.setName("Assignment 3");
//        item.setGrade(100);
//        item.setWeight(6);
//        work.add(item);
//        
//        item = new CourseWork();
//        item.setCategory("Assignment");
//        item.setName("Assignment 4");
//        item.setGrade(60);
//        item.setWeight(6);
//        work.add(item);
//        
//        item = new CourseWork();
//        item.setCategory("Midterm");
//        item.setName("Midterm Exam");
//        item.setGrade(70);
//        item.setWeight(20);
//        work.add(item);
//        
////        item = new CourseWork();
////        item.setCategory("Final");
////        item.setName("Final Exam");
////        item.setGrade(90);
////        item.setWeight(30);
////        work.add(item);
//        
//        item = new CourseWork();
//        item.setCategory("Assignment");
//        item.setName("Assignment 5");
//        item.setGrade(70);
//        item.setWeight(6);
//        work.add(item);
//        
//        item = new CourseWork();
//        item.setCategory("Projects");
//        item.setName("Group Project");
//        item.setGrade(85);
//        item.setWeight(15);
//        work.add(item);
//        
//        item = new CourseWork();
//        item.setCategory("Miscellaneous");
//        item.setName("Attendance");
//        item.setGrade(100);
//        item.setWeight(5);
//        work.add(item);
//        
//        List<CourseWork> assignments = new ArrayList<CourseWork>();
//        List<CourseWork> quizzes = new ArrayList<CourseWork>();
//        List<CourseWork> midterms = new ArrayList<CourseWork>();
//        List<CourseWork> finals = new ArrayList<CourseWork>();
//        List<CourseWork> projects = new ArrayList<CourseWork>();
//        List<CourseWork> miscellaneous = new ArrayList<CourseWork>();
//        
//        for (int i = 0; i < work.size(); i++){
//        	
//        	
//        	if (work.get(i).category.equals("Assignment")){
//        		assignments.add(work.get(i));
//        	}
//        	
//        	else if(work.get(i).category.equals("Midterm")){
//        		midterms.add(work.get(i));
//        	}
//        	else if(work.get(i).category.equals("Final")){
//        		finals.add(work.get(i));
//        	}
//        	
//        	else if(work.get(i).category.equals("Projects")){
//        		projects.add(work.get(i));
//        	}
//        	else if(work.get(i).category.equals("Miscellaneous")){
//        		miscellaneous.add(work.get(i));
//        	}
//        	else if(work.get(i).category.equals("Quizzes")){
//        		miscellaneous.add(work.get(i));
//        	}
//        }
//        
//        listItems.put(listHeader.get(0), assignments);
//        listItems.put(listHeader.get(1), projects);
//        listItems.put(listHeader.get(2), midterms);
//        listItems.put(listHeader.get(3), finals);
//        listItems.put(listHeader.get(4), miscellaneous);
//        listItems.put(listHeader.get(5), quizzes);
//
//        }
//    
//    @Override
//    protected Dialog onCreateDialog(int id) {
//     AlertDialog dialogDetails = null;
//     switch (id) {
//     case DIALOG_LOGIN:
//      LayoutInflater inflater = LayoutInflater.from(this);
//      View dialogview = inflater.inflate(R.layout.add_course_work, null);
//      AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
//      dialogbuilder.setTitle("New Assignment");
//      dialogbuilder.setView(dialogview);
//      dialogDetails = dialogbuilder.create();
//      break;
//     }
//     return dialogDetails;
//    }
//
//   @Override
//    protected void onPrepareDialog(int id, Dialog dialog) {
//
//   	switch (id) {
//       case DIALOG_LOGIN:
//     final AlertDialog alertDialog = (AlertDialog) dialog;
//      Button loginbutton = (Button) alertDialog
//        .findViewById(R.id.btn_login3);
//      Button cancelbutton = (Button) alertDialog
//        .findViewById(R.id.btn_cancel3);
//      
//      final TextView text = (TextView) dialog.findViewById(R.id.text);
//      text.setText("Enter new assignment information and press Submit!");
//      final EditText assignname = (EditText) alertDialog
//        .findViewById(R.id.txt_name);
//      final RadioGroup category = (RadioGroup) alertDialog
//        .findViewById(R.id.radioCategory);
//      final EditText assignweight = (EditText) alertDialog
//   	.findViewById(R.id.assignmentweight);
//      final EditText grade = (EditText) alertDialog
//   		   .findViewById(R.id.assignmentgrade);
//     
//
//      loginbutton.setOnClickListener(new View.OnClickListener() {
//       @Override
//       public void onClick(View v) {
//        alertDialog.dismiss();
//        int selectedID = category.getCheckedRadioButtonId();
//        RadioButton categoryButton = (RadioButton) findViewById(selectedID);
//        Toast.makeText(
//          CourseWorkActivity.this,
//          
//          " Assignment Name : " + assignname.getText().toString()
//            + " Category : "
//            + categoryButton.getText().toString() + "  Assignment Grade : "
//                    + grade.getText().toString()  + "  Assignment Weight : "
//                            + assignweight.getText().toString(),
//          Toast.LENGTH_LONG).show();
//
//       }
//
//      });
//      cancelbutton.setOnClickListener(new View.OnClickListener() {
//       @Override
//       public void onClick(View v) {
//        alertDialog.dismiss();
//       }
//      });
//      break;
//     }
//    }
//    }
//
