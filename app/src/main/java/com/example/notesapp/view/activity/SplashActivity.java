package com.example.notesapp.view.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.notesapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser curentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (curentUser == null) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                } else {
                    // dang nhap thanh cong se chuyen sang main activity
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 1000);
    }
}