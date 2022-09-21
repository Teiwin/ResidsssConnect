package fr.rhadamanthe.residsssconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PrefsActivity extends AppCompatActivity {

    private Button connectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, PrefsFragment.getInstance()).commit();

        connectButton = findViewById(R.id.connect_button);
        connectButton.setOnClickListener(
                new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent activityChangeIntent = new Intent(PrefsActivity.this, WaitWifiActivity.class);
                         PrefsActivity.this.startActivity(activityChangeIntent);
                     }
                 });
    }
}