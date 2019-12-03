package com.example.click_for_help.click_help_1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Donate extends AppCompatActivity {

    EditText payment_amount,visa_number,mobile_number;

    Button confirm_donation;

    TextView activity_name_donate;

    RadioButton visa,bkash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        payment_amount=(EditText)findViewById(R.id.payment_amount);
        visa_number=(EditText)findViewById(R.id.visa_number);
        mobile_number=(EditText)findViewById(R.id.mobile_number);

        confirm_donation=(Button)findViewById(R.id.confirm_donation);

        activity_name_donate=(TextView)findViewById(R.id.activity_name_donate);

        visa=(RadioButton)findViewById(R.id.visa);
        bkash=(RadioButton)findViewById(R.id.bkash);



        confirm_donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(Donate.this);

                builder.setIcon(R.mipmap.ic_warning_black_24dp);

                builder.setTitle("CONFIRMATION");

                builder.setMessage("Are u sure u want to donate?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();

                        Toast.makeText(Donate.this,"Thanks For Your Donation",Toast.LENGTH_LONG).show();

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();

                        Toast.makeText(Donate.this,"Dismiss",Toast.LENGTH_LONG).show();

                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });





        // back arrow enable

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    // back arrow enable

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {


            Intent intent=new Intent(Donate.this,Navigation.class);
            intent.putExtra("For_single_frag","From_Donate");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
