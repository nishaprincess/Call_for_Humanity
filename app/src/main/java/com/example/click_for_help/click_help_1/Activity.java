package com.example.click_for_help.click_help_1;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);




       // To see all activities just to see in activity fragment after login page(all activity page)

        Intent j=getIntent();


        Bundle bundle=new Bundle();
      //  bundle.putString("btn_visible", "activity");

        String flag=j.getStringExtra("For_Signle_Activity");
        String post_key=j.getStringExtra("post_key");
        //String for_single=j.getStringExtra("Main_activity"); kaje lage nai






        if(flag.equals("total_activity"))
        {
            After_Activity_Button after_activity_button=new After_Activity_Button();


            FragmentManager fm=getSupportFragmentManager();

           /* bundle.putString("activity_single", "activity");
            bundle.putString("Main_activity",for_single);*/


            FragmentTransaction ft=fm.beginTransaction();

            ft.replace(R.id.frag_just_activity,after_activity_button);

            after_activity_button.setArguments(bundle);

            ft.commit();
        }
        else if(flag.equals("before_login"))
        {


            Single_Fragment single_fragment=new Single_Fragment();

            FragmentManager fm=getSupportFragmentManager();

            //bundle.putString("activity_single", "activity");

            FragmentTransaction ft=fm.beginTransaction();


            bundle.putString("visible","activity");
            bundle.putString("post_key",post_key);




            ft.replace(R.id.frag_just_activity,single_fragment);

            single_fragment.setArguments(bundle);

            ft.commit();
        }




    }
}
