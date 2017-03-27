package com.codepath.apps.tweetsplay.models;

import com.codepath.apps.tweetsplay.database.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Parse JSON ,  store data, encapsulate state logic, display logic
 */

@Table(database = MyDatabase.class)
public class Tweet extends BaseModel{

    private static final String KEY_UID = "id";
    private static final String KEY_TEXT = "text";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_USER = "user";

    @PrimaryKey
    @Column
    long uid; // unique db id for tweet
    @Column
    private String body;
    @Column
    @ForeignKey(saveForeignKeyModel =true)
    private User user;
    @Column
    private String createdAt;

    public Tweet(){
        super();
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    //deserialize JSON and convert into java object
    //Tweet.fromJSON

    public static Tweet fromJSON(JSONObject jsonTweet) {

        Tweet tweet = new Tweet();

        // store id
        try {
            tweet.uid = jsonTweet.getLong(KEY_UID);
            tweet.body = jsonTweet.getString(KEY_TEXT);
            tweet.createdAt = jsonTweet.getString(KEY_CREATED_AT);
            tweet.user = User.fromJSON(jsonTweet.getJSONObject(KEY_USER));
            tweet.save();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tweet;
    }

    /**
     *
     * @param jsonArr
     * @return Arraylist of tweets
     */
    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArr) {

        ArrayList<Tweet> listTweets = new ArrayList<Tweet>();

        for(int i =0;i<jsonArr.length();i++){

            try {
                JSONObject jsonTweet = jsonArr.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(jsonTweet);
                if(tweet!=null) {
                    listTweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }

        return listTweets;
    }
}
