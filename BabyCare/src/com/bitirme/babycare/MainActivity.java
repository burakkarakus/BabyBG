/*
 * Created by 
 * Bekir Gokay Erim
 * Burak Karakus
 * 27 02 2014
 * */

package com.bitirme.babycare;




import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

@TargetApi(Build.VERSION_CODES.HONEYCOMB) 
public class MainActivity extends Activity{ 

	private RadioGroup radioBaglantiTuruGrup;
	RadioButton radioCokTekButton;
	private Button btnSec;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anasayfa);
		
		
		radioBaglantiTuruGrup=(RadioGroup)findViewById(R.id.radioBaglantiTuru);
		btnSec = (Button) findViewById(R.id.btn_Sec);
		btnSec.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				int selectedId = radioBaglantiTuruGrup.getCheckedRadioButtonId();
				Intent intent = null;
				radioCokTekButton = (RadioButton) findViewById(selectedId);
				if(selectedId==R.id.radioCift){
					 intent = new Intent(MainActivity.this, BebekCiftCihaz.class);
				}
				else if(selectedId==R.id.radioTek){
					
					intent = new Intent(MainActivity.this, BebekTekCihaz.class);
				}
				startActivity(intent);
					
			}
		});
		
	}
	
//	public void addListenerOnButton() {
//		// TODO Auto-generated method stub
//		
//		radioBaglantiTuruGrup=(RadioGroup)findViewById(R.id.radioBaglantiTuru);
//		btnSec = (Button) findViewById(R.id.btn_Sec);
//		//txtview = (TextView) findViewById(R.id.txt1);
//		btnSec.setOnClickListener(new OnClickListener() {
//		
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//				int selectedId = radioBaglantiTuruGrup.getCheckedRadioButtonId();
//				Intent intent = null;
//				radioCokTekButton = (RadioButton) findViewById(selectedId);
//				if(selectedId==R.id.radioCift){
//					 intent = new Intent(MainActivity.this, BebekCiftCihaz.class);
//				}
//				else if(selectedId==R.id.radioTek){
//					
//				 intent = new Intent(MainActivity.this, BebekTekCihaz.class);
//				}
//				startActivity(intent);
//					
//			}
//		});
//		
//		
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
		/*Button btn_baby = (Button) findViewById(R.layout.fragment_bebek);
		Button btn_parrent = (Button) findViewById(R.layout.fragment_ebeveyn);
		
		btn_baby.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// 
				Intent intent = new Intent(MainActivity.this, client.class);
				MainActivity.this.startActivity(intent);
				
			}
		});
		*/
		
		/*btn_parrent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(MainActivity.this, server.class);
				startActivity(intent);
				
			}
		});*/
		

		
		
	

	
	
	
	

}
