package mrhk.net.login_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Admin extends AppCompatActivity {
    private Button btn_login;
    private EditText et_email;
    private EditText et_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__admin);

        //once we logged in there is no need to login again so we use this format part 2
        SharedPreferences sp = getSharedPreferences("Login",MODE_PRIVATE);
        String email = sp.getString("email","NA");

        if (email.equals("admin@gmail.com")){
            Intent i = new Intent(Login_Admin.this,Dashboard.class);
            startActivity(i);
            finish();
        }

        //find view by id
        et_email =(EditText)findViewById(R.id.et_email);
        et_password =(EditText)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_login);

        //Set the onclick method to button
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (email.isEmpty()) {
                    et_email.setError("Email is required");
                    et_email.requestFocus();
                } else if (password.isEmpty()) {
                    et_password.setError("Password is required");
                    et_password.requestFocus();
                } else {
                    login(email, password);
                }
            }
        });
    }
    public void login(String email, String password){

        //Toast.makeText(getApplicationContext(),"Email"+email+"\n Password ="+password,Toast.LENGTH_SHORT).show();
        if(email.equals("admin@gmail.com") && password.equals("12345")){
            Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();


            //once we logged in there is no need to login again so we use this format part 1

            SharedPreferences sp = getSharedPreferences("Login",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("email",email);
            editor.apply();

            // If login Success take to new Activity
            Intent i = new Intent(Login_Admin.this,Dashboard.class);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_SHORT).show();

            // make field empty
            et_email.setText("");
            et_password.setText("");

        }

    }
}