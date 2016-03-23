package com.example.android.jokedisplay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class DisplayActivity extends FragmentActivity {

    public final static String QUESTION_TEXT = "question_text";
    public final static String ANSWER_TEXT = "answer_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.displayContainer);

        if (fragment == null) {
            fragment = new JokeDisplayFragment();
            manager.beginTransaction()
                    .add(R.id.displayContainer, fragment)
                    .commit();
        }

    }
}
