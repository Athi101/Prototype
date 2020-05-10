package com.mark32.prototype;

import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Set;

// TODO: Add listener for each item
public class BluetoothRecyclerAdapter extends RecyclerView.Adapter<BluetoothRecyclerAdapter.BluetoothViewHolder> {

    private Set<BluetoothDevice> devices;

    public BluetoothRecyclerAdapter(Set<BluetoothDevice> devices) {
        this.devices = devices;
    }

    @NonNull
    @Override
    public BluetoothViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bluetooth_devices, parent, false);

        return new BluetoothViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BluetoothViewHolder holder, int position) {
        BluetoothDevice[] array = devices.toArray(new BluetoothDevice[devices.size()]);
        holder.bluetoothDeviceName.setText(array[position].getName());
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public static class BluetoothViewHolder extends RecyclerView.ViewHolder {

        public TextView bluetoothDeviceName;

        public BluetoothViewHolder(@NonNull View itemView) {
            super(itemView);
            bluetoothDeviceName = itemView.findViewById(R.id.tv_bluetooth);

        }
    }

}
