package com.codepath.apps.twitter;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.twitter.fragments.UserTimelineFragment;
import com.codepath.apps.twitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ProfileActivity extends AppCompatActivity {

    TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String screenName1 = getIntent().getStringExtra("screen_name");
        UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(screenName1);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flContainer, userTimelineFragment);
        ft.commit();

        client = TwitterApplication.getRestClient();
        if (screenName1 == null ) {
            client.getUserInfo(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        User user = User.fromJSON(response);
                        getSupportActionBar().setTitle(user.screenName);
                        populateUserHeadline(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }else{
            client.getUserInfo(screenName1 ,new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        User user = User.fromJSON(response);
                        getSupportActionBar().setTitle(user.screenName);
                        populateUserHeadline(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }

    }

    public void populateUserHeadline(User user){
        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvTagline = (TextView) findViewById(R.id.tvTagLine);
        TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfile);
        tvName.setText(user.name);
        tvTagline.setText(user.tagLine);
        tvFollowers.setText(user.followersCount + " Followers");
        tvFollowing.setText(user.followingCount + " Following");
        Glide.with(this).load(user.profileImageUrl).into(ivProfileImage);
    }


//    public void populateUserHeadline(User user){
//        TextView tvName = (TextView) findViewById(R.id.tvName);
//        TextView tvTagline = (TextView) findViewById(R.id.tvTagLine);
//        TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
//        TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
//        ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfile);
//        tvName.setText(user.name);
//        tvTagline.setText(user.tagLine);
//        tvFollowers.setText(user.followersCount + " Followers");
//        tvFollowing.setText(user.followingCount + " Following");
//        Glide.with(this).load(user.profileImageUrl).into(ivProfileImage);
//    }


}
