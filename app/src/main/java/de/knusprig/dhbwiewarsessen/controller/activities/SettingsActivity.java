package de.knusprig.dhbwiewarsessen.controller.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import de.knusprig.dhbwiewarsessen.R;

public class SettingsActivity extends AppCompatActivity {

    private String serverUrl;
    private TextView textViewServerUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        serverUrl = i.getStringExtra("serverUrl");

        setContentView(R.layout.activity_settings);

        textViewServerUrl = findViewById(R.id.etServerUrl);
        textViewServerUrl.setText(serverUrl);

        View buttonSave = findViewById(R.id.btnSave);
        buttonSave.setOnClickListener(v -> saveSettings());
    }

    private void saveSettings() {{
        serverUrl = textViewServerUrl.getText().toString();
        Intent data = new Intent();
        data.putExtra("serverUrl", serverUrl);
        setResult(RESULT_OK, data);
        finish();
    }

    }
}
