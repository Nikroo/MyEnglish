package by.gsu.curiosity.mybd.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.gsu.curiosity.mybd.R;

public class RulesFragment extends AbstractTabFragment {

    private final static int RULES = R.layout.fragment_rules;



    public static RulesFragment getInstance(Context context){
        Bundle args = new Bundle();
        RulesFragment fragment = new RulesFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_rules));
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(RULES,container, false);


//        mImageView = (ImageView) view.findViewById(R.id.imageView1);
//        mImageView.setImageResource(R.drawable.rul1);
//
//        mImageView.setImageBitmap(imageUtil.getImageBitmap() );
//
//        FileInputStream fis = new FileInputStream(SyncStateContract.Constants. + this.image);
//        BufferedInputStream bis = new BufferedInputStream(fis);
//
//        Bitmap  = BitmapFactory.decodeStream(bis);




        return view;
    }


    public void setContext(Context context) {
        this.context = context;
    }


}


