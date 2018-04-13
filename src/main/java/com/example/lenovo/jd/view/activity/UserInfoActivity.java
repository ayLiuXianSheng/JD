package com.example.lenovo.jd.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.jd.R;
import com.example.lenovo.jd.view.base.BaseActivity;
import com.example.lenovo.jd.view.base.BasePresenter;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
/**
 * 用户信息
 * */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvPersonalIcon;
    private TextView mUserName;
    private TextView mUserUid;
    /**
     * 退出登录
     */
    private Button mBtnLoginOut;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor edit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView() {

        mIvPersonalIcon = (ImageView) findViewById(R.id.iv_personal_icon);
        mIvPersonalIcon.setOnClickListener(this);
        mUserName = (TextView) findViewById(R.id.user_name);
        mUserName.setOnClickListener(this);
        mUserUid = (TextView) findViewById(R.id.user_uid);
        mUserUid.setOnClickListener(this);
        mBtnLoginOut = (Button) findViewById(R.id.btn_login_out);
        mBtnLoginOut.setOnClickListener(this);
        mSharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        edit = mSharedPreferences.edit();

    }

    @Override
    protected void getData() {
        String username = mSharedPreferences.getString("username", "");//这是获取值
        String uid = mSharedPreferences.getString("uid", "");
        String icon = mSharedPreferences.getString("icon", "");
        mUserName.setText(username);
        mUserUid.setText(uid);
        if (!"".equals(icon) && icon != null){
            Glide.with(this)
                    .load(icon)
                    .bitmapTransform(new RoundedCornersTransformation(this, 100, 5))
                    .into(mIvPersonalIcon);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_personal_icon:
                break;
            case R.id.user_name:
                break;
            case R.id.user_uid:
                break;
            case R.id.btn_login_out:
                Toast.makeText(this,"退出成功",Toast.LENGTH_LONG).show();
                edit.clear();
                edit.commit();
                finish();
                break;
        }
    }
}
