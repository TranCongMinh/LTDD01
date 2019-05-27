package com.example.admin.skype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Show view register
        final TextView viewRegister = (TextView) findViewById(R.id.view_register);
        viewRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
                finish();
            }
        });
        //Exec login
        final EditText username = (EditText)findViewById(R.id.txtUsername);
        final EditText password = (EditText)findViewById(R.id.txtPassword);
        final TextView btnlogin = (TextView) findViewById(R.id.btn_login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (username.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Please enter data!", Toast.LENGTH_LONG).show();
                    return;
                }
                UserDatabase udb = new UserDatabase(MainActivity.this);
                User user = udb.getUser(username.getText().toString());
                if (user.getPassword().equals(password.getText().toString())){
                    Intent i = new Intent(MainActivity.this, Main3Activity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Login failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

