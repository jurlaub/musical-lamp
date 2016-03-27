package com.udacity.gradle.builditbigger;

/**
 * Created by dev on 3/26/16.
 *
 * Exploring the use of
 * http://www.making-software.com/2012/10/31/testable-android-asynctask/
 */
public interface IDownloadListener {

    void downloadCompleted(String question, String answer);
}