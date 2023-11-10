package com.example.androidservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class MyService extends Service {


        //creating a mediaplayer object
        private MediaPlayer player;

        //If it is needed to bind the service with an activity this method is called.
        // The service can result back something to the activity after binding.
        // But if you do not want to bind the service with activity then you should return null on this method.
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
//As you can see we are simply playing the default ringtone inside the onStartCommand()
// so when you start this service the default ringtone will start ringing on loop until you don’t stop the service.
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            //getting systems default ringtone
            player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
            //setting loop play to true
            //this will make the ringtone continuously playing
            player.setLooping(true);

            //staring the player
            player.start();

            //we have some options for service
            //start sticky means service will be explicity started and stopped
            return START_STICKY;
        }


        @Override
        public void onDestroy() {
            super.onDestroy();
            //stopping the player when service is destroyed
            player.stop();
        }
    }

