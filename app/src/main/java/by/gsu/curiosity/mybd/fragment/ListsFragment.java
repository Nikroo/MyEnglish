package by.gsu.curiosity.mybd.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import by.gsu.curiosity.mybd.R;
import by.gsu.curiosity.mybd.info.CollectionsActivity;
import by.gsu.curiosity.mybd.lists.InfoActivity;


public class ListsFragment extends AbstractTabFragment {

    private final static int LISTS = R.layout.fragment_lists;




    public static ListsFragment getInstance(Context context){
        Bundle args = new Bundle();
        ListsFragment fragment = new ListsFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_lists));
        return fragment;

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LISTS,container, false);


       Button button = (Button) view.findViewById(R.id.fragment_srart_list);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                      Intent intent = new Intent(ListsFragment.this.getActivity(), InfoActivity.class);
                        startActivity(intent);
                    }
                });

                return view;
            }


    public void setContext(Context context) {
        this.context = context;
    }


}

