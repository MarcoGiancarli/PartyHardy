package com.hardyparty.partyhardy;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class EventProvider extends ContentProvider {
	
	/*
	 * Defines a handle to the database helper object. The MainDatabaseHelper class is defined
	 * in a following snippet.
	 */
	private MainDatabaseHelper mOpenHelper;
	
	UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	// Defines the database name
	private static final String DBNAME = "party_hardy_db";
	
	// Defines the table name
	private static final String TABLE_EVENTS = "events";
	
	// Holds the database object
	private SQLiteDatabase db;
	
	// A string that defines the SQL statement for creating a table
	private static final String SQL_CREATE_EVENTS = "CREATE TABLE " +
	    TABLE_EVENTS + " " +            // Table's name
	    "(" +                           // The columns in the table
	    " _ID INTEGER PRIMARY KEY, " +
	    " WORD TEXT" +
	    " FREQUENCY INTEGER " +
	    " LOCALE TEXT )";
	
	public boolean onCreate() {
	
	    /*
	     * Creates a new helper object. This method always returns quickly.
	     * Notice that the database itself isn't created or opened
	     * until SQLiteOpenHelper.getWritableDatabase is called
	     */
	    mOpenHelper = new MainDatabaseHelper(
	        getContext()//,        // the application context
//	        DBNAME,              // the name of the database)
//	        null,                // uses the default SQLite cursor
//	        1                    // the version number
	    );
	
	    return true;
	}
	
	// Implements the provider's insert method
	public Uri insert(Uri uri, ContentValues values) {
	    // Insert code here to determine which table to open, handle error-checking, and so forth
		
	    /*
	     * Gets a writeable database. This will trigger its creation if it doesn't already exist.
	     */
	    db = mOpenHelper.getWritableDatabase();
	    return null;
	}
	
	/**
	 * Helper class that actually creates and manages the provider's underlying data repository.
	 */
	protected static final class MainDatabaseHelper extends SQLiteOpenHelper {

	    /*
	     * Instantiates an open helper for the provider's SQLite data repository
	     * Do not do database creation and upgrade here.
	     */
	    MainDatabaseHelper(Context context) {
	        super(context, DBNAME, null, 1);
	    }

	    /*
	     * Creates the data repository. This is called when the provider attempts to open the
	     * repository and SQLite reports that it doesn't exist.
	     */
	    public void onCreate(SQLiteDatabase db) {

	        // Creates the main table
	        db.execSQL(SQL_CREATE_EVENTS);
	    }
	    
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        // Drop older table if existed
	        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);

	        // Create tables again
	        onCreate(db);
	    }
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
//		// SQLiteQueryBuilder is a helper class that creates the
//        // proper SQL syntax for us.
//        SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
//
//        // Set the table we're querying.
//        qBuilder.setTables(TABLE_EVENTS);
//
//        int uriType = mUriMatcher.match(uri);
//
//    	switch (uriType) {
//    	    case PRODUCTS_ID:
//    	        queryBuilder.appendWhere(MainDatabaseHelper.COLUMN_ID + "="
//    	                + uri.getLastPathSegment());
//    	        break;
//    	    case PRODUCTS:
//    	        break;
//    	    default:
//    	        throw new IllegalArgumentException("Unknown URI");
//    	}
//
//        // Make the query.
//        Cursor c = qBuilder.query(db,
//                projection,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                sortOrder);
//        c.setNotificationUri(getContext().getContentResolver(), uri);
//        return c;
		return null;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
}