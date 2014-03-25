package com.bitirme.babycare;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class BabyListen extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baby_listening);
		
		SeekBar seek= (SeekBar)findViewById(R.id.seekBar_treshhold);
		TextView tresholdText = (TextView) findViewById(R.id.txt_trashold_level);
		ProgressBar noiseBar= (ProgressBar) findViewById(R.id.noiseBar);
		
		int level= seek.getProgress();
		tresholdText.setText(Integer.toString(level));
		
		Random r= new Random();
		int a;
		while(true)//ProgressBarin Degeri Surekli Degisecek
		{
			a=r.nextInt(100);
			noiseBar.setProgress(a);
		}
	}

}
