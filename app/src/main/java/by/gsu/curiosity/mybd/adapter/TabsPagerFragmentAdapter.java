package by.gsu.curiosity.mybd.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

import by.gsu.curiosity.mybd.fragment.ExampleFragment;
import by.gsu.curiosity.mybd.fragment.InfoFragment;
import by.gsu.curiosity.mybd.fragment.ListsFragment;
import by.gsu.curiosity.mybd.fragment.RulesFragment;


public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {

    private String tabs[];

    public TabsPagerFragmentAdapter(FragmentManager fm) {
        super(fm);

        tabs = new String[] {

                "TEST",
                "RULES",
                "LISTS",
                "INFO"

        };

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ExampleFragment.getInstance();
            case 1:
                return RulesFragment.getInstance();
            case 2:
                return ListsFragment.getInstance();
            case 3:
                return InfoFragment.getInstance();


        }

        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
