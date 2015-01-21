package com.uts.jadwalkuliah;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        
        Button senin = (Button) findViewById(R.id.buttonMerah);
        senin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
		        Intent i = new Intent(HomeActivity.this, SeninActivity.class);
            	startActivity(i);
            	finish();
			}
		});
        
        Button selasa = (Button) findViewById(R.id.buttonBiru);
        selasa.setOnClickListener(new View.OnClickListener() {
			
        	@Override
			public void onClick(View arg0) {
		        Intent i = new Intent(HomeActivity.this, SelasaActivity.class);
            	startActivity(i);
            	finish();
			}
		});
        
        Button rabu = (Button) findViewById(R.id.buttonUngu);
        rabu.setOnClickListener(new View.OnClickListener() {
			
        	@Override
			public void onClick(View arg0) {
		        Intent i = new Intent(HomeActivity.this, RabuActivity.class);
            	startActivity(i);
            	finish();
			}
		});
        
        Button kamis = (Button) findViewById(R.id.buttonHijau);
        kamis.setOnClickListener(new View.OnClickListener() {
			
        	@Override
			public void onClick(View arg0) {
		        Intent i = new Intent(HomeActivity.this, KamisActivity.class);
            	startActivity(i);
            	finish();
			}
		});
        
        Button jumat = (Button) findViewById(R.id.buttonOranye);
        jumat.setOnClickListener(new View.OnClickListener() {
			
        	@Override
			public void onClick(View arg0) {
		        Intent i = new Intent(HomeActivity.this, JumatActivity.class);
            	startActivity(i);
            	finish();
			}
		});
        
    }
    
}
