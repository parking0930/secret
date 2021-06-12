package com.inhatc.secret;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class BoardCreatFragment extends Fragment {

    EditText title, content;
    Button creat;
    String strtitle, strcontent, strwriter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_board_creat, container, false);
        Bundle bundle = getArguments();

        title = (EditText)view.findViewById(R.id.edtTitle);
        content = (EditText)view.findViewById(R.id.edtContent);
        creat = (Button)view.findViewById(R.id.btnBoardCreat);

        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strtitle = title.getText().toString().trim();
                strcontent = content.getText().toString();

                try {
                    BoardCreat boardCreat = new BoardCreat();
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("title", strtitle);
                    params.put("content", strcontent);
                    params.put("writer", bundle.getString("nickname"));

                    boardCreat.execute(params);
                    Toast.makeText(getActivity(), "등록 완료!", Toast.LENGTH_SHORT);

                    FreeBoardFrgment freeBoardFrgment = new FreeBoardFrgment();
                    freeBoardFrgment.setArguments(getArguments());
                    ((MainActivity)getActivity()).replaceFragment(freeBoardFrgment);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}