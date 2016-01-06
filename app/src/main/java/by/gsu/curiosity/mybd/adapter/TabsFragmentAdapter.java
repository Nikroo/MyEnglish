package by.gsu.curiosity.mybd.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import by.gsu.curiosity.mybd.R;
import by.gsu.curiosity.mybd.fragment.AbstractTabFragment;
import by.gsu.curiosity.mybd.fragment.ExampleFragment;
import by.gsu.curiosity.mybd.fragment.InfoFragment;
import by.gsu.curiosity.mybd.fragment.ListsFragment;
import by.gsu.curiosity.mybd.fragment.RulesFragment;


public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        initTabMap(context);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();

    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabMap(Context context) {
        tabs = new HashMap<>();
        tabs.put(0, ExampleFragment.getInstance(context));
        tabs.put(1, RulesFragment.getInstance(context));
        tabs.put(2, ListsFragment.getInstance(context));
        tabs.put(3, InfoFragment.getInstance(context));
    }
}
