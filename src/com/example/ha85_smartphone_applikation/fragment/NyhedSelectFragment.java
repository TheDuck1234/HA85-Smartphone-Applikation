package com.example.ha85_smartphone_applikation.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ha85_smartphone_applikation.R;
import com.example.ha85_smartphone_applikation.html.NyhedsFile;

/**
 * Created by Anders Petersen
 */
public class NyhedSelectFragment extends Fragment {

    private View rootView;
    private NyhedsFile nyhed;

    public NyhedSelectFragment(NyhedsFile nyhedsFile){
        nyhed = nyhedsFile;
    }
    /**
     * @author Anders Petersen
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_nyhed_select, container, false);
        TextView siteTextView = null;
        if (rootView != null) {
            siteTextView = (TextView) rootView.findViewById(R.id.nyhedSiteText);
        }
        TextView siteHeaderView = null;
        if (rootView != null) {
            siteHeaderView = (TextView) rootView.findViewById(R.id.nyhedSiteHeader);
        }
        if (siteHeaderView != null) {
            siteHeaderView.setText(nyhed.getDateHeader());
        }
        if (siteTextView != null) {
            siteTextView.setText(nyhed.getText());
        }
        return rootView;
    }
}