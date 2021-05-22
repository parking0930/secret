package com.inhatc.secret;

import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MemberInsert extends AsyncTask<Map<String, String>, Integer, String> {

    public static String ip = "192.168.0.8";

    @Override
    protected String doInBackground(Map<String, String>... maps) {
        HttpClient.Builder http = new HttpClient.Builder("POST", "http://" + ip + ":8081/secret/joinMember");
        http.addAllParameters(maps[0]);

        HttpClient post = http.create();
        post.request();

        int statusCode = post.getHttpStatusCode();

        // 응답 본문 가져오기
        String body = post.getBody();

        return body; // return 되면 아래의 onPostExecute의 인수로 넘어감
    }

    @Override
    protected void onPostExecute(String data) { // 받은 결과 출력

    }
}
