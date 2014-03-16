/*
 * Created by 
 * Bekir Gokay Erim
 * Burak Karakus
 * 27 02 2014
 * */

package com.bitirme.babycare;


import com.bitirme.tabsswipe.adapter.TabsPagerAdapter;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar.Tab;
//import android.app.Activity; kullanmadýk
import android.app.FragmentTransaction;
//import android.content.Intent; kullanmadýk
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.ActionBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.HONEYCOMB) // API 11 muhabbeti için eklendi
public class MainActivity extends FragmentActivity implements ActionBar.TabListener{ //FragmentActivity geldi Activity yerine

	private RadioGroup radioCokTekGrup;
	private RadioButton radioCokTekButton;
	private Button btnSec;
	private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Bebek", "Ebeveyn"}; // tab isimleri burdan ekleniyo forla veriliyo isim aþaðýda
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anasayfa);
		
		
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		
		viewPager.setAdapter(mAdapter);
		//actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		addListenerOnButton();
		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
	}
	
	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		
		radioCokTekGrup =(RadioGroup)findViewById(R.id.radioBaglantiTuru);
		btnSec = (Button) findViewById(R.id.btn_Sec);
		
		btnSec.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				int selectedId = radioCokTekGrup.getCheckedRadioButtonId();
				
				radioCokTekButton = (RadioButton) findViewById(selectedId);
				
				Toast.makeText(MainActivity.this, radioCokTekButton.getText(), Toast.LENGTH_SHORT).show();
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		
				// on tab selected
				// show respected fragment view
				viewPager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
		
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
