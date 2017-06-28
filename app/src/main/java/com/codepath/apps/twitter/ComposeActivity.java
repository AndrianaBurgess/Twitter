package com.codepath.apps.twitter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        client = new TwitterClient(this);
        context = this;
    }

    public void onSubmit(View v) {
        // closes the activity and returns to first screen
        etBody = (EditText) findViewById(R.id.etTweet);
        client.onTweet(etBody.getText().toString() , new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Tweet tweet = null;
                try {
                    tweet =  Tweet.fromJSON(response);
                    Intent i = new Intent();
                    i.putExtra("tweet", Parcels.wrap(tweet));
                    setResult(RESULT_OK,i);
                    // brings up the second activity
                    //startActivity(i);
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
