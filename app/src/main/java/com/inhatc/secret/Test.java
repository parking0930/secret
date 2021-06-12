package com.inhatc.secret;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Test extends AppCompatActivity {
    TextView edttest, edtWrtier, edtViewcnt;
    Button back;
    List<BoardVO> vo_list;
    String body;
    BoardVO vo = new BoardVO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        edttest = findViewById(R.id.testTitle);
        Board_mainview_5 board_mainview_5 = new Board_mainview_5();
        Gson gson = new GsonBuilder().serializeNulls().create();

        try {
            body = (String)board_mainview_5.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TestingBody---------------------------" + body);
        System.out.println("----------------------------------------------------------------------");
        vo_list = gson.fromJson(body, new TypeToken<ArrayList<BoardVO>>(){}.getType());
        //list = gson.fromJson(body, new TypeToken<List<BoardVO>>(){}.getType());
        System.out.println("----------------------------------------------------------------------"+vo_list.toString());
        for (BoardVO v:vo_list) {
            System.out.println("test----"+v.getBno());
            System.out.println("test----"+v.getContent());
            System.out.println("test----"+v.getWriter());
            System.out.println("test----"+v.getRegdate());
            System.out.println("test----"+v.getTitle());
        }
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        System.out.println("cccccccccccccccccccccccccccccccccccccc");
    }
}