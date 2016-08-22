package com.example.shaunakbasu.finalproject;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 * Created by shaunak basu on 21-08-2016.
 */
public class CourseVideo extends AppCompatActivity {

    ProgressDialog progressDialog;
    MediaController mediaControls;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_video);

        String url=getIntent().getStringExtra("video_url");
        String title=getIntent().getStringExtra("title");

        setTitle(title);

        if(mediaControls==null){
            mediaControls=new MediaController(CourseVideo.this);
        }
        webView=(WebView) findViewById(R.id.video_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.loadUrl("http://www.youtube.com/embed/" + url + "?autoplay=1&vq=small");
        webView.setWebChromeClient(new WebChromeClient());

        final GestureDetector gestureDetector=new GestureDetector(new MyGestureDetector());
        View.OnTouchListener gestureListener=new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        };

        webView.setOnTouchListener(gestureListener);


    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e){
            webView.reload();
            return super.onSingleTapConfirmed(e);
        }
    }

}
