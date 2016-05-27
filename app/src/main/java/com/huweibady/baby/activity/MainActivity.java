package com.huweibady.baby.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.huweibady.baby.R;
import com.huweibady.baby.fragment.CommunityFragment;
import com.huweibady.baby.fragment.FindFragment;
import com.huweibady.baby.fragment.MenuFragment;
import com.huweibady.baby.fragment.MessageFragment;
import com.huweibady.baby.fragment.MineFragment;
import com.huweibady.baby.fragment.SchoolFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private FrameLayout flMain;
    private RadioGroup rgMain;
    private RadioButton rbFind;
    private RadioButton rbCommunity;
    private RadioButton rbSchool;
    private RadioButton rbMessage;
    private RadioButton rbMine;

    /**
     * Fragment的TAG值
     */
    private String TAG_FIND = "RB_FIND";
    private String TAG_Community = "RB_COMMUNITY";
    private String TAG_School = "RB_SCHOOL";
    private String TAG_Message = "RB_MESSAGE";
    private String TAG_Mine = "RB_MINE";

    /**
     * 底部导航栏列表
     */
    private ArrayList<Fragment> fragmentList;

    private FragmentManager fragmentManager;

    /**
     * 再点一次退出，记录上一次点击时间
     */
    private long mLastTime = 0;
    /**
     * 侧滑菜单
     */
    private SlidingMenu mSlidingMenu;

    public static MainActivity mMainActivity;

    @Override
    public void initView() {

        flMain = (FrameLayout) findViewById(R.id.fl_main);
        rgMain = (RadioGroup) findViewById(R.id.rg_main);
        rbFind = (RadioButton) findViewById(R.id.rb_find);
        rbCommunity = (RadioButton) findViewById(R.id.rb_community);
        rbSchool = (RadioButton) findViewById(R.id.rb_school);
        rbMessage = (RadioButton) findViewById(R.id.rb_message);
        rbMine = (RadioButton) findViewById(R.id.rb_mine);

        mMainActivity = this;

    }

    @Override
    public void initListener() {

        rbFind.setOnClickListener(this);
        rbCommunity.setOnClickListener(this);
        rbSchool.setOnClickListener(this);
        rbMessage.setOnClickListener(this);
        rbMine.setOnClickListener(this);

    }

    @Override
    public void initData() {

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new FindFragment());
        fragmentList.add(new CommunityFragment());
        fragmentList.add(new SchoolFragment());
        fragmentList.add(new MessageFragment());
        fragmentList.add(new MineFragment());


        setBehindContentView(R.layout.fragment_school_menu);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.menu, new MenuFragment());
//        fragmentTransaction.replace(R.id.ll_content, new SchoolFragment());
        fragmentTransaction.commit();

        mSlidingMenu = getSlidingMenu();
        mSlidingMenu.setShadowWidth(50);
        //设置分割线背景
        mSlidingMenu.setShadowDrawable(R.mipmap.button_background);

        int widthPixels = getResources().getDisplayMetrics().widthPixels;

        mSlidingMenu.setBehindOffset(widthPixels / 4);
        // 设置渐入渐出效果的值
        mSlidingMenu.setFadeDegree(0.35f);


        //设置SlidingMenu与下方视图的移动的速度比，当为1时同时移动，取值0-1
        mSlidingMenu.setBehindScrollScale(1.0f);
        //默认选中校园
        fragmentManager = getSupportFragmentManager();
        jumpPager(R.id.rb_school);
        rgMain.check(R.id.rb_school);

    }

    @Override
    public int setLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void onAnotherClick(View v) {

        jumpPager(v.getId());

    }

    /**
     * 再点一次退出
     */
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mLastTime > 2000) {
            // 两次返回时间超出两秒
            Toast.makeText(this, "再点一次退出程序", Toast.LENGTH_SHORT).show();
            mLastTime = System.currentTimeMillis();
        } else {
            // 两次返回时间小于两秒，可以退出
            finish();
        }
    }

    /**
     * 跳转界面的方法
     *
     * @param checkedId 要跳转的控件id
     */
    public void jumpPager(int checkedId) {

        FragmentTransaction beginTransaction = fragmentManager
                .beginTransaction();

        switch (checkedId) {
            case R.id.rb_find:// 发现

                beginTransaction.replace(R.id.fl_main, fragmentList.get(0),
                        TAG_FIND);

                break;

            case R.id.rb_community:// 圈子
                beginTransaction.replace(R.id.fl_main, fragmentList.get(1),
                        TAG_Community);
                break;

            case R.id.rb_school:// 校园
                beginTransaction.replace(R.id.fl_main, fragmentList.get(2),
                        TAG_School);

                break;

            case R.id.rb_message:// 消息
                beginTransaction.replace(R.id.fl_main, fragmentList.get(3),
                        TAG_Message);
                break;

            case R.id.rb_mine:// 我的
                beginTransaction.replace(R.id.fl_main, fragmentList.get(4),
                        TAG_Mine);
                break;

            default:
                break;
        }

        beginTransaction.commit();
    }


    /**
     * 返回slidingMenu对象
     *
     * @return
     */
    public SlidingMenu getmSlidingMenu() {
        return mSlidingMenu;
    }
}
