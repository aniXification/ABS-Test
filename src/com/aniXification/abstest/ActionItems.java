package com.aniXification.abstest;

import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class ActionItems extends SherlockActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	setTheme(MainActivity.THEME); //Used for theme switching in samples
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        setContent((TextView)findViewById(R.id.text));
    }
    
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		 //Used to put dark icons on light action bar
        boolean isLight = MainActivity.THEME == R.style.Theme_Sherlock_Light;

        menu.add("Save")
            .setIcon(isLight ? R.drawable.ic_compose_inverse : R.drawable.ic_compose)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        menu.add("Search")
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        menu.add("Refresh")
            .setIcon(isLight ? R.drawable.ic_refresh_inverse : R.drawable.ic_refresh)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return true;
	}

    protected void setContent(TextView view) {
        view.setText(R.string.action_items_content);
    }

}
