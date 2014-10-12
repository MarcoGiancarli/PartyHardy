package com.hardyparty.partyhardy;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.android.gms.maps.model.LatLng;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
	
	private String id;
	private String title;
	private String description;
	private Calendar startTime;
	private Calendar endTime;
	private LatLng latLng;
	
	public static String[] months = {
	        "Jan",
	        "Feb",
	        "Mar",
	        "Apr",
	        "May",
	        "June",
	        "July",
	        "Aug",
	        "Sept",
	        "Oct",
	        "Nov",
	        "Dec"
	};
	
	public Event() {
	}
	
	public String getId() {
		if(id != null) {
			return id;
		} else {
			return null;
		}
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getStartTime() {
		Date time = new Date();
		if(startTime != null) {
			time.setTime(startTime.getTimeInMillis());
			return time;
		} else {
			return null;
		}
	}
	public void setStartTime(Date newTime) {
		startTime = new GregorianCalendar();
		startTime.setTime(newTime);
	}
	public String getStartTimeAsString() {
		if(startTime != null) {
			String month = months[startTime.get(Calendar.MONTH)-1];
			String day = Integer.toString(startTime.get(Calendar.DATE));
			String hour = Integer.toString(startTime.get(Calendar.HOUR));
			String minute = Integer.toString(startTime.get(Calendar.MINUTE));
			String amPm = startTime.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
			return month+" "+day+" "+hour+":"+minute+" "+amPm;
		} else {
			return null;
		}
	}
	
	public Date getEndTime() {
		if(endTime != null) {
			Date time = new Date();
			time.setTime(endTime.getTimeInMillis());
			return time;
		} else {
			return null;
		}
	}
	public void setEndTime(Date newTime) {
		endTime = new GregorianCalendar();
		endTime.setTime(newTime);
	}
	public String getEndTimeAsString() {
		if(endTime != null) {
			String month = months[endTime.get(Calendar.MONTH)-1];
			String day = Integer.toString(endTime.get(Calendar.DATE));
			String hour = Integer.toString(endTime.get(Calendar.HOUR));
			String minute = Integer.toString(endTime.get(Calendar.MINUTE));
			String amPm = endTime.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
			return month+" "+day+" "+hour+":"+minute+" "+amPm;
		} else {
			return null;
		}
	}
	
	public String getTitle() {
		if(title != null) {
			return title;
		} else {
			return null;
		}
	}
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	public String getDescription() {
		if(description != null) {
			return description;
		} else {
			return null;
		}
	}
	public void setDescription(String newDescription) {
		description = newDescription;
	}
	
	public LatLng getLatLng() {
		return latLng;
	}
	public void setLatLng(LatLng newLatLng) {
		latLng = newLatLng;
	}
	
	@Override
	public String toString() {
		return getTitle();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(description);
		dest.writeString(id);
		dest.writeLong(startTime.getTimeInMillis());
		dest.writeLong(endTime.getTimeInMillis());
	}
	
	public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel source) {
            final Event e = new Event();
            e.title = (String) source.readString();
            e.description = (String) source.readString();
            e.id = (String) source.readString();
            
            e.startTime = new GregorianCalendar();
            e.startTime.setTimeInMillis((Long) source.readLong());
            e.endTime = new GregorianCalendar();
            e.endTime.setTimeInMillis((Long) source.readLong());
            return e;
        }

        public Event[] newArray(int size) {
            throw new UnsupportedOperationException();
        }
    };
}
