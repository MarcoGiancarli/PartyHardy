package com.hardyparty.partyhardy;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.TextView;
 
public class ListViewAdapter extends BaseAdapter {
	
    Context context;
    LayoutInflater inflater;
    //ImageLoader imageLoader;
    private List<Event> eventList = null;
    private ArrayList<Event> arraylist;
 
    public ListViewAdapter(Context context,
            List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<Event>();
        this.arraylist.addAll(eventList);
        //imageLoader = new ImageLoader(context);
    }
 
    public class ViewHolder {
        TextView title;
        TextView description;
        TextView startTime;
        TextView endTime;
        //ImageView image;
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
            view = inflater.inflate(R.layout.fragment_event_detail, null);
            // Locate the TextViews
            holder.title = (TextView) view.findViewById(R.id.event_title);
            holder.description = (TextView) view.findViewById(R.id.event_description);
            holder.startTime = (TextView) view.findViewById(R.id.event_start_time);
            holder.endTime = (TextView) view.findViewById(R.id.event_end_time);
            // Locate the ImageView
            //holder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.title.setText(eventList.get(position).getTitle());
        holder.description.setText(eventList.get(position).getDescription());
        holder.startTime.setText(eventList.get(position).getStartTime().toString());
        holder.startTime.setText(eventList.get(position).getEndTime().toString());
        // Set the results into ImageView
        //imageLoader.DisplayImage(eventList.get(position).getImage(), holder.image);
        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // Send single item click data to single item view class
                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra("title", eventList.get(position).getTitle());
                intent.putExtra("description", eventList.get(position).getDescription());
                intent.putExtra("startTime", eventList.get(position).getStartTime());
                intent.putExtra("endTime", eventList.get(position).getEndTime());
                context.startActivity(intent);
            }
        });
        return view;
    }

}