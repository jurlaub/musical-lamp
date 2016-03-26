package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dev.cloudii.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

//    private JokeLibrary mJokeLibrary;

    private String mJokeQuestion;
    private String mJokeAnswer;

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

    public void tellJoke(View view){

        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));
        Log.v("mainActivity", "In the tellJoke after async task");

//        Joke joke = mJokeLibrary.getJoke();
//
////
////        Toast.makeText(this, joke.getJokeQuestion(), Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(this, DisplayActivity.class);
//
//        intent.putExtra(DisplayActivity.QUESTION_TEXT, joke.getJokeQuestion());
//        intent.putExtra(DisplayActivity.ANSWER_TEXT, joke.getJokeAnswer());
//
//
//        startActivity(intent);
//

    }





    private class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private  MyApi myApiService = null;
        private Context context;
        private String remoteData;

        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if(myApiService == null) {  // Only do this once
//                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                        new AndroidJsonFactory(), null)
//                        // options for running against local devappserver
//                        // - 10.0.2.2 is localhost's IP address in Android emulator
//                        // - turn off compression when running against local devappserver
//                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
////                        .setRootUrl("http://192.168.0.221:8080/_ah/api/")
//                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                            @Override
//                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                                abstractGoogleClientRequest.setDisableGZipContent(true);
//                            }
//                        });
                // end options for devappserver


                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://joke-endpoint133.appspot.com/_ah/api/");

                myApiService = builder.build();
            }

            context = params[0].first;
//            String name = params[0].second;

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

            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }


    private void getJokeFromJSON(String jokeJSONStr) {

    }


}
