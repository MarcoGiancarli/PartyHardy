package com.hardyparty.partyhardy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapTabFragment extends Fragment {
	
	public static MapTabFragment newInstance() {
		MapTabFragment fragment = new MapTabFragment();
		return fragment;
	}

	public MapTabFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_map_tab, container,
				false);
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
}
