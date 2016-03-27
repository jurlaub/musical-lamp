package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import junit.framework.Assert;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FetchJokeAsyncTaskTest extends AndroidTestCase implements IDownloadListener {

    FetchJokeAsyncTask mFetchJokeAsyncTask;
    CountDownLatch signal;

    String testQuestion;
    String testAnswer;

    protected void setUp() throws Exception {
        super.setUp();

        this.signal = new CountDownLatch(1);
        this.mFetchJokeAsyncTask = new FetchJokeAsyncTask(this);

        this.testQuestion = null;
        this.testAnswer = null;

        Log.v("Fetch_Test", "In Setup");
    }

    public void testDownload() throws InterruptedException {

        Log.v("Fetch_Test", "In the testDownload");

        Assert.assertNull(testQuestion);
        Assert.assertNull(testAnswer);

        mFetchJokeAsyncTask.download();
        signal.await(30, TimeUnit.SECONDS);

        Assert.assertNotNull(testQuestion);
        Assert.assertNotNull(testAnswer);

        Assert.assertTrue(testAnswer.length() > 0);

        Log.v("Fetch_Test", "testDownload after asserts");
    }

    @Override
    public void downloadCompleted(String question, String answer) {
        Log.v("Fetch_Test", "downloadCompleted-- q: " + question + " a: " + answer);
        this.testQuestion = question;
        this.testAnswer = answer;

    }


//    public void testToFail() throws InterruptedException {
//
//        mFetchJokeAsyncTask.download();
//        signal.await(30, TimeUnit.SECONDS);
//
////        Assert.assertTrue(testAnswer.length() > 5);
////        Assert.assertEquals(testQuestion + " :::" + testAnswer, testQuestion, "howdyDuty");
//
//
//    }


}