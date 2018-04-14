package com.example.lenovo.jd.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.presenter.LoginPresenter;
import com.example.lenovo.jd.view.api.Api;
import com.example.lenovo.jd.view.base.BaseActivity;
import com.example.lenovo.jd.view.bean.LoginSuperClass;

import com.example.lenovo.jd.view.bean.QQLoginSuperClass;
import com.google.gson.Gson;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * 登录
 * */
public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener,ILoginView {

    /**
     * 请输入手机号
     */
    private EditText mEditTel;
    /**
     * 请输入密码
     */
    private EditText mEditPass;
    /**
     * 登录
     */
    private Button mBtnDl;
    /**
     * 注册
     */
    private Button mBtnZc;
    private ImageView mIamgeWeixin;
    private ImageView mIamgeQq;
    private ImageView mIamgeWeibo;
    private SharedPreferences mSharedPreferences;
    private static final String TAG = "LoginActivity";
    private static final String APP_ID = "1106742841";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private SharedPreferences.Editor edit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter getPresenter() {
        presenter = new LoginPresenter(this);
        return presenter;
    }

    @Override
    protected void initView() {

        mEditTel = (EditText) findViewById(R.id.edit_tel);
        mEditPass = (EditText) findViewById(R.id.edit_pass);
        mBtnDl = (Button) findViewById(R.id.btn_dl);
        mBtnDl.setOnClickListener(this);
        mBtnZc = (Button) findViewById(R.id.btn_zc);
        mBtnZc.setOnClickListener(this);
        mIamgeWeixin = (ImageView) findViewById(R.id.iamge_weixin);
        mIamgeWeixin.setOnClickListener(this);
        mIamgeQq = (ImageView) findViewById(R.id.iamge_qq);
        mIamgeQq.setOnClickListener(this);
        mIamgeWeibo = (ImageView) findViewById(R.id.iamge_weibo);
        mIamgeWeibo.setOnClickListener(this);
        mSharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        edit = mSharedPreferences.edit();
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID,LoginActivity.this.getApplicationContext());

    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_dl:
                String tel = mEditTel.getText().toString().trim();
                String pass = mEditPass.getText().toString().trim();
                presenter.login(Api.HOME_NAME,tel,pass);
                break;
            case R.id.btn_zc:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.iamge_weixin:
                Toast.makeText(this, "微信登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iamge_qq:
                qqLogin();
                break;
            case R.id.iamge_weibo:
                Toast.makeText(this, "微博登录", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSccuess(LoginSuperClass loginSuperClass) {
        Toast.makeText(this, loginSuperClass.getMsg(), Toast.LENGTH_SHORT).show();
        /*Intent intent = new Intent(this,MineFragment.class);
        startActivity(intent);*/
        LoginSuperClass.DataBean data = loginSuperClass.getData();
        edit.putString("username",data.getUsername());//这是存数据
        edit.putString("uid",data.getUid() + "");//这是存数据
        edit.putString("icon",data.getIcon());//这是存数据
        edit.commit();//这是将数据提交
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }

    private void qqLogin() {
        /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
         官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
         第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
        mIUiListener = new BaseUiListener();
        //all表示获取所有权限
        mTencent.login(LoginActivity.this,"all", mIUiListener);
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener{

        @Override
        public void onComplete(Object response) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());
                        Gson gson = new Gson();
                        QQLoginSuperClass qqLoginSuperClass = gson.fromJson(response.toString(), QQLoginSuperClass.class);
                        edit.putString("username",qqLoginSuperClass.getNickname());//这是存数据
                        edit.putString("uid",qqLoginSuperClass.getYear());//这是存数据
                        edit.putString("icon",qqLoginSuperClass.getFigureurl_qq_2());//这是存数据
                        edit.commit();//这是将数据提交
                        finish();
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
