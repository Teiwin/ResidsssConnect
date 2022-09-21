package fr.rhadamanthe.residsssconnect;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class WaitWifiActivity extends AppCompatActivity {

    private final String TAG = "waitWifiActivity";

    private Network net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_wifi);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        WifiManager wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);


        Thread t = new Thread() {
            @Override
            public void run() {
                  try {
                    // check if enabled!
                    while (!wifi.isWifiEnabled() || net == null) {
                        for (Network network : cm.getAllNetworks()) {
                            if (cm.getNetworkInfo(network).getType() == ConnectivityManager.TYPE_WIFI) {
                                Log.d(TAG, "onStart: Setting process network to " + network);
                                net = network;
                                break;
                            }
                        }
                        // Wait to until it is
                        Thread.sleep(500);
                    }
                    Log.d(TAG, "run: wifi enabled: "+ wifi.isWifiEnabled());
                    Intent activityChangeIntent = new Intent(WaitWifiActivity.this, MainActivity.class);
                    WaitWifiActivity.this.startActivity(activityChangeIntent);

                } catch (Exception e) {
                }
            }
        };
        t.start();
        if(!wifi.isWifiEnabled()) {
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        }
    }
}