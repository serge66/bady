package com.huweibady.baby.fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.huweibady.baby.R;

public class SchoolFragment extends BaseFragment {

    @Override
    public View initView() {


        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.fragment_school, null);


        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

}
