package by.gsu.curiosity.mybd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import by.gsu.curiosity.mybd.R;
import by.gsu.curiosity.mybd.lists.CollectionsActivity;
import by.gsu.curiosity.mybd.test.MenuTest;


public class ListsFragment extends Fragment {

    private final static int LISTS = R.layout.fragment_lists;



    public static ListsFragment getInstance(){
        Bundle args = new Bundle();
        ListsFragment listsFragment = new ListsFragment();
        listsFragment.setArguments(args);
        return listsFragment;
    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LISTS,container, false);


       Button button = (Button) view.findViewById(R.id.fragment_srart_list);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                      Intent intent = new Intent(ListsFragment.this.getActivity(), CollectionsActivity.class);
                        startActivity(intent);
                    }
                });

                return view;
            }
        }

