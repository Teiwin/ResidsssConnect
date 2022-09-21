package fr.rhadamanthe.residsssconnect;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PrefsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, PrefsFragment.getInstance()).commit();
    }
}