package dropgrade.dropgrade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class Calculator extends Activity
{
	//Declaring local variables, form variables
	TableLayout tLayout;
	Button btnCalc;
	Button btnClear;
	Button btnBack;
	Button btnSave;
	TextView tvResult;
	EditText etName;
	private HashMap<String, Integer> criteria;
	private int rowCount=0;
	private int tvCount=0;
	private int etCount=0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
		
		//Linking form variables to form elements
		tLayout=(TableLayout)findViewById(R.id.tabLayout1);
		tvResult=(TextView)findViewById(R.id.tvResult);
		
		//Getting the hash map from intent
		Intent getIntent = getIntent();
		Bundle bundle = getIntent.getExtras();
		criteria = (HashMap<String, Integer>) bundle.getSerializable("criteria");
		
		//Calling create function
		create();
	
		btnCalc=(Button)findViewById(R.id.btnCalcGrade);
		//Handling calculate button click
		btnCalc.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				if(validate()){
					calcGrade();
				}else{
					Toast.makeText(Calculator.this, "Fill all values", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		btnClear=(Button)findViewById(R.id.btnClear);
		//Handling clear button click
		btnClear.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				clearAll();
			}
		});
		
		btnBack=(Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	/*
	 * This method uses the has map received from the intent
	 * It iterates through the map and populates the table with the keys and values
	 * from the map
	 */
	private void create(){
		for (Entry<String,Integer> entry : criteria.entrySet())
		{
			final TableRow tRow = new TableRow(Calculator.this);
			LayoutParams lParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			tRow.setLayoutParams(lParams);
			tRow.setId(rowCount);
			tRow.setBackgroundResource(R.drawable.border);
			
			LayoutParams tvParams1=new LayoutParams(255,150);
			TextView tvCriteria = new TextView(Calculator.this);
			tvCriteria.setLayoutParams(tvParams1);
			tvCriteria.setText(entry.getKey());
			tvCriteria.setId(tvCount);
			tvCriteria.setTextSize(20);
			
			LayoutParams tvParams2=new LayoutParams(100,100);
			tvParams2.rightMargin=10;
			TextView tvWeight = new TextView(Calculator.this);
			tvWeight.setLayoutParams(tvParams2);
			tvWeight.setText("("+String.valueOf(entry.getValue())+"%)");
			tvWeight.setId(++tvCount);
			tvWeight.setTextSize(16);
			
			LayoutParams etParams=new LayoutParams(200,100);
			
			final EditText etTotal=new EditText(Calculator.this);
			etTotal.setLayoutParams(etParams);
			etTotal.setId(etCount);
			etTotal.setHint("0.0");
			etTotal.setInputType(InputType.TYPE_CLASS_NUMBER);
			etTotal.setKeyListener(DigitsKeyListener.getInstance(false, true));
			
			final EditText etObtained=new EditText(Calculator.this);
			etObtained.setLayoutParams(etParams);
			etObtained.setId(++etCount);
			etObtained.setHint("0.0");
			etObtained.setInputType(InputType.TYPE_CLASS_NUMBER);
			etObtained.setKeyListener(DigitsKeyListener.getInstance(false, true));
			
			tRow.addView(tvCriteria);
			tRow.addView(tvWeight);
			tRow.addView(etTotal);
			tRow.addView(etObtained);
			
			tLayout.addView(tRow);
			
			rowCount++;
			tvCount++;
			etCount++;
		}
	}
	
	/*
	 * This function validates the user input
	 * The function returns false if user inputs null or invalid values
	 */
	private boolean validate(){
		float total=0.0f;
		float obtained=0.0f;
		
		for(int i=1;i<tLayout.getChildCount();i++){
			TableRow tr = (TableRow)tLayout.getChildAt(i);
			View v1=tr.getChildAt(2);
			View v2=tr.getChildAt(3);
			
			if(v1 instanceof EditText){
				if(((EditText) v1).getText().toString().trim().equalsIgnoreCase("")||
						((EditText) v1).getText().toString().trim().equalsIgnoreCase(".")){
					return false;
				}
				else{
					total=Float.valueOf(((EditText) v1).getText().toString().trim());
				}
			}
			if(v2 instanceof EditText){
				if(((EditText) v2).getText().toString().trim().equalsIgnoreCase("")||
						((EditText) v2).getText().toString().trim().equalsIgnoreCase(".")){
					return false;
				}
				else{
					obtained=Float.valueOf(((EditText) v2).getText().toString().trim());
				}
			}
			if(total<obtained){
				((EditText) v2).setText(((EditText) v1).getText().toString().trim());
			}
		}
		return true;
	}
	/*
	 * This function accepts a char sequence as input
	 * It removes all characters except digits
	 * And returns only the digits in string format
	 */
	private static String stripNonDigits(final CharSequence input){
    final StringBuilder sb = new StringBuilder(input.length());
    for(int i = 0; i < input.length(); i++){
        final char c = input.charAt(i);
        if(c > 47 && c < 58){
            sb.append(c);
        }
    }
    return sb.toString();
}
	
	/*
	 * This function calculates the grade
	 * The function loops through the table and gets all value
	 * It then applies the formula to calculate the grade
	 */
	private void calcGrade(){
		String strWeight="";
		int weight=0;
		float total=0.0f;
		float obtained=0.0f;
		float sum=0.0f;
		String result="";
		
		for(int i=1;i<tLayout.getChildCount();i++){
			TableRow tr = (TableRow)tLayout.getChildAt(i);
			View v=tr.getChildAt(1);
			View v1=tr.getChildAt(2);
			View v2=tr.getChildAt(3);
			
			if(v instanceof TextView){
				strWeight=stripNonDigits(((TextView) v).getText().toString());
				weight=Integer.valueOf(strWeight);
			}
			if(v1 instanceof EditText){
				total=Float.valueOf(((EditText) v1).getText().toString());
			}
			if(v2 instanceof EditText){
				obtained=Float.valueOf(((EditText) v2).getText().toString());
			}
			sum+=(obtained/total)*weight;
		}
		DecimalFormat df = new DecimalFormat("#.0");
		result="Grade point average: "+String.valueOf(df.format(sum));
		tvResult.setText(result);
	}
	
	/*
	 * This function clears all the values
	 * Including the result
	 */
	private void clearAll(){
		for(int i=1;i<tLayout.getChildCount();i++){
			TableRow tr = (TableRow)tLayout.getChildAt(i);
			View v1=tr.getChildAt(2);
			View v2=tr.getChildAt(3);
			
			if(v1 instanceof EditText){
				((EditText) v1).setText("");
			}
			if(v2 instanceof EditText){
				((EditText) v2).setText("");
			}
		}
		tvResult.setText("");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}