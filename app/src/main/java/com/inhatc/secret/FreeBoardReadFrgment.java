package com.inhatc.secret;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FreeBoardReadFrgment extends Fragment {

    TextView title, writer, content, date, viewcnt;
    String strbno, strtitle, strwriter, strcontent, strdate, strviewcnt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_free_board_read, container, false);

        title = view.findViewById(R.id.BoardReadTitle);
        writer = view.findViewById(R.id.BoardReadWriter);
        content = view.findViewById(R.id.BoardReadContent);
        date = view.findViewById(R.id.BoardReadDate);
        viewcnt = view.findViewById(R.id.BoardReadViewCnt);

        Bundle bundle = getArguments();

        strbno = bundle.getString("bno");
        strtitle = bundle.getString("title");
        strwriter = bundle.getString("writer");
        strcontent = bundle.getString("content");
        strdate = bundle.getString("strdate");
        strviewcnt = bundle.getString("viewcnt");

        title.setText(strtitle);
        writer.setText("작성자: 익명");
        content.setText(strcontent);
        date.setText(strdate);
        viewcnt.setText(strviewcnt);
        return view;
    }
}