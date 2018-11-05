package com.example.goran.lab3_pm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private Toast toast;

    private Timer timer;
    private TimerTask timerTask;
    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            showToast("Your service is still working");
        }
    }

    private void showToast(String text) {
        toast.setText(text);
        toast.show();
    }


    @Override
    public void onCreate() {
        super.onCreate();

        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        timer = new Timer();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        clearTimerSchedule();
        timerTask = new MyTimerTask();//uruchamiam na nowo timer po jego zresetowaniu
        timer.scheduleAtFixedRate(timerTask, 4 * 1000, 4 * 1000);
        showToast("Your service has been started");
        return super.onStartCommand(intent, flags, startId);

    }
    private void clearTimerSchedule() {
        if(timerTask != null) {
            timerTask.cancel();
            timer.purge();
        }
    }



    @Override
    public void onDestroy() {
        clearTimerSchedule();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
