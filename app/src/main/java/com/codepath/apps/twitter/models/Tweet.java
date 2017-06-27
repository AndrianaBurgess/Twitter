package com.codepath.apps.twitter.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aburgess11 on 6/26/17.
 */

public class Tweet {
    //list out attributes
    public String body;
    public long uid;
    public User user;
    public String createdAt;

    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException{
        Tweet tweet = new Tweet();

        //extract from json object

        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        return tweet;

    }


}
