package com.example.toto.volleydemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleyActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String Tag = VolleyActivity.class.getSimpleName();
    private String url;
    private RequestQueue queue;
    private StringRequest stringRequest;
    private Button sendButton, cancelButton;
    private LruCache<String, Bitmap> cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = "https://www.baidu.com/";
        setContentView(R.layout.activity_volley);
        sendButton = (Button) findViewById(R.id.send);
        cancelButton = (Button) findViewById(R.id.cancel);
        sendButton.setText("send repuest");
        sendButton.setOnClickListener(this);
        cancelButton.setText("cancel");
        cancelButton.setOnClickListener(this);
        queue = Volley.newRequestQueue(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                sendRequest();
                break;
            case R.id.cancel:
                cancelRequest();
                break;
        }
    }

    private void cancelRequest() {
        queue.cancelAll(this);
    }

    public int count = 1;

    private void sendRequest() {
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(Tag, "ok");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(Tag, "error");
            }
        });
        queue.add(stringRequest);
    }
}
