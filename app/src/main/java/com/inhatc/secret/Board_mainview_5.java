package com.inhatc.secret;

import android.os.AsyncTask;

public class Board_mainview_5 extends AsyncTask<String, Integer, Object> {


    @Override
    protected Object doInBackground(String... strings) {
        IP_and_Port ipAndPort = new IP_and_Port();
        String ip = ipAndPort.getIp();
        String port = ipAndPort.getPort();
        HttpClient.Builder http = new HttpClient.Builder("POST", "http://" + ip + ":" + port + "/secret/board_mainview_5");
        //http.addAllParameters(maps[0]);

        HttpClient post = http.create();
        post.request();

        int statusCode = post.getHttpStatusCode();

        // 응답 본문 가져오기
        String body = post.getBody();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa : "+body);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

        return body; // return 되면 아래의 onPostExecute의 인수로 넘어감
    }
}