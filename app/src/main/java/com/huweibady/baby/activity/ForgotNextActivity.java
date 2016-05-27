package com.huweibady.baby.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huweibady.baby.R;
import com.huweibady.baby.utils.Constants;
import com.huweibady.baby.utils.Utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ForgotNextActivity extends BaseActivity {

    /**
     * 跟布局
     */
    private LinearLayout llRoot;
    /**
     * 注册按钮
     */
    private TextView tvRegister;
    /**
     * 输入的密码
     */
    private EditText etPwd;
    /**
     * 再次输入密码
     */
    private EditText etRepwd;
    /**
     * 用户输入的手机号
     */
    private String phone;

    @Override
    public void initView() {

        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        tvRegister = (TextView) findViewById(R.id.register);

        Utils.setBackground(getResources(), R.mipmap.loginbackground, llRoot);

        etPwd = (EditText) findViewById(R.id.et_pwd);
        etRepwd = (EditText) findViewById(R.id.et_repwd);
        
    }

    @Override
    public void initListener() {

        tvRegister.setOnClickListener(this);

    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
//		String identifying = intent.getStringExtra("identifying");


    }

    @Override
    public void onAnotherClick(View v) {

        switch (v.getId()) {

            case R.id.register:// 登录

                //注册
                requestRegister();

                break;

            default:
                break;
        }
    }

    /**
     * 请求注册
     */
    private void requestRegister() {

        String sPwd = etPwd.getText().toString().trim();
        String sRepwd = etRepwd.getText().toString().trim();

        if (TextUtils.isEmpty(sPwd)
                || TextUtils.isEmpty(sRepwd)) {
            Toast.makeText(ForgotNextActivity.this, "密码不能为空",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!sPwd.equals(sRepwd)) {
            Toast.makeText(ForgotNextActivity.this, "两次密码不一致",
                    Toast.LENGTH_SHORT).show();
            return;
        }


        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder().add("mob", phone)
                .add("password", sPwd).build();

        Request request = new Request.Builder()
                .url(Constants.BASE_URL2 + "messages/updatepw")
                .post(formBody).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call arg0, Response arg1) throws IOException {


                if (arg1.code() == 200) {

                    ForgotNextActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(ForgotNextActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                        }
                    });

//                    Intent intent = new Intent(ForgotNextActivity.this, LoginActivity.class);
//                    startActivity(intent);

                    //关闭验证码注册页面
                    ForgotActivity.forgotActivity.finish();

                    //关闭自己
                    finish();

                } else {

                    ForgotNextActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(ForgotNextActivity.this, "服务器错误", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }

            @Override
            public void onFailure(Call arg0, IOException arg1) {

                ForgotNextActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(ForgotNextActivity.this, "联网失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

    @Override
    public int setLayoutResID() {
        return R.layout.activity_forgot_next;
    }

}
