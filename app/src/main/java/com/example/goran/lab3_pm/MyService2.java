package com.example.goran.lab3_pm;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.io.Console;
import java.util.Timer;
import java.util.TimerTask;


public class MyService2 extends Service {

    private final IBinder binder = new MyBinder();
    private Handler handler = new Handler();
    private int counter = 0;
    private Timer timer;
    //skopiowane z MyService
    private Toast toast;
    private TimerTask timerTask;
    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            showToast("Your bound service is still working");
            counter++;
        }
    }

    private void showToast(String text) {
        toast.setText(text);
        toast.show();
    }
//koniec
    public MyService2() {
    }


    @Override
    public IBinder onBind(Intent intent) {

       toast = Toast.makeText(this, "Your bound service has been started", Toast.LENGTH_LONG);
       toast.show();

        timer = new Timer();
        timerTask=new MyTimerTask();
        timer.scheduleAtFixedRate(timerTask,4*1000,4*1000);
//


        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent){
        timer.cancel();
        timer.purge();
        return false;
    }




    public void showCounter(){

        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), "Counter: "+counter, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public class MyBinder extends Binder implements IBinder {
        MyService2 getService(){
            return MyService2.this;
        }
    }
}