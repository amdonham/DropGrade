package dropgrade.dropgrade;

/**
 * By: Madison McHam
 * 
 * Activity that hold the list of professors when click search professor on main menu
 * includes: layout.search_professor_activity
 * 
 * 
 */

// TODO Delete hardcoded professors and add professor list from database

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


public class SearchProfessorActivity extends AppCompatActivity {
	private ListView lv;
	
	ArrayAdapter<String> adapter;
	LoginDataBaseAdapter loginDataBaseAdapter;
	EditText inputSearch;
	String UserID;
	
	ArrayList<HashMap<String, String>> professorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    UserID = extras.getString("UID");
		}
		
        setContentView(R.layout.search_professor_activity);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		ArrayList<String> professors = loginDataBaseAdapter.getAllProfessors();
        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        
        adapter = new ArrayAdapter<String>(this, R.layout.professor_list, R.id.product_name, professors);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int itemPosition = position;
				String itemValue = (String) lv.getItemAtPosition(position);
				Intent nextScreen = new Intent(view.getContext(),ProfessorReviewActivity.class);
				nextScreen.putExtra("UID",UserID);
				nextScreen.putExtra("PName",adapter.getItem(position).split(" - ")[0]);
				startActivityForResult(nextScreen, 0);
				
			}
        	
        	
        });
        
        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {
             
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                SearchProfessorActivity.this.adapter.getFilter().filter(cs);   
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


}