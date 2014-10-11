package com.hardyparty.partyhardy;

import com.google.android.gms.maps.MapFragment;

import android.os.Bundle;
import android.app.DialogFragment;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EventDetailFragment extends DialogFragment {
    
    public static final String ARG_EVENT = "event";
    
    private Event event;
    
	public EventDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
//        if(getArguments().containsKey(ARG_EVENT_ID)) {
//        	String eventId = getArguments().getString(ARG_EVENT_ID);
//			for(Event e : ((MainActivity) getActivity()).getEvents()) {
//				if(e.getId().equals(eventId)) {
//					event = e;
//					break;
//				}
//			}
//			if(event == null) {
//				// Query database for event? Or find some other solution/handle error.
//			}
//        } else {
//        	// Handle the case of not having an event id.
//        }
        
        event = (Event) getArguments().getParcelable(ARG_EVENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_detail, container, false);
        
		if (event != null) {
            ((TextView) rootView.findViewById(R.id.event_title))
            		.setText(event.getTitle());
            ((TextView) rootView.findViewById(R.id.event_description))
            		.setText(event.getDescription());
            ((TextView) rootView.findViewById(R.id.event_start_time))
            		.setText(event.getStartTimeAsString());
            ((TextView) rootView.findViewById(R.id.event_end_time))
            		.setText(event.getEndTimeAsString());
        }

        return rootView;
    }

	public static EventDetailFragment newInstance() {
		return new EventDetailFragment();
	}
}
