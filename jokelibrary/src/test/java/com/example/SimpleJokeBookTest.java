package com.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by dev on 3/17/16.
 */
public class SimpleJokeBookTest {

    private SimpleJokeBook mJokeBook;

    @Before
    public void setup(){
        mJokeBook = SimpleJokeBook.getInstance();

    }

    // note: hardcoded test
    @Test
    public void verifySingletonHasContents(){


        Assert.assertNotNull(mJokeBook);

    }

    // note: hardcoded test
    @Test
    public void getJokeTest(){
        Joke j = mJokeBook.getJoke(0);

        Assert.assertNotNull(j);

    }

    @Test
    public void addJoke(){
        String id = "aj0066";
        String question = "Wait till you hear this joke!";
        String answer = "It is a great one!";

        Assert.assertEquals(4, mJokeBook.getJokeCount());
        mJokeBook.addJokeToBook(id, question, answer);

        Assert.assertEquals(5, mJokeBook.getJokeCount());



    }



}
