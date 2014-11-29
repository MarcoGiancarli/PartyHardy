package com.hardyparty.partyhardy;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.common.images.ImageManager;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
 
public class EventListViewAdapter extends BaseAdapter implements ListAdapter {
	
    Context context;
    LayoutInflater inflater;
//    ImageLoader imageLoader;
    private List<Event> eventList = null;
    private ArrayList<Event> arraylist;
 
    public EventListViewAdapter(Context context,
            List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<Event>();
        this.arraylist.addAll(eventList);
//        imageLoader = new ImageManager(context);
    }
 
    public class ViewHolder {
        TextView titleText;
        TextView lowerText;
        TextView distance;
//        ImageView image;
    }
 
    @Override
    public int getCount() {
        return eventList.size();
    }
 
    @Override
    public Object getItem(int position) {
        return eventList.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.event_list_item, null);
            // Locate the TextViews
            holder.titleText = (TextView) view.findViewById(R.id.titleText);
            holder.lowerText = (TextView) view.findViewById(R.id.lowerText);
            holder.distance = (TextView) view.findViewById(R.id.distance);
            // Locate the ImageView
//            holder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.titleText.setText(eventList.get(position).getTitle());
        holder.lowerText.setText(eventList.get(position).getStartTimeAsString()+" - "+
        		eventList.get(position).getEndTimeAsString());
        holder.distance.setText("1.2 mi"); //TODO: replace with real field or calculation
        // Set the results into ImageView
//        imageLoader.DisplayImage(eventList.get(position).getImage(), holder.image);
        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {
        	
            @Override
            public void onClick(View view) {
                // Send single item click data to single item view class
                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra(EventDetailFragment.ARG_EVENT, eventList.get(position));
//                intent.putExtra("description", eventList.get(position).getDescription());
//                intent.putExtra("startTime", eventList.get(position).getStartTime());
//                intent.putExtra("endTime", eventList.get(position).getEndTime());
                context.startActivity(intent);
            }
        });
        return view;
    }

}