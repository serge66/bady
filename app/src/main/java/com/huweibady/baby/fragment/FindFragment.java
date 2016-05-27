package com.huweibady.baby.fragment;

import android.view.View;
import android.widget.TextView;

public class FindFragment extends BaseFragment {

    @Override
    public View initView() {

        TextView tv = new TextView(getActivity());
        tv.setText("发现");

        return tv;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
    }

}
