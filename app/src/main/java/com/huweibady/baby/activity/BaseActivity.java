package com.huweibady.baby.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.huweibady.baby.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public abstract class BaseActivity extends SlidingFragmentActivity implements
        OnClickListener {

    /**
     * 上下文环境
     */
    public Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setLayoutResID());

        mContext = this;

        setBehindContentView(R.layout.fragment_school_menu);
        SlidingMenu sm = getSlidingMenu();
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);

        initView();

        initListener();

        initData();
    }

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 初始化监听
     */
    public abstract void initListener();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 设置activity的布局
     *
     * @return 返回一个int类型的布局id
     */
    public abstract int setLayoutResID();

    /**
     * 另外的点击事件
     */
    public void onAnotherClick(View v) {

    }

    /**
     * 父类点击事件
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.back:

                finish();

                break;

            default:

                onAnotherClick(v);

                break;
        }

    }
}
