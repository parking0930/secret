package com.inhatc.secret;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FreeBoardAdapter extends BaseAdapter{
    String strbno, strtitle, strwriter, strcontent, strdate, strviewcnt;
    int viewcnt;
    ArrayList<BoardVO> vo_list = new ArrayList<BoardVO>();

    @Override
    public int getCount() {
        return vo_list.size();
    }

    @Override
    public Object getItem(int position) {
        return vo_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_free_board_item, parent, false);
        }
        //View v = View.inflate(context, R.layout.activity_free_board_item, null);

        TextView title, writer, content, viewcnt, date;
        title = convertView.findViewById(R.id.FreeBoardTitle);
        writer = convertView.findViewById(R.id.FreeBoardWriter);
        content = convertView.findViewById(R.id.FreeBoardContent);
        viewcnt = convertView.findViewById(R.id.FreeBoardViewcnt);
        date = convertView.findViewById(R.id.FreeBoardDate);

        BoardVO vo = (BoardVO) getItem(position);

        title.setText(vo.getTitle());
        writer.setText(vo.getWriter());
        content.setText(vo.getContent());
        viewcnt.setText(vo.getViewcnt()); //------------------------------
        date.setText(vo.getRegdate());

        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.FreeBoardLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ViewCntUpdate viewCntUpdate = new ViewCntUpdate();
                Map<String, String> params = new HashMap<String, String>();

                strbno = vo_list.get(position).getBno();
                strtitle = vo_list.get(position).getTitle();
                strwriter = vo_list.get(position).getWriter();
                strcontent = vo_list.get(position).getContent();
                strdate = vo_list.get(position).getRegdate();
                strviewcnt = vo_list.get(position).getViewcnt();

                params.put("bno", strbno);
                viewCntUpdate.execute(params);

                //Intent intent=new Intent(context,FreeBoardReadFrgment.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //dddddddddddddddddddddddddddddd
                FreeBoardReadFrgment readFreeBoard = new FreeBoardReadFrgment();
                Bundle bundle = new Bundle();
                bundle.putString("bno", strbno.toString());
                bundle.putString("title", strtitle.toString());
                bundle.putString("writer", strwriter.toString());
                bundle.putString("content", strcontent.toString());
                bundle.putString("strdate", strdate.toString());
                bundle.putString("viewcnt", strviewcnt);
                readFreeBoard.setArguments(bundle);
                ((MainActivity) context).replaceFragment(readFreeBoard);
            }
        });

        return convertView;
    }

    public void addItem(String title, String content, String writer, String date, String bno, String viewcnt) {

        BoardVO vo = new BoardVO();

        /* MyItem에 아이템을 setting한다. */
        vo.setBno(bno);
        vo.setTitle(title);
        vo.setContent(content);
        vo.setWriter("익명");
        vo.setRegdate(date);
        vo.setViewcnt("조회 "+viewcnt); //------------------------------

        /* mItems에 MyItem을 추가한다. */
        vo_list.add(vo);
    }
}