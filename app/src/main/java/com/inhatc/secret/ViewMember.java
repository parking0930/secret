package com.inhatc.secret;

import android.os.AsyncTask;

import java.util.Map;

public class ViewMember extends AsyncTask<Map<String, String>, Integer, String> {

    @Override
    protected String doInBackground(Map<String, String>... maps) {
        IP_and_Port ipAndPort = new IP_and_Port();
        String ip = ipAndPort.getIp();
        String port = ipAndPort.getPort();
        HttpClient.Builder http = new HttpClient.Builder("POST", "http://" + ip + ":" + port + "/secret/viewMember");

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
