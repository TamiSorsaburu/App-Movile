package com.example.servicedelautomotor;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
public class activity_video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        VideoView mivideo=findViewById(R.id.video);

        String videoPatch= "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri= Uri.parse(videoPatch);
        mivideo.setVideoURI(uri);

        MediaController mediaController=new MediaController(this);
        mivideo.setMediaController(mediaController);
        mediaController.setAnchorView(mivideo);
    }
}