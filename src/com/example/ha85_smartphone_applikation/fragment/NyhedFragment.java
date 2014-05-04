package com.example.ha85_smartphone_applikation.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import com.example.ha85_smartphone_applikation.R;
import com.example.ha85_smartphone_applikation.html.NyhedsFile;

import java.util.ArrayList;

/**
 * Created by Anders Petersen
 */
public class NyhedFragment extends Fragment {

    private TableLayout tableRow1;
    private LayoutInflater mainInflater;
    private ArrayList<NyhedsFile> nyhedsFiles;

    public NyhedFragment(ArrayList<NyhedsFile> nyhed){
        nyhedsFiles = nyhed;
    }
    /**
     * @author Anders Petersen
     */
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mainInflater = inflater;
        View rootView = inflater.inflate(R.layout.fragment_nyhed, container, false);
        if (rootView != null) {
            tableRow1 = (TableLayout) rootView.findViewById(R.id.tableRowlayout);
        }
        printProducts(nyhedsFiles);

        return rootView;
    }
    /**
     * @author Anders Petersen
     */
    private void printProducts(ArrayList<NyhedsFile> products)
    {
        for(NyhedsFile nyhed: products) {
            View newChild = mainInflater.inflate(R.layout.child_list_nyhed, null);
            TextView header = null;
            if (newChild != null) {
                header = (TextView) newChild.findViewById(R.id.textviewfirstline);
            }
            if (header != null) {
                header.setText(nyhed.getLinkHeader()) ;
            }
            if (newChild != null) {
                tableRow1.addView(newChild);
            }
        }
    }

}
