package com.uts.jadwalkuliah;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        
        Button btn = (Button) findViewById(R.id.btnCancel);
        btn.setOnClickListener(new View.OnClickListener() {
        	EditText username = (EditText) findViewById(R.id.editText2);
            EditText password = (EditText) findViewById(R.id.txtMatkul);
        	
			@Override
			public void onClick(View arg0) {
				if(username.getText().toString().equals("") && password.getText().toString().equals("")) {
					Intent intent = new Intent(MainActivity.this, HomeActivity.class);
					startActivity(intent);
				} else if(username.getText().toString().equals("natania") && password.getText().toString().equals("natania")) {
					Intent intent = new Intent(MainActivity.this, HomeActivity.class);
					startActivity(intent);
				}
			}
		});
    }
    
}
