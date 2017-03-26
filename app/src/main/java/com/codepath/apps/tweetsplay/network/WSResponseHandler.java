package com.codepath.apps.tweetsplay.network;

import android.content.Context;
import android.util.Log;

import com.codepath.apps.tweetsplay.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * This class receives callback from Webservice and triggers events to eg :Activities
 * registered for {@link IWSResponseListener#onWSSuccess()} & {@link IWSResponseListener#onWSFailure(Object)}
 */
public class WSResponseHandler extends JsonHttpResponseHandler{

    private static final String TAG = WSResponseHandler.class.getSimpleName();
    IWSResponseListener iwsResponseListener;

    public WSResponseHandler(Context context){

        iwsResponseListener = (IWSResponseListener) context;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray responseArr) {
        super.onSuccess(statusCode, headers, responseArr);

        // deserialize JSON and create model
        ArrayList<Tweet> listTweets = Tweet.fromJSONArray(responseArr);
        Log.d(TAG,""+responseArr.toString());
        iwsResponseListener.onSuccess(listTweets);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);

        Log.d(TAG,""+responseString);
        iwsResponseListener.onFailure(null);
    }

}
