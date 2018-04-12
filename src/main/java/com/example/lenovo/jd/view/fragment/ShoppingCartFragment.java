package com.example.lenovo.jd.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.presenter.ShoppingCartPresenter;
import com.example.lenovo.jd.view.activity.IShoppingCartView;
import com.example.lenovo.jd.view.adapter.MyExpandableAdapter;
import com.example.lenovo.jd.view.api.Api;
import com.example.lenovo.jd.view.base.BaseFragment;
import com.example.lenovo.jd.view.bean.ShoppingCartSuperClass;

import java.util.List;

/**
 * 购物车
 */

public class ShoppingCartFragment extends BaseFragment<ShoppingCartPresenter> implements IShoppingCartView, MyExpandableAdapter.OnClickAddAndSub, MyExpandableAdapter.OnSetChecked {
    private View view;
    /**
     * 返回
     */
    private TextView mBtnBack;
    /**
     * 编辑
     */
    private TextView mBtnEditor;
    private ExpandableListView mExpandList;
    /**
     * 全选
     */
    private CheckBox mBtnCheckAll;
    /**
     * 合计:￥0.00
     */
    private TextView mTvTotalPrice;
    /**
     * 结算(0)
     */
    private TextView mBtnAmount;
    private MyExpandableAdapter adapter;
    private List<ShoppingCartSuperClass.DataBean> list;
    private int num = 0;
    private double price = 0.00;

    @Override
    protected int getLayoutId() {
        return R.layout.shopping_cart_fragment;
    }

    @Override
    protected ShoppingCartPresenter getPresenter() {
        presenter = new ShoppingCartPresenter(this);
        return presenter;
    }

    @Override
    protected void initView(View view) {
        mBtnBack = (TextView) view.findViewById(R.id.btnBack);
        mBtnEditor = (TextView) view.findViewById(R.id.btnEditor);
        mExpandList = (ExpandableListView) view.findViewById(R.id.expand_list);
        mBtnCheckAll = (CheckBox) view.findViewById(R.id.btnCheckAll);
        mTvTotalPrice = (TextView) view.findViewById(R.id.tvTotalPrice);
        mBtnAmount = (TextView) view.findViewById(R.id.btnAmount);
        adapter = new MyExpandableAdapter(getContext());
        adapter.setOnClickAddAndSub(this);
        adapter.setOnSetChecked(this);
    }

    @Override
    protected void getData() {
        presenter.shoppingCart(Api.HOME_NAME, "2584", "android");
        mExpandList.setAdapter(adapter);
        mExpandList.setGroupIndicator(null);
        mBtnCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllChecked(((CheckBox)v).isChecked());
                statisticsPrice();
            }
        });
    }

    @Override
    public void onFailed(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(List<ShoppingCartSuperClass.DataBean> data) {
        this.list = data;
        if (data != null){
            adapter.setList(data);
        }
        for (int i = 0; i < adapter.getGroupCount() ; i++){
            mExpandList.expandGroup(i);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }

    @Override
    public void add(int groupPosition, int childPosition) {
        ShoppingCartSuperClass.DataBean.ListBean listBean = list.get(groupPosition).getList().get(childPosition);
        int num = listBean.getNum();
        num++;
        listBean.setNum(num);

        statisticsPrice();

        adapter.notifyDataSetChanged();
    }

    @Override
    public void sub(int groupPosition, int childPosition) {
        ShoppingCartSuperClass.DataBean.ListBean listBean = list.get(groupPosition).getList().get(childPosition);
        int num = listBean.getNum();

        if (num <= 1){
            return;
        }

        num--;
        listBean.setNum(num);

        statisticsPrice();

        adapter.notifyDataSetChanged();
    }

    @Override
    public void groupChecked(int groupPosition, boolean isChecked) {
        ShoppingCartSuperClass.DataBean dataBean = list.get(groupPosition);
        dataBean.setGroupChoosed(isChecked);

        for (ShoppingCartSuperClass.DataBean.ListBean listBean : dataBean.getList()){
            listBean.setChildChoosed(isChecked);
        }

        if (isAllGroupChecked()){
            mBtnCheckAll.setChecked(true);
        }else {
            mBtnCheckAll.setChecked(false);
        }

        statisticsPrice();

        adapter.notifyDataSetChanged();
    }

    @Override
    public void childChecked(int groupPosition, int childPosition, boolean isChecked) {
        ShoppingCartSuperClass.DataBean dataBean = list.get(groupPosition);
        ShoppingCartSuperClass.DataBean.ListBean listBean = dataBean.getList().get(childPosition);
        listBean.setChildChoosed(isChecked);

        boolean checked = isAllChildChecked(groupPosition);

        if (checked){
            dataBean.setGroupChoosed(checked);
        }else {
            dataBean.setGroupChoosed(checked);
        }

        if (isAllGroupChecked()){
            mBtnCheckAll.setChecked(true);
        }else {
            mBtnCheckAll.setChecked(false);
        }

        statisticsPrice();

        adapter.notifyDataSetChanged();
    }

    private boolean isAllChildChecked(int groupPosition){
        ShoppingCartSuperClass.DataBean dataBean = list.get(groupPosition);
        for (ShoppingCartSuperClass.DataBean.ListBean listBean : dataBean.getList()){
            if (!listBean.isChildChoosed()){
                return false;
            }
        }
        return true;
    }

    private boolean isAllGroupChecked(){
        for (ShoppingCartSuperClass.DataBean dataBean : list) {
            if (!dataBean.isGroupChoosed()){
                return false;
            }
        }
        return true;
    }

    private void isAllChecked(boolean checked) {
        for (ShoppingCartSuperClass.DataBean dataBean : list) {
            dataBean.setGroupChoosed(checked);
            for (ShoppingCartSuperClass.DataBean.ListBean listBean : dataBean.getList()) {
                listBean.setChildChoosed(checked);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void statisticsPrice(){
        num = 0;
        price = 0.00;
        for (ShoppingCartSuperClass.DataBean dataBean : list){
            for (ShoppingCartSuperClass.DataBean.ListBean listBean : dataBean.getList()){
                if (listBean.isChildChoosed()){
                    num++;
                    price += listBean.getPrice() * listBean.getNum();
                }
            }
        }
        //设置文本信息 合计、结算的商品数量
        mTvTotalPrice.setText("合计:￥"+price);
        mBtnAmount.setText("结算("+num+")");
    }
}
