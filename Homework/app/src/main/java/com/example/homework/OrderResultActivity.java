package com.example.homework;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderresult);

        // 获取订单详情数据
        String orderDetails = getIntent().getStringExtra("orderDetails");

        // 显示订单详情
        TextView orderDetailsTextView = findViewById(R.id.orderDetailsTextView);
        orderDetailsTextView.setText(orderDetails);
    }
}
