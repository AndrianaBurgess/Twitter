package com.codepath.apps.twitter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.codepath.apps.twitter.R.id.tvScreenName;

public class TweetDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        TextView tvUserName = (TextView) findViewById(tvScreenName);
        TextView tvScreenName = (TextView) findViewById(R.id.tvAtName);
        TextView bodyOfTweet = (TextView)  findViewById(R.id.tvBody);
        ImageView ivProfile = (ImageView) findViewById(R.id.ivProfile);
        tvUserName.setText(getIntent().getStringExtra("user_name"));
        bodyOfTweet.setText(getIntent().getStringExtra("body"));
        tvScreenName.setText(getIntent().getStringExtra("screen_name"));
        Glide.with(this).load(getIntent().getStringExtra("pic")).into(ivProfile);

    }
}
