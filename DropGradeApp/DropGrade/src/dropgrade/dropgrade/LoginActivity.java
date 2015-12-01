package dropgrade.dropgrade;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
	
	final private static int DIALOG_FORGOTPASSWORD = 1;
	final private static int DIALOG_LOGIN = 2;
	final Context context = this;
	EditText Username;
	EditText Password;
	Button SignInButton;
	Button RegisterButton;
	Button ForgotPasswordButton;
	LoginDataBaseAdapter loginDataBaseAdapter;
   ArrayList<User> Users;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
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
    			String storedPassword = loginDataBaseAdapter.getSinlgeEntry(password);
    			if (password.equals(storedPassword)){
					Toast.makeText(LoginActivity.this, "Congrats: Login Successfully", Toast.LENGTH_LONG).show();
					Intent ii=new Intent(LoginActivity.this,MainMenuActivity.class);
					startActivity(ii);
					finish();
    			}
//    			if (login.equals(("testuser")) && password.equals("password")){
//    				Intent myIntent = new Intent(v.getContext(), MainMenuActivity.class);
//                    startActivityForResult(myIntent, 0);
//                    finish();
//    				}
                
    				else{
    					Toast.makeText(getApplicationContext(), "Invalid Login! Try Again!", Toast.LENGTH_SHORT).show();
    					Password.setText("");
    					Username.setText("");
    				}
            	}
            
        });
        

//        RegisterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(v.getContext(), NewUserActivity.class);
//                startActivityForResult(myIntent, 0);
//                
//            }
//        });
//        
        
        RegisterButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				showDialog(DIALOG_LOGIN);

			}
        	
        });
     

        
        ForgotPasswordButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				showDialog(DIALOG_FORGOTPASSWORD);

			}
        	
        });
        
    }
    
    @Override
     protected Dialog onCreateDialog(int id) {
      AlertDialog dialogDetails = null;
      switch (id) {
      case DIALOG_FORGOTPASSWORD:
       LayoutInflater inflater = LayoutInflater.from(this);
       View dialogview = inflater.inflate(R.layout.forgot_password_activity, null);
       AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
       dialogbuilder.setTitle("Forgot Password");
       dialogbuilder.setView(dialogview);
       dialogDetails = dialogbuilder.create();
       break;
      case DIALOG_LOGIN:
    	  LayoutInflater inflator = LayoutInflater.from(this);
    	  View dialogview2 = inflator.inflate(R.layout.register_activity, null);
    	  AlertDialog.Builder dialogbuilder2 = new AlertDialog.Builder(this);
    	  dialogbuilder2.setTitle("New User Register");
    	  dialogbuilder2.setView(dialogview2);
    	  dialogDetails = dialogbuilder2.create();
    	  	
      }
      return dialogDetails;
     }

    @Override
     protected void onPrepareDialog(int id, Dialog dialog) {

    	switch (id) {
        case DIALOG_FORGOTPASSWORD:
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
        case DIALOG_LOGIN:
        	final AlertDialog alertDialog2 = (AlertDialog) dialog;
            Button registerbutton = (Button) alertDialog2
              .findViewById(R.id.btn_register);
            Button cancelbutton2 = (Button) alertDialog2
              .findViewById(R.id.btn_cancel);
            
            final TextView text2 = (TextView) dialog.findViewById(R.id.text2);
            text2.setText("Enter new username information and click Register");
            final EditText fname = (EditText) alertDialog2
              .findViewById(R.id.txt_fname);
            final EditText lname = (EditText) alertDialog2
                    .findViewById(R.id.txt_lname);
            final EditText username = (EditText) alertDialog2
                    .findViewById(R.id.editUsername);
            final EditText email = (EditText) alertDialog2
                    .findViewById(R.id.editTextEmailID);
            final EditText password2 = (EditText) alertDialog2
              .findViewById(R.id.password);
            final EditText repassword2 = (EditText) alertDialog2
                    .findViewById(R.id.password2);
            registerbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
            	 
            	 String pass = password2.getText().toString();
            	 String repass = repassword2.getText().toString();
            	 String user = username.getText().toString();
            	 String useremail = email.getText().toString();
            	 String fuser = fname.getText().toString();
            	 String luser = lname.getText().toString();
            	 
            	 if(fuser.equals("")||luser.equals("")||user.equals("")||useremail.equals("")||pass.equals("")||repass.equals(""))
 				{
 						Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_LONG).show();
 						return;
 				}
            	 if(!pass.equals(repass))
 				{
 					Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
 					return;
 				}
            	 else
 				{
            		 //String username, String fname, String lname, String email, String password,String repassword
 				    // Save the Data in Database
 				    loginDataBaseAdapter.insertEntry(user, fuser, luser, useremail, pass, repass);
 				   
 				   // reg_btn.setVisibility(View.GONE);
 				    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
 				    Log.d("PASSWORD",pass);
 				    Log.d("RE PASSWORD",repass);
 				    Log.d("USERNAME",user);
 				    Log.d("FNAME",fuser);
 				    Log.d("LNAME",luser);
 				    Log.d("EMAIL",useremail);
 				    Intent i=new Intent(LoginActivity.this,LoginActivity.class);
 				   	startActivity(i);
 					
 				}
              alertDialog2.dismiss();
              Toast.makeText(
                LoginActivity.this,
                
                "User Name : " + username.getText().toString()
                  + "  Email : "
                  + email.getText().toString(),
                Toast.LENGTH_LONG).show();

             }

            });
            cancelbutton2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
              alertDialog2.dismiss();
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
