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
import com.example.ha85_smartphone_applikation.xml.XmlInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anders Petersen
 */
public class InfoSelectFragment extends Fragment {

    private ArrayList<XmlInfo> xmlInfos;

    public InfoSelectFragment(ArrayList<XmlInfo> xml){
        xmlInfos = xml;

    }
    /**
     * @author Anders Petersen
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_info_select, container, false);
        ExpandableListView expandableListView = null;
        if (rootView != null) {
            expandableListView = (ExpandableListView) rootView.findViewById(R.id.androidlist);
        }
        List<ParentItem> itemList = new ArrayList<ParentItem>();

        for(XmlInfo info: xmlInfos){
            ParentItem newParent = new ParentItem(info.getHold());
            newParent.getChildItemList().add(new ChildItem("Navn: "+"\n"+ info.getNavn()));
            newParent.getChildItemList().add(new ChildItem("Telefon: "+"\n" + info.getTelefon()));
            newParent.getChildItemList().add(new ChildItem("Email: "+"\n" + info.getEmail()));

            itemList.add(newParent);
        }

        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(getActivity(), itemList);
        if (expandableListView != null) {
            expandableListView.setAdapter(adapter);
        }
        return rootView;
    }

}