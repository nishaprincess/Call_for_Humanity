package com.example.click_for_help.click_help_1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class After_Activity_Button extends Fragment {


   // Button no_use;

    private RecyclerView activity_list_before;

    private DatabaseReference before_login_data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_after__activity__button, container, false);

       // no_use=(Button)v.findViewById(R.id.no_use);

        activity_list_before=(RecyclerView)v.findViewById(R.id.activity_list_before);

        activity_list_before.setHasFixedSize(true);

        activity_list_before.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Firebase

        before_login_data= FirebaseDatabase.getInstance().getReference().child("Event");



       /* no_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(),Activity.class);

                intent.putExtra("For_Signle_Activity","before_login");

                startActivity(intent);

            }
        });*/


        return  v;
    }

    @Override
    public void onStart() {
        super.onStart();

       FirebaseRecyclerAdapter<Event_Class,Event_View_Holder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Event_Class, Event_View_Holder>(

               Event_Class.class,
               R.layout.activity_row,
               Event_View_Holder.class,
               before_login_data


       ) {
           @Override
           protected void populateViewHolder(Event_View_Holder viewHolder, Event_Class model, int position) {

               final String post_key=getRef(position).getKey();

               viewHolder.setTitle(model.getTitle());
               viewHolder.setDate(model.getDate());
               viewHolder.setPlace(model.getPlace());
               viewHolder.setImage(getActivity(),model.getImage());

               viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                       Intent intent=new Intent(getActivity(),Activity.class);

                       intent.putExtra("For_Signle_Activity","before_login");
                       intent.putExtra("post_key",post_key);

                       startActivity(intent);
                   }
               });



           }
       };

        activity_list_before.setAdapter(firebaseRecyclerAdapter);


    }

    public static class Event_View_Holder extends RecyclerView.ViewHolder
    {
        View mview;

        public Event_View_Holder(View itemView) {
            super(itemView);

            mview=itemView;
        }


        public void setTitle(String title)
        {
            TextView post_title=(TextView) mview.findViewById(R.id.activity_title);

            post_title.setText(title);
        }

        public void setDate(String date)
        {
            TextView post_date=(TextView) mview.findViewById(R.id.activity_date);

            post_date.setText(date);
        }

        public void setImage(Context ctx,String image)
        {
            ImageView post_image=(ImageView)mview.findViewById(R.id.activity_image);

            Picasso.with(ctx).load(image).into(post_image);
        }

        public void setPlace(String place)
        {
            TextView post_place=(TextView)mview.findViewById(R.id.activity_place);

            post_place.setText(place);
        }

    }

}
