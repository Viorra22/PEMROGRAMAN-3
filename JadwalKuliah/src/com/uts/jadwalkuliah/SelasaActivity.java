package com.uts.jadwalkuliah;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class SelasaActivity extends Activity {
	JadwalSource sqlcon;
	TextView tv;
	TableLayout table_layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.selasa_layout);
	    
	    sqlcon = new JadwalSource(this);

		table_layout = (TableLayout) findViewById(R.id.tableLayout1);
	    		
		BuildTable();
		
	    Button back = (Button) findViewById(R.id.btnCancel);
        back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(SelasaActivity.this, HomeActivity.class);
            	startActivity(i);
            	finish();
			}
		});
	}
	
	private void BuildTable() {

		sqlcon.open();
		Cursor c = sqlcon.readJadwal("selasa");

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

			}

			c.moveToNext();

			table_layout.addView(row);

		}
		sqlcon.close();
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    		case R.id.action_tambah:
    			Intent i = new Intent(SelasaActivity.this, TambahActivity.class);
    			i.putExtra("hari", "selasa");
            	startActivity(i);
            	finish();
    			//Toast.makeText(HomeActivity.this, "List Rumah Selected", Toast.LENGTH_SHORT).show();
                return true;
                
    		case R.id.action_lihat:
    			Intent i2 = new Intent(SelasaActivity.this, LihatActivity.class);
    			i2.putExtra("hari", "selasa");
            	startActivity(i2);
            	finish();
    			//Toast.makeText(HomeActivity.this, "List Rumah Selected", Toast.LENGTH_SHORT).show();
                return true;
                
    		default:
                return super.onOptionsItemSelected(item);
            
    	}
    }
}
