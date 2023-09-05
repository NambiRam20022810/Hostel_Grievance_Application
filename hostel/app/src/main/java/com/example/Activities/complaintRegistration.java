package com.example.hoste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.hoste.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class complaintRegistration extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    Button complaintregisterbutton;
    EditText Roomnumber, otherissues, residentregno;

    FirebaseDatabase database;
    DatabaseReference reference;


    String[] hostelnames = {"Gents Hostel-1", "Gents Hostel-2", "Ladies Hostel"};
    String[] issues = {"Electrical malfunctioning", "Furniture", "Water", "Restroom", "Wifi", "Other"};

    String[] floor = {"A Block-Ground Floor", "A Block-First Floor", "A Block-Second Floor",
            "B Block-Ground Floor", "B Block-First Floor", "B Block-Second Floor",
            "C Block-Ground Floor", "C Block-First Floor", "C Block-Second Floor",
            "D Block-Ground Floor", "D Block-First Floor", "D Block-Second Floor",
            "E Block-Ground Floor", "E Block-First Floor", "E Block-Second Floor",
            "Ground Floor-Gent's Hostel", "First Floor-Gent's Hostel", "Second Floor-Gent's Hostel"};
    AutoCompleteTextView autoCompleteTextView;
    AutoCompleteTextView autoCompleteTextView1;

    AutoCompleteTextView autoCompleteTextView2;

    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterItems1;

    ArrayAdapter<String> adapterItems2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        setContentView(R.layout.activity_complaint_registration);

        complaintregisterbutton = findViewById(R.id.registerbutton);

        autoCompleteTextView = findViewById(R.id.auto_complete_text);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, hostelnames);

        autoCompleteTextView1 = findViewById(R.id.auto_complete_text2);
        adapterItems1 = new ArrayAdapter<String>(this, R.layout.list_item, issues);

        autoCompleteTextView2 = findViewById(R.id.auto_complete_text1);
        adapterItems2 = new ArrayAdapter<String>(this, R.layout.list_item, floor);

        Roomnumber = findViewById(R.id.RoomNumber);
        otherissues = findViewById(R.id.otherissue);
        residentregno = findViewById(R.id.resiregno);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String hostelnames = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(complaintRegistration.this, "Your Hostel: " + hostelnames, Toast.LENGTH_SHORT).show();
            }
        });


        autoCompleteTextView1.setAdapter(adapterItems1);

        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String issues = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(complaintRegistration.this, "Your issue: " + issues, Toast.LENGTH_SHORT).show();
            }
        });


        autoCompleteTextView2.setAdapter(adapterItems2);

        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String floor = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(complaintRegistration.this, "Your Floor/Block: " + floor, Toast.LENGTH_SHORT).show();
            }
        });

        complaintregisterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(complaintRegistration.this, android.Manifest.permission.SEND_SMS)
                            != PackageManager.PERMISSION_GRANTED) {

                        sendSMS();


                } else {
                        ActivityCompat.requestPermissions(complaintRegistration.this,
                                new String[]{Manifest.permission.SEND_SMS},
                                MY_PERMISSIONS_REQUEST_SEND_SMS);


                }



            }
        });

    }
    private void sendSMS() {


        String hostelname = autoCompleteTextView.getText().toString().trim();
        String residentfloor = autoCompleteTextView2.getText().toString().trim();
        String residentissue = autoCompleteTextView1.getText().toString().trim();
        String residentroomnumber = Roomnumber.getText().toString().trim();
        String otherissuesoftheresident = otherissues.getText().toString().trim();
        String residentregisternumber = residentregno.getText().toString().trim();


        String phoneNumber="7904033763";


        String message = "Hostel Name : "+ autoCompleteTextView.getText().toString()+"\n"+"Block/floor name : "+autoCompleteTextView2.getText().toString().trim()+"\n"
                +"Issue : "+autoCompleteTextView1.getText().toString()+"\n"+"Room Number : "+Roomnumber.getText().toString()+"\n"+"Other issue : "+otherissues.getText().toString()+"\n"+"Register Number : "+residentregno.getText().toString();
        //Toast.makeText(this, "hii"+message, Toast.LENGTH_SHORT).show();
        SmsManager smsManager = SmsManager.getDefault();
        if(!(phoneNumber.isEmpty() || message.isEmpty())){
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);


            Toast.makeText(this, "SMS sent successfully!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "please fill the correct phone number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, send SMS message
                    sendSMS();
                } else {
                    // Permission denied
                    Toast.makeText(this, "Permission denied to send SMS", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}