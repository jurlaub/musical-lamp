package com.example;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by dev on 3/16/16.
 */
public class SimpleJokeBook {

    private static SimpleJokeBook sSimpleJokeBook = null;
    private ArrayList<Joke> mJokes;
    private Random mRandom;

    protected SimpleJokeBook(){
        mJokes = new ArrayList<>();
        mRandom = new Random();

        generateStaticJokes();
    }

    public static SimpleJokeBook getInstance(){
        if(sSimpleJokeBook == null) {
            sSimpleJokeBook = new SimpleJokeBook();
        }

        return sSimpleJokeBook;
    }


    public ArrayList<Joke> getmJokes(){
        return mJokes;
    }


    // get target joke
    public Joke getJoke(int n){
        return mJokes.get(n);
    }

    // get a random joke based on a pseudorandom number.
    public Joke getRandomJoke(){
        int targetJoke = mRandom.nextInt(mJokes.size());

        return mJokes.get(targetJoke);
    }



    // A class to generate a static set of jokes.
    // special thanks to jokes on http://www.rd.com/jokes/riddles/
    private void generateStaticJokes(){

        Joke j1 = new Joke("j001",
                "What is Purple, Green, and Squigilly?",
                "Sad, I was hoping you could tell me... ");

        mJokes.add(j1);

        Joke j2 = new Joke("j002",
                "With pointed fangs I sit and wait; with piercing force I crunch out fate; grabbing victims, proclaiming might; physically joining with a single bite. What am I?",
                "A stapler");
        mJokes.add(j2);

        Joke j3 = new Joke("j003",
                "What is easy to get into but hard to get out of?",
                "Trouble");
        mJokes.add(j3);

        Joke j4 = new Joke("j004",
                "I am the beginning of hte end, and the end of time and Space. I am essential to " +
                        "creation, and I surround every place. Who am I?",
                "The letter E.");
        mJokes.add(j4);



    }

    // note: ID not guaranteed to be unique/
    public void addJokeToBook(String id, String question, String answer){
        Joke newJoke = new Joke(id, question, answer);
        mJokes.add(newJoke);

    }

    public int getJokeCount(){
        return mJokes.size();
    }



}
