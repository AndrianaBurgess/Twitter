package com.codepath.apps.twitter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codepath.apps.twitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    TwitterClient client;
    EditText etBody;
    Context context;
    String nameR ="" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        client = new TwitterClient(this);
        context = this;
        etBody = (EditText) findViewById(R.id.etTweet);
        if(getIntent().getStringExtra("name") != null) {
            nameR = getIntent().getStringExtra("name");
            etBody.setText("@" + nameR);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compose, menu);
        return true;
    }

    MenuItem miActionProgressItem;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Store instance of the menu item containing progress
        miActionProgressItem = menu.findItem(R.id.miActionProgress);
        // Extract the action-view from the menu item
        ProgressBar v =  (ProgressBar) MenuItemCompat.getActionView(miActionProgressItem);
        // Return to finish
        return super.onPrepareOptionsMenu(menu);
    }

    public void showProgressBar() {
        // Show progress item
        miActionProgressItem.setVisible(true);
    }

    public void hideProgressBar() {
        // Hide progress item
        miActionProgressItem.setVisible(false);
    }

    public void onSubmit(View v) {
        // closes the activity and returns to first screen
        etBody = (EditText) findViewById(R.id.etTweet);
        showProgressBar();
        client.onTweet(etBody.getText().toString() , new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Tweet tweet = null;
                try {
                    tweet =  Tweet.fromJSON(response);
                    Intent i = new Intent();
                    i.putExtra("tweet", Parcels.wrap(tweet));
                    setResult(RESULT_OK,i);
                    hideProgressBar();
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(context, "failed", Toast.LENGTH_LONG).show();
                Log.d("failed", responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(context, "failed", Toast.LENGTH_LONG).show();
                Log.d("failed", errorResponse.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Toast.makeText(context, "failed", Toast.LENGTH_LONG).show();
                Log.d("failed", errorResponse.toString());
            }
        });

    }


}
