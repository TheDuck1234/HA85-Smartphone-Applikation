package com.example.ha85_smartphone_applikation.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ha85_smartphone_applikation.R;
/**
 * Created by Anders Petersen
 */
public class SportFragment extends Fragment {
	
	public SportFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_sport, container, false);
    }
}
