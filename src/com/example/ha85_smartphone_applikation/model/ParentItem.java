package com.example.ha85_smartphone_applikation.model;


import java.util.ArrayList;
import java.util.List;
/**
 * Created by Anders Petersen
 */
public class ParentItem {

    private String header;

    private List<ChildItem> childItemList;

    public ParentItem(String headerText) {
        header = headerText;
        childItemList = new ArrayList<ChildItem>();
    }

    public List<ChildItem> getChildItemList() {
        return childItemList;
    }

    public String getHeader() {
        return header;
    }
}

