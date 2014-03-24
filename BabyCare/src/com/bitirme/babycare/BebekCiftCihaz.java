package com.bitirme.babycare;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
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
		randomPassword=r.nextInt(8999)+1000;						//1000 <-> 9999
		otp.setText(randomPassword.toString());
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
		
		
		
	}
}
