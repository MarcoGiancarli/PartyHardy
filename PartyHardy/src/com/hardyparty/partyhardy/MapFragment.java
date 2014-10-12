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
	public final int DEFAULT_MAP_ZOOM = 18;
	
	private ArrayList<Event> events;

	public MapFragment() {
	}

//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		View rootView = inflater.inflate(R.layout.fragment_map, container, false);
//		return rootView;
//	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity().getBaseContext());
        if(status!=ConnectionResult.SUCCESS) {
        	// Google Play Services are not available
        	int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, getActivity(), requestCode);
            dialog.show();
        } else {
        	// Google Play Services are available
        	googleMap = getMap();
            googleMap.setMyLocationEnabled(true);
            locationManager = (LocationManager) getActivity().getSystemService(MainActivity.LOCATION_SERVICE);
            
            Criteria criteria = new Criteria();
            provider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(provider);
            
            // Display initial location on the map, if possible
            if(location != null) {
                goToCurrentLocation(location);
            }
            
            // Get events
            
        	for(Event event : events) {
	            // Place event as a marker on the map
	            MarkerOptions options = new MarkerOptions()
	            		.title(event.getTitle())
	            		.position(event.getLatLng());
	            googleMap.addMarker(options);
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
}
