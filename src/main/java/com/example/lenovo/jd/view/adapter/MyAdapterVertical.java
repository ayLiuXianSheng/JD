package com.example.lenovo.jd.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.activity.HomePageParticularsActivity;
import com.example.lenovo.jd.view.activity.ParticularsActivity;
import com.example.lenovo.jd.view.bean.HomePageSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/3/12.
 */

public class MyAdapterVertical extends RecyclerView.Adapter<MyAdapterVertical.ViewHolder> {
    private Context context;
    private List<HomePageSuperClass.TuijianBean.ListBean> list;

    public MyAdapterVertical(Context context) {
        this.context = context;
    }

    public void setList(List<HomePageSuperClass.TuijianBean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vitem_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,HomePageParticularsActivity.class);
                intent.putExtra("detailUrl",list.get(holder.getLayoutPosition()).getDetailUrl());
                context.startActivity(intent);
            }
        });*/
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                int pid = list.get(position).getPid();
                Intent intent = new Intent(context, ParticularsActivity.class);
                intent.putExtra("pid",pid + "");
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        Glide.with(context)
                .load(split[0])
                .into(holder.imagev_view);
        holder.text_title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imagev_view;
        private TextView text_title;

        public ViewHolder(View itemView) {
            super(itemView);
            imagev_view = itemView.findViewById(R.id.imagev_view);
            text_title = itemView.findViewById(R.id.text_title);
        }
    }
}
