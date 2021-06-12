package com.inhatc.secret;

import android.os.AsyncTask;

import java.util.Map;

public class LastBoardGet extends AsyncTask<Void, Integer, String> {

    @Override
    protected String doInBackground(Void... params) {
        IP_and_Port ipAndPort = new IP_and_Port();
        String ip = ipAndPort.getIp();
        String port = ipAndPort.getPort();
        HttpClient.Builder http = new HttpClient.Builder("POST", "http://" + ip + ":" + port + "/secret/getLastBoard");

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
