package com.eywa_kitchen.eywa_security.Main;

import android.accessibilityservice.AccessibilityService;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import com.eywa_kitchen.eywa_security.ui.FireActivity;
import com.eywa_kitchen.eywa_security.ui.GasActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static xdroid.toaster.Toaster.toast;

public class MyAccessibilityService extends AccessibilityService {
    public static boolean ServiceConnect;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("SecureService");
    private BluetoothAdapter mBluetoothAdapter;
    private static final String TAG = "EywaSec ";

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.e(TAG, "CONNECTED");
       /*   mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
          if (!mBluetoothAdapter.isEnabled()) {
           Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
           startActivity(enableBtIntent);
         } else {
                startServer();
             } */

    }

    @Override
    public boolean onKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        Log.e(TAG, "KEY");

        switch (keyCode) {
            case KeyEvent.KEYCODE_S:
                Intent intent = new Intent(this, GasActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case KeyEvent.KEYCODE_D:
                Intent intent2 = new Intent(this, FireActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;
        }
        return super.onKeyEvent(event);
    }

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();
        ServiceConnect = true;
        myRef.setValue("ON");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        ServiceConnect = false;
        myRef.setValue("OFF");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }

    public void startServer(){
        try{
            mkmsg("preparation..");
            Thread.sleep(10000);
            mkmsg("online");
            new Thread(new BluetoothThread(mBluetoothAdapter)).start();
        }catch (Exception e){
            mkmsg("Error while charging");
        }
    }

    public void mkmsg(String str){
        toast(str);
    }

}
