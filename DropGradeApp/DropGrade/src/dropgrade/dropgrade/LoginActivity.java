package dropgrade.dropgrade;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
	
	final private static int DIALOG_LOGIN = 1;
	final Context context = this;
	EditText Username;
	EditText Password;
	Button SignInButton;
	Button RegisterButton;
	Button ForgotPasswordButton;
   
   ArrayList<User> Users;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        SignInButton = (Button) findViewById(R.id.email_sign_in_button);
        RegisterButton = (Button) findViewById(R.id.buttonRegister);
        ForgotPasswordButton = (Button) findViewById(R.id.forgotpw);
        
        SignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	String password = Password.getText().toString();
    			String login = Username.getText().toString();
    			if (login.equals(("testuser")) && password.equals("password")){
    				Intent myIntent = new Intent(v.getContext(), MainMenuActivity.class);
                    startActivityForResult(myIntent, 0);
                    finish();
    				}
                
    				else{
    					Toast.makeText(getApplicationContext(), "Invalid Login! Try Again!", Toast.LENGTH_SHORT).show();
    					Password.setText("");
    					Username.setText("");
    				}
            	}
            
        });
        

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), NewUserActivity.class);
                startActivityForResult(myIntent, 0);
                
            }
        });
        
        ForgotPasswordButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				showDialog(DIALOG_LOGIN);

			}
        	
        });
        
    }
    
    @Override
     protected Dialog onCreateDialog(int id) {
      AlertDialog dialogDetails = null;
      switch (id) {
      case DIALOG_LOGIN:
       LayoutInflater inflater = LayoutInflater.from(this);
       View dialogview = inflater.inflate(R.layout.forgot_password_activity, null);
       AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
       dialogbuilder.setTitle("Forgot Password");
       dialogbuilder.setView(dialogview);
       dialogDetails = dialogbuilder.create();
       break;
      }
      return dialogDetails;
     }

    @Override
     protected void onPrepareDialog(int id, Dialog dialog) {

    	switch (id) {
        case DIALOG_LOGIN:
      final AlertDialog alertDialog = (AlertDialog) dialog;
       Button loginbutton = (Button) alertDialog
         .findViewById(R.id.btn_login);
       Button cancelbutton = (Button) alertDialog
         .findViewById(R.id.btn_cancel);
       
       final TextView text = (TextView) dialog.findViewById(R.id.text);
       text.setText("Enter username and password and an email will be sent to you with your password!");
       final EditText userName = (EditText) alertDialog
         .findViewById(R.id.txt_name);
       final EditText password = (EditText) alertDialog
         .findViewById(R.id.password);
       loginbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         alertDialog.dismiss();
         Toast.makeText(
           LoginActivity.this,
           
           "User Name : " + userName.getText().toString()
             + "  Email : "
             + password.getText().toString(),
           Toast.LENGTH_LONG).show();

        }

       });
       cancelbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         alertDialog.dismiss();
        }
       });
       break;
      }
     }


    public void createProfile()
    {
        Intent intent=new Intent(this,NewUserActivity.class);
        startActivity(intent);
    }
    
    public void getUserProfile(){
    	Users = new ArrayList<User>();
    	User userInfo = new User();
    	userInfo.setName("Madison McHam");
    	userInfo.setUserName("mlmcham");
    	userInfo.setPassword("rooney");
    	userInfo.setEmail("mlmcham@crimson.ua.edu");
    	Users.add(userInfo);
    	
    
    	
    	
    }

}
