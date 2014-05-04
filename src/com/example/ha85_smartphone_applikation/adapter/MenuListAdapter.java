package com.example.ha85_smartphone_applikation.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ha85_smartphone_applikation.R;
import com.example.ha85_smartphone_applikation.model.MenuItem;

import java.util.ArrayList;
/**
 * Created by Anders Petersen
 */
public class MenuListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<MenuItem> navMenuItems;
	
	public MenuListAdapter(Context context, ArrayList<MenuItem> navMenuItems){
		this.context = context;
		this.navMenuItems = navMenuItems;
	}

	@Override
	public int getCount() {
		return navMenuItems.size();
	}

	@Override
	public Object getItem(int position) {		
		return navMenuItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        ImageView imgIcon = (ImageView) (convertView != null ? convertView.findViewById(R.id.icon) : null);
        TextView txtTitle = (TextView) (convertView != null ? convertView.findViewById(R.id.title) : null);
        TextView txtCount = (TextView) (convertView != null ? convertView.findViewById(R.id.counter) : null);

        if (imgIcon != null) {
            imgIcon.setImageResource(navMenuItems.get(position).getIcon());
        }
        if (txtTitle != null) {
            txtTitle.setText(navMenuItems.get(position).getTitle());
        }

        if(navMenuItems.get(position).getCounterVisibility()){
            if (txtCount != null) {
                txtCount.setText(navMenuItems.get(position).getCount());
            }
        }else{
            if (txtCount != null) {
                txtCount.setVisibility(View.GONE);
            }
        }
        
        return convertView;
	}

}
