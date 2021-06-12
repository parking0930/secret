package com.inhatc.secret;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

public class Fragment1 extends Fragment {

    private TextView txtCommunityMore;
    private TextView txtSchool;
    private TextView txtCommunityInner, txtCommunityInner2;
    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle bundle = getArguments();

        MapFragment map = new MapFragment();
        map.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mapFrame, map).commit();

        View view = inflater.inflate(R.layout.fragment_1, container, false);

        LastBoardGet lastBoardGet = new LastBoardGet();
        txtCommunityInner = (TextView)view.findViewById(R.id.txtCommunityInner);
        txtCommunityInner2 = (TextView)view.findViewById(R.id.txtCommunityInner2);
        try {
            String jsonStr = lastBoardGet.execute().get();
            Gson gson = new Gson();
            BoardVO board = gson.fromJson(jsonStr, BoardVO.class);
            txtCommunityInner.setText(board.getTitle());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FreeBoardFrgment freeBoard = new FreeBoardFrgment();
        freeBoard.setArguments(bundle);
        txtCommunityInner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).changeNav();
                ((MainActivity)getActivity()).replaceFragment(freeBoard);
            }
        });

        txtCommunityInner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).changeNav();
                ((MainActivity)getActivity()).replaceFragment(freeBoard);
            }
        });

        txtSchool = (TextView)view.findViewById(R.id.txtSchool);
        txtSchool.setText(bundle.getString("school"));
        txtCommunityMore = (TextView)view.findViewById(R.id.txtCommunityMore);
        txtCommunityMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment2 menu2 = new Fragment2();
                ((MainActivity)getActivity()).replaceFragment(menu2);
                ((MainActivity)getActivity()).changeNav();
            }
        });
        return view;
    }
}