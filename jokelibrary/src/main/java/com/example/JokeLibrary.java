package com.example;

public class JokeLibrary {

    private static SimpleJokeBook mJokeSource;

    public JokeLibrary(){
        this.mJokeSource = SimpleJokeBook.getInstance();

    }


    public Joke getJoke(){

        return mJokeSource.getRandomJoke();
    }







}
