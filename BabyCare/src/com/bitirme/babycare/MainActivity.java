/*
 * Created by 
 * Bekir Gokay Erim
 * Burak Karakus
 * 27 02 2014
 * */

package com.bitirme.babycare;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn_baby = (Button) findViewById(R.id.button_baby);
		Button btn_parrent = (Button) findViewById(R.id.button_parrent);
		
		btn_baby.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Baby.class);
				MainActivity.this.startActivity(intent);
				
			}
		});
		
		btn_parrent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Parrent.class);
				startActivity(intent);
				
			}
		});
		

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	

}
