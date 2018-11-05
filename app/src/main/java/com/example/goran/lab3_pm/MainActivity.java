package com.example.goran.lab3_pm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.content.Context;
import android.content.ComponentName;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               NewActivity(v);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowCounter(v);
            }
        });

//        Intent serviceIntent = new Intent(this, MyService.class);
//        startService(serviceIntent);

        Intent intent2 = new Intent(this, MyService2.class);
        bindService(intent2, mConnection, Context.BIND_AUTO_CREATE);
    }
    private MyService2 mService;
    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Because we have bound to an explicit
            // service that is running in our own process, we can
            // cast its IBinder to a concrete class and directly access it.
            MyService2.MyBinder binder = (MyService2.MyBinder) service;
            mService = binder.getService();
        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {

        }
    };

    @Override
    public void onPause(){
        super.onPause();
        unbindService(mConnection);
    }


    public void ShowCounter(View view){
        mService.showCounter();
    }

    public void NewActivity(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

}
