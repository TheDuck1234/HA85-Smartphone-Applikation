package com.example.ha85_smartphone_applikation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.example.ha85_smartphone_applikation.R;
import com.example.ha85_smartphone_applikation.model.ChildItem;
import com.example.ha85_smartphone_applikation.model.ParentItem;

import java.util.List;

/**
 * Created by Anders Petersen
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private static final class ViewHolder {
        TextView textLabel;
    }

    private final List<ParentItem> itemList;
    private final LayoutInflater inflater;

    public ExpandableListViewAdapter(Context context, List<ParentItem> itemList) {
        this.inflater = LayoutInflater.from(context);
        this.itemList = itemList;
    }

    @Override
    public ChildItem getChild(int groupPosition, int childPosition) {

        return itemList.get(groupPosition).getChildItemList().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemList.get(groupPosition).getChildItemList().size();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             final ViewGroup parent) {
        View resultView = convertView;
        ViewHolder holder;


        if (resultView == null) {

            resultView = inflater.inflate(R.layout.child_row_info, null);
            holder = new ViewHolder();
            if (resultView != null) {
                holder.textLabel = (TextView) resultView.findViewById(R.id.grp_child);
            }
            if (resultView != null) {
                resultView.setTag(holder);
            }
        } else {
            holder = (ViewHolder) resultView.getTag();
        }

        final ChildItem item = getChild(groupPosition, childPosition);

        if (item != null) {
            holder.textLabel.setText(item.getText());
        }

        return resultView;
    }

    @Override
    public ParentItem getGroup(int groupPosition) {
        return itemList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return itemList.size();
    }

    @Override
    public long getGroupId(final int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View theConvertView, ViewGroup parent) {
        View resultView = theConvertView;
        ViewHolder holder;

        if (resultView == null) {
            resultView = inflater.inflate(R.layout.group_row_info, null);
            holder = new ViewHolder();
            if (resultView != null) {
                holder.textLabel = (TextView) resultView.findViewById(R.id.row_name);
            }
            if (resultView != null) {
                resultView.setTag(holder);
            }
        } else {
            holder = (ViewHolder) resultView.getTag();
        }

        final ParentItem item = getGroup(groupPosition);

        if (item != null) {
            holder.textLabel.setText(item.getHeader());
        }

        return resultView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
