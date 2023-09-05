package com.example.hoste;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class Submitfeedback extends AppCompatActivity {

    EditText yourname,hostelnameroomnumber,problemfixed,response,resolve;
    Button submit;
    RatingBar star;


    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        setContentView(R.layout.activity_submitfeedback);

        yourname=findViewById(R.id.editTextTextPersonName);
        hostelnameroomnumber=findViewById(R.id.editTextTextPersonName2);
        problemfixed=findViewById(R.id.editTextTextPersonName3);
        response=findViewById(R.id.editTextTextPersonName4);
        resolve=findViewById(R.id.editTextTextPersonName5);
        star=findViewById(R.id.rb);
        submit=findViewById(R.id.btn);




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //FirebaseFirestore db=FirebaseFirestore.getInstance();

                final String resname=yourname.getText().toString().trim();
                String reshostelnameroomnumber=hostelnameroomnumber.getText().toString().trim();
                final String resproblems=problemfixed.getText().toString().trim();
                final String resresponse=response.getText().toString().trim();
                final String resresolve=resolve.getText().toString().trim();
                Map<String,Object>city=new HashMap<>();

                city.put("residentname",resname);
                city.put("residenthostelnameroomnumber",reshostelnameroomnumber);
                city.put("residentproblem",resproblems);
                city.put("responsefeed",resresponse);
                city.put("residentresolvationfeed",resresolve);


                if(TextUtils.isEmpty(resname)){
                    yourname.setError("Name is Required");
                    return;
                }

                if(TextUtils.isEmpty(reshostelnameroomnumber)){
                    hostelnameroomnumber.setError("Hostel and Floor is Required");
                    return;
                }

                if(TextUtils.isEmpty(resproblems)){
                   problemfixed.setError("Hostel and Floor is Required");
                    return;
                }

                if(TextUtils.isEmpty(resresponse)){
                    response.setError("Hostel and Floor is Required");
                    return;
                }

                if(TextUtils.isEmpty(resresolve)){
                    resolve.setError("Hostel and Floor is Required");
                    return;
                }

//                else{
//                    //db.collection("usersfeedbacks").document(resname)
//                            .set(city)
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    Log.d("hi","Feedback Submiited Successfully");
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Log.w("hi","Error occuring document",e);
//                                }
//                            });
//
//                    Toast.makeText(Submitfeedback.this, "Feedback Submitted Successfully", Toast.LENGTH_SHORT).show();
//                    Intent intent=new Intent(Submitfeedback.this,Home.class);
//                    startActivity(intent);
//                }




            }
        });
    }
}