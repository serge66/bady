package com.huweibady.baby.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class CommunityFragment extends BaseFragment {

	@Override
	public View initView() {
		TextView tv = new TextView(getActivity());
		tv.setText("圈子");
		tv.setTextColor(Color.BLUE);
		return tv;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initListener() {

	}

}
