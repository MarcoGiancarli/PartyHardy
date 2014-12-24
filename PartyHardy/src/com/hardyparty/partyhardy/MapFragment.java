package com.hardyparty.partyhardy;

import java.util.ArrayList;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapFragment extends com.google.android.gms.maps.MapFragment {
	
	GoogleMap googleMap;
	LocationManager locationManager;
	String provider;
	
	// Default zoom level for map
	public final int DEFAULT_MAP_ZOOM = 17;
	
	private ArrayList<Event> events;
	
	public static MapFragment newInstance() {
		return new MapFragment();
	}
	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		View rootView = inflater.inflate(R.layout.fragment_map, container, false);
//		return rootView;
//	}
	
	@Override
	public void onStart() {
		super.onStart();
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity().getBaseContext());
        if(status!=ConnectionResult.SUCCESS) {
        	// Google Play Services are not available
        	int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, getActivity(), requestCode);
            dialog.show();
        } else {
        	// Google Play Services are available
        	googleMap = super.getMap();
            googleMap.setMyLocationEnabled(true);
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            
            Criteria criteria = new Criteria();
            provider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(provider);
            
            // Display initial location on the map, if possible
            if(location != null) {
                goToCurrentLocation(location);
            }
            
            // Get events
            events = ((MainActivity)getActivity()).getEvents();
            
        	for(Event event : events) {
        		if(event.getLatLng() != null) {
	        			// Place event as a marker on the map
		            MarkerOptions options = new MarkerOptions()
		            		.title(event.getTitle())
		            		.position(event.getLatLng());
		            googleMap.addMarker(options);
        		}
        	}
        }
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	public void goToCurrentLocation(Location location) {
		// Get lat and long from the location object
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        
		// Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);
 
        // Showing the current location in Google Map
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(DEFAULT_MAP_ZOOM));
	}
	
	public void goToCurrentLocation() {
		// Use last known location by default
		Location location = locationManager.getLastKnownLocation(provider);
		goToCurrentLocation(location);
	}
}
