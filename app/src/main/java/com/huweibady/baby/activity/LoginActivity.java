package com.huweibady.baby.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huweibady.baby.R;
import com.huweibady.baby.utils.Constants;
import com.huweibady.baby.utils.InstalltionId;
import com.huweibady.baby.utils.LogPrint;
import com.huweibady.baby.utils.Utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 登录界面
 *
 * @author Administrator
 */
public class LoginActivity extends BaseActivity {

    /**
     * 跟布局
     */
    private LinearLayout llRoot;
    /**
     * 登录按钮对象
     */
    private TextView tvLogin;
    /**
     * 注册按钮
     */
    private TextView tvRegister;
    /**
     * 找回密码
     */
    private TextView tvRetrieve;
    /**
     * 输入的密码
     */
    private EditText etPwd;
    /**
     * 输入的手机号
     */
    private EditText etPhone;

    @Override
    public void initView() {

        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        tvRegister = (TextView) findViewById(R.id.register);
        tvLogin = (TextView) findViewById(R.id.login);
        tvRetrieve = (TextView) findViewById(R.id.retrieve);

        Utils.setBackground(getResources(), R.mipmap.loginbackground, llRoot);

        etPhone = (EditText) findViewById(R.id.et_phone);
        etPwd = (EditText) findViewById(R.id.et_pwd);

    }

    @Override
    public void initListener() {

        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvRetrieve.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onAnotherClick(View v) {

        switch (v.getId()) {

            case R.id.register:// 注册

                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

                break;

            case R.id.login:// 登录

                String sPhone = etPhone.getText().toString().trim();
                String sPwd = etPwd.getText().toString().trim();

                if (TextUtils.isEmpty(sPwd) || TextUtils.isEmpty(sPhone)) {
                    Toast.makeText(LoginActivity.this, "手机号或密码不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // if (sPhone.length() < 11) {
                //
                // Toast.makeText(LoginActivity.this, "手机号小于11位",
                // Toast.LENGTH_SHORT).show();
                // return;
                // }

                try {
                    requestData(sPhone, sPwd);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.retrieve:// 找回密码


                startActivity(new Intent(this,ForgotActivity.class));

                break;

            default:
                break;
        }
    }

    /**
     * 请求数据
     *
     * @throws IOException
     */
    private void requestData(String sPhone, String sPwd) throws IOException {


        String uuId = InstalltionId.id(mContext);
        LogPrint.logILsj("-------uuid: "+uuId);

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder().add("mob", sPhone)
                .add("password", sPwd).add("uuid",uuId).build();

        Request request = new Request.Builder()
                .url(Constants.BASE_URL2 + "tokens?expand=kindergarten").get()
                .post(formBody).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call arg0, Response arg1) throws IOException {


                String json = arg1.body().string();
                LogPrint.logILsj("----登录成功" + json);

                if (arg1.code() == 200) {

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("json", json);
                    startActivity(intent);


                    finish();

                } else {

                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(LoginActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }

            @Override
            public void onFailure(Call arg0, IOException arg1) {

                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(LoginActivity.this, "联网失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }


    @Override
    public int setLayoutResID() {
        return R.layout.activity_login;
    }

}
