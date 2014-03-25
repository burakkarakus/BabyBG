package com.bitirme.babycare;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;


public class BebekCiftCihaz extends Activity{
	Integer randomPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bebek_cift);
		Random r= new Random();
		TextView otp =(TextView)findViewById(R.id.OTP_connection);
		Button btn = (Button) findViewById(R.id.btn_baslat_cift_telsiz);
		
		randomPassword=r.nextInt(8999)+1000;						//1000 <-> 9999
		otp.setText(randomPassword.toString());
		
		
		
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				Intent intent =new Intent(BebekCiftCihaz.this, BabyListen.class);
				startActivity(intent);
			}
		});
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
		
		
		
	}
}
