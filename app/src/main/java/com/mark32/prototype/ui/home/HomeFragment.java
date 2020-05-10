package com.mark32.prototype.ui.home;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.mark32.prototype.R;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Set;

public class HomeFragment extends Fragment {

    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter mBluetoothAdapter;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Register for broadcasts when a device is discovered.
        // Register for broadcasts
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        Objects.requireNonNull(getActivity()).registerReceiver(mReceiver, filter);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(!mBluetoothAdapter.startDiscovery()){
            Log.e("Bluetooth", "discovery error");
        }

        checkBluetoothState();
        return root;
    }


    // Create a BroadcastReceiver for bluetooth actions
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i("Bluetooth", "got action " + action);
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.i("Bluetooth", "got device " + deviceName );
            }
        }
    };


    private void checkBluetoothState() {
        // Checks for the Bluetooth support and then makes sure it is turned on
        // If it isn't turned on, request to turn it on
        // List paired devices
        if (mBluetoothAdapter == null) {
            Toast.makeText(getContext(), "\nBluetooth NOT supported. Aborting.", Toast.LENGTH_LONG).show();
            return;
        } else {
            if (mBluetoothAdapter.isEnabled()) {
                Toast.makeText(getContext(), "\nBluetooth is enabled...", Toast.LENGTH_LONG).show();

                // Listing paired devices
                Toast.makeText(getContext(), "\n\nPaired Devices are:", Toast.LENGTH_LONG).show();

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_home_items, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        boolean showMessage = false;
        String message = "You click fragment ";

        if (itemId == R.id.fragment_bluetooth_search) {
            Toast.makeText(getContext(), "Vamo's button", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            checkBluetoothState();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}


