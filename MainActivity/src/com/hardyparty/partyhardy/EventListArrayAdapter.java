package com.hardyparty.partyhardy;

import java.util.List;

import com.hardyparty.partyhardy.ListViewAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EventListArrayAdapter extends ArrayAdapter {
	
	public EventListArrayAdapter(Context context, int resource, int textViewResourceId, List<Event> objects){
		super ( context, resource, textViewResourceId, objects);
		ListViewAdapter lVAdapter = new ListViewAdapter(context, objects);
		lVAdapter.new ViewHolder();
   }

	@Override  //Context needed for getSystemService
	   public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = vi.inflate(R.layout.relativelayout_list_item, null);
            vh = new ViewHolder(); // will store references to views
            vh.description = (TextView)convertView.findViewById(R.id.relativelayout_list_item);
            convertView.setTag(vh); // store vh away
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

//        int height = res.getDisplayMetrics().heightPixels;
//        // workaround for small screens
//        if (height < 490){
//            RelativeLayout.LayoutParams par = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, 30);
//            vh.answerBlock.setLayoutParams(par);
//        }
        return convertView;
    }
}
