package com.professionalandroiddevelopment.swiperdiaper;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TopFragment extends Fragment{

    private TopFragmentListener activityCommander;
    private static EditText topText;
    private static EditText bottomText;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityCommander = (TopFragmentListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    public interface TopFragmentListener{
        public void createMeme(String top, String bottom);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_fragment,container,false);

        topText = (EditText)view.findViewById(R.id.topText);
        bottomText = (EditText)view.findViewById(R.id.bottomText);
        final Button button = (Button)view.findViewById(R.id.buttonForMeme);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        button.setText("Ha Ha");
                        //Toast.makeText(getActivity(),"hi there",Toast.LENGTH_SHORT).show();
                       // Log.i("bhavik","on click");
                        buttonClicked(v);
                    }
                }
        );
        return view;
    }

    public void buttonClicked(View view){
        activityCommander.createMeme(topText.getText().toString(),bottomText.getText().toString()) ;
    }
}
