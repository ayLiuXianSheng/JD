package com.example.lenovo.jd.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.activity.LoginActivity;
import com.example.lenovo.jd.view.activity.RegisterActivity;
import com.example.lenovo.jd.view.activity.UserInfoActivity;
import com.example.lenovo.jd.view.base.BaseFragment;
import com.example.lenovo.jd.view.base.BasePresenter;

/**
 * 我的
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private ImageView mMySetHei;
    private ImageView mMyMessageHei;
    private ImageView mUserHead;
    private TextView mUserName;
    private ImageView mImgDfk;
    private ImageView mImgDsh;
    private ImageView mImgDpj;
    private ImageView mImgThsh;
    private ImageView mImgWddd;
    private LinearLayout mTestJd;
    private LinearLayout mTestYhq;
    private LinearLayout mTestBt;
    private LinearLayout mTestJdxjk;
    private ImageView mImgWdqb;
    private LinearLayout mTextSpgz;
    private LinearLayout mTextDpgz;
    private LinearLayout mTextNrsc;
    private LinearLayout mTextLljl;
    private ImageView mImgWdhd;
    private ImageView mImgSq;
    private ImageView mImgKffw;
    private LinearLayout mTextJdcs;
    private Intent intent;

    @Override
    protected int getLayoutId() {
        return R.layout.mine_fragment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView(View view) {

        mMySetHei = (ImageView) view.findViewById(R.id.my_set_hei);
        mMySetHei.setOnClickListener(this);
        mMyMessageHei = (ImageView) view.findViewById(R.id.my_message_hei);
        mMyMessageHei.setOnClickListener(this);
        mUserHead = (ImageView) view.findViewById(R.id.user_head);
        mUserHead.setOnClickListener(this);
        mUserName = (TextView) view.findViewById(R.id.user_name);
        mUserName.setOnClickListener(this);
        mImgDfk = (ImageView) view.findViewById(R.id.img_dfk);
        mImgDfk.setOnClickListener(this);
        mImgDsh = (ImageView) view.findViewById(R.id.img_dsh);
        mImgDsh.setOnClickListener(this);
        mImgDpj = (ImageView) view.findViewById(R.id.img_dpj);
        mImgDpj.setOnClickListener(this);
        mImgThsh = (ImageView) view.findViewById(R.id.img_thsh);
        mImgThsh.setOnClickListener(this);
        mImgWddd = (ImageView) view.findViewById(R.id.img_wddd);
        mImgWddd.setOnClickListener(this);
        mTestJd = (LinearLayout) view.findViewById(R.id.test_jd);
        mTestJd.setOnClickListener(this);
        mTestYhq = (LinearLayout) view.findViewById(R.id.test_yhq);
        mTestYhq.setOnClickListener(this);
        mTestBt = (LinearLayout) view.findViewById(R.id.test_bt);
        mTestBt.setOnClickListener(this);
        mTestJdxjk = (LinearLayout) view.findViewById(R.id.test_jdxjk);
        mTestJdxjk.setOnClickListener(this);
        mImgWdqb = (ImageView) view.findViewById(R.id.img_wdqb);
        mImgWdqb.setOnClickListener(this);
        mTextSpgz = (LinearLayout) view.findViewById(R.id.text_spgz);
        mTextSpgz.setOnClickListener(this);
        mTextDpgz = (LinearLayout) view.findViewById(R.id.text_dpgz);
        mTextDpgz.setOnClickListener(this);
        mTextNrsc = (LinearLayout) view.findViewById(R.id.text_nrsc);
        mTextNrsc.setOnClickListener(this);
        mTextLljl = (LinearLayout) view.findViewById(R.id.text_lljl);
        mTextLljl.setOnClickListener(this);
        mImgWdhd = (ImageView) view.findViewById(R.id.img_wdhd);
        mImgWdhd.setOnClickListener(this);
        mImgSq = (ImageView) view.findViewById(R.id.img_sq);
        mImgSq.setOnClickListener(this);
        mImgKffw = (ImageView) view.findViewById(R.id.img_kffw);
        mImgKffw.setOnClickListener(this);
        mTextJdcs = (LinearLayout) view.findViewById(R.id.text_jdcs);
        mTextJdcs.setOnClickListener(this);
        intent = new Intent(getActivity(),LoginActivity.class);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.my_set_hei:
                //设置
                Toast.makeText(getContext(),"设置",Toast.LENGTH_LONG).show();
                break;
            case R.id.my_message_hei:
                //设置
                Toast.makeText(getContext(),"消息",Toast.LENGTH_LONG).show();
                break;
            case R.id.user_head:
                //头像
                /*Toast.makeText(getContext(),"头像",Toast.LENGTH_LONG).show();*/
                startActivity(intent);
                break;
            case R.id.user_name:
                //用户名
                /*Toast.makeText(getContext(),"用户名",Toast.LENGTH_LONG).show();*/
                startActivity(intent);
                break;
            case R.id.img_dfk:
                //待付款
                Toast.makeText(getContext(),"待付款",Toast.LENGTH_LONG).show();
                break;
            case R.id.img_dsh:
                //待收货
                Toast.makeText(getContext(),"待收货",Toast.LENGTH_LONG).show();
                break;
            case R.id.img_dpj:
                //待评价
                Toast.makeText(getContext(),"待评价",Toast.LENGTH_LONG).show();
                break;
            case R.id.img_thsh:
                //退换/售后
                Toast.makeText(getContext(),"退换/售后",Toast.LENGTH_LONG).show();
                break;
            case R.id.img_wddd:
                //我的订单
                Toast.makeText(getContext(),"我的订单",Toast.LENGTH_LONG).show();
                break;
            case R.id.test_jd:
                //京豆
                Toast.makeText(getContext(),"京豆",Toast.LENGTH_LONG).show();
                break;
            case R.id.test_yhq:
                //优惠券
                Toast.makeText(getContext(),"优惠券",Toast.LENGTH_LONG).show();
                break;
            case R.id.test_bt:
                //白条
                Toast.makeText(getContext(),"白条",Toast.LENGTH_LONG).show();
                break;
            case R.id.test_jdxjk:
                //京东小金库
                Toast.makeText(getContext(),"京东小金库",Toast.LENGTH_LONG).show();
                break;
            case R.id.img_wdqb:
                //我的钱包
                Toast.makeText(getContext(),"我的钱包",Toast.LENGTH_LONG).show();
                break;
            case R.id.text_spgz:
                //商品关注
                Toast.makeText(getContext(),"商品关注",Toast.LENGTH_LONG).show();
                break;
            case R.id.text_dpgz:
                //店铺关注
                Toast.makeText(getContext(),"店铺关注",Toast.LENGTH_LONG).show();
                break;
            case R.id.text_nrsc:
                //内容收藏
                Toast.makeText(getContext(),"内容收藏",Toast.LENGTH_LONG).show();
                break;
            case R.id.text_lljl:
                //浏览记录
                Toast.makeText(getContext(),"浏览记录",Toast.LENGTH_LONG).show();
                break;
            case R.id.img_wdhd:
                //我的活动
                Toast.makeText(getContext(),"我的活动",Toast.LENGTH_LONG).show();
                break;
            case R.id.img_sq:
                //社区
                Toast.makeText(getContext(),"社区",Toast.LENGTH_LONG).show();
                break;
            case R.id.img_kffw:
                //客户服务
                Toast.makeText(getContext(),"客户服务",Toast.LENGTH_LONG).show();
                break;
            case R.id.text_jdcs:
                //京东超市
                Toast.makeText(getContext(),"京东超市",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
