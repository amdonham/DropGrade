package dropgrade.dropgrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewUserActivity extends AppCompatActivity {

    EditText Name;
    EditText Username;
    EditText EmailID;
    EditText Password;
    EditText CPassword;
    Button RegButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_activity);

        Name =(EditText) findViewById(R.id.editName);
        Username = (EditText) findViewById(R.id.editUsername);
        EmailID  = (EditText) findViewById(R.id.editTextEmailID);
        Password = (EditText) findViewById(R.id.pwd);
        CPassword  = (EditText) findViewById(R.id.cpwd);
        RegButton = (Button) findViewById(R.id.buttonRegister);
        RegButton.setActivated(false);

        if(Password.getText().equals(CPassword.getText()))
        {
            RegButton.setActivated(true);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Passwords do not match! Try Again", Toast.LENGTH_SHORT).show();
        }

        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainMenuActivity.class);
                startActivityForResult(myIntent, 0);
                finish();
            }
        });
    }

    public boolean isPasswordMatching(String password, String confirmPassword) {
        Pattern pattern = Pattern.compile(password, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(confirmPassword);

        if (!matcher.matches()) {
            // do your Toast("passwords are not matching");

            return false;
        }

        return true;
    }
}