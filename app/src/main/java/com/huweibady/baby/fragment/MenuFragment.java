package com.huweibady.baby.fragment;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.huweibady.baby.R;

/**
 * Created by Administrator on 2016/5/27.
 */
public class MenuFragment extends BaseFragment {

    private View view;

    @Override
    public View initView() {


        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.fragment_school_menu, null);

        view.setBackgroundColor(Color.BLUE);


        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
