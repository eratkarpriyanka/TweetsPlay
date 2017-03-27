package com.codepath.apps.tweetsplay.models;

import com.codepath.apps.tweetsplay.database.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

@Table(database = MyDatabase.class)
public class User extends BaseModel{

    private static final String KEY_UID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCR_NAME = "screen_name";
    private static final String KEY_PROFILE_IMG_URL = "profile_image_url";

    @PrimaryKey
    @Column
    long uid;
    @Column
    private String name;
    @Column
    private String screenName;
    @Column
    private String profileImgUrl;

    public User(){
        super();
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    public static User fromJSON(JSONObject jsonUser){

        User user = new User();

        try {
            user.name = jsonUser.getString(KEY_NAME);
            user.uid = jsonUser.getLong(KEY_UID);
            user.screenName = jsonUser.getString(KEY_SCR_NAME);
            user.profileImgUrl = jsonUser.getString(KEY_PROFILE_IMG_URL);
            user.save();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
