package com.bitirme.tabsswipe.adapter;

import com.bitirme.babycare.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EbeveynFragment extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_ebeveyn, container, false); 
        /** 
         Tabs adapter'dan buraya yönlendiðinde custom bi ebeveyn Viewý oluþturup onu gösteriyo. 
         * */
         
        return rootView;
    }

}
