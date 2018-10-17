package com.example.david.edison;

import android.app.Activity;
import android.os.Bundle;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class startup extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_startup);
    }
}
