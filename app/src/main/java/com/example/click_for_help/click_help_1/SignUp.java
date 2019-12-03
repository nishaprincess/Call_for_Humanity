package com.example.click_for_help.click_help_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    EditText name_signup,email_signup,password_signup,area_signup,city_signup;

    Button signup_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name_signup=(EditText)findViewById(R.id.name_signup);
        email_signup=(EditText)findViewById(R.id.email_signup);
        password_signup=(EditText)findViewById(R.id.password_signup);
        area_signup=(EditText)findViewById(R.id.area_signup);
        city_signup=(EditText)findViewById(R.id.city_signup);

        signup_signup=(Button)findViewById(R.id.signup_signup);

        signup_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this,LogIn.class);

                startActivity(intent);
            }
        });
    }
}
