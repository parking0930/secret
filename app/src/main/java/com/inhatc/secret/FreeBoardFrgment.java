package com.inhatc.secret;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FreeBoardFrgment extends Fragment {

    List<BoardVO> vo_list;
    FreeBoardAdapter adapter;
    String body;
    ListView listView;
    Button btnWrite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_free_board, container, false);

        btnWrite = (Button)view.findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getArguments();
                BoardCreatFragment boardCreatFragment = new BoardCreatFragment();
                boardCreatFragment.setArguments(bundle);
                ((MainActivity)getActivity()).replaceFragment(boardCreatFragment);
            }
        });
        /* 위젯과 멤버변수 참조 획득 */
        listView = view.findViewById(R.id.freeboardlistview);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();
        return view;
    }

    private void dataSetting(){

        FreeBoard freeBoard = new FreeBoard();
        Gson gson = new GsonBuilder().serializeNulls().create();

        try {
            body = (String)freeBoard.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        vo_list = gson.fromJson(body, new TypeToken<ArrayList<BoardVO>>(){}.getType());

        FreeBoardAdapter mMyAdapter = new FreeBoardAdapter();

        for (BoardVO vo : vo_list){
            mMyAdapter.addItem(vo.getTitle(),vo.getContent(),vo.getWriter(),vo.getRegdate(), vo.getBno(), vo.getViewcnt());
        }

        /* 리스트뷰에 어댑터 등록 */
        listView.setAdapter(mMyAdapter);
        listViewHeightSet(mMyAdapter, listView);
    }

    private void listViewHeightSet(Adapter listAdapter, ListView listView){
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}