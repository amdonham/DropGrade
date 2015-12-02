package dropgrade.dropgrade;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class WhatIfActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.what_if_activity);

        final Button calculateButton = (Button) findViewById(R.id.button);
        final EditText currentGradeEditText = (EditText) findViewById(R.id.editText);
        final EditText examWeightEditText = (EditText) findViewById(R.id.editText2);
        final EditText expectedExamMarkEditText = (EditText) findViewById(R.id.editText3);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.equals(calculateButton)) {
                    if (currentGradeEditText.getText().toString().length() == 0 || examWeightEditText.getText().toString().length() == 0 || expectedExamMarkEditText.getText().toString().length() == 0) {
                        // Shows Error messages to user if s/he didn't put a grade in each of the textfields
                        Context context = getApplicationContext();
                        CharSequence text = "Make sure you entered in all the text fields!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        return;

                    } else {
                        //When the user has pressed the button AND THE TEXTFIELD IS FULL
                        gradeCalculation();
                    }

                } else {
                    Context context2 = getApplicationContext();
                    CharSequence text2 = "Enter your grade";
                    int duration2 = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context2, text2, duration2);
                    toast.show();
                }
            }
        };
        // Convert button's listner (Listens to see when it will be pressed)
        calculateButton.setOnClickListener(listener);
    }


    // Calculation method -- Calculates the grade
    public void gradeCalculation() {

        EditText currentGradeEditText = (EditText) findViewById(R.id.editText);
        double currentGrade = Double.parseDouble(currentGradeEditText.getText().toString().trim());

        EditText examWeightEditText = (EditText) findViewById(R.id.editText2);
        double examWeight = Double.parseDouble(examWeightEditText.getText().toString().trim());

        EditText expectedExamMarkEditText = (EditText) findViewById(R.id.editText3);
        double expectedExamMark = Double.parseDouble(expectedExamMarkEditText.getText().toString().trim());

        double regYearWeight = 1 - (examWeight / 100);
        double gradeNowPer = currentGrade * regYearWeight;
        double examGrade = examWeight * (expectedExamMark / 100);

        double finalGrade = gradeNowPer + examGrade;
        String finalGradeString = Double.toString(finalGrade);

        TextView showIt = (TextView) findViewById(R.id.textView);

        showIt.setText(finalGradeString + "%");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}