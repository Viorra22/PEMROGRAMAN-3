package com.uts.jadwalkuliah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class JadwalSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { 
									dbHelper.COLUMN_ID,
									dbHelper.COLUMN_HARI,
									dbHelper.COLUMN_JAM,
									dbHelper.COLUMN_MATKUL
								  };
	
	public JadwalSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public void deleteData(String id) {
		database.delete(dbHelper.TABLE_JADWAL, dbHelper.COLUMN_ID+"=?", new String[]{id}); 
	}
	
	public Jadwal create(String hari, String jam, String matkul) {
		ContentValues value = new ContentValues();
		value.put(dbHelper.COLUMN_HARI, hari);
		value.put(dbHelper.COLUMN_JAM, jam);
		value.put(dbHelper.COLUMN_MATKUL, matkul);
		
		long insertId = database.insert(dbHelper.TABLE_JADWAL, null, value);
		Cursor cursor = database.query(dbHelper.TABLE_JADWAL,
		allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
		null, null, null);
		cursor.moveToFirst();
		Jadwal newJadwal = cursorToString(cursor);
		cursor.close();
		return newJadwal;
	}
	
	private Jadwal cursorToString(Cursor cursor) {
		Jadwal jadwal = new Jadwal();
		jadwal.setHari(cursor.getString(0));
		jadwal.setJam(cursor.getString(1));
		jadwal.setMatkul(cursor.getString(2));
	    return jadwal;
	}
	
	public Cursor readJadwal(String mk) {
		String[] allColumns = new String[] {
				dbHelper.COLUMN_JAM,
				dbHelper.COLUMN_MATKUL
		};
		
		Cursor c = database.query(dbHelper.TABLE_JADWAL, allColumns, dbHelper.COLUMN_HARI+"=?", new String[]{mk}, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public Cursor readEntry(String mk) {
		String[] allColumns = new String[] {
				dbHelper.COLUMN_ID,
				dbHelper.COLUMN_JAM,
				dbHelper.COLUMN_MATKUL
		};
		
		Cursor c = database.query(dbHelper.TABLE_JADWAL, allColumns, dbHelper.COLUMN_HARI+"=?", new String[]{mk}, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public String getMatkul(String mk, String hari) {
		Cursor cursor=database.query(dbHelper.TABLE_JADWAL, null, dbHelper.COLUMN_MATKUL+"=? AND "+dbHelper.COLUMN_HARI+"=?", new String[]{mk,hari}, null,
				null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String matkul= cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_MATKUL));
        cursor.close();
        return matkul;                
    }
	
	public String getJam(String jam, String hari) {
		Cursor cursor=database.query(dbHelper.TABLE_JADWAL, null, dbHelper.COLUMN_JAM+"=? AND "+dbHelper.COLUMN_HARI+"=?" , new String[]{jam,hari}, null,
				null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String matkul= cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_JAM));
        cursor.close();
        return matkul;                
    }
	
	public Cursor getData(String hari) {
		Cursor c = database.query(dbHelper.TABLE_JADWAL, null, dbHelper.COLUMN_HARI+"=?", new String[]{hari}, null,
				null, null, null);

        if (c != null) {
        	c.moveToFirst();
		}
        //String user= cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_USER));
        //cursor.close();
        return c;                
    }
}
