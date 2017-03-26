package com.codepath.apps.tweetsplay.activities;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.tweetsplay.R;
import com.codepath.apps.tweetsplay.adapters.TweetsArrayAdapter;
import com.codepath.apps.tweetsplay.app.TweetsPlayApp;
import com.codepath.apps.tweetsplay.models.Tweet;
import com.codepath.apps.tweetsplay.network.IWSResponseListener;
import com.codepath.apps.tweetsplay.network.TwitterClient;
import com.codepath.apps.tweetsplay.utils.EndlessScrollListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class TimelineActivity extends AppCompatActivity implements IWSResponseListener{

    private static final int CALL_API = 111;
    private static final int LIMIT_PER_SCROLL = 25;
    private static final int CALL_NEW_API = 112;
    private ListView lvTweets;
    private TweetsArrayAdapter adapter;
    private ArrayList<Tweet> listTweets;
    private int offset = 1;
    private EndlessScrollListener scrollListener;
    private UIHandler uiHandler;
    private Message msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        uiHandler = new UIHandler(this);
        getHomeTimeline(1,-1);
        setViews();
    }

    private void setViews() {

        listTweets = new ArrayList<Tweet>();
        lvTweets = (ListView)findViewById(R.id.lvTweets);
        adapter = new TweetsArrayAdapter(this,listTweets);
        lvTweets.setAdapter(adapter);
        // Attach the listener to the AdapterView onCreate
        scrollListener = new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                loadNextDataFromApi(page);
                // or loadNextDataFromApi(totalItemsCount);
                return true; // ONLY if more data is actually being loaded; false otherwise.
            }
        };
        lvTweets.setOnScrollListener(scrollListener);
    }

    // Append the next page of data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    private void loadNextDataFromApi(int page) {

        long maxId = 845878024604172288L;
        getNewRequest(offset,maxId);
    }

    private void getHomeTimeline(int offset,long maxId) {

        msg = Message.obtain(uiHandler, 0);
        msg.what = CALL_API ;
        Bundle bundle  = new Bundle();
        bundle.putInt("offset",offset);
        bundle.putString("max_id",String.valueOf(maxId));
        msg.setData(bundle);
        msg.sendToTarget();
    }

    private void getNewRequest(int offset,long maxId){

        msg = Message.obtain(uiHandler, 0);
        msg.what = CALL_NEW_API ;
        Bundle bundle  = new Bundle();
        bundle.putInt("offset",offset);
        bundle.putString("max_id",String.valueOf(maxId));
        msg.setData(bundle);
        msg.sendToTarget();
    }

    private void callAPI(int offset,long maxId){
        TwitterClient client = TweetsPlayApp.getRestClient();
        client.callTimeline(this,offset,maxId);
    }

    @Override
    public void onSuccess(Object response) {

        if(response!=null) {
            if (listTweets == null || listTweets.size() == 0) {
                adapter.clear();
                listTweets = (ArrayList<Tweet>) response;
                adapter.addAll(listTweets);
            } else {
                listTweets.addAll(listTweets.size(), (ArrayList<Tweet>) response);
            }
        }
        adapter.notifyDataSetChanged();
        scrollListener.resetState();
    }

    @Override
    public void onFailure(Object response) {

        String errorString = getResources().getString(R.string.network_failure);
        Toast.makeText(TimelineActivity.this,errorString,Toast.LENGTH_LONG).show();
    }


    /**
     * Instances of static inner classes <br/>
     * do not hold an implicit reference to their outer class.
     */
    private static class UIHandler extends Handler {


        private static final String TAG = TimelineActivity.class.getSimpleName();
        private final WeakReference<TimelineActivity> activity;

        public UIHandler( TimelineActivity activity ) {
            this.activity = new WeakReference< TimelineActivity >(activity);
        }

        public void handleMessage( Message msg ) {

            TimelineActivity mActivity = activity.get();

            switch ( msg.what ) {

                case CALL_API:
                    if ( mActivity != null ) {

                        Bundle bundle = msg.getData();
                        int offset = bundle.getInt("offset");
                        long maxId = Long.parseLong(bundle.getString("max_id"));
                        Log.d(TAG,"Arguments in Handler = offset:"+offset+" maxId:"+maxId);
                        mActivity.callAPI(offset,maxId);
                    }
                    break;

            }

        }
    }
}
