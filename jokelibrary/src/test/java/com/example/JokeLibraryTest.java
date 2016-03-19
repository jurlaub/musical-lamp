package com.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by dev on 3/17/16.
 */
public class JokeLibraryTest {

    private JokeLibrary mJokeLibrary;

    @Before
    public void setup(){
        mJokeLibrary = new JokeLibrary();

    }

    @Test
    public void testGetRandomJoke(){
        Joke testJoke = mJokeLibrary.getJoke();

        Assert.assertNotNull(testJoke);

        String testQuestion = testJoke.getJokeQuestion();
        String testAnswer = testJoke.getJokeAnswer();

        Assert.assertTrue(testQuestion.length() > 1);
        Assert.assertTrue(testAnswer.length() > 1);
        Assert.assertTrue(testQuestion.contains("?"));
    }

}
