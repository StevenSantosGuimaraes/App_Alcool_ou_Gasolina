package br.com.steven.alcoolougasolina.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.steven.alcoolougasolina.R;
import br.com.steven.alcoolougasolina.database.AlcoolGasolinaDB;

public class SplashActivity extends AppCompatActivity {

    public static final int TIME_OUT_SPLASH = 2500;
    private AlcoolGasolinaDB alcoolGasolinaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iniciarDB();
        trocarTelaSplash();
    }

    private void iniciarDB() {
        alcoolGasolinaDB = new AlcoolGasolinaDB(this);
    }

    private void trocarTelaSplash() {
        new Handler().postDelayed(() -> {
            Intent telaPrincipal = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(telaPrincipal);
            finish();
        }, TIME_OUT_SPLASH);
    }

}
