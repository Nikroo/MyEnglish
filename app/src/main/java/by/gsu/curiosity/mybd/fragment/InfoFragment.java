package by.gsu.curiosity.mybd.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import by.gsu.curiosity.mybd.R;
import by.gsu.curiosity.mybd.info.CollectionsActivity;


public class InfoFragment extends AbstractTabFragment {

    private final static int INFO = R.layout.fragment_info;




    public static InfoFragment getInstance(Context context){
        Bundle args = new Bundle();
       InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_info));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(INFO,container, false);



        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(InfoFragment.this.getActivity(), CollectionsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }



    public void setContext(Context context) {
        this.context = context;
    }


}
