package com.example.lenovo.jd.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.activity.HomePageParticularsActivity;
import com.example.lenovo.jd.view.activity.ParticularsActivity;
import com.example.lenovo.jd.view.bean.ListSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/3.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context context;
    private List<ListSuperClass.DataBean> list;

    public ListAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ListSuperClass.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
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
                .into(holder.list_image);
        holder.text_title.setText(list.get(position).getTitle());
        holder.text_oldprice.setText("原价：" + list.get(position).getPrice());
        holder.text_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.text_newprice.setText("优惠价：" + list.get(position).getBargainPrice());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView list_image;
        private TextView text_title,text_oldprice,text_newprice;

        public ViewHolder(View itemView) {
            super(itemView);
            list_image = itemView.findViewById(R.id.list_image);
            text_title = itemView.findViewById(R.id.text_title);
            text_oldprice = itemView.findViewById(R.id.text_oldprice);
            text_newprice = itemView.findViewById(R.id.text_newprice);
        }
    }
}
