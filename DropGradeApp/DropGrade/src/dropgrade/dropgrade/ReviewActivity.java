package dropgrade.dropgrade;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Madison McHam
 * 
 * Review Course Activity
 * inludes: layout.review_activity
 *
 */
public class ReviewActivity extends AppCompatActivity{
	
	private RatingBar ratingBar;
	private TextView txtRatingValue;
	private Button btnSubmit;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_activity);

		//addListenerOnRatingBar();
		addListenerOnButton();

		Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner);
		String[] items = new String[] { "CS360 Data Structures", "CS315 Software Engineer", "CS457 Database Management Systems" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
		 dynamicSpinner.setAdapter(adapter);
		 
		 dynamicSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
	            @Override
	            public void onItemSelected(AdapterView<?> parent, View view,
	                    int position, long id) {
	                Log.v("item", (String) parent.getItemAtPosition(position));
	            }
	            
				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
					
				}
		 });
	
	}
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
	}
	
	  public void addListenerOnButton() {

			ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
			btnSubmit = (Button) findViewById(R.id.saveButton);

			//if click on me, then display the current rating value.
			btnSubmit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					Toast.makeText(ReviewActivity.this,
						String.valueOf(ratingBar.getRating()),
							Toast.LENGTH_SHORT).show();

				}

			});

		  }
	  /*
	  public void addListenerOnRatingBar() {

			ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
			txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

			//if rating value is changed,
			//display the current rating value in the result (textview) automatically
			ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
				public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {

					txtRatingValue.setText(String.valueOf(rating));

				}
			});
		  }*/

}
