package com.codepath.apps.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ComposeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
    }

    public void onSubmit(View v) {
        // closes the activity and returns to first screen
        Intent i = new Intent(ComposeActivity.this, TimelineActivity.class);
        // put "extras" into the bundle for access in the second activity
        i.putExtra("username", "foobar");

        // brings up the second activity
        startActivity(i);

        this.finish();
    }
}
