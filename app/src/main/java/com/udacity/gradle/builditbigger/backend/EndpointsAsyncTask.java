package com.udacity.gradle.builditbigger.backend;

import android.os.AsyncTask;
import android.util.Log;

import com.example.rien.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<EndpointsAsyncTask.EndpointCallbackInterface, Void, String> {
    private static final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();
    private static MyApi sMyApiService = null;
    private EndpointCallbackInterface mCallback;

    @Override
    protected String doInBackground(EndpointCallbackInterface... params) {
        Log.d(LOG_TAG, "Starting async task");
        if (sMyApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            sMyApiService = builder.build();
        }
        mCallback = params[0];

        try {
            return sMyApiService.getJoke().execute().getJoke();
        }
        catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.OnJokeLoaded(result);
    }


    public interface EndpointCallbackInterface {

        void OnJokeLoaded(String result);

    }
}
