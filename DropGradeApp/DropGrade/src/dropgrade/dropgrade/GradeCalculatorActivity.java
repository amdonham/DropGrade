package dropgrade.dropgrade;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * 
 * @author Madison McHam
 * 
 * Final grade calculator, needs work on the calculations. 
 *
 */
public class GradeCalculatorActivity extends AppCompatActivity {

	private TextView currentHeader,wantHeader,worthHeader,needHeader;
	private TextView currentText,wantText,finalText,needText;
	private double currentGrade,wantGrade,weight,needGrade;
	
    public GradeCalculatorActivity(){
    	currentGrade = wantGrade = weight = needGrade = 0;
    }
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.what_activity);
      
        //the headers
        currentHeader = (TextView)findViewById(R.id.currentGrade);
        
        wantHeader = (TextView)findViewById(R.id.whatWant);

        worthHeader = (TextView)findViewById(R.id.finalWorth);

        needHeader = (TextView)findViewById(R.id.whatNeed);

		currentText = (EditText) findViewById(R.id.currentText); //the percentages
        currentText.addTextChangedListener(new TextWatcher() {
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		String currentString = currentText.getText().toString();
        		if(currentText.getText().toString().length() > 0){ //make sure the edittext isnt empty
        			currentGrade = Double.valueOf(currentString);
        		}else{
        			currentGrade = 0;
        		}

        		needGrade = (wantGrade - (1-(weight/100))*currentGrade)/(weight/100);
        		needGrade = Math.round( needGrade * 1000.0 ) / 1000.0;
        		needText.setText(needGrade + "");

				int position = currentString.length(); //always sets the selection at the last position
				Editable etext = (Editable) currentText.getText();
				Selection.setSelection(etext,position);
        	}
        	public void afterTextChanged(Editable s) {
        	}
        	public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
        });
        wantText = (EditText) findViewById(R.id.wantText);
        wantText.addTextChangedListener(new TextWatcher() {
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		String wantString = wantText.getText().toString();
        		if(wantText.getText().toString().length() > 0){
        			wantGrade = Double.valueOf(wantString);
        		}else{
        			wantGrade = 0;
        		}

        		needGrade = (wantGrade - (1-(weight/100))*currentGrade)/(weight/100);
        		needGrade = Math.round( needGrade * 1000.0 ) / 1000.0;
        		needText.setText(needGrade + "");

				int position = wantString.length(); //always sets the selection at the last position
				Editable etext = (Editable) wantText.getText();
				Selection.setSelection(etext,position);
        	}
        	public void afterTextChanged(Editable s) {}
        	public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
        });
        finalText = (EditText) findViewById(R.id.finalText);
        finalText.addTextChangedListener(new TextWatcher() {
        	public void onTextChanged(CharSequence s, int start, int before, int count) {
        		String finalString = finalText.getText().toString();
        		if(finalText.getText().toString().length() > 0){
        			weight = Double.valueOf(finalString);
        		}else{
        			weight = 0;
        		}

        		needGrade = (wantGrade - (1-(weight/100))*currentGrade)/(weight/100);
        		needGrade = Math.round( needGrade * 1000.0 ) / 1000.0;
        		needText.setText(needGrade + "");

				int position = finalString.length(); //always sets the selection at the last position
				Editable etext = (Editable) finalText.getText();
				Selection.setSelection(etext,position);
        	}
        	public void afterTextChanged(Editable s) {}
        	public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
        });

        needText = (EditText) findViewById(R.id.needText);
        needText.setFocusable(false);
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

}
