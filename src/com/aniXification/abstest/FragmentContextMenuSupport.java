package com.aniXification.abstest;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class FragmentContextMenuSupport extends SherlockFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(MainActivity.THEME); //Used for theme switching in samples
        super.onCreate(savedInstanceState);

        // Create the list fragment and add it as our sole content.
        ContextMenuFragment content = new ContextMenuFragment();
        getSupportFragmentManager().beginTransaction().add(
                android.R.id.content, content).commit();
    }

    public static class ContextMenuFragment extends SherlockFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_context_menu, container, false);
            registerForContextMenu(root.findViewById(R.id.long_press));
            return root;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            menu.add(Menu.NONE, R.id.a_item, Menu.NONE, "Menu A");
            menu.add(Menu.NONE, R.id.b_item, Menu.NONE, "Menu B");
        }

        @Override
        public boolean onContextItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.a_item:
                    Log.i("ContextMenu", "Item 1a was chosen");
                    return true;
                case R.id.b_item:
                    Log.i("ContextMenu", "Item 1b was chosen");
                    return true;
            }
            return super.onContextItemSelected(item);
        }
    }
}
