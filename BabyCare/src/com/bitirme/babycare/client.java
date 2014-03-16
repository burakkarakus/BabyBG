package com.bitirme.babycare;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class client extends Activity {

	private Socket socket;

	private static final int SERVERPORT = 5000;
	private static String SERVER_IP1 = "10.0.2.";
	private static int SERVER_IP2;
	private static String SERVER_IP;
	
	

	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client);		

		new Thread(new ClientThread()).start();
	}

	public void onClick(View view) {
		try {
			EditText et = (EditText) findViewById(R.id.editText_sendClient);
			String str = et.getText().toString();
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())),
					true);
			out.println(str);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class ClientThread implements Runnable {

		@Override
		public void run() {
		//TODO EMÝN DEÐÝLÝM
			
			try {
				InetAddress serverAddr = null;
				while(!serverAddr.isReachable(10000)){ // bu kýsýmdan tam emin deðilim
				for(int i=1;i<=255;i++){ 
					
				SERVER_IP2=i;
				SERVER_IP=SERVER_IP1+String.valueOf(SERVER_IP2);
				InetAddress.getByName(SERVER_IP);
					
				if(serverAddr.isReachable(10000)){
					
					Toast.makeText(getApplicationContext(),
							"Connected to "+serverAddr,
							Toast.LENGTH_SHORT);
					
					
				}
				else{
					Toast.makeText(getApplicationContext(),
							"Failed to connect "+serverAddr,
							Toast.LENGTH_LONG);
				}
					
				}
				}
			
				socket = new Socket(serverAddr, SERVERPORT);

			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}
}