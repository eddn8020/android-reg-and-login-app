package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper DB;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);
        TextView repassword = findViewById(R.id.forgetpass);

        Button signupBtn = findViewById(R.id.regbtn);
        loginBtn = findViewById(R.id.logbtn);
        DB = new DBHelper(this);

        //admin and admin
        signupBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals("")){
                    // if correct
                    Toast.makeText(MainActivity.this, "Please complete all of the fields", Toast.LENGTH_SHORT).show();
                }else
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if (insert==true){
                                Toast.makeText(MainActivity.this, "Successful registration!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(MainActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "User already exists, please sign in!", Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Passwords not matching!", Toast.LENGTH_SHORT).show();
                    }

            }
        });


        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

    }
}