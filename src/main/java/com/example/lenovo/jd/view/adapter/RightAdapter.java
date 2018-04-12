package com.example.lenovo.jd.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.activity.HomePageParticularsActivity;
import com.example.lenovo.jd.view.activity.ListActivity;
import com.example.lenovo.jd.view.bean.ClassifyRightSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/9.
 */

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private Context context;
    private List<ClassifyRightSuperClass.DataBean> list;

    public RightAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ClassifyRightSuperClass.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RightAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.group_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RightAdapter.ViewHolder holder, int position) {
        holder.right_text.setText(list.get(position).getName());
        MyAdapter adapter = new MyAdapter(context,list.get(position).getList());

        holder.group_recycle.setLayoutManager(new GridLayoutManager(context,3));

        holder.group_recycle.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView right_text;
        private RecyclerView group_recycle;

        public ViewHolder(View itemView) {
            super(itemView);
            right_text = itemView.findViewById(R.id.right_text);
            group_recycle = itemView.findViewById(R.id.group_recycle);
        }
    }
}
