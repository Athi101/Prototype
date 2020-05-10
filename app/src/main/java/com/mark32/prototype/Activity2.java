package com.mark32.prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        TextView textView = (TextView) findViewById(R.id.edit1);
        int pos = getIntent().getIntExtra("position", 0);
        textView.setText(pos + " index");

    }
}
