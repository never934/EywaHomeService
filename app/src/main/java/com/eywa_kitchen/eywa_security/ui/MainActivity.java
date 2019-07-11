package com.eywa_kitchen.eywa_security.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.eywa_kitchen.eywa_security.Main.MyAccessibilityService;
import com.eywa_kitchen.eywa_security.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView ServiceState;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        myRef.setValue("Hello, World!");

        ServiceState = (TextView)findViewById(R.id.ServiceState);

        if (MyAccessibilityService.ServiceConnect==true) {
            ServiceState.setText("Включена");
            ServiceState.setTextColor(Color.GREEN);
        }
        else
        {
            ServiceState.setText("Выключена");
            ServiceState.setTextColor(Color.RED);
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if (MyAccessibilityService.ServiceConnect==true) {
            ServiceState.setText("Включена");
            ServiceState.setTextColor(Color.GREEN);
        }
        else
        {
            ServiceState.setText("Выключена");
            ServiceState.setTextColor(Color.RED);
        }
    }




}


