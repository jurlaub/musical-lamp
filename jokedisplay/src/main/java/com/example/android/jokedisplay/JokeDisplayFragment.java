package com.example.android.jokedisplay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class JokeDisplayFragment extends Fragment {

    private final String LOG_TAG = JokeDisplayFragment.class.getSimpleName();

    private final String DISPLAY_QUESTION = "displayquestion";
    private final String DISPLAY_ANSWER = "displayanswer";
    private final String DISPLAY_BOOLEAN = "displaytest";

    // used to change the button text
    private boolean mDisplayAnswer = false;

    private String mQuestion;
    private String mAnswer;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_joke_display, container, false);


        if (savedInstanceState == null){

            Bundle extras = getActivity().getIntent().getExtras();
            mQuestion = extras.getString(DisplayActivity.QUESTION_TEXT);
            mAnswer = extras.getString(DisplayActivity.ANSWER_TEXT);


        } else {
            mQuestion = savedInstanceState.getString(DISPLAY_QUESTION);
            mAnswer = savedInstanceState.getString(DISPLAY_ANSWER);
            mDisplayAnswer = savedInstanceState.getBoolean(DISPLAY_BOOLEAN);


        }



        TextView questionText = (TextView)view.findViewById(R.id.questionText);
        questionText.setText(mQuestion);


        final TextView answerText = (TextView)view.findViewById(R.id.answerText);
        answerText.setText(mAnswer);
        final Button button = (Button)view.findViewById(R.id.answerButton);

        // sets the visibility and button text in the case of a fragment lifecycle event/rotation
        // false --> do not display the answer text
        if (mDisplayAnswer == false ) {
            answerText.setVisibility(View.INVISIBLE);
            Log.v(LOG_TAG, "answerText set to invisible");


        // true --> display the answer text and change the button text
        } else {
            button.setText(R.string.finishButton);
            answerText.setVisibility(View.VISIBLE);
            Log.v(LOG_TAG, "answerText set to visible");
        }



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // changes button and text based on button press
                if(mDisplayAnswer == false) {

                    mDisplayAnswer = true;
                    button.setText(R.string.finishButton);

                    answerText.setVisibility(View.VISIBLE);

                } else {
                    getActivity().finish();
                }


            }
        });


        return view;
    }







    @Override
    public void onResume(){
        super.onResume();

        // restoring values after rotation, test for null because they only need to be restored if returning from pause.
//        if (mQuestion == null & mAnswer == null) {
//            SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
//            mQuestion = preferences.getString(DISPLAY_QUESTION, getString(R.string.sample_question) );
//            mAnswer = preferences.getString(DISPLAY_ANSWER, getString(R.string.sample_answer));
////        mDisplayAnswer = preferences.getBoolean(DISPLAY_BOOLEAN, true);
//
//        }


        Log.v(LOG_TAG, "onResume: sharedPreference - question: " + mQuestion);

    }


    @Override
    public void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);

        outstate.putString(DISPLAY_QUESTION, mQuestion);
        outstate.putString(DISPLAY_ANSWER, mAnswer);
        outstate.putBoolean(DISPLAY_BOOLEAN, mDisplayAnswer);
        Log.v(LOG_TAG, "SavedInstanceState mDisplayAnswer: " +mDisplayAnswer );
    }


    @Override
    public void onPause(){
        super.onPause();

//         storing values to protect against rotation
//        SharedPreferences.Editor editor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
//        editor.putString(DISPLAY_QUESTION, mQuestion);
//        editor.putString(DISPLAY_ANSWER, mAnswer);
////        editor.putBoolean(DISPLAY_BOOLEAN, mDisplayAnswer);
//        editor.apply();

        Log.v(LOG_TAG, "onPause");
    }

}
