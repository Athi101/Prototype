package com.mark32.prototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class Activity2 extends AppCompatActivity {

    private SensorRecyclerAdapter sensorAdapter;
    private RecyclerView sensorRecyclerView;
    private  RecyclerView.Adapter sensorRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        ArrayList<String> sensors = new ArrayList<>();
        sensors.add("234");
        sensors.add("345");
        sensors.add("3241");

        sensorRecyclerView = findViewById(R.id.recyclerView_sensor);
        sensorAdapter = new SensorRecyclerAdapter(sensors);
        sensorRecyclerView.setAdapter(sensorAdapter);

        sensorRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), sensorRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }) {
        });

    }
}
