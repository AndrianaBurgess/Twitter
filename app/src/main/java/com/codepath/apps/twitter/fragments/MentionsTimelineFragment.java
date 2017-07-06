package com.codepath.apps.twitter.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.codepath.apps.twitter.TwitterApplication;
import com.codepath.apps.twitter.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by aburgess11 on 7/3/17.
 */

public class MentionsTimelineFragment extends TweetsListFragment {


    private TwitterClient client;
    private SwipeRefreshLayout swipeContainer;



    @Nullable
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        populateTimeline();

       // swipeContainer = (SwipeRefreshLayout) findViewById(swipeContainer);
        // Setup refresh listener which triggers new data loading
//        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Your code to refresh the list here.
//                // Make sure you call swipeContainer.setRefreshing(false)
//                // once the network request has completed successfully.
//                //fetchTimelineAsync(0);
//
//                populateTimeline();
//
//            }
//        });
    }

    private void populateTimeline(){

        client.getMentionsTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                swipeContainer.setRefreshing(false);
                Log.d("TwitterClient", response.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //Log.d("TwitterClient", response.toString());
                //iterate json array
                //for each entry , deserialize the json object
                addItems(response);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                swipeContainer.setRefreshing(false);
                Log.d("TwitterClient", responseString);
                throwable.printStackTrace();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                swipeContainer.setRefreshing(false);
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                swipeContainer.setRefreshing(false);
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }
        });
    }
}
