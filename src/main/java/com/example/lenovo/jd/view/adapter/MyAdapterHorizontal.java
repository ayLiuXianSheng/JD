package com.example.lenovo.jd.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.activity.HomePageParticularsActivity;
import com.example.lenovo.jd.view.activity.ParticularsActivity;
import com.example.lenovo.jd.view.bean.HomePageSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/3/12.
 */

public class MyAdapterHorizontal extends RecyclerView.Adapter<MyAdapterHorizontal.ViewHolder> {
    private Context context;
    private List<HomePageSuperClass.MiaoshaBean.ListBeanX> list;

    public MyAdapterHorizontal(Context context) {
        this.context = context;
    }

    public void setList(List<HomePageSuperClass.MiaoshaBean.ListBeanX> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hitem_layout, parent, false);
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
                .into(holder.imageh_view);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageh_view;

        public ViewHolder(View itemView) {
            super(itemView);
            imageh_view = itemView.findViewById(R.id.imageh_view);
        }
    }
}
