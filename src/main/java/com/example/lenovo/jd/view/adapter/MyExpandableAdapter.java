package com.example.lenovo.jd.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.bean.ShoppingCartSuperClass;

import java.util.List;

/**
 * Created by lenovo on 2018/4/11.
 */

public class MyExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<ShoppingCartSuperClass.DataBean> list;
    private OnClickAddAndSub onClickAddAndSub;
    private OnSetChecked onSetChecked;
    private boolean flag;

    public MyExpandableAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ShoppingCartSuperClass.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnClickAddAndSub(OnClickAddAndSub onClickAddAndSub) {
        this.onClickAddAndSub = onClickAddAndSub;
    }

    public void setOnSetChecked(OnSetChecked onSetChecked) {
        this.onSetChecked = onSetChecked;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList() != null ? list.get(groupPosition).getList().size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        if (convertView == null){
            //查找控件
            convertView = LayoutInflater.from(context).inflate(R.layout.group_item, parent, false);
            holder = new GroupViewHolder();
            holder.ck_group_choosed = convertView.findViewById(R.id.ck_group_choosed);
            convertView.setTag(holder);
        }else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        //赋值
        holder.ck_group_choosed.setText(list.get(groupPosition).getSellerName());
        holder.ck_group_choosed.setChecked(list.get(groupPosition).isGroupChoosed());

        holder.ck_group_choosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSetChecked.groupChecked(groupPosition,((CheckBox)v).isChecked());
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.child_item, parent, false);
            holder = new ChildViewHolder();
            //商品选择
            holder.ck_child_choosed = convertView.findViewById(R.id.ck_child_choose);
            //商品图片
            holder.iv_show_pic = convertView.findViewById(R.id.iv_show_pic);
            //商品主标题
            holder.tv_commodity_name = convertView.findViewById(R.id.tv_commodity_name);
            //商品副标题
            holder.tv_commodity_attr = convertView.findViewById(R.id.tv_commodity_attr);
            //商品价格
            holder.tv_commodity_price = convertView.findViewById(R.id.tv_commodity_price);
            //商品数量
            holder.tv_commodity_num = convertView.findViewById(R.id.tv_commodity_num);
            //商品减
            holder.iv_sub = convertView.findViewById(R.id.iv_sub);
            //商品加减中的数量变化
            holder.tv_commodity_show_num = convertView.findViewById(R.id.tv_commodity_show_num);
            //商品加
            holder.iv_add = convertView.findViewById(R.id.iv_add);
            //删除按钮
            holder.btn_commodity_delete = convertView.findViewById(R.id.btn_commodity_delete);

            convertView.setTag(holder);
        }else {
            holder = (ChildViewHolder) convertView.getTag();
        }

        //设置文本信息
        holder.ck_child_choosed.setChecked(list.get(groupPosition).getList().get(childPosition).isChildChoosed());
        holder.tv_commodity_name.setText(list.get(groupPosition).getList().get(childPosition).getTitle());
        holder.tv_commodity_attr.setText(list.get(groupPosition).getList().get(childPosition).getSubhead());
        holder.tv_commodity_price.setText("￥"+list.get(groupPosition).getList().get(childPosition).getPrice());
        holder.tv_commodity_num.setText("x"+list.get(groupPosition).getList().get(childPosition).getNum());
        holder.tv_commodity_show_num.setText(list.get(groupPosition).getList().get(childPosition).getNum()+"");

        String images = list.get(groupPosition).getList().get(childPosition).getImages();
        String[] split = images.split("\\|");
        Glide.with(context)
                .load(split[0])
                .into(holder.iv_show_pic);

        holder.iv_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddAndSub.sub(groupPosition,childPosition);
            }
        });

        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddAndSub.add(groupPosition,childPosition);
            }
        });

        holder.ck_child_choosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSetChecked.childChecked(groupPosition,childPosition,((CheckBox)v).isChecked());
            }
        });

        if (flag){
            holder.btn_commodity_delete.setVisibility(View.VISIBLE);
        }else {
            holder.btn_commodity_delete.setVisibility(View.GONE);
        }


        holder.btn_commodity_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(groupPosition).getList().remove(childPosition);
                if (list.get(groupPosition).getList().size() == 0){
                    list.remove(groupPosition);
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder{
        private CheckBox ck_group_choosed;
    }

    class ChildViewHolder{
        private CheckBox ck_child_choosed;
        private ImageView iv_show_pic;
        private TextView tv_commodity_name;
        private TextView tv_commodity_attr;
        private TextView tv_commodity_price;
        private TextView tv_commodity_num;
        private TextView iv_sub;
        private TextView tv_commodity_show_num;
        private TextView iv_add;
        private Button btn_commodity_delete;
    }

    public interface OnClickAddAndSub{
        void add(int groupPosition, int childPosition);

        void sub(int groupPosition, int childPosition);
    }

    public interface OnSetChecked{
        void groupChecked(int groupPosition,boolean isChecked);

        void childChecked(int groupPosition, int childPosition,boolean isChecked);
    }
}
