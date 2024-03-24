package com.example.project_42;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void onFirstFragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment2,
                FirstFragment.class, null).commit();
    }
    public void onSecondFragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment2,
                SecondFragment.class, null).commit();
    }
    public void onThirdFragment(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment2,
                ThirdFragment.class, null).commit();
    }
    public void onFirstFragment_2(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment3,
                FirstFragment.class, null).commit();
    }
    public void onSecondFragment_2(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment3,
                SecondFragment.class, null).commit();
    }
    public void onThirdFragment_2(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment3,
                ThirdFragment.class, null).commit();
    }
}