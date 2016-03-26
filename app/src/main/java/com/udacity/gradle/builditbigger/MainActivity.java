package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.jokedisplay.DisplayActivity;
import com.example.dev.cloudii.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

//    private JokeLibrary mJokeLibrary;

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
            new EndpointsAsyncTask(this).execute();
            Log.v("mainActivity", "In the tellJoke after async task");

        } else {

            // display no network message
            Toast.makeText(this, getString(R.string.network_not_detected), Toast.LENGTH_LONG).show();
            Log.v(LOG_TAG, "no network available path");


        }




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
        editor.putString(JOKEQUESTION, mJokeAnswer);
        editor.apply();

    }




    // Async task to obtain information from Google Cloud endpoint.
    protected class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
        private  MyApi myApiService = null;
        private Context context;
        private String remoteData;

        public EndpointsAsyncTask(Context c){
            this.context = c;
        }

        @Override
        protected String doInBackground(String... vals) {
            if(myApiService == null) {


                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://joke-endpoint133.appspot.com/_ah/api/");

                myApiService = builder.build();
            }



            try {
//                return myApiService.sayHi(name).execute().getData();
                remoteData = myApiService.getJoke().execute().toString();
                return remoteData;
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.v("onPostExecute", "string 2: " + result);

            // here to have the JSON return on the UI thread for project testing purposes
            getJokeFromJSON(result);


            // results from endpoint should be assigned to mJokeQuestion & mJokeAnswer
            generateJokeDisplayIntent();


//            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }



    // extract the JSON recieved from the Google Cloud Endpoint and save it to variables.
    private void getJokeFromJSON(String jokeJSONStr) {

        final String JOKE_QUESTION = "jokeQuestion";
        final String JOKE_ANSWER = "jokeAnswer";

        try {
            JSONObject jokeJSON = new JSONObject(jokeJSONStr);

            mJokeAnswer = jokeJSON.getString(JOKE_ANSWER);
            mJokeQuestion = jokeJSON.getString(JOKE_QUESTION);

        } catch (JSONException e) {
            Log.e(LOG_TAG, "JSON exception: " + e);
        }


    }


}
