package com.example.fridgeapp.db_adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBFridgeAdapter {
	public static final String KEY_ROWID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_DUEDATE = "duedate";
	public static final String KEY_ALARMTIME = "alarmtime";
	public static final String KEY_SURROGATES = "surrogates";
	public static final String KEY_QUANTITY = "quantity";
	public static final String KEY_ALARM = "alarm";
	private static final String TAG = "DBAdapter";

	private static final String DATABASE_NAME = "FridgeDB";
	private static final String DATABASE_TABLE = "fridge";
	private static final int DATABASE_VERSION = 3;

	private static final String DATABASE_CREATE = "create table if not exists fridge (id integer primary key autoincrement, "
			+ "name VARCHAR not null, duedate VARCHAR, alarmtime VARCHAR, surrogates VARCHAR, quantity integer, alarm integer );";

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBFridgeAdapter(Context ctx) {
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
			db.execSQL("DROP TABLE IF EXISTS fridge");
			onCreate(db);
		}
	}

	// ---opens the database---
	public DBFridgeAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		DBHelper.close();
	}

	public long createRecord(String name, String duedate, String time,
			String surrogates, long quantity, long alarm) {
		if (findRecord(name) != -1)
			return -1;
		return insertRecord(name, duedate, time, surrogates, quantity, alarm);
	}

	// ---insert a record into the database---
	private long insertRecord(String name, String duedate, String time,
			String surrogates, long quantity, long alarm) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_DUEDATE, duedate);
		initialValues.put(KEY_ALARMTIME, time);
		initialValues.put(KEY_SURROGATES, surrogates);
		initialValues.put(KEY_QUANTITY, quantity);
		initialValues.put(KEY_ALARM, alarm);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	// ---deletes a particular record---
	public boolean deleteRecord(long rowId) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	// ---retrieves all the records---
	public Cursor getAllRecords() {
		return db.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_NAME,
				KEY_DUEDATE, KEY_ALARMTIME, KEY_SURROGATES, KEY_QUANTITY,
				KEY_ALARM }, null, null, null, null, null);
	}

	// ---finds a record by name
	public long findRecord(String name) throws SQLException {
		Cursor mCursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE
				+ " WHERE " + KEY_NAME + " = ?", new String[] { name });
		if (mCursor != null)
			mCursor.moveToFirst();
		if (mCursor.getCount() == 0)
			return -1;
		return Long.parseLong(mCursor.getString(0));
	}

	// ---retrieves a particular record---
	public Cursor getRecord(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_NAME, KEY_DUEDATE, KEY_ALARMTIME,
				KEY_SURROGATES, KEY_QUANTITY, KEY_ALARM }, KEY_ROWID + "="
				+ rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	// ---updates a record---
	public boolean updateRecord(long rowId, String name, String duedate,
			String time, String surrogates, long quantity, long alarm) {
		ContentValues args = new ContentValues();
		args.put(KEY_NAME, name);
		args.put(KEY_DUEDATE, duedate);
		args.put(KEY_ALARMTIME, time);
		args.put(KEY_SURROGATES, surrogates);
		args.put(KEY_QUANTITY, quantity);
		args.put(KEY_ALARM, alarm);
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}

	public boolean updateSurrogates(long rowId, String surrogates) {
		Cursor rCursor = getRecord(rowId);
		return updateRecord(rowId, rCursor.getString(1), rCursor.getString(2),
				rCursor.getString(3), surrogates,
				Long.parseLong(rCursor.getString(5)), Long.parseLong(rCursor.getString(6)));
	}
	
	public boolean switchOffAlarm(long rowId) {
		Cursor rCursor = getRecord(rowId);
		return updateRecord(rowId, rCursor.getString(1), rCursor.getString(2),
				"0", rCursor.getString(4),
				Long.parseLong(rCursor.getString(5)), 0);
	}
}
