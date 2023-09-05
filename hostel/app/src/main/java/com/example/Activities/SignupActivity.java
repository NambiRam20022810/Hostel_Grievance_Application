package com.example.hoste;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.util.Patterns;
import android.widget.Toast;

import com.example.hoste.HelperClass;
import com.example.hoste.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SignupActivity extends AppCompatActivity {
    EditText name,email,mobilenumber,registernumber,password;
    Button signButton;
    ProgressBar Bar;
    TextView t1;
    FirebaseDatabase database;
    DatabaseReference reference;
    static int time = 60;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_email);
        mobilenumber=findViewById(R.id.signup_mobilenumber);
        registernumber = findViewById(R.id.signup_renumber);
        password = findViewById(R.id.signup_password);
        Bar = findViewById(R.id.progressBar);
        signButton = findViewById(R.id.signup_button);
        t1 = findViewById(R.id.loginRedirectText);


//        t1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//            }
//
//        });

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                String uname = name.getText().toString();
                String uemail = email.getText().toString();
                String uusername = registernumber.getText().toString();
                String upassword = password.getText().toString();
                //String udate = date.getText().toString();
                String uphone = mobilenumber.getText().toString();
                if(uname.isEmpty() || uemail.isEmpty()|| uusername.isEmpty() || upassword.isEmpty() || uphone.isEmpty()){
                    Toast.makeText(SignupActivity.this, "please fill the all the details", Toast.LENGTH_SHORT).show();
                }else {
                    HelperClass helperClass = new HelperClass(uname, uemail, uusername, upassword, uphone);
                    reference.child(uusername).setValue(helperClass);
                    Toast.makeText(SignupActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
//                    intent.putExtra("name",name);
//                    intent.putExtra("email",email);
//                    intent.putExtra("username",username);
//                    intent.putExtra("password",password);
//                    intent.putExtra("udate",udate);
//                    intent.putExtra("uphone",uphone);
                    startActivity(intent);
                }
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });





    }

}