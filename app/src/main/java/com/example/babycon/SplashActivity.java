package com.example.babycon;

import androidx.annotation.Nullable;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DataBaseHelper myDB = new DataBaseHelper(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    openNextActivity(true, LoginActivity.class);
            }
        }, 3000);
    }

    private void openNextActivity(boolean b, Class activity) {
        Intent intent = new Intent(SplashActivity.this, activity);
        startActivity(intent);
        finish();
    }
}