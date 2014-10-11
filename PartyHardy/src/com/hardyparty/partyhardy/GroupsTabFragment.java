package com.hardyparty.partyhardy;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GroupsTabFragment extends Fragment {

	public static GroupsTabFragment newInstance() {
		GroupsTabFragment fragment = new GroupsTabFragment();
		return fragment;
	}

	public GroupsTabFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_groups_tab, container,
				false);
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
}
