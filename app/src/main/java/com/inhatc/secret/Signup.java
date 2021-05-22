package com.inhatc.secret;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

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

        firebaseAuth = FirebaseAuth.getInstance();

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
                id = editTextId.getText().toString().trim();
                pwd = editTextPWD.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(id, pwd).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(Signup.this, LoginActivity.class);
                            Toast.makeText(Signup.this, "등록 완료", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Signup.this, "등록 에러", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
//                Intent intent = new Intent(
//                        getApplicationContext(), // 현재 화면의 제어권자
//                        LoginActivity.class);
//                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        if(currentUser != null){
//            reload();
//        }
    }
    private void reload() {
    }

//    public void postFirebaseDatabase(boolean add){
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        Map<String, Object> childUpdates = new HashMap<>();
//        Map<String, Object> postValues = null;
//        if(add){
//            FireBasePost post = new FireBasePost(age, id, name, pwd, school);
//            postValues = post.toMap();
//        }
//        childUpdates.put("/Member/" + editTextId, postValues);
//        mDatabase.updateChildren(childUpdates);
//    }
//    public void writeNewpost(String id, String pwd, String name, String age, String school){
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        String key = mDatabase.child("Member").push().getKey();
//        FireBasePost post = new FireBasePost(id, pwd, age, school, name);
//        Map<String, Object> postValues = post.toMap();
//
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/Member/" + key, postValues);
//        childUpdates.put("/user-posts/" + id + "/" + key, postValues);
//    }
//    public void insertsignup(String id, String pwd, String name, String age, String school){
//        FireBasePost fireBasePost = new FireBasePost();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        mDatabase.child("Member").child(id).child("ID").setValue(id);
//        mDatabase.child("Member").child(id).child("Name").setValue(name);
//        mDatabase.child("Member").child(id).child("Age").setValue(age);
//        mDatabase.child("Member").child(id).child("PWD").setValue(pwd);
//        mDatabase.child("Member").child(id).child("School").setValue(school);
//    }
}