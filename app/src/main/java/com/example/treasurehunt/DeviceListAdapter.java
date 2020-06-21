package com.example.treasurehunt;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author Mitch Tabian
 * Got this code from internet. Comments were made by Group 4
 */

public class DeviceListAdapter extends ArrayAdapter {
    private LayoutInflater LIF;
    private ArrayList<BluetoothDevice> BTDevices;
    private int ViewResourceId;

    public DeviceListAdapter(Context context, int tvResourceId, ArrayList<BluetoothDevice> devices) {
        super(context, tvResourceId, devices);
        //initialize fields
        this.BTDevices = devices;
        LIF = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewResourceId = tvResourceId;
    }

    //method for obtaining postion, name and adress of bluetooth device
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LIF.inflate(ViewResourceId, null);

        BluetoothDevice device = BTDevices.get(position);

        if (device != null) {
            TextView deviceName = (TextView) convertView.findViewById(R.id.tvDeviceName);
            TextView deviceAddress = (TextView) convertView.findViewById(R.id.tvDeviceAddress);

            if (deviceName != null) {
                deviceName.setText(device.getName());
            }
            if (deviceAddress != null) {
                deviceAddress.setText(device.getAddress());
            }
        }

        return convertView;
    }

}


