package com.example.goran.lab3_pm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private Toast toast;

    private void showToast(String text) {
        toast.setText(text);
        toast.show();
    }
    private void writeToLogs(String message) {
        Log.d("HelloServices", message);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        writeToLogs("Called onCreate() method.");
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        writeToLogs("Called onStartCommand() methond");
        showToast("Your service has been started");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        writeToLogs("Called onDestroy() method");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
