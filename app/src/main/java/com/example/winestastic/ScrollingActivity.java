package com.example.winestastic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.winestastic.databinding.ActivityScrollingBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class ScrollingActivity extends AppCompatActivity {

    protected ActivityScrollingBinding binding;
    protected String title = "";
    protected Class lastActivity = MainActivity.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupActionBar();
        configSwipe();
    }

    private void configSwipe() {
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Simulamos una actualización de 2 segundos
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.swipe.setRefreshing(false);
                        // Refrescar la actividad actual
                        recreate();
                    }
                }, 600);
            }
        });
    }

    public void onBackPressed(){
        super.onBackPressed();

        Intent intent = new Intent(this, lastActivity);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void setupActionBar(){

        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(title);

        //toolBarLayout.setCollapsedTitleTextColor("");
        //toolBarLayout.setCollapsedTitleTextColor(R.color.flexible_text_color);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}