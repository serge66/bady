package com.huweibady.baby.fragment;

import android.view.View;
import android.widget.TextView;

public class MessageFragment extends BaseFragment {

	@Override
	public View initView() {
		TextView tv = new TextView(getActivity());
		tv.setText("消息");
		return tv;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public View initListener() {
		// TODO Auto-generated method stub
		return null;
	}

}
