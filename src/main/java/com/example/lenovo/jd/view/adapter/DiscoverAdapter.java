package com.example.lenovo.jd.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.activity.ParticularsActivity;
import com.example.lenovo.jd.view.bean.DiscoverSuperClass;

import java.util.List;

/**
 * 发现的适配器
 */

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder> {
    private Context context;
    private List<DiscoverSuperClass.DataBean> list;

    public DiscoverAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<DiscoverSuperClass.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public DiscoverAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.grid_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                int pid = list.get(position - 1).getPid();
                Intent intent = new Intent(context, ParticularsActivity.class);
                intent.putExtra("pid",pid + "");
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(DiscoverAdapter.ViewHolder holder, int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        Glide.with(context)
                .load(split[0])
                .into(holder.all_image_view);
        holder.all_text_title.setText(list.get(position).getTitle());
        holder.all_old_price.setText("原价：" + list.get(position).getBargainPrice());
        holder.all_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.all_new_price.setText("折扣价：" + list.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView all_image_view;
        private TextView all_text_title,all_old_price,all_new_price;

        public ViewHolder(View itemView) {
            super(itemView);
            all_image_view = itemView.findViewById(R.id.all_image_view);
            all_text_title = itemView.findViewById(R.id.all_text_title);
            all_old_price = itemView.findViewById(R.id.all_old_price);
            all_new_price = itemView.findViewById(R.id.all_new_price);
        }
    }
}
