package com.huweibady.baby.fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.huweibady.baby.R;
import com.huweibady.baby.activity.MainActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class SchoolFragment extends BaseFragment implements View.OnClickListener {

    /***
     * 用户头像
     */
    private CircleImageView picture;

    @Override
    public View initView() {


        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.fragment_school, null);
        picture = (CircleImageView) view.findViewById(R.id.picture);


        initListener();
        initData();

        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    picture.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.picture:

                MainActivity.mMainActivity.getSlidingMenu().toggle();


                break;


        }

    }
}
