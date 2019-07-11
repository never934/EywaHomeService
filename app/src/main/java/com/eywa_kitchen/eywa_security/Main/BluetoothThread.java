package com.eywa_kitchen.eywa_security.Main;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.UUID;

import static xdroid.toaster.Toaster.toast;

public class BluetoothThread extends Thread {
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothServerSocket tmp;
    private static final UUID MY_UUID = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
    private static final String NAME = "BluetoothDemo";
    private BluetoothServerSocket mmServerSocket;
    private BluetoothSocket socket;
    private PrintWriter out;

    public BluetoothThread(BluetoothAdapter Adapter) {
        this.mBluetoothAdapter=Adapter;
        tmp = null;
        try {
            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
            mkmsg("Start server\n");
        } catch (IOException e) {
            mkmsg("Failed to start server\n");
        }
        mmServerSocket = tmp;
    }

    public void run() {
        mkmsg("waiting on accept");
        socket = null;
        try {
            socket = mmServerSocket.accept();
        } catch (IOException e) {
            mkmsg("Failed to accept\n");
        }

        // If a connection was accepted
        if (socket != null) {
            mkmsg("Connection made\n");
            mkmsg("Remote device address: " + socket.getRemoteDevice().getAddress() + "\n");
            //Note this is copied from the TCPdemo code.
            try {
                while (socket!=null){
                    mkmsg("Attempting to receive a message ...\n");
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String str = in.readLine();
                    PressController.PressKey(str); // press key
                    mkmsg("received a message:\n" + str + "\n");
                }

            } catch (Exception e) {
                mkmsg("Error happened sending/receiving\n");
            } finally {
                try {
                    socket.close();
                    mkmsg("Socket closed\n");
                } catch (IOException e) {
                    mkmsg("Unable to close socket" + e.getMessage() + "\n");
                }}

        } else {
            mkmsg("Made connection, but socket is null\n");
        }
        mkmsg("Server ending \n");
        refresh();
    }

    private void mkmsg(String str){
        toast(str);
    }

    private void refresh(){
        mBluetoothAdapter.disable();
        try{
            Thread.sleep(2000);
            mkmsg("refreshing 50%\n");
        }catch(InterruptedException e){
            mkmsg("fatal error with restarting\n");
        }
        mBluetoothAdapter.enable();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        try{
            Thread.sleep(10000);
            mkmsg("refreshing 100%\n");
        }catch(InterruptedException e){
            mkmsg("fatal error with restarting\n");
        }
        new Thread(new BluetoothThread(mBluetoothAdapter)).start();
    }
}
