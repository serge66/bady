package com.huweibady.baby.fragment;

import android.view.View;
import android.widget.TextView;

public class SchoolFragment extends BaseFragment {

	@Override
	public View initView() {
		TextView tv = new TextView(getActivity());
		tv.setText("校园");
		return tv;
	}

	@Override
	public void initData() {

	}

	@Override
	public View initListener() {
		return null;
	}

}
