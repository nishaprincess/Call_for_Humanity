package com.example.click_for_help.click_help_1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


public class New_Event extends Fragment {

    private ImageButton image;

    private Button add_event;

    private EditText title,description,place;

    private Uri imageuri=null;

    public static final int GALLERY_REQUEST=123;

    private StorageReference imageupload;
    private DatabaseReference image_info;

    private ProgressDialog progress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_new__event, container, false);

        image=(ImageButton)v.findViewById(R.id.imageButton);

        add_event=(Button)v.findViewById(R.id.add_event);

        title=(EditText)v.findViewById(R.id.add_title);

        description=(EditText)v.findViewById(R.id.add_description);

        place=(EditText)v.findViewById(R.id.add_place);

        progress=new ProgressDialog(getActivity());




                //Firebase


        imageupload= FirebaseStorage.getInstance().getReference();
        image_info= FirebaseDatabase.getInstance().getReference().child("Event");


        // select image from gallery

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST);

            }
        });


        // click for new event create

        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startposting();
            }
        });




        return  v;
    }

    private void startposting() {

        final String image_title=title.getText().toString().trim();
        final String image_desc=description.getText().toString().trim();
        final String image_place=place.getText().toString().trim();

        if(!TextUtils.isEmpty(image_title) && !TextUtils.isEmpty(image_desc) && imageuri!=null)
        {

            progress.setMessage("Creating New Event...");
            progress.show();

            StorageReference filepath=imageupload.child("Image").child(imageuri.getLastPathSegment());

            filepath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    // *********Date Save


                    Calendar c = Calendar.getInstance();
                    System.out.println("Current time => " + c.getTime());

                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String formattedDate = df.format(c.getTime());

                    // storage upload complete

                    Uri downloadurl=taskSnapshot.getDownloadUrl();

                    HashMap<String,String> hash=new HashMap<String, String>();

                    hash.put("Title",image_title);
                    hash.put("Description",image_desc);
                    hash.put("Image", String.valueOf(downloadurl));
                    hash.put("Place", String.valueOf(image_place));

                    hash.put("Date", formattedDate);

                    String key=image_info.push().getKey();

                    image_info.child(key).setValue(hash);


                    progress.dismiss();

                    Toast.makeText(getActivity(),"Successfully created new Event",Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    progress.dismiss();

                    Toast.makeText(getActivity(),"Failed to create new event",Toast.LENGTH_SHORT).show();

                }
            });
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK)
        {


            imageuri=data.getData();

            // set image to imageButton

            image.setImageURI(imageuri);
        }
    }
}
