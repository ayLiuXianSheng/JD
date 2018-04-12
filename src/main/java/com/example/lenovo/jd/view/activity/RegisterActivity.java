package com.example.lenovo.jd.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.presenter.RegPresenter;
import com.example.lenovo.jd.view.api.Api;
import com.example.lenovo.jd.view.base.BaseActivity;
/**
 * 注册
 * */
public class RegisterActivity extends BaseActivity<RegPresenter> implements View.OnClickListener,IRegisterView {

    private ImageView mImgReturn;
    /**
     * 请输入手机号
     */
    private EditText mEditTel1;
    /**
     * 请输入密码
     */
    private EditText mEditPass1;
    /**
     * 注册
     */
    private Button mBtnZc1;
    private ImageView mIamgeWeixin1;
    private ImageView mIamgeQq1;
    private ImageView mIamgeWeibo1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected RegPresenter getPresenter() {
        presenter = new RegPresenter(this);
        return presenter;
    }

    @Override
    protected void initView() {

        mImgReturn = (ImageView) findViewById(R.id.img_return);
        mImgReturn.setOnClickListener(this);
        mEditTel1 = (EditText) findViewById(R.id.edit_tel1);
        mEditPass1 = (EditText) findViewById(R.id.edit_pass1);
        mBtnZc1 = (Button) findViewById(R.id.btn_zc1);
        mBtnZc1.setOnClickListener(this);
        mIamgeWeixin1 = (ImageView) findViewById(R.id.iamge_weixin1);
        mIamgeWeixin1.setOnClickListener(this);
        mIamgeQq1 = (ImageView) findViewById(R.id.iamge_qq1);
        mIamgeQq1.setOnClickListener(this);
        mIamgeWeibo1 = (ImageView) findViewById(R.id.iamge_weibo1);
        mIamgeWeibo1.setOnClickListener(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img_return:
                finish();
                break;
            case R.id.btn_zc1:
                String tel = mEditTel1.getText().toString().trim();
                String pass = mEditPass1.getText().toString().trim();
                presenter.reg(Api.HOME_NAME,tel,pass);
                break;
            case R.id.iamge_weixin1:
                Toast.makeText(this, "微信登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iamge_qq1:
                Toast.makeText(this, "QQ登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iamge_weibo1:
                Toast.makeText(this, "微博登录", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }
}
