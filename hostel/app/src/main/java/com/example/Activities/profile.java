package com.example.hoste;

import static android.app.ProgressDialog.show;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;


public class profile extends AppCompatActivity {
    EditText name,email,reg,ph;
    TextView t1;
    String username;
    DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.userofficialname);
        email = findViewById(R.id.usermailid);
        reg = findViewById(R.id.userregisternumber);
        t1 = findViewById(R.id.complaintreg1);
        ph = findViewById(R.id.usermobilenumber);
        show();



    }
    public void show(){
        Intent intent = getIntent();
        String mail = intent.getStringExtra("name");
        reg.setText(mail);
        reference = FirebaseDatabase.getInstance().getReference("users").child(mail);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String uname = snapshot.child("name").getValue().toString();
                String uemail = snapshot.child("email").getValue().toString();
                String uphone = snapshot.child("phone").getValue().toString();
                name.setText(uname);
                t1.setText(uname);
                email.setText(uemail);
                ph.setText(uphone);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}