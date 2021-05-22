package com.inhatc.secret;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    EditText edtSignUpId, edtSignUpPwd, edtSignUpPwdChk, edtNickname, edtSchool, edtAge;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtSignUpId = (EditText)findViewById(R.id.edtSignUpId);
        edtSignUpPwd = (EditText)findViewById(R.id.edtSignUpPwd);
        edtSignUpPwdChk = (EditText)findViewById(R.id.edtSignUpPwdChk);
        edtNickname = (EditText)findViewById(R.id.edtNickname);
        edtSchool = (EditText)findViewById(R.id.edtSchool);
        edtAge = (EditText)findViewById(R.id.edtAge);

        btnSignUp = (Button)findViewById(R.id.SignUpBtn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 비밀번호 일치 체크
                if(!edtSignUpPwd.getText().toString().equals(edtSignUpPwdChk.getText().toString())){
                    Toast.makeText(Signup.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try{
                    MemberInsert networkTask = new MemberInsert();

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id", edtSignUpId.getText().toString());
                    params.put("pw", edtSignUpPwd.getText().toString());
                    params.put("nickname", edtNickname.getText().toString());
                    params.put("edtSchool", edtSchool.getText().toString());
                    params.put("school", edtSchool.getText().toString());
                    params.put("birth", edtAge.getText().toString());

                    String data = networkTask.execute(params).get(); // 실행 후 결과 반환

                    Gson gson = new Gson();
                    //MemberVO member = gson.fromJson(data, MemberVO.class);
                    Map<String, String> result = gson.fromJson(data, Map.class);
                    System.out.println("받음:"+result.get("result"));
                    if(result.get("result").equals("1")){
                        Toast.makeText(Signup.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    }else{ // 가입 실패 시
                        Toast.makeText(Signup.this, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}