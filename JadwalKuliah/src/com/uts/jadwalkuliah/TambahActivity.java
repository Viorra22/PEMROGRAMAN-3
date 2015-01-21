package com.uts.jadwalkuliah;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TambahActivity extends Activity {
	
	EditText txthari, txtmatkul, txtjam;
	JadwalSource db;
	Intent i;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_layout);
        
        Intent in = getIntent();
    	String hari = in.getStringExtra("hari");
        
    	db = new JadwalSource(this);
    	db.open();
        
        TextView viewH = (TextView) findViewById(R.id.viewHari);
        txthari = (EditText) findViewById(R.id.txtHari);
        txtmatkul = (EditText) findViewById(R.id.txtMatkul);
        txtjam = (EditText) findViewById(R.id.txtJam);
        
        viewH.setText(hari);
        txthari.setText(hari);
        txthari.setVisibility(View.GONE);
        
        Button Submit = (Button)findViewById(R.id.btnSubmit);
        Submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String hari   = txthari.getText().toString();
				String matkul = txtmatkul.getText().toString();
				String jam    = txtjam.getText().toString();
				
				String mk = db.getMatkul(matkul,hari);
				String time = db.getJam(jam,hari);
				
				if(matkul.equals("") || jam.equals("")) {
					Toast.makeText(getApplicationContext(), "Field Kosong", Toast.LENGTH_LONG).show();
                    return;
				} else {
					if(mk.equals(matkul)) {
						Toast.makeText(getApplicationContext(), "Jadwal Already Registered ", Toast.LENGTH_LONG).show();
					} else if(time.equals(jam)) {
						Toast.makeText(getApplicationContext(), "Jam Already Registered ", Toast.LENGTH_LONG).show();
					} else {
						db.create(hari,jam,matkul);
		                Toast.makeText(getApplicationContext(), "Jadwal Successfully Created ", Toast.LENGTH_LONG).show();
		                	
		                if (hari.equals("senin")) {
		                	 i = new Intent(TambahActivity.this, SeninActivity.class);
		                } else if (hari.equals("selasa")) {
		                	 i = new Intent(TambahActivity.this, SelasaActivity.class);
		                } else if (hari.equals("rabu")) {
		                	 i = new Intent(TambahActivity.this, RabuActivity.class);
		                } else if (hari.equals("kamis")) {
		                	 i = new Intent(TambahActivity.this, KamisActivity.class);
		                } else if (hari.equals("jumat")) {
		                	 i = new Intent(TambahActivity.this, JumatActivity.class);
		                }
						startActivity(i);
						finish();
					}
					
				}
				
			}
        	
        });
        
        Button cancel = (Button) findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(TambahActivity.this, SeninActivity.class);
            	startActivity(i);
            	finish();
			}
		});
	}
}
