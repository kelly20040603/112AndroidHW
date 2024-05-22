package com.example.menudemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        // 取得從 MainActivity 傳遞過來的內容
        String outputText = getIntent().getStringExtra("output");

        // 在 TextView 中顯示內容
        TextView textView = findViewById(R.id.textViewOutput);
        textView.setText(outputText);
    }
}
