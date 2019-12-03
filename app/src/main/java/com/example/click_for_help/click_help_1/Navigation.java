package com.example.click_for_help.click_help_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();





        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_view_headline_white_24dp);

        Intent j=getIntent();



        String key=j.getStringExtra("For_single_frag");//login tekhe asce "log" or single activity dekhte asce

        String post_Key=j.getStringExtra("post_key");





        // for button visibility
       // String btn=j.getStringExtra("btn_visible");

       // After login fragmrntation j kokhon kon fragment change hosse



        if(key.equals("single"))
        {

            // Single activity te click krar por after login page e ase single activity dekhte



                Single_Fragment single_fragment = new Single_Fragment();

                FragmentManager fm = getSupportFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();

                Bundle args = new Bundle();

                args.putString("visible", "log");


                args.putString("post_key",post_Key);


                single_fragment.setArguments(args);



                single_fragment.setArguments(args);


                ft.replace(R.id.mylayout, single_fragment);

                ft.commit();

        }
        else if(key.equals("log"))
        {

            // login krar por activity page navigation page e ase page change krte hobe all activity dekhte

            After_LogIn after_logIn=new After_LogIn();

            FragmentManager fm=getSupportFragmentManager();

            FragmentTransaction ft=fm.beginTransaction();

           // for button visiblity
            Bundle args = new Bundle();

            args.putString("Main_activity","log");


            after_logIn.setArguments(args);



            ft.replace(R.id.mylayout,after_logIn);

            ft.commit();
        }
        else if(key.equals("From_Donate"))
        {
            After_LogIn after_logIn=new After_LogIn();

            FragmentManager fm=getSupportFragmentManager();

            FragmentTransaction ft=fm.beginTransaction();

            // for button visiblity
            Bundle args = new Bundle();
            args.putString("Main_activity", "log");
            after_logIn.setArguments(args);



            ft.replace(R.id.mylayout,after_logIn);

            ft.commit();
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


           if (id == R.id.action_add) {

               FragmentManager fm = getSupportFragmentManager();

               FragmentTransaction ft = fm.beginTransaction();


               ft.replace(R.id.mylayout, new New_Event());

               ft.commit();

               return true;
           }




        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



            if (id == R.id.nav_account) {

            } else if (id == R.id.nav_donate) {

                After_LogIn after_logIn=new After_LogIn();

                FragmentManager fm = getSupportFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();

                Bundle args = new Bundle();
                args.putString("Main_activity", "log");
                after_logIn.setArguments(args);

                ft.replace(R.id.mylayout, after_logIn);

                ft.commit();

            } else if (id == R.id.nav_logout) {

                Intent intent=new Intent(Navigation.this, MainActivity.class);

                startActivity(intent);

            }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
