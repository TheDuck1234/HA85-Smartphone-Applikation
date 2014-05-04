package com.example.ha85_smartphone_applikation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.ha85_smartphone_applikation.adapter.MenuListAdapter;
import com.example.ha85_smartphone_applikation.fragment.*;
import com.example.ha85_smartphone_applikation.html.HA85Loader;
import com.example.ha85_smartphone_applikation.html.NyhedsFile;
import com.example.ha85_smartphone_applikation.model.MenuItem;
import com.example.ha85_smartphone_applikation.xml.ParserXml;
import com.example.ha85_smartphone_applikation.xml.XmlInfo;
import com.example.ha85_smartphone_applikation.xml.XmlSport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Anders Petersen
 */
public class MainActivity extends Activity {
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private ActionBarDrawerToggle drawerToggle;
    private ParserXml parserXml = new ParserXml();
	private CharSequence drawerTitle;
	private CharSequence title;
    private ProgressDialog progressDialog;
    private ArrayList<NyhedsFile> nyhedsFiles = new ArrayList<NyhedsFile>();


	private String[] navMenu;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        if(isNetworkConnected()) {
            new NyhedLoader().execute();
        }

		title = drawerTitle = getTitle();

		navMenu = getResources().getStringArray(R.array.nav_drawer_items);

		//icons from resources
        TypedArray navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerList = (ListView) findViewById(R.id.list_slidermenu);

        ArrayList<MenuItem> navMenuItems = new ArrayList<MenuItem>();

		
		// Home And First
        assert navMenuIcons != null;
        navMenuItems.add(new MenuItem(navMenu[0], navMenuIcons.getResourceId(0, -1)));

		navMenuItems.add(new MenuItem(navMenu[1], navMenuIcons.getResourceId(1, -1)));
		
		navMenuItems.add(new MenuItem(navMenu[2], navMenuIcons.getResourceId(2, -1)));
		
		navMenuItems.add(new MenuItem(navMenu[3], navMenuIcons.getResourceId(3, -1)));
		

		//array
		navMenuIcons.recycle();

		drawerList.setOnItemClickListener(new SlideMenuClickListener());

		// list adapter
        MenuListAdapter adapter = new MenuListAdapter(getApplicationContext(),
                navMenuItems);
		drawerList.setAdapter(adapter);

		// toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, //toggle icon
				R.string.app_name, // open
				R.string.app_name // close 
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(title);
				
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(drawerTitle);
				
				invalidateOptionsMenu();
			}
		};
		drawerLayout.setDrawerListener(drawerToggle);
	}

    public void setNyhedsFiles(ArrayList<NyhedsFile> nyhedsFiles) {
        this.nyhedsFiles = nyhedsFiles;
    }

    /**
     * @author Anders Petersen, Martin Juhl
     */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		
		boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

    /**
     * @author Anders Petersen, Martin Juhl
     */
    public void sportClick(View view){
        InputStream stream = null;
        try {
            stream = getAssets().open("Sport.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<XmlSport> sports = null;
        String title = null;
        switch(view.getId()){
            case R.id.fodboldtButton:
                sports = new ArrayList<XmlSport>((java.util.Collection<? extends XmlSport>) parserXml.XmlFactory(stream, "sport", "fodboldt"));
                title = "Fodboldt Kontakter";
                break;
            case R.id.haendboldtButton:
                sports = new ArrayList<XmlSport>((java.util.Collection<? extends XmlSport>) parserXml.XmlFactory(stream, "sport", "haendboldt"));
                title = "Håndboldt Kontakter";
                break;
            case R.id.badmintonButton:
                sports = new ArrayList<XmlSport>((java.util.Collection<? extends XmlSport>) parserXml.XmlFactory(stream, "sport", "badminton"));
                title = "Badminton Kontakter";
                break;
            case R.id.loebeButton:
                sports = new ArrayList<XmlSport>((java.util.Collection<? extends XmlSport>) parserXml.XmlFactory(stream, "sport", "loebe"));
                title = "Løbe Kontakter";
                break;
        }
        Fragment fragment = new SportSelectFragment(sports);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment).commit();
        setTitle(title);

    }

    public void sponsorClick(View view){
        String url = "http://www.ha85.dk/";
        switch(view.getId()) {
            case R.id.remaImageView:
                url = "http://rema1000.dk/om-rema-1000/abningstider/";
                break;
            case R.id.jackImageView:
                url = "http://www.jack-wolfskin.com/Home.aspx";
                break;
            case R.id.rygImageView:
                url = "http://www.silkeborgrygcenter.dk/";
                break;
            case R.id.windImageView:
                url = "http://www.windestate.com/forside.aspx";
                break;
            case R.id.winterImageView:
                url = "http://www.winthergruppen.dk/";
                break;
            case R.id.fundImageView:
                url = "http://www.fundraiseren.dk/";
                break;
            case R.id.synImageView:
                url = "http://www.nytsyn.dk/Silkeborg?gclid=CPuUvPHHoLoCFTR7cAodHjYATw#destination-0";
                break;
            case R.id.elstockImageView:
                url = "http://www.elstock.net/";
                break;
            case R.id.bogtrykImageView:
                url = "http://www.sb-s.dk/";
                break;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
    /**
     * @author Anders Petersen
     */
    public void infoClick(View view){
        InputStream stream = null;
        try {
            stream = getAssets().open("Info.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<XmlInfo> infos = null;
        String title = null;
        switch(view.getId()){
            case R.id.fodboldtButton:
                infos = new ArrayList<XmlInfo>((java.util.Collection<? extends XmlInfo>) parserXml.XmlFactory(stream, "info", "fodboldt"));
                title = "Fodboldt Kontakter";
                break;
            case R.id.haendboldtButton:
                infos = new ArrayList<XmlInfo>((java.util.Collection<? extends XmlInfo>) parserXml.XmlFactory(stream, "info", "haendboldt"));
                title = "Håndboldt Kontakter";
                break;
            case R.id.badmintonButton:
                infos = new ArrayList<XmlInfo>((java.util.Collection<? extends XmlInfo>) parserXml.XmlFactory(stream, "info", "badminton"));
                title = "Badminton Kontakter";
                break;
            case R.id.loebeButton:
                infos = new ArrayList<XmlInfo>((java.util.Collection<? extends XmlInfo>) parserXml.XmlFactory(stream, "info", "loebe"));
                title = "Løbe Kontakter";
                break;
        }
        Fragment fragment = new InfoSelectFragment(infos);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment).commit();
        setTitle(title);

    }
    /**
     * @author Anders Petersen
     */
    public void nyhedsClick(View view){
        TextView textView = (TextView)view.findViewById(R.id.textviewfirstline);
        NyhedsFile nyhed = null;
        for (NyhedsFile nyhedsFile: nyhedsFiles){
            String target = nyhedsFile.getLinkHeader();
            if(target.equals(textView.getText())){
                nyhed = nyhedsFile;
                break;
            }
        }

        Fragment fragment = new NyhedSelectFragment(nyhed);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment).commit();
        if (nyhed != null) {
            setTitle(nyhed.getDateHeader());
        }

    }
    /**
     * @author Anders Petersen
     */
    protected void WriteXml(String xmlString, String fileName){
        try {
            //TODO does not work
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(xmlString.getBytes());
            fos.close();
        }
        catch(IOException ignored){

        }
    }

    /**
     * @author Anders Petersen
     */
	private void displayView(int position){
        Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new NyhedFragment(nyhedsFiles);
			break;
		case 1:
			fragment = new SportFragment();
			break;
		case 2:
			fragment = new InfoFragment();
			break;
		case 3:
			fragment = new SponsorFragment();
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			
			drawerList.setItemChecked(position, true);
			drawerList.setSelection(position);
			setTitle(navMenu[position]);
			drawerLayout.closeDrawer(drawerList);
		}
	}

    /**
     * @author Anders Petersen
     */
    private class NyhedLoader extends AsyncTask<Void, Void, Void> {
        ArrayList<NyhedsFile> data = null;

        @Override
        protected void onPreExecute() {
            if(isNetworkConnected()) {
                super.onPreExecute();
                progressDialog.setTitle("Nyheder");
                progressDialog.setMessage("Loading...");
                progressDialog.setIndeterminate(false);
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            if(isNetworkConnected()) {
                // Connect to the web site
                HA85Loader loader = new HA85Loader();
                // Get the html document title
                data = loader.GetNyhder();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(data == null){
                progressDialog.dismiss();
                displayView(0);
                setTitle("No Connection");
            }
            else {
                nyhedsFiles = data;
                progressDialog.dismiss();
                displayView(0);
            }
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null;
    }
	@Override
	public void setTitle(CharSequence title) {
		this.title = title;
		getActionBar().setTitle(this.title);
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}

}