package com.huweibady.baby.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huweibady.baby.utils.Constants;

public abstract class BaseFragment extends Fragment {

    /**
     * 父类定义公共的SharedPreferences
     */
    public SharedPreferences mSp;

    /**
     * 上下文环境
     */
    public Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
        mSp = mContext.getSharedPreferences(Constants.SP_FINENAME,
                mContext.MODE_PRIVATE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 初始化布局
     *
     * @return
     */
    public abstract View initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化监听
     *
     * @return
     */
    public abstract void initListener();

}
