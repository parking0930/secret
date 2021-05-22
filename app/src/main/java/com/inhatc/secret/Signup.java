package com.inhatc.secret;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends AppCompatActivity {
    EditText editTextId, editTextPWD, editTextPWDchk, editTextName, editTextSchool, editTextAge;
    Button btnSignUp;

    String id;
    String pwd;
    String name;
    String age;
    String school;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextId = (EditText)findViewById(R.id.SignUpId);
        editTextPWD = (EditText)findViewById(R.id.SignUpPwd);
        editTextName = (EditText)findViewById(R.id.SignUpName);
        editTextAge = (EditText)findViewById(R.id.SignUpAge);
        editTextSchool = (EditText)findViewById(R.id.SignUpSchool);

        btnSignUp = (Button)findViewById(R.id.SignUpBtn);

        id = editTextId.getText().toString();
        pwd = editTextPWD.getText().toString();
        name = editTextName.getText().toString();
        age = editTextAge.getText().toString();
        school = editTextSchool.getText().toString();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}