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

import com.bumptech.glide.Glide;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.activity.ParticularsActivity;
import com.example.lenovo.jd.view.bean.DataBean;
import com.example.lenovo.jd.view.bean.ListSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/3.
 */

public class ConfirmAnOrderAdapter extends RecyclerView.Adapter<ConfirmAnOrderAdapter.ViewHolder> {
    private Context context;
    private List<DataBean> list;

    public ConfirmAnOrderAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.confirm_an_order_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        Glide.with(context)
                .load(split[0])
                .into(holder.confirm_image);
        holder.confirm_title.setText(list.get(position).getTitle());
        holder.confirm_oldprice.setText("原价：" + list.get(position).getPrice());
        holder.confirm_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.confirm_newprice.setText("优惠价：" + list.get(position).getBargainPrice());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView confirm_image;
        private TextView confirm_title,confirm_oldprice,confirm_newprice;

        public ViewHolder(View itemView) {
            super(itemView);
            confirm_image = itemView.findViewById(R.id.confirm_image);
            confirm_title = itemView.findViewById(R.id.confirm_title);
            confirm_oldprice = itemView.findViewById(R.id.confirm_oldprice);
            confirm_newprice = itemView.findViewById(R.id.confirm_newprice);
        }
    }
}
