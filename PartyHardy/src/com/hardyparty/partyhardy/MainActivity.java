package com.hardyparty.partyhardy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.PushService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements ActionBar.TabListener, EventListFragment.Callbacks {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	ActionBar actionBar;
	
	// Save fragments
	private GroupsTabFragment groupsTabFragment;
	private MapFragment mapFragment;
	private EventListFragment eventListFragment;
	
	// List of events currently loaded from Parse
	private ArrayList<Event> events;
	
	// Temporary list used for loading objects from parse
	private List<ParseObject> objects;
	
	private GoogleMap googleMap;
	private LocationManager locationManager;
	private String provider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		
		// Connect to Parse.
		Parse.initialize(this, getString(R.string.parse_application_id), 
				getString(R.string.parse_client_key));
//		ParseFacebookUtils.initialize(getString(R.string.facebook_id));
		
		// Set up as default push activity.
//		PushService.setDefaultPushCallback(this, MainActivity.class);
//		ParseInstallation.getCurrentInstallation().saveInBackground();
		
		// If user is not signed in, then start a sign in or sign up activity.
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
			// Do stuff with the user, like filtering events based on preferences
			
		} else {
			// Show the login screen.
			//startActivity(new Intent(this, LoginActivity.class));
		}
		
        events = new ArrayList<Event>();
        
        try {
            // Locate the class table named "Event" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Event").setLimit(20);
            
            // We could add some sort of order either here or in cloud code first.
            
            objects = query.find();
            for (ParseObject parseEvent : objects) {
//	            ParseFile image = (ParseFile) parseEvent.get("image");

                Event event = new Event();
                event.setId((String) parseEvent.getObjectId());
                event.setTitle((String) parseEvent.get("title"));
                event.setDescription((String) parseEvent.get("description"));
                event.setStartTime((Date) parseEvent.getDate("startTime"));
                event.setEndTime((Date) parseEvent.getDate("endTime"));
//		        event.setImage(image.getUrl()); // Let's worry about images later
                events.add(event);
            }
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
	}
	
	@Override
    public void onStart() {
    	super.onStart();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			// pull up settings fragment/activity
			return true;
		}
		if (id == R.id.action_search) {
			// launch search stuff -- check docs for adding search feature
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
		actionBar.setTitle(mSectionsPagerAdapter.getPageTitle(tab.getPosition()));
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}
	
	/**
     * Callback method from {@link EventListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        // In single-pane mode, simply start the detail activity
        // for the selected item ID.
//		Bundle bundle = new Bundle();
//		bundle.putString(EventDetailFragment.ARG_EVENT_ID, id);
//    	eventDetailFragment = EventDetailFragment.newInstance();
//    	eventDetailFragment.setArguments(bundle);
//    	
//    	// Show detail fragment. Just kidding use activity instead.
//    	FragmentTransaction ft = getFragmentManager().beginTransaction();
//    	//ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
//    	ft.replace(R.id.container, eventDetailFragment, "eventDetailFragment");
//    	ft.addToBackStack("eventListFragment");
//    	ft.commit();
    	
    	// Find the event to pass as an argument.
    	Event event = null;
    	for(Event e : events) {
    		if(e.getId().equals(id)) {
    			event = e;
    			break;
    		}
    	}
    	
    	if(event != null) {
	    	// Show detail activity.
	    	Intent detailIntent = new Intent(this, EventDetailActivity.class);
	    	detailIntent.putExtra(EventDetailFragment.ARG_EVENT, (Parcelable) event);
	    	startActivity(detailIntent);
    	} else {
    		// Deal with the error condition (no event for event detail page)
    	}
    }

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch(position) {
			case 0:
				eventListFragment = EventListFragment.newInstance();
				return eventListFragment;
			case 1:
				mapFragment = MapFragment.newInstance();
				return mapFragment;
			case 2:
				groupsTabFragment = GroupsTabFragment.newInstance();
				return groupsTabFragment;
			}
			return EventListFragment.newInstance();
		}

		@Override
		public int getCount() {
			return 2;
//			return 3;  TODO: remove this after groups is implemented
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// Uncomment items in this method to make titles capital
			//Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_events_tab)/*.toUpperCase(l)*/;
			case 1:
				return getString(R.string.title_map_tab)/*.toUpperCase(l)*/;
			case 2:
				return getString(R.string.title_groups_tab)/*.toUpperCase(l)*/;
			}
			return null;
		}
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
}