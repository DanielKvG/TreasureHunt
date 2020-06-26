package com.example.treasurehunt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "MainActivity";
    BluetoothAdapter BA;
    Button btnDiscOn;
    public ArrayList<BluetoothDevice> BTDevices = new ArrayList<>();
    public DeviceListAdapter DLA;
    ListView ListviewDevices;
    private Button btn_NextActivity;

    // Create a BroadcastReceiver for ACTION_FOUND. this will log the states of turning on bluetooth
    private final BroadcastReceiver BroadcastReceiver1 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            //if the bluetooth action state changes, log the change
            if (action.equals(BA.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BA.ERROR);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "STATE OFF");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG, "STATE TURNING OFF");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "STATE ON");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "STATE TURNING ON");
                        break;
                }
            }
        }
    };

    //log changes of the scan activity
    private final BroadcastReceiver BroadcastReceiver2 = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            //if the scan mode has changed, log the change
            if (action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)) {

                int mode = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, BluetoothAdapter.ERROR);

                switch (mode) {
                    //Device is in Discoverable Mode
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
                        Log.d(TAG, "BroadcastReceiver2: Discoverability Enabled.");
                        break;
                    //Device not in discoverable mode
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                        Log.d(TAG, "BroadcastReceiver2: Discoverability Disabled. Able to receive connections.");
                        break;
                    case BluetoothAdapter.SCAN_MODE_NONE:
                        Log.d(TAG, "BroadcastReceiver2: Discoverability Disabled. Not able to receive connections.");
                        break;
                    case BluetoothAdapter.STATE_CONNECTING:
                        Log.d(TAG, "BroadcastReceiver2: Connecting....");
                        break;
                    case BluetoothAdapter.STATE_CONNECTED:
                        Log.d(TAG, "BroadcastReceiver2: Connected.");
                        break;
                }

            }
        }
    };

    //Log when a bluetooth device is found and store the device in the array adapter
    private BroadcastReceiver BroadcastReceiver3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d(TAG, "onReceive: ACTION_FOUND.");

            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                //declare the device, then get it by parcelableExtra
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //store the device in the array adapter
                BTDevices.add(device);
                //log name and address of bluetooth device
                Log.d(TAG, "onReceive: " + device.getName() + ": " + device.getAddress());
                DLA = new DeviceListAdapter(context, R.layout.device_adapter_view, BTDevices);
                ListviewDevices.setAdapter(DLA);
            }
        }
    };

    //Log changes in bonding state
    private final BroadcastReceiver BroadcastReceiver4 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
                BluetoothDevice BTdevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //3 cases:
                //case1: bonded already
                if (BTdevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                    Log.d(TAG, "BroadcastReceiver: BOND_BONDED.");
                }
                //case2: creating a bone
                if (BTdevice.getBondState() == BluetoothDevice.BOND_BONDING) {
                    Log.d(TAG, "BroadcastReceiver: BOND_BONDING.");
                }
                //case3: breaking a bond
                if (BTdevice.getBondState() == BluetoothDevice.BOND_NONE) {
                    Log.d(TAG, "BroadcastReceiver: BOND_NONE.");
                }
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(BroadcastReceiver1);
        unregisterReceiver(BroadcastReceiver2);
        unregisterReceiver(BroadcastReceiver3);
        unregisterReceiver(BroadcastReceiver4);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intitialize fields
        Button btnDiscover = (Button) findViewById(R.id.btnDiscover);
        btnDiscOn = (Button) findViewById(R.id.btnDiscOn);
        ListviewDevices = (ListView) findViewById(R.id.ListviewDevices);
        BTDevices = new ArrayList<>();
        btn_NextActivity = (Button) findViewById(R.id.btn_NextActivity);

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(BroadcastReceiver4, filter);


        ListviewDevices.setOnItemClickListener(MainActivity.this);

        //set the bluetooth default adapter for all bluetooth actions
        BA = BluetoothAdapter.getDefaultAdapter();

        btnDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if bluetooth is enabled
                CheckBTState();
            }
        });

    }

    //add method for enabling/disabling bluetooth
    public void CheckBTState() {
        //if your device does not have bluetooth
        if (BA == null) {
            Log.d(TAG, "enableDisableBT: does not have BT capabilities");
        }

        //if bluetooth is not enabled
        if (!BA.isEnabled()) { //the exclamation mark is for reversing the statement
            Intent BTpopup = new Intent(getApplicationContext(), BTconnect_popup.class);
            startActivity(BTpopup);

        }

        //if bluetooth is enabled
        if (BA.isEnabled()) {
            Log.d(TAG, "enableDisableBT: disabling BT.");
            BA.disable();

            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(BroadcastReceiver1, BTIntent);
        }
    }


    public void btnDiscOn(View view) {
        //log that the device will be made visible
        Log.d(TAG, "btnDiscover: Making device discoverable for 300 seconds.");

        //make the device visible for 300 seconds, first define the intent
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        //then add the time amount to the intent
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        //start the intent
        startActivity(discoverableIntent);

        //send all the changes to broadcast receiver 2
        IntentFilter intentFilter = new IntentFilter(BA.ACTION_SCAN_MODE_CHANGED);
        registerReceiver(BroadcastReceiver2, intentFilter);
    }

    public void btnDisc(View view) {
        Log.d(TAG, "btnDisc: Looking for unpaired devices.");

        //when device is already discovering, start it again
        if (BA.isDiscovering()) {
            BA.cancelDiscovery();

            //For Lollipop and higher, BT permission is needed
            //this refers to an already existing method for permission
            BTpermission();

            BA.startDiscovery();
            IntentFilter DiscoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(BroadcastReceiver3, DiscoverDevicesIntent);
        }

        //when device is not discovering, start it
        if (!BA.isDiscovering()) {
            BTpermission();
            BA.startDiscovery();
            IntentFilter DiscoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(BroadcastReceiver3, DiscoverDevicesIntent);
        }
    }

    //method for bluetooth permission by Mitch Tabian (without comments)
    private void BTpermission() {
        //check if the androidversion is higher then lollipop
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            //check if there is permission
            int permissionCheck = this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            permissionCheck += this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
            //if there is no permission, send a request for permission
            if (permissionCheck != 0) {

                this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001); //Any number
            }
            //if not, then no permission is needed
        } else {
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //first cancel discovery because its very memory intensive.
        BA.cancelDiscovery();

        Log.d(TAG, "onItemClick: You Clicked on a device.");
        String deviceName = BTDevices.get(position).getName();
        String deviceAddress = BTDevices.get(position).getAddress();

        Log.d(TAG, "onItemClick: deviceName = " + deviceName);
        Log.d(TAG, "onItemClick: deviceAddress = " + deviceAddress);

        //create the bond.
        //NOTE: Requires API 18+
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Log.d(TAG, "Trying to pair with " + deviceName);
            //to make a bond with for example, the next code must be added:
            //  BTDevices.get(int).createBond();
            //but for the prototype, the user will be sent immediately to the next activity
            startActivity(new Intent(MainActivity.this, GameModes.class));
        }
    }

    public void btn_NextActivity(View view) {
        startActivity(new Intent(MainActivity.this, GameModes.class));
    }
}