package dropgrade.dropgrade;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;

import android.app.ActionBar.OnNavigationListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/*
 * Created by: Madison McHam
 * This is the Main Menu Activity it is the first screen the user 
 * sees when they login into DropGrade
 * 
 */

@SuppressWarnings("deprecation")
public class MainMenuActivity extends Activity {

	Button schedule;
	Button searchc;
	Button searchp;
	Button review;
	Button whatif;
	String UserID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		
		schedule = (Button) findViewById(R.id.scheduleb);
		searchc= (Button) findViewById(R.id.searchcb);
		searchp = (Button) findViewById(R.id.searchpb);
		review = (Button) findViewById(R.id.reviewb);
		whatif = (Button) findViewById(R.id.whatifb);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    UserID = extras.getString("UID");
		}

	}
	
	//depending on which button is pushed you will go to a new Activity
	public void buttonOnClick(View view)
	{
		if (view == schedule){
			Intent nextScreen = new Intent(view.getContext(),CourseActivity.class);
			nextScreen.putExtra("UID",UserID);
			startActivityForResult(nextScreen, 0);
		}
		
		else if (view == searchc){
        	Intent nextScreen = new Intent(view.getContext(),SearchCourseActivity.class);
			startActivityForResult(nextScreen, 0);
        }
        else if (view == searchp){
        	Intent nextScreen = new Intent(view.getContext(),SearchProfessorActivity.class);
			startActivityForResult(nextScreen, 0);
        }
        else if (view == review){
        	Intent nextScreen = new Intent(view.getContext(),ReviewActivity.class);
			startActivityForResult(nextScreen, 0);
        }
        else if (view == whatif){
        	Intent nextScreen = new Intent(view.getContext(),WhatIfGradeCalculator.class);
			startActivityForResult(nextScreen, 0);
        }
     
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
