package com.example.ha85_smartphone_applikation.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.example.ha85_smartphone_applikation.R;
import com.example.ha85_smartphone_applikation.adapter.ExpandableListViewAdapter;
import com.example.ha85_smartphone_applikation.model.ChildItem;
import com.example.ha85_smartphone_applikation.model.ParentItem;
import com.example.ha85_smartphone_applikation.xml.XmlSport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anders
 */
public class SportSelectFragment extends Fragment {

    private ArrayList<XmlSport> xmlSports;

    public SportSelectFragment(ArrayList<XmlSport> xml){
        xmlSports = xml;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sport_select, container, false);
        ExpandableListView expandableListView = null;
        if (rootView != null) {
            expandableListView = (ExpandableListView) rootView.findViewById(R.id.androidlist);
        }
        List<ParentItem> itemList = new ArrayList<ParentItem>();

        for(XmlSport sport: xmlSports){
            ParentItem newParent = new ParentItem(sport.getNavn());
            if(!sport.gettStart().equals("null")){
                newParent.getChildItemList().add(new ChildItem("Start: "+"\n"+ sport.gettStart()));
            }
            newParent.getChildItemList().add(new ChildItem("Dag: "+"\n" + sport.gettDag()));
            newParent.getChildItemList().add(new ChildItem("Sted: "+"\n" + sport.gettSted()));
            newParent.getChildItemList().add(new ChildItem("Tid: "+"\n" + sport.gettTid()));

            itemList.add(newParent);
        }

        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(getActivity(), itemList);
        if (expandableListView != null) {
            expandableListView.setAdapter(adapter);
        }
        return rootView;
    }
}