package dropgrade.dropgrade;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class SearchCourseActivity extends AppCompatActivity {
	
	private ListView lv;
	LoginDataBaseAdapter loginDataBaseAdapter;
	ArrayAdapter<String> adapter;
	
	EditText inputSearch;
	
	ArrayList<HashMap<String, String>> courseList;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_course_activity);
		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		ArrayList<String> courses = loginDataBaseAdapter.getAllCourses();
//		String courses[] = {"CS 102 Mircocomputer Applications", "CS 104 The Science of Programming",
//				"CS 202 Introduction to the Information Highway", "CS 205 Web Site Design", 
//				"CS 260 Foundation of Computer Science", "CS 150 Programming I", "CS 250 Prgramming II",
//				"CS 350 Programming III C", "CS 350 Programming III C++", "CS 350 Programming III Java",
//				"CS 360 Data Structures and Allgorithms" };
		
		lv = (ListView) findViewById(R.id.list_view2);
        inputSearch = (EditText) findViewById(R.id.inputSearch2);
        
        adapter = new ArrayAdapter<String>(this, R.layout.professor_list, R.id.product_name, courses);
        lv.setAdapter(adapter);
        
        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {
             
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                SearchCourseActivity.this.adapter.getFilter().filter(cs);   
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
             
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub                          
            }
        });
        
}
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId();
	if (id == R.id.action_settings) {
	return true;
	}
	return super.onOptionsItemSelected(item);
	}*/

}

