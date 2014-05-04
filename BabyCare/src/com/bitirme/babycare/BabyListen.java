package com.bitirme.babycare;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.PrivateCredentialPermission;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class BabyListen extends Activity{
	
    private static final int POLL_INTERVAL = 300;
    

    /** running state **/

    private int mTickCount = 0;
    private int mHitCount=0;
    private boolean mActivated=false;
    private boolean isStop=true;
    private int level;
    private Button stopButton;
   
    SeekBar seek;
    ProgressBar noiseBar;
    TextView tresholdText;

    /** config state **/
  
    private int mPollDelay;
    double amp;
    
    

    private Handler mHandler = new Handler();

    /* References to view elements */
 

    /* data source */
    private SoundMeter mSensor;
    
    /* SMS remote control */


    private Runnable mSleepTask = new Runnable() {
            public void run() {
                    try {
						start();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
					}
            }
    };
    private Runnable mPollTask = new Runnable() {
            public void run() {
                    amp = mSensor.getAmplitude();
                    noiseBar.setProgress((int) amp);
					Log.i("1", String.valueOf(amp));//Log Ekranin gosterimi
                    
                    if (amp > Integer.parseInt(tresholdText.getText().toString())) //Tresholdu TextViewDan Aliyor
                    {
                            mHitCount++;
                            if (mHitCount > 5){
                            	try {
									help();
								} catch (IllegalStateException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}// Aktive oldugunda intent islemini yapiyor
                            	//return ;// Programi bitiyor optional
                            	
                            	
                            }
                    }

                    mTickCount++;
                    
                    
                    if (( mPollDelay > 0) && mTickCount > 100) {
                            
                    } else {
                            mHandler.postDelayed(mPollTask, POLL_INTERVAL);
                    }
            }
    };
     
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baby_listening);
		
		seek= (SeekBar)findViewById(R.id.seekBar_treshhold);
		tresholdText = (TextView) findViewById(R.id.txt_trashold_level);
		 noiseBar= (ProgressBar) findViewById(R.id.noiseBar);
		stopButton = (Button) findViewById(R.id.btn_stop_baby_listening);
		
		seek.setProgress(70);
		tresholdText.setText("70");
		 level= seek.getProgress();
		tresholdText.setText(Integer.toString(level));
		ChangeTresholdText(seek,tresholdText,noiseBar);
		mSensor = new SoundMeter();
		stopButton.setText("Dinlemeyi Baslat");
		stopButton.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {	
				if(isStop)
				{
					try {
						startListening();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
										
						try {
							stopListening();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					
				}
					
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
       
		
        Intent sentIntent = new Intent("in.wptrafficanalyzer.activity.status.sent");

        sentIntent.putExtra("number", number);
        sentIntent.putExtra("status", 0);

        Intent deliveredIntent = new Intent("in.wptrafficanalyzer.activity.status.delivered");
        deliveredIntent.putExtra("number", number);
        deliveredIntent.putExtra("status", 1);
        PendingIntent piSent = PendingIntent.getActivity(getBaseContext(), 0, sentIntent, PendingIntent.FLAG_ONE_SHOT);
        PendingIntent piDelivered = PendingIntent.getActivity(getBaseContext(), 0, deliveredIntent, PendingIntent.FLAG_ONE_SHOT);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, message, piSent, piDelivered);
    }
	@Override
    public void onResume() {
            super.onResume();
                    
    }

    @Override
    public void onStop() {
            super.onStop();
            try {
				stopListening();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
    }
	
	
	private void start() throws IllegalStateException, IOException 
	{
        mTickCount = 0;
        
        mHitCount = 0;
        mSensor.start();
        
        
        mHandler.postDelayed(mPollTask, POLL_INTERVAL);    
        Log.i("deneme", "ikinci");
	}

	private void stop() throws IllegalStateException, IOException {
        
        mHandler.removeCallbacks(mSleepTask);
        mHandler.removeCallbacks(mPollTask);
        mSensor.stop();
	}


	private void help() throws IllegalStateException, IOException
	{

		if(getIntent().getStringExtra("message") != null)
		{
			sendSMS(getIntent().getStringExtra("number"), getIntent().getStringExtra("message"));
			stopListening();
		
		}
		else
		{ 
			Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+getIntent().getStringExtra("number")));
			startActivity(intent);
			stopListening();
			        Timer mTimer= new Timer();
			        Log.i("deneme", "sonraki 15 sn sonra");
	        mTimer.schedule(new TimerTask() {			
				@Override
				public void run() {
					try {
						start();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}, 15000);
			
		}
		


	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		try {
			stopListening();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void stopListening() throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		stopButton.setText("Dinlemeyi Baslat");
		isStop=true;
		stop();
		
		noiseBar.setProgress(0);
		Toast.makeText(getApplicationContext(), "Program Dur aq", Toast.LENGTH_LONG).show();
	}
	private void startListening() throws IllegalStateException, IOException
	{
		//isStop=false;
		start();
		stopButton.setText("Dinlemeyi Durdur");
		
	}
}
