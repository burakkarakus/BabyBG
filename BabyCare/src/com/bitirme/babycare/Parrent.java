package com.bitirme.babycare;

import java.util.List;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Parrent extends Activity {
	
	TextView mainText;
    WifiManager mainWifi;
    WifiReceiver receiverWifi;
    List<ScanResult> wifiList;
    StringBuilder sb = new StringBuilder();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parrent_make_connection);
		Button make_connection = (Button) findViewById(R.id.button_make_connection);
		
		make_connection.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Client olarak server arayacak 
				//ARA TCP Client Server Code
				Log.i("brk","onClick");
				mainText = (TextView) findViewById(R.id.mainText);
			       mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
			       receiverWifi = new WifiReceiver();
			       registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
			       mainWifi.startScan();
			       mainText.setText("\\nStarting Scan...\\n");
				
			}
		});
		
		
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.i("brk","onRefresh");
        menu.add(0, 0, 0, "Refresh");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	Log.i("brk","onMenuItemSelected");
        mainWifi.startScan();
        mainText.setText("Starting Scan");
        return super.onMenuItemSelected(featureId, item);
    }

    protected void onPause() {
    	Log.i("brk","onPause");
        unregisterReceiver(receiverWifi);
        super.onPause();
    }

    protected void onResume() {
    	Log.i("brk","onResume");
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }
	
	

}
