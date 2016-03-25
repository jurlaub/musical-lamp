/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.dev.cloudii.backend;

import com.example.Joke;
import com.example.JokeLibrary;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.cloudii.dev.example.com",
    ownerName = "backend.cloudii.dev.example.com",
    packagePath=""
  )
)
public class MyEndpoint {
    private JokeLibrary mJokeLibrary = new JokeLibrary();

    /** A simple endpoint method that takes a name and says Hi back */
//    @ApiMethod(name = "sayHi")
//    public MyBean sayHi(@Named("name") String name) {
//        MyBean response = new MyBean();
//        response.setData("Hi, " + name);
//
//        return response;
//    }

    @ApiMethod(name = "getJoke")
    public Joke getJoke(){
//        JokeLibrary mJokeLibrary = new JokeLibrary();

//        Joke joke = mJokeLibrary.getJoke();
        Joke response = mJokeLibrary.getJoke();
//        MyBean response = new MyBean();
//        response.setData(joke.getJokeQuestion());
////        response.setmData2(joke.getJokeAnswer());
//
////        Log.v("response + " + response.getData() + " /n ::/n  " + response.getmData2());

        return response;

    }

}
