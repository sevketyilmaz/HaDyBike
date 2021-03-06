package com.hacktusdynamics.android.hadybike.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hacktusdynamics.android.hadybike.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, NotificationActivity.class);
    }
}
