package com.mark32.prototype;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBluetoothState();
    }

    private void checkBluetoothState() {
        // Checks for the Bluetooth support and then makes sure it is turned on
        // If it isn't turned on, request to turn it on
        // List paired devices
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "\nBluetooth NOT supported. Aborting.", Toast.LENGTH_LONG).show();
            return;
        } else {
            if (mBluetoothAdapter.isEnabled()) {
                Toast.makeText(this, "\nBluetooth is enabled...", Toast.LENGTH_LONG).show();

                // Listing paired devices
                Toast.makeText(this, "\n\nPaired Devices are:", Toast.LENGTH_LONG).show();

                Log.v("BLUETOOTH", "\nPaired Devices are:");
                Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();
                for (BluetoothDevice device : devices) {
                    Log.v("BLUETOOTH", "\n  Device: " + device.getName() + ", " + device);
                }

                mBluetoothAdapter.getBluetoothLeAdvertiser();
            } else {
                //Prompt user to turn on Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            checkBluetoothState();
        }
    }

}
