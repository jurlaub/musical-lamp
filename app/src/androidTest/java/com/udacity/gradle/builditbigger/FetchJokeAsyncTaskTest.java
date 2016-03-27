import android.test.AndroidTestCase;
import android.util.Log;

import com.udacity.gradle.builditbigger.FetchJokeAsyncTask;
import com.udacity.gradle.builditbigger.IDownloadListener;

import junit.framework.Assert;

import java.lang.Exception;
import java.lang.InterruptedException;
import java.lang.Override;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FetchJokeAsyncTaskTests extends AndroidTestCase implements IDownloadListener {

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


}