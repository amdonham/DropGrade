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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


public class SearchProfessorActivity extends AppCompatActivity {
	private ListView lv;
	
	ArrayAdapter<String> adapter;
	LoginDataBaseAdapter loginDataBaseAdapter;
	EditText inputSearch;
	
	ArrayList<HashMap<String, String>> professorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_professor_activity);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		ArrayList<String> professors = loginDataBaseAdapter.getAllProfessors();
//        String professors[] = {"Alan Smith", "Betty Smith", "Monica Anderson", "Matthew Beagle", "David Cordes",
//                "Richard Borie", "Jeff Gray",
//                "Dana Hooper", "John Lusth", "Nicholas Kraft", "Yang Xiao", "Susan Vrbsky",
//                "Kimberly Wright", "Gregg Bell", "Sandra Bond", "Paul Brothers",
//                "Kathy Black"};
        
        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        
        adapter = new ArrayAdapter<String>(this, R.layout.professor_list, R.id.product_name, professors);
        lv.setAdapter(adapter);
        
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