package by.gsu.curiosity.mybd.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import by.gsu.curiosity.mybd.test.MenuTest;
import by.gsu.curiosity.mybd.R;

public class RulesFragment extends Fragment {

    private final static int RULES = R.layout.fragment_rules;



    public static RulesFragment getInstance(){
        Bundle args = new Bundle();
        RulesFragment fragment = new RulesFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private View view;
//    private ImageView mImageView;



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

    }


