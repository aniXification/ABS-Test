package com.aniXification.abstest;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class FragmentArgumentsSupport extends SherlockFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 setTheme(MainActivity.THEME); //Used for theme switching in samples
         super.onCreate(savedInstanceState);
         setContentView(R.layout.fragment_arguments_support);

         if (savedInstanceState == null) {
             // First-time init; create fragment to embed in activity.
             FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
             Fragment newFragment = MyFragment.newInstance("From Arguments");
             ft.add(R.id.created, newFragment);
             ft.commit();
         }
    }


    public static class MyFragment extends SherlockFragment {
        CharSequence mLabel;

        /**
         * Create a new instance of MyFragment that will be initialized
         * with the given arguments.
         */
        static MyFragment newInstance(CharSequence label) {
            MyFragment f = new MyFragment();
            Bundle b = new Bundle();
            b.putCharSequence("label", label);
            f.setArguments(b);
            return f;
        }

        /**
         * Parse attributes during inflation from a view hierarchy into the
         * arguments we handle.
         */
        @Override public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
            super.onInflate(activity, attrs, savedInstanceState);

            TypedArray a = activity.obtainStyledAttributes(attrs, R.styleable.FragmentArguments);
            mLabel = a.getText(R.styleable.FragmentArguments_android_label);
            a.recycle();
        }

        /**
         * During creation, if arguments have been supplied to the fragment
         * then parse those out.
         */
        @Override public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Bundle args = getArguments();
            if (args != null) {
                CharSequence label = args.getCharSequence("label");
                if (label != null) {
                    mLabel = label;
                }
            }
        }

        /**
         * Create the view for this fragment, using the arguments given to it.
         */
        @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.hello_world, container, false);
            View tv = v.findViewById(R.id.text);
            ((TextView)tv).setText(mLabel != null ? mLabel : "(no label)");
            tv.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.gallery_thumb));
            return v;
        }
    }
    
}
