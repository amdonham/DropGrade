package dropgrade.dropgrade;


import java.util.HashMap;
import java.util.Map.Entry;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;

public class WhatIfGradeCalculator extends Activity implements OnItemSelectedListener
{
	//Declaring local variable, form variables
	Spinner spinner1;
	EditText etWeight1;
	Button btnAddRow;
	Button btnNext;
	TableLayout tLayout;
	private static int count=1;
	HashMap<String, Integer> criteria=new HashMap<String,Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Linking form variables to local variables
		tLayout=(TableLayout)findViewById(R.id.tableLayout1);
		
		etWeight1=(EditText)findViewById(R.id.etWeight1);
		etWeight1.setKeyListener(DigitsKeyListener.getInstance(false, false));
		
		//Populating Spinner
		spinner1=(Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.criteria, android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter);
		spinner1.setPrompt("--Select--");
		spinner1.setOnItemSelectedListener(this);
		
		btnAddRow=(Button)findViewById(R.id.btnAddRow);
		//handling add row button click
		btnAddRow.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				createFields();
			}
		});
		
		btnNext=(Button)findViewById(R.id.btnCalculate);
		//Handling button click to transfer to next activity
		btnNext.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String message="";
				//Function call
				int flag=validateInput();
				// TODO Auto-generated method stub
				//Performing action based on the validation done in above function call
				if(flag==1){
					message="Criteria empty or duplicate criterias";
					Toast.makeText(WhatIfGradeCalculator.this, message, Toast.LENGTH_SHORT).show();
				}else if(flag==2){
					message="Check all criterias";
					Toast.makeText(WhatIfGradeCalculator.this, message, Toast.LENGTH_SHORT).show();
				}else if(flag==3){
					message="Sum of weight should be 100";
					Toast.makeText(WhatIfGradeCalculator.this, message, Toast.LENGTH_SHORT).show();
				}else if(flag==0){
					//If all conditions are satisfied redirecting to another activity
					//with bundle of hashmap
					Intent transferIntent = new Intent(WhatIfGradeCalculator.this,Calculator.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("criteria", criteria);
					transferIntent.putExtras(bundle);
					startActivity(transferIntent);
				}
			}
		});
	}

	/*
	 * This function creates a set of form elements
	 * This function is called on Add another row button click
	 * This function creates a spinner and populates it with values from String.xml
	 * This function creates an edit text which accepts only whole numbers
	 * This function creates a button which on click removes that table row
	 */
	public void createFields(){
		count++;
		final TableRow tRow = new TableRow(WhatIfGradeCalculator.this);
		tRow.setId(count);
		LayoutParams lParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lParams.bottomMargin+=20;
		tRow.setLayoutParams(lParams);
		
		LayoutParams spinnerParams = new LayoutParams(200,100);
		spinnerParams.leftMargin+=40;
		spinnerParams.rightMargin+=40;
		Spinner spinner = new Spinner(WhatIfGradeCalculator.this);
		spinner.setId(count);
		spinner.setLayoutParams(spinnerParams);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.criteria, android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setPrompt("--Select--");
		spinner.setOnItemSelectedListener(this);
		
		LayoutParams etParams = new LayoutParams(100,LayoutParams.WRAP_CONTENT);
		etParams.rightMargin+=40;
		EditText editText=new EditText(WhatIfGradeCalculator.this);
		editText.setId(count);
		editText.setLayoutParams(etParams);
		editText.setHint("%");
		editText.setInputType(InputType.TYPE_CLASS_NUMBER);
		editText.setKeyListener(DigitsKeyListener.getInstance(false, false));
		editText.requestFocus();
		
		LayoutParams btnParams = new LayoutParams(50,50);
		final Button btnRemove = new Button(WhatIfGradeCalculator.this);
		btnRemove.setLayoutParams(btnParams);
		btnRemove.setId(count);
		btnRemove.setBackgroundResource(R.drawable.cancel);
		
		btnRemove.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				int rowId = v.getId();
				if(rowId==btnRemove.getId()){
					TableRow tRow1 = (TableRow)findViewById(rowId);
					tLayout.removeView(tRow1);
				}
			}
		});
		
		tRow.addView(spinner);
		tRow.addView(editText);
		tRow.addView(btnRemove);
		
		tLayout.addView(tRow,lParams);
	}
	
	/*
	 * This function is used to organize all the inputs
	 * This function iterates through all rows in the table
	 * It then puts the values obtained in each row in a Hash map
	 * The reason to use Hash Map data structure is to avoid duplicate criteria
	 * The function returns a fully populated hash map if all values are valid
	 * Or it returns an empty hash map
	 */
	private HashMap<String, Integer> getCriterias(){
		criteria.clear();
		String key="";
		int value=0;
		for(int i=0;i<tLayout.getChildCount();i++){
			TableRow tr = (TableRow) tLayout.getChildAt(i);
				View v = tr.getChildAt(0);
				View v1 = tr.getChildAt(1);
				if(v instanceof Spinner){
					Spinner spin = (Spinner)tr.getChildAt(0);
					key = spin.getSelectedItem().toString();
					if(criteria.containsKey(key)){
						criteria.clear();
						break;
					}
				}
				if(v1 instanceof EditText){
					EditText eText = (EditText)tr.getChildAt(1);
					String val=eText.getText().toString();
					if(!val.equalsIgnoreCase("")){
						value=Integer.parseInt(val);
					}
				}
				criteria.put(key, value);
			}
		return criteria;
	}
	
	/*
	 * This function validates the user inputs
	 * This function gets the hash map populated in getCriterias()
	 * The funtion returns an integer value:
	 * 1: if the hash map is empty, which means the user entered a duplicate value
	 * 2: if the user failed to select a value in the drop down
	 * 3. if the sum of weight is not 100
	 * 0: if all values are valid
	 */
	public int validateInput(){
		getCriterias();
		int sum=0;
		
		if(criteria.isEmpty()){
			return 1;
		}
		
		for (Entry<String,Integer> entry : criteria.entrySet())
		{
			String val=entry.getKey();
			if(val.equalsIgnoreCase("--Select--")){
				return 2;
			}
			sum+=entry.getValue();
		}
		
		if(sum!=100){
			return 3;
		}
		
		return 0;
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

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
