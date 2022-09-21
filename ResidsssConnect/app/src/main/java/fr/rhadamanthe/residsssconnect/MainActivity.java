package fr.rhadamanthe.residsssconnect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.CaptivePortal;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.http.SslError;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.rhadamanthe.residsssconnect.volley.NetworkRequest;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private WebView web;
    private CaptivePortal captivePortal;
    private Network net;
    private TextView textView;
    private RequestQueue queue;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        queue = Volley.newRequestQueue(this);

        // if the app started by a captive portal sign in intent, get the captive portal
        //if (ConnectivityManager.ACTION_CAPTIVE_PORTAL_SIGN_IN.equals(intent.getAction())) {
        net = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK);
        captivePortal = intent.getParcelableExtra(ConnectivityManager.EXTRA_CAPTIVE_PORTAL);




        final WebViewClient webClient = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) { // run js after page finished loading
                super.onPageFinished(view, url);
                textView.setText(R.string.info_portal_injecting);
                Log.d(TAG, "onPageFinished: Injecting Credentials");
                view.evaluateJavascript(
                        "document.getElementById('ft_un').value='"+ PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getString("pref_user_id", "") +"';" +
                                "document.getElementById('ft_pd').value='"+ PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getString("pref_password", "") +"';" +
                                "document.forms[0].submit();",
                        s -> {
                        });
                Log.d(TAG, "onPageFinished: Credentials Injected");
                reportCaptivePortalDismissed();
                finish();
            }
        };

        web = findViewById(R.id.webView);
        // set the webclient to the custom one
        web.setWebViewClient(webClient);
        web.getSettings().setJavaScriptEnabled(true);
        Log.d(TAG, "Javascript enabled");


        textView = findViewById(R.id.info_view);
        textView.setText(R.string.info_starting);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (net == null) {
            for (Network network : cm.getAllNetworks()) {
                if (cm.getNetworkInfo(network).getType() == ConnectivityManager.TYPE_WIFI) {
                    Log.d(TAG, "onStart: Setting process network to " + network);
                    net = network;
                }
            }
        }


        Log.d(TAG, "onStart: net " + net);
        if (net != null) {
            // make sure to use the network controlled by the captive portal to trigger it
            cm.bindProcessToNetwork(net);

            // get the captive portal url and act on it
            String URL = "http://detectportal.firefox.com/";
            NetworkRequest stringRequest = new NetworkRequest(Request.Method.GET, URL,
                    response -> {
                            Pattern pattern = Pattern.compile("http://\\d+\\.\\d+\\.\\d+\\.\\d+:?\\d*/fgtauth\\?[a-z0-9]+");
                            String response_data = new String(response.data, StandardCharsets.UTF_8);
                            Matcher matcher = pattern.matcher(response_data);
                            if (response.headers.containsKey("Location")) { //some portal returns 200 instead of 302
                                web.loadUrl(response.headers.get("Location"));
                            } else if (matcher.find()) {
                                String url = response_data.substring(matcher.start(), matcher.end());
                                Log.d(TAG, "onStart: "+url);
                                web.loadUrl(url);
                            } else {
                                textView.setText(R.string.info_no_portal);
                                reportCaptivePortalDismissed();
                            }

                    },
                    error -> {
                        if (error.networkResponse.statusCode == 302) {
                            textView.setText(R.string.info_starting);
                            web.loadUrl(error.networkResponse.headers.get("Location"));
                        } else {
                            reportCaptivePortalDismissed();
                            textView.setText(R.string.network_error);
                        }
                    });
            queue.add(stringRequest);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: reporting captive portal dismissed");
        reportCaptivePortalDismissed();
        Log.d(TAG, "onDestroy: END");
    }

    protected void reportCaptivePortalDismissed() {
        if (captivePortal != null) {
            captivePortal.reportCaptivePortalDismissed();
        }
    }
}