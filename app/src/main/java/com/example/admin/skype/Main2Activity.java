package com.example.admin.skype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Show view login
        final Button viewLogin = (Button) findViewById(R.id.view_login);
        viewLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        //Exec register
        final Button register = (Button) findViewById(R.id.btn_register);
        final EditText username = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (username.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Please enter data!", Toast.LENGTH_LONG).show();
                    return;
                }
                UserDatabase udb = new UserDatabase(Main2Activity.this);
                User user = new User(username.getText().toString(),password.getText().toString());
                udb.addUser(user);
                Toast.makeText(getApplicationContext(),
                        "Register successfully!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
