package com.example.easysoft.kaiwaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_ITEM = "item";
    Socket socket = null;
    myModel myModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myModel = (myModel) getIntent().getSerializableExtra(KEY_ITEM);
        
    }
}
