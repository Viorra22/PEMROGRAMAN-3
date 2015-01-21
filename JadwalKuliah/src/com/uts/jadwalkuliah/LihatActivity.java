package com.uts.jadwalkuliah;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;

public class LihatActivity extends Activity {
	
	JadwalSource sqlcon;
	TextView tv;
	TableLayout table_layout;
	EditText firstname_et;
	Button addmem_btn;
	
	ProgressDialog PD;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.table_layout2);
	    
	    sqlcon = new JadwalSource(this);
	    
	    firstname_et = (EditText) findViewById(R.id.fistname_et_id);
		addmem_btn = (Button) findViewById(R.id.addmem_btn_id);

		table_layout = (TableLayout) findViewById(R.id.tableLayout1);
	    		
		BuildTable();
		
		addmem_btn.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(firstname_et.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(),"You must select row..",Toast.LENGTH_LONG).show();
				} else {
					new MyAsync().execute();
				}
			}

		});
	}
	
	private void BuildTable() {
		Intent in = getIntent();
		String hari = in.getStringExtra("hari");
		sqlcon.open();
		Cursor c = sqlcon.readEntry(hari);

		int rows = c.getCount();
		int cols = c.getColumnCount();

		c.moveToFirst();
		// outer for loop
		for (int i = 0; i < rows; i++) {

			TableRow row = new TableRow(this);
			row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
			LayoutParams.WRAP_CONTENT));

			// inner for loop
			for (int j = 0; j < cols; j++) {

				tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
				//tv.setBackgroundResource(R.drawable.cell_shape);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setPadding(0, 5, 0, 5);
				tv.setText(c.getString(j));

				row.addView(tv);
				
				row.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						TableRow tr1=(TableRow)v;
						TextView tv1= (TextView)tr1.getChildAt(0);
						String a = tv1.getText().toString();
						//tr1.setBackgroundColor(Color.BLACK);
						firstname_et.setText(a);
						Toast.makeText(getApplicationContext(),tv1.getText().toString()+" Selected",Toast.LENGTH_LONG).show();
					}

					
				});

			}

			c.moveToNext();

			table_layout.addView(row);

		}
		sqlcon.close();
	}
	
	private class MyAsync extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {

			super.onPreExecute();

			table_layout.removeAllViews();

			PD = new ProgressDialog(LihatActivity.this);
			PD.setTitle("Please Wait..");
			PD.setMessage("Loading...");
			PD.setCancelable(false);
			PD.show();
		}

		@Override
		protected Void doInBackground(Void... params) {

			String firstname = firstname_et.getText().toString();

			// inserting data
			sqlcon.open();
			sqlcon.deleteData(firstname);
			// BuildTable();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			BuildTable();
			PD.dismiss();
		}
	}
}
