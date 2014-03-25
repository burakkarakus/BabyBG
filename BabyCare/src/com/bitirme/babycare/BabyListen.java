package com.bitirme.babycare;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class BabyListen extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baby_listening);
		
		final SeekBar seek= (SeekBar)findViewById(R.id.seekBar_treshhold);
		final TextView tresholdText = (TextView) findViewById(R.id.txt_trashold_level);
		final ProgressBar noiseBar= (ProgressBar) findViewById(R.id.noiseBar);
		seek.setProgress(70);//70 Defualt
		tresholdText.setText("70");//Defualt
		int level= seek.getProgress();
		tresholdText.setText(Integer.toString(level));
		seek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
				tresholdText.setText(Integer.toString(progress));
				noiseBar.setProgress(progress); // Progress bar� da de�i�tirdim kontrol ama�l�
			}
		});
		
	}

}
