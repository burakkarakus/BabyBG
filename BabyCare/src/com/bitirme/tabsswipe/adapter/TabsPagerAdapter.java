package com.bitirme.tabsswipe.adapter;

//import com.bitirme.tabsswipe.GamesFragment;
//import com.bitirme.tabsswipe.MoviesFragment;
//import com.bitirme.tabsswipe.TopRatedFragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.bitirme.tabsswipe.adapter.BebekFragment;
import com.bitirme.tabsswipe.adapter.EbeveynFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter{

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		
	}

	@Override
	public Fragment getItem(int index) {
	
		switch (index) {
        case 0:
            // Bebek Fragment Activity'si ekleniyor
            return new BebekFragment();
        case 1:
            // Ebeveyn Fragment Activity'si ekleniyor
            return new EbeveynFragment();
		}
		return null;
            
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//tab sayýsý kadar return etsin
		return 2;
	}

}
