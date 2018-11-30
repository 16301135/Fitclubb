package com.example.lenovo.yourgym1.me;

import android.Manifest;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.lenovo.yourgym1.R;


public class videoPlay extends AppCompatActivity {

    VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //无title
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_play);

        ActivityCompat.requestPermissions(videoPlay.this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        mVideoView = (VideoView) findViewById(R.id.video_view);

        //加载指定的视频文件 从服务端加载视频，
        //String path = Environment.getExternalStorageDirectory().getPath()+"/20180730.mp4";
        //mVideoView.setVideoPath(path);
        //mVideoView.setVideoPath("http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8");
        mVideoView.setVideoPath("rtsp://192.168.43.229:8554/test.mkv");
        //创建MediaController对象
        MediaController mediaController = new MediaController(this);

        //VideoView与MediaController建立关联
        mVideoView.setMediaController(mediaController);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mp)
            {
                mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);

            }
        });

        //让VideoView获取焦点
        mVideoView.requestFocus();

        mVideoView.start();

    }
}

