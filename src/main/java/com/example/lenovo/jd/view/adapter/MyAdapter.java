package com.example.lenovo.jd.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.activity.ListActivity;
import com.example.lenovo.jd.view.bean.ClassifyRightSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/9.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<ClassifyRightSuperClass.DataBean.ListBean> list;

    public MyAdapter(Context context, List<ClassifyRightSuperClass.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ListActivity.class);
                intent.putExtra("pscid",list.get(holder.getLayoutPosition()).getPscid() + "");
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position).getIcon())
                .into(holder.child_image);
        holder.child_text.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView child_image;
        private TextView child_text;

        public ViewHolder(View itemView) {
            super(itemView);
            child_image = itemView.findViewById(R.id.child_image);
            child_text = itemView.findViewById(R.id.child_text);
        }
    }
}
