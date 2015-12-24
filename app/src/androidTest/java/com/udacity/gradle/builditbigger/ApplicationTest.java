package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.udacity.gradle.builditbigger.backend.EndpointsAsyncTask;

import java.util.concurrent.CountDownLatch;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    String mJoke;
    CountDownLatch mCountDownLatch;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        mCountDownLatch = new CountDownLatch(1);
    }

    @Override
    public void tearDown() throws Exception {
        mCountDownLatch.countDown();
    }

    public void testGetJokeFromServer() throws InterruptedException {
        EndpointsAsyncTask task = new EndpointsAsyncTask();
        task.execute(new EndpointsAsyncTask.EndpointCallbackInterface() {
            @Override
            public void OnJokeLoaded(String result) {
                mJoke = result;
                mCountDownLatch.countDown();
            }
        });
        mCountDownLatch.await();

        assertNotNull(mJoke);
        assertTrue(mJoke.startsWith("joke: "));
    }
}