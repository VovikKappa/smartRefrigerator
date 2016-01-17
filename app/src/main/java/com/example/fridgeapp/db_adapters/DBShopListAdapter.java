package com.example.fridgeapp.db_adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBShopListAdapter {
	public static final String KEY_ROWID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_RATING = "rating";
	private static final String TAG = "DBAdapter";

	private static final String DATABASE_NAME = "ShopListDB";
	private static final String DATABASE_TABLE = "shoplist";
	private static final int DATABASE_VERSION = 2;

	private static final String DATABASE_CREATE = "create table if not exists shoplist (id integer primary key autoincrement, "
			+ "name VARCHAR not null, rating integer);";

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBShopListAdapter(Context ctx) {
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
	public DBShopListAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		DBHelper.close();
	}

	// ---insert a record into the database---
	public long createRecord(String name, long rating) {
		if (findRecord(name)!=-1) return -1;
		return insertRecord(name, rating);
	}
	private long insertRecord(String name, long rating) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_RATING, rating);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	// ---deletes a particular record---
	public boolean deleteRecord(long rowId) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	// ---retrieves all the records---
	public Cursor getAllRecords() {
		return db.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_NAME,
				KEY_RATING }, null, null, null, null, null);
	}

	// ---finds a record by name
	public long findRecord(String name) throws SQLException {
		Cursor mCursor = db.rawQuery("SELECT * FROM "+DATABASE_TABLE+" WHERE "+KEY_NAME+" = ?", new String[] {name});
		if (mCursor != null) mCursor.moveToFirst();
		if (mCursor.getCount() == 0) return -1;
		return Long.parseLong(mCursor.getString(0));
	}

	// ---retrieves a particular record---
	public Cursor getRecord(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_NAME, KEY_RATING }, KEY_ROWID + "=" + rowId,
				null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	// ---updates a record---
	public boolean updateRecord(long rowId, String name, long rating) {
		ContentValues args = new ContentValues();
		args.put(KEY_NAME, name);
		args.put(KEY_RATING, rating);
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}
}
