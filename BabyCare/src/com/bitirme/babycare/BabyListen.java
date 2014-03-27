package com.bitirme.babycare;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
		final Button stopButton = (Button) findViewById(R.id.btn_stop_baby_listening);
		
		seek.setProgress(70);
		tresholdText.setText("70");
		int level= seek.getProgress();
		tresholdText.setText(Integer.toString(level));
		ChangeTresholdText(seek,tresholdText,noiseBar);
		
		stopButton.setOnClickListener(new OnClickListener() {
			
			// btn_ Baby, parrent / doublephone/ sms body
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					
					if(getIntent().getStringExtra("message") != null)
					{
						sendSMS(getIntent().getStringExtra("number"), getIntent().getStringExtra("message"));
						
					}
					else
					{ 
						Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+getIntent().getStringExtra("number")));
						startActivity(intent);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//			
//				
			}
		});
		
		
		
	}
	private void ChangeTresholdText(SeekBar seek, final TextView tresholdText ,final ProgressBar noiseBar) {
		seek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
				tresholdText.setText(Integer.toString(progress));
				noiseBar.setProgress(progress); // Progress bar� da de�i�tirdim kontrol ama�l�
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	private void sendSMS(String number, String message) {
       
		 /** Creating an intent, corresponding to sent delivery report */
        /** This intent will call the activity SmsStatus */
        Intent sentIntent = new Intent("in.wptrafficanalyzer.activity.status.sent");

        /** Sms is sent to this number */
        sentIntent.putExtra("number", number);

        /** Setting status data on the intent */
        sentIntent.putExtra("status", 0);

        /** Creating an intent, corresponding to delivered delivery report */
        /** This intent will call the activity SmsStatus */
        Intent deliveredIntent = new Intent("in.wptrafficanalyzer.activity.status.delivered");

        /** Sms is sent to this number */
        deliveredIntent.putExtra("number", number);

        /** Setting status data on the intent */
        deliveredIntent.putExtra("status", 1);

        /** Creating a pending intent which will be invoked by SmsManager when an sms message is successfully sent */
        PendingIntent piSent = PendingIntent.getActivity(getBaseContext(), 0, sentIntent, PendingIntent.FLAG_ONE_SHOT);

        /** Creating a pending intent which will be invoked by SmsManager when an sms message is successfully delivered */
        PendingIntent piDelivered = PendingIntent.getActivity(getBaseContext(), 0, deliveredIntent, PendingIntent.FLAG_ONE_SHOT);

        /** Getting an instance of SmsManager to sent sms message from the application*/
        SmsManager smsManager = SmsManager.getDefault();

        /** Sending the Sms message to the intended party */
        smsManager.sendTextMessage(number, null, message, piSent, piDelivered);
    }
    

}
