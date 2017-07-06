package com.codepath.apps.twitter.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.TweetAdapter;
import com.codepath.apps.twitter.models.Tweet;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by aburgess11 on 7/3/17.
 */

public class TweetsListFragment extends Fragment implements TweetAdapter.TweetAdapterListener{

    public interface TweetSelectedListener{
        public void onTweetSelected(Tweet tweet);
    }

    TweetAdapter tweetAdapter;
    ArrayList<Tweet> tweets;
    RecyclerView rvTweets;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragments_tweets_list, container, false);

        rvTweets = (RecyclerView) v.findViewById(R.id.rvTweet);
        tweets = new ArrayList<>();
        tweetAdapter = new TweetAdapter(tweets, this);
        //RecyclerView setup(layout manager, use adapter
        rvTweets.setLayoutManager(new LinearLayoutManager(getContext()));
        //set the adapter
        rvTweets.setAdapter(tweetAdapter);

        return v;
    }

    public void addItems(JSONArray response){

        tweetAdapter.clear();
//        swipeContainer.setRefreshing(false);

            //convert each object to a tweet model
            //add that tweet model to our data source
            //notify the adapter that we;ve added an item
        try {
            for (int i = 0; i < response.length(); i++) {
                Tweet tweet = Tweet.fromJSON(response.getJSONObject(i));
                tweets.add(tweet);
                tweetAdapter.notifyItemInserted(tweets.size() - 1);

            }
        }catch(JSONException e){
                    e.printStackTrace();
                }


    }

    @Override
    public void onItemSelected(View view, int position) {
        Tweet tweet = tweets.get(position);

        ((TweetSelectedListener) getActivity()).onTweetSelected(tweet);
    }
}
