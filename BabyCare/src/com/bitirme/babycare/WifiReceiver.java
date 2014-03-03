package com.bitirme.babycare;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class WifiReceiver extends BroadcastReceiver {

	Parrent pr = new Parrent();
	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		
		pr.sb = new StringBuilder();
        pr.wifiList = pr.mainWifi.getScanResults();
        for(int i = 0; i < pr.wifiList.size(); i++){
        	pr.sb.append(new Integer(i+1).toString() + ".");
        	pr.sb.append((pr.wifiList.get(i)).toString());
        	pr.sb.append("\\n");
        }
        pr.mainText.setText(pr.sb);
		
	}

}
