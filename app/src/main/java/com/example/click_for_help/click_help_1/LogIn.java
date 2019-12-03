package com.example.click_for_help.click_help_1;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogIn extends AppCompatActivity  {

    EditText email_login,password_login;
    Button login_login;
    Button need_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);




        email_login=(EditText)findViewById(R.id.email_login);
        password_login=(EditText)findViewById(R.id.password_login);

        login_login=(Button)findViewById(R.id.login_login);

        Intent j=getIntent();

        final String for_single=j.getStringExtra("Main_activity");




        login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LogIn.this,Navigation.class);

                intent.putExtra("For_single_frag","log");
                intent.putExtra("Main_activity","log");

                 startActivity(intent);

            }
        });



        need_signup=(Button)findViewById(R.id.need_signup);

        need_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LogIn.this,SignUp.class);

                startActivity(intent);
            }
        });

    }


}
