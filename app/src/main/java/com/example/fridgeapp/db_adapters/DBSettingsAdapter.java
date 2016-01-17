package com.example.fridgeapp.db_adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBSettingsAdapter {
	public static final String KEY_ROWID = "id";
	public static final String KEY_SIGNAL = "signal";
	public static final String KEY_REMINDER = "reminder";
	public static final String KEY_TIME = "time";
	private static final String TAG = "DBAdapter";

	private static final String DATABASE_NAME = "SettingsDB";
	private static final String DATABASE_TABLE = "settings";
	private static final int DATABASE_VERSION = 2;

	private static final String DATABASE_CREATE = "create table if not exists settings (id integer primary key autoincrement, "
			+ "signal VARCHAR, reminder VARCHAR, time VARCHAR);";

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBSettingsAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(DATABASE_CREATE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS contacts");
			onCreate(db);
		}
	}

	// ---opens the database---
	public DBSettingsAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		DBHelper.close();
	}

	// ---insert a record into the database---
	private long initSettings(String signal, String reminder, String time) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_ROWID, 1);
		initialValues.put(KEY_SIGNAL, signal);
		initialValues.put(KEY_REMINDER, reminder);
		initialValues.put(KEY_TIME, time);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	// ---deletes a first record in SettingsDB
	private boolean deleteSettings() {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + 1, null) > 0;
	}

	// get quantity of records in SettingsDB
	public int getDBCount() {
		return db.query(DATABASE_TABLE,
				new String[] { KEY_ROWID, KEY_SIGNAL, KEY_REMINDER, KEY_TIME },
				null, null, null, null, null).getCount();
	}

	// ---retrieves a first record in SettingsDB
	public Cursor getSettings() throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_SIGNAL, KEY_REMINDER, KEY_TIME }, KEY_ROWID
				+ "=" + 1, null, null, null, null, null);
		if (mCursor != null) mCursor.moveToFirst();
		return mCursor;
	}

	// ---updates a record---
	public boolean updateRecord(String signal, String reminder,
			String time) {
		if (getDBCount() == 0) {
			initSettings(signal, reminder, time);
			return true;
		}
		ContentValues args = new ContentValues();
		args.put(KEY_SIGNAL, signal);
		args.put(KEY_REMINDER, reminder);
		args.put(KEY_TIME, time);
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + 1, null) > 0;
	}
}
