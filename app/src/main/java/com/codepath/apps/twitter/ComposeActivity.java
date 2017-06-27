package com.codepath.apps.twitter;

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
        this.finish();
    }
}
