package by.gsu.curiosity.mybd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import by.gsu.curiosity.mybd.test.MenuTest;
import by.gsu.curiosity.mybd.R;


public class ExampleFragment extends Fragment {

private final static int EXAMPLE = R.layout.fragment_example;



  public static ExampleFragment getInstance(){
      Bundle args = new Bundle();
      ExampleFragment fragment = new ExampleFragment();
      fragment.setArguments(args);
      return fragment;
  }


    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(EXAMPLE,container, false);



        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ExampleFragment.this.getActivity(),MenuTest.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
