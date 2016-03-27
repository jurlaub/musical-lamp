package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.jokedisplay.DisplayActivity;


public class MainActivity extends ActionBarActivity implements IDownloadListener {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    FetchJokeAsyncTask mFetchJokeAsyncTask;

    // to save in sharedpreferences
    private final String JOKEQUESTION = "jokequestion";
    private final String JOKEANSWER = "jokeanswer";

    protected String mJokeQuestion;
    protected String mJokeAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mJokeLibrary = new JokeLibrary();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    // Action hardwired into the fragment button.
    public void tellJoke(View view){

        if (Utility.networkIsAvailable(this)) {

            mFetchJokeAsyncTask = new FetchJokeAsyncTask(this);
            mFetchJokeAsyncTask.download();
//            new EndpointsAsyncTask(this).execute();
            Log.v("mainActivity", "In the tellJoke after async task");

        } else {

            // display no network message
            Toast.makeText(this, getString(R.string.network_not_detected), Toast.LENGTH_LONG).show();
            Log.v(LOG_TAG, "no network available path");


        }




    }

    @Override
    public void downloadCompleted(String question, String answer) {

        Log.v(LOG_TAG, "downloadCompleted: Question: " + question);
        Log.v(LOG_TAG, "downloadCompleted: Answer: " + answer);

        mJokeQuestion = question;
        mJokeAnswer = answer;

        generateJokeDisplayIntent();


    }

    // called from the button press
    private void generateJokeDisplayIntent(){

        if (mJokeAnswer != null && mJokeQuestion != null) {
            Log.v(LOG_TAG, "mJokeAnswer and mJokeQuestion is not null");

            Intent intent = new Intent(this, DisplayActivity.class);

            intent.putExtra(DisplayActivity.QUESTION_TEXT, mJokeQuestion);
            intent.putExtra(DisplayActivity.ANSWER_TEXT, mJokeAnswer);

            startActivity(intent);

        } else {
            Log.v(LOG_TAG, "mJokeAnswer or mJokeQuestion is null");
        }

    }


    @Override
    public void onResume(){
        super.onResume();

        // return values in case of rotation
        SharedPreferences preferences = this.getPreferences(Context.MODE_PRIVATE);
        mJokeQuestion = preferences.getString(JOKEQUESTION, getString(R.string.default_question));
        mJokeAnswer = preferences.getString(JOKEANSWER, getString(R.string.default_answer));
    }

    @Override
    public void onStop(){
        super.onStop();

        // save values in case of rotation
        SharedPreferences.Editor editor = this.getPreferences(Context.MODE_PRIVATE).edit();
        editor.putString(JOKEQUESTION, mJokeQuestion);
        editor.putString(JOKEANSWER, mJokeAnswer);
        editor.apply();

    }




}
