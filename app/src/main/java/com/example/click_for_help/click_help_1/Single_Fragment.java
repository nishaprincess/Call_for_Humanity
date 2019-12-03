package com.example.click_for_help.click_help_1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class Single_Fragment extends Fragment {

    TextView activity_name,description,event_place;
    Button interested,donate;

    private DatabaseReference database_single;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_single_, container, false);

        Bundle bundle =new Bundle();
        bundle= this.getArguments();

        String visibility=bundle.getString("visible");
        final  String post_key=bundle.getString("post_key");

        database_single=FirebaseDatabase.getInstance().getReference().child("Event");


        activity_name=(TextView)v.findViewById(R.id.activity_name);
        description=(TextView)v.findViewById(R.id.description);
        event_place=(TextView)v.findViewById(R.id.event_place);




        interested=(Button)v.findViewById(R.id.interested);

        donate=(Button)v.findViewById(R.id.donate);


        // For button visibility set up





        Toast.makeText(getActivity(),post_key,Toast.LENGTH_SHORT).show();



        //Add value Event listner for single post

        if(!TextUtils.isEmpty(post_key))
        {

            Log.i("IIIIIIIIIIIII",post_key);
            database_single.child(post_key).addValueEventListener(new ValueEventListener() {



              @Override
             public void onDataChange(DataSnapshot dataSnapshot) {

                  Log.i("IIIIIIIIIIIIINNNNN",post_key);

                  String name=dataSnapshot.child("Title").getValue().toString();
                  String desc=dataSnapshot.child("Description").getValue().toString();
                  String place=dataSnapshot.child("Place").getValue().toString();

                  Log.i("IIIIIIIIIIIIINNNNNERRRR",name);
                  Log.i("IIIIIIIIIIIIINNNNNERRRR",desc);
                  Log.i("IIIIIIIIIIIIINNNNNERRRR",place);

                  activity_name.setText(name);
                  description.setText(desc);
                  event_place.setText(place);

                 }

                   @Override
                 public void onCancelled(DatabaseError databaseError) {

                  }});
        }



       /* database_single.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                activity_name.setText(dataSnapshot.child("Title").getValue().toString());
                description.setText(dataSnapshot.child("Descruption").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/





            // button visibility set krte loin tekhe asle (donate button) ar activity tekhe asle (interest button)

        if(visibility.equals("log")) {


            donate.setVisibility(v.VISIBLE);

            donate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getActivity(), Donate.class);
                    startActivity(intent);
                }
            });

            Toast.makeText(getActivity(),visibility,Toast.LENGTH_SHORT).show();


        }
        else if(visibility.equals("activity")) {


            interested.setVisibility(v.VISIBLE);

            interested.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // if donate krte interested hoi the tahole login page e redirect

                    Intent intent = new Intent(getActivity(), LogIn.class);
                    startActivity(intent);


                }
            });


             Toast.makeText(getActivity(),visibility,Toast.LENGTH_SHORT).show();

        }












        return  v;


    }



}
