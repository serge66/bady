package com.huweibady.baby.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huweibady.baby.R;
import com.huweibady.baby.utils.Constants;
import com.huweibady.baby.utils.LogPrint;
import com.huweibady.baby.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForgotActivity extends BaseActivity {


    /**
     * 跟布局
     */
    private LinearLayout llRoot;
    /**
     * 上一步
     */
    private TextView tvPre;
    /**
     * 下一步
     */
    private TextView tvNext;
    /*
     * 验证码
     */
    private TextView tvIdentifying;
    private EditText etPhone;
    private EditText mIdentifying;

    /**
     * 自动倒计时
     */
    private final int MSG_COUNT_DOWN = 0;

    /**
     * 倒计时,单位：秒
     */
    private int countDown = 60;

    /**
     * 注册用户收到的验证码的id
     */
    private int id;

    /**
     * 让ForgotNextActivity关闭自己
     */
    public static ForgotActivity forgotActivity;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case MSG_COUNT_DOWN://倒计时

                    if (countDown == 0) {

                        countDown = 60;

                        tvIdentifying.setText("获取验证码");
                        tvIdentifying.setEnabled(true);

                    } else {

                        countDown--;

                        tvIdentifying.setText("      " + countDown + "  s     ");

                        handler.sendEmptyMessageDelayed(MSG_COUNT_DOWN, 1000);

                    }


                    break;
            }

        }
    };


    @Override
    public void initView() {

        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        Utils.setBackground(getResources(), R.mipmap.loginbackground, llRoot);
        tvPre = (TextView) findViewById(R.id.login);
        tvNext = (TextView) findViewById(R.id.next);
        tvIdentifying = (TextView) findViewById(R.id.identifying);
        etPhone = (EditText) findViewById(R.id.et_phone);
        mIdentifying = (EditText) findViewById(R.id.et_pwd);
        forgotActivity = this;

    }

    @Override
    public void initListener() {

        tvIdentifying.setOnClickListener(this);
        tvPre.setOnClickListener(this);
        tvNext.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public int setLayoutResID() {
        return R.layout.activity_forgot;
    }

    @Override
    public void onAnotherClick(View v) {

        String sPhone = etPhone.getText().toString().trim();
        String identifying = mIdentifying.getText().toString().trim();


        switch (v.getId()) {

            case R.id.identifying:// 验证码

                //请求验证码
                requestIdentifying(sPhone);

                break;

            case R.id.login:// 上一步

                finish();

                break;
            case R.id.next:// 下一步


                requestNext(sPhone, identifying);

                break;

            default:
                break;
        }
    }


    /**
     * 输入验证码后，请求下一步
     *
     * @param sPhone      输入的号码
     * @param identifying 输入的验证码
     */
    private void requestNext(final String sPhone, final String identifying) {

        if (TextUtils.isEmpty(sPhone) || TextUtils.isEmpty(identifying)) {
            Toast.makeText(ForgotActivity.this, "号码或验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }


        OkHttpClient okhttp3 = new OkHttpClient();

        final Request request = new Request.Builder().url(Constants.BASE_URL2 + "messages/" + id + "?mob=" + sPhone + "&code=" + identifying).build();

        okhttp3.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {


                ForgotActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(ForgotActivity.this, "联网失败", Toast.LENGTH_SHORT).show();
                    }
                });


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //这个只有200和401两种状态码

                if (response.code() == 200) {//如果是200，说明手机号和验证码匹配成功

//                    String json = response.body().string();

                    //解析json

                    Intent intent = new Intent(ForgotActivity.this,
                            ForgotNextActivity.class);
                    intent.putExtra("phone", sPhone);
//                    intent.putExtra("identifying", identifying);

                    startActivity(intent);


                } else {


                    ForgotActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(ForgotActivity.this, "验证码不匹配", Toast.LENGTH_SHORT).show();
                            mIdentifying.setText("");
                        }
                    });

                }

            }
        });


    }

    /**
     * 请求验证码
     *
     * @param sPhone 输入的手机号
     */
    private void requestIdentifying(String sPhone) {

        if (sPhone.length() == 0) {

            Toast.makeText(ForgotActivity.this, "手机号不能为空", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        if (sPhone.length() != 11) {

            Toast.makeText(ForgotActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT)
                    .show();
            return;
        }


        OkHttpClient okhttp3 = new OkHttpClient();

        final Request request = new Request.Builder().url(Constants.BASE_URL2 + "messages/resetpw?mob=" + sPhone).build();

        okhttp3.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

                LogPrint.logILsj("联网失败");

                ForgotActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(ForgotActivity.this, "联网失败", Toast.LENGTH_SHORT).show();
                    }
                });


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.code() == 200) {

                    String json = response.body().string();

                    //解析json
                    parseJson(json);

                } else {

                    LogPrint.logILsj("获取验证码失败");

                    ForgotActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(ForgotActivity.this, "获取验证码失败,请重试", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });
    }

    /**
     * 解析收到的验证码信息json
     *
     * @param json 收到的验证码信息
     */
    private void parseJson(String json) {

        LogPrint.logILsj("------------json 注册： " + json);

        try {

            JSONObject jo = new JSONObject(json);
            String status = (String) jo.get("status");
            int sta = Integer.parseInt(status);


            if (sta == 200) {//获取验证码成功

                //不可点击，发送消息，循环执行倒计时
                ForgotActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        tvIdentifying.setEnabled(false);
                    }
                });

                handler.sendEmptyMessage(MSG_COUNT_DOWN);


                id = (int) jo.get("id");

//                id = Integer.parseInt(sId);


            } else {

                ForgotActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(ForgotActivity.this, "服务器出错，请重试", Toast.LENGTH_SHORT).show();
                    }
                });
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
