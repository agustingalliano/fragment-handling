package galliano.android.tools.utilities;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import galliano.android.tools.R;

public class ServiceMusic extends Service {

    private MediaPlayer mediaPlayer;

    public void onCreate(){
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.tema);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100,100);
    }

    public int onStartCommand (Intent intent, int flags, int startId) {
        mediaPlayer.start();
        return START_STICKY;
    }

    public void onDestroy (){
        super.onDestroy();
        if(mediaPlayer.isPlaying()) mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer=null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
