package com.bitirme.babycare;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
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
    private boolean isStop=true;
    private boolean isCall= false;
    private boolean isExit=false;
    private int level;


    private	Button testButton;
   
    private Button stopButton;   
    private SeekBar seek;
    private ProgressBar noiseBar;
    private TextView tresholdText;
    private TextView startstopListening;


    /** config state **/
  
    private int mPollDelay;
    private boolean mTestingMode;//Test mode control
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
					Log.i("desibel", String.valueOf(amp));
                    
                    if (amp > Integer.parseInt(tresholdText.getText().toString())&&!mTestingMode) //Tresholdu TextViewDan Aliyor
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
								}
                            	
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
		testButton = (Button) findViewById(R.id.testButton);
		
		

		startstopListening = (TextView) findViewById(R.id.startstopListening);

		seek.setProgress(70);
		tresholdText.setText("70");
		 level= seek.getProgress();
		tresholdText.setText(Integer.toString(level));
		ChangeTresholdText(seek,tresholdText,noiseBar);
		mSensor = new SoundMeter();
		stopButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {	
				try {
					if(isStop)
					{
						startListening();
						if(!mTestingMode)//Test modu degil ise butonu gorunmez yap
							testButton.setVisibility(View.INVISIBLE);
						
					}
					else
					{		
						stopListening();
						if(!mTestingMode)
							testButton.setVisibility(View.VISIBLE);
						
					}
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
		});
		testButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				try {
					if(event.getAction()==MotionEvent.ACTION_DOWN)
					{
						startListening();
						mTestingMode=true;
					}
					else{
						stopListening();
						mTestingMode=false;
					}
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
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
       
		SmsManager smsMgr = SmsManager.getDefault();
		  smsMgr.sendTextMessage(number, null, message, null, null);
//
    }
	@Override
    public void onResume() {
            super.onResume();
                    
    }

    @Override
    public void onStop() {
            super.onStop();
            try {
            	Log.i("Gokay","Home Button");
            	isExit=true;
				stopListening();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
    }
    @Override
    protected void onRestart() {
    	// TODO Auto-generated method stub
    	super.onRestart();
    	isExit=false;
    }
	
	
	private void start() throws IllegalStateException, IOException 
	{
        mTickCount = 0;
        
        mHitCount = 0;
        
        mSensor.start();
        
        
        
        mHandler.postDelayed(mPollTask, POLL_INTERVAL);    
 
	}

	private void stop() throws IllegalStateException, IOException {
        
        mHandler.removeCallbacks(mSleepTask);
        mHandler.removeCallbacks(mPollTask);
        mSensor.stop();
	}


	private void help() throws IllegalStateException, IOException
	{
		Timer mTimer= new Timer();
		if(getIntent().getStringExtra("message") != null && !isCall)
		{
			sendSMS(getIntent().getStringExtra("number"), getIntent().getStringExtra("message"));
			stopListening();
			Log.i("Gokay", "Mesaj yollandi");
			isCall=true;
			mTimer.schedule(new TimerTask() {			
				@Override
				public void run() {
					try {
						if(!isExit){
						startListening();
						Log.i("Gokay", "Mesajdan 15 saniye sonra baslatildi");
						}
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
		else
		{ 
			Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+getIntent().getStringExtra("number")));
			startActivity(intent);
			stopListening();
			        
			        Log.i("Gokay", "Arama yapildi");
	        mTimer.schedule(new TimerTask() {			
				@Override
				public void run() {
					try {
						if(!isExit){
						Log.i("Gokay", "Aramadan 15 saniye sonra baslatildi");
						startListening();
						}
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}, 61000);
			
		}
		


	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
	    	Log.i("Gokay", "Back Button");
	    	try {
	    		isExit=true;
				stopListening();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	        finish();
	    }	else if ((keyCode == KeyEvent.KEYCODE_HOME))
    	{
	    	Log.i("Gokay", "home Button");
    	try {
    		isExit=true;
			stopListening();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
        finish();}
	    	
	    return super.onKeyDown(keyCode, event);
	}
	
	
	
	private void stopListening() throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		startstopListening.setText(R.string.start_listening);
		stopButton.setBackgroundResource(R.drawable.play1pressed);
		isStop=true;
		stop();
		
		noiseBar.setProgress(0);
		Toast.makeText(getApplicationContext(),
				getApplicationContext().getString(R.string.toastDinlemeDurdur),
				Toast.LENGTH_LONG).show();
	}
	
	private void startListening() throws IllegalStateException, IOException
	{
		isStop=false;
		
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				startstopListening.setText(R.string.stop_listening);
				stopButton.setBackgroundResource(R.drawable.button_blue_stop);
				Toast.makeText(getApplicationContext(),
						getApplicationContext().getString(R.string.toastDinlemeBasla),
						Toast.LENGTH_SHORT).show();
			}
		});		
		
		start();
		
		
	}
}
