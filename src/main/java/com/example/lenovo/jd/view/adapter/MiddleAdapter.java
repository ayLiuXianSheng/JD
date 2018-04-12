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
import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/9.
 */

public class MiddleAdapter extends RecyclerView.Adapter<MiddleAdapter.ViewHolder> {
    private Context context;
    private List<ClassifyLeftSuperClass.DataBean> list;

    public MiddleAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ClassifyLeftSuperClass.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MiddleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.middle_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ListActivity.class);
                intent.putExtra("pscid",list.get(holder.getLayoutPosition()).getCid() + "");
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MiddleAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position).getIcon())
                .into(holder.middle_image);
        holder.middle_text.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView middle_image;
        private TextView middle_text;

        public ViewHolder(View itemView) {
            super(itemView);
            middle_image = itemView.findViewById(R.id.middle_image);
            middle_text = itemView.findViewById(R.id.middle_text);
        }
    }

}
