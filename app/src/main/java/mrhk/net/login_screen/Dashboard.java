package mrhk.net.login_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Key;

public class Dashboard extends AppCompatActivity {
    private Button btn_view, btn_add, btn_logout;
    private EditText et_name, et_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        et_name = (EditText)findViewById(R.id.et_name);
        et_mobile = (EditText)findViewById(R.id.et_mobile);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_logout = (Button) findViewById(R.id.btn_logout);


        //set Onclick listener in logout button
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("admin",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("email");
                editor.commit();

                String email =sp.getString("email", "NA");
                if (email.equals("NA")) {
                    Toast.makeText(getApplicationContext(), "Logout success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Login_Admin.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        //Add Button
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString().trim();
                String mobile = et_mobile.getText().toString().trim();
                if (name.isEmpty()){
                    et_name.setError("Empty");
                    et_name.requestFocus();

                }else if (mobile.isEmpty()){
                    et_mobile.setError("Empty");
                    et_mobile.requestFocus();
                }else {
                    add_data(name, mobile);
                }

            }
        });

        btn_view = (Button) findViewById(R.id.btn_view);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp = getSharedPreferences("name",MODE_PRIVATE);
                String name = sp.getString("name","NA");
                String mobile = sp.getString("mobile","NA");
                Toast.makeText(getApplicationContext(),"name ="+name+"mobile ="+mobile,Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void add_data(String name, String mobile) {
        SharedPreferences sp = getSharedPreferences("admin",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",name);
        editor.putString("mobile",mobile);
        editor.apply();
        Toast.makeText(getApplicationContext(),"Data Saved",Toast.LENGTH_SHORT).show();
    }
}