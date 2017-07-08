package com.professionalandroiddevelopment.swiperdiaper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by BHAVIK PATEL on 29-May-17.
 */

public class BottomFragment extends Fragment {

    private TextView topText,bottomText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_fragment,container,false);
        topText = (TextView) view.findViewById(R.id.topMemeText);
        bottomText = (TextView) view.findViewById(R.id.bottomMemeText);
        return view;
    }

    public void createMeme(String top, String bottom){
        topText.setText(top);
        bottomText.setText(bottom);
    }
}
