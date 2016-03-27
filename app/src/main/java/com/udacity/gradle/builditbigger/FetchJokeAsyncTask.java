package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dev.cloudii.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by dev on 3/26/16.
 *
 *
 *
 * Exploring the use of
 * http://www.making-software.com/2012/10/31/testable-android-asynctask/
 */


public class FetchJokeAsyncTask {
    private final String LOG_TAG = FetchJokeAsyncTask.class.getSimpleName();

    private IDownloadListener downloadListener;

    public FetchJokeAsyncTask(IDownloadListener downloadListener) {
        this.downloadListener = downloadListener;
        Log.v(LOG_TAG, "FetchJokeAsyncTask generated");

    }

    public void download(){

        Log.v(LOG_TAG, "FetchJokeAsyncTask download started");

        new EndpointsAsyncTask().execute();
    }



    // Async task to obtain information from Google Cloud endpoint.
    protected class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
        private final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();
        private MyApi myApiService = null;
        private Context context;
        private String remoteData;

        protected String mJokeQuestion;
        protected String mJokeAnswer;

//        public EndpointsAsyncTask(Context c){
//            this.context = c;
//        }

        public EndpointsAsyncTask(){
            Log.v(LOG_TAG, "EndpointsAsyncTask generated");
        }

        @Override
        protected String doInBackground(String... vals) {
            if(myApiService == null) {


                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://joke-endpoint133.appspot.com/_ah/api/");

                myApiService = builder.build();
            }



            try {

                Log.v(LOG_TAG, "doInBackground about to pull data");

//                return myApiService.sayHi(name).execute().getData();
                remoteData = myApiService.getJoke().execute().toString();
                return remoteData;
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String jokeJSONStr) {
            Log.v(LOG_TAG, "onPostExecute: " + jokeJSONStr);

            // here to have the JSON return on the UI thread for project testing purposes
//            getJokeFromJSON(result);


            // results from endpoint should be assigned to mJokeQuestion & mJokeAnswer
//            generateJokeDisplayIntent();

            final String JOKE_QUESTION = "jokeQuestion";
            final String JOKE_ANSWER = "jokeAnswer";

            try {
                JSONObject jokeJSON = new JSONObject(jokeJSONStr);

                mJokeAnswer = jokeJSON.getString(JOKE_ANSWER);
                mJokeQuestion = jokeJSON.getString(JOKE_QUESTION);

                Log.v(LOG_TAG, "onPostExecute about to send the joke question and answer: " + mJokeAnswer);
                downloadListener.downloadCompleted(mJokeQuestion, mJokeAnswer);

            } catch (JSONException e) {
                Log.e(LOG_TAG, "JSON exception: " + e);
            }



//            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }



//    // extract the JSON received from the Google Cloud Endpoint and save it to variables.
//    private void getJokeFromJSON(String jokeJSONStr) {
//
//        final String JOKE_QUESTION = "jokeQuestion";
//        final String JOKE_ANSWER = "jokeAnswer";
//
//        try {
//            JSONObject jokeJSON = new JSONObject(jokeJSONStr);
//
//            mJokeAnswer = jokeJSON.getString(JOKE_ANSWER);
//            mJokeQuestion = jokeJSON.getString(JOKE_QUESTION);
//
//        } catch (JSONException e) {
//            Log.e(LOG_TAG, "JSON exception: " + e);
//        }
//
//
//    }




}
