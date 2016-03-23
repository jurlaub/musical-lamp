package com.example.android.jokedisplay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class JokeDisplayFragment extends Fragment {

    // used to change the button text
    private boolean mDisplayAnswer = true;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_joke_display, container, false);

        final TextView answerText = (TextView)view.findViewById(R.id.answerText);
        answerText.setVisibility(View.INVISIBLE);

        final Button button = (Button)view.findViewById(R.id.answerButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mDisplayAnswer == true) {
                    mDisplayAnswer = false;
                    button.setText(R.string.finishButton);

                    answerText.setVisibility(View.VISIBLE);

                } else {
                    getActivity().finish();
                }


            }
        });


        return view;
    }

}
