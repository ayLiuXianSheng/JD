package com.example.lenovo.jd.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/9.
 */

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
    private Context context;
    private List<ClassifyLeftSuperClass.DataBean> list;
    private int index = 0;
    private OnItemClickedListen onItemClickedListen;

    public LeftAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ClassifyLeftSuperClass.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnItemClickedListen(OnItemClickedListen onItemClickedListen) {
        this.onItemClickedListen = onItemClickedListen;
    }

    @Override
    public LeftAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.text_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(LeftAdapter.ViewHolder holder, final int position) {
        holder.left_text.setText(list.get(position).getName());
        if (index == position){
            holder.left_text.setTextColor(Color.RED);
            holder.left_text.setBackgroundColor(Color.parseColor("#eeeeee"));
        }else {
            holder.left_text.setTextColor(Color.BLACK);
            holder.left_text.setBackgroundColor(Color.WHITE);
        }

        holder.left_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = position;
                onItemClickedListen.OnItemClicked(list.get(position).getCid() + "");
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView left_text;

        public ViewHolder(View itemView) {
            super(itemView);
            left_text = itemView.findViewById(R.id.left_text);
        }
    }

    public interface OnItemClickedListen{
        void OnItemClicked(String cid);
    }
}
