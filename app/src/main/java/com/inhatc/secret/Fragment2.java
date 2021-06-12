package com.inhatc.secret;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;


public class Fragment2 extends Fragment {

    private TextView txtFree, txtFreeInner;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        txtFree = view.findViewById(R.id.txtFree);
        txtFreeInner = view.findViewById(R.id.txtFreeInner);

        LastBoardGet lastBoardGet = new LastBoardGet();
        try {
            String jsonStr = lastBoardGet.execute().get();
            Gson gson = new Gson();
            BoardVO board = gson.fromJson(jsonStr, BoardVO.class);
            txtFreeInner.setText(board.getTitle());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FreeBoardFrgment freeBoard = new FreeBoardFrgment();
        freeBoard.setArguments(getArguments());
        txtFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(freeBoard);
            }
        });
        txtFreeInner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(freeBoard);
            }
        });
        return view;
    }
}