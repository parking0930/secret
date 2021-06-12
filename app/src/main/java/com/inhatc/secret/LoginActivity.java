package com.inhatc.secret;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    TextView signupBtn;

    EditText edtId;
    EditText edtPw;

    String id, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);

        edtId = findViewById(R.id.edtId);
        edtPw = findViewById(R.id.edtPw);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = edtId.getText().toString().trim();
                pw = edtPw.getText().toString().trim();

                try{
                    Login login = new Login();

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id", id);
                    params.put("pw", pw);

                    String data = login.execute(params).get(); // 실행 후 결과 반환

                    Gson gson = new Gson();

                    Map<String, String> result = gson.fromJson(data, Map.class);
                    System.out.println("받음:"+result.get("result"));
                    if(result.get("result").equals("1")){
                        Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(LoginActivity.this, FreeBoardActivity.class);
                        ViewMember viewMember = new ViewMember();
                        String jsonStr = viewMember.execute(params).get();
                        MemberVO member = gson.fromJson(jsonStr, MemberVO.class);
                        System.out.println(member.toString());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("nickname", member.getNickname());
                        intent.putExtra("school", member.getSchool());

                        startActivity(intent);
                    }else{ // 가입 실패 시
                        Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Signup.class);
                startActivity(intent);
            }
        });
    }
}