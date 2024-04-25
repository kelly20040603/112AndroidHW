//A111223016 黃香綾
package com.example.homework;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup genderRadioGroup, ticketTypeRadioGroup;
    private EditText quantityEditText;
    private TextView resultTextView;
    private List<String> orderDetailsList;
    private int totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        ticketTypeRadioGroup = findViewById(R.id.ticketTypeRadioGroup);
        quantityEditText = findViewById(R.id.quantityEditText);
        resultTextView = findViewById(R.id.resultTextView);
        orderDetailsList = new ArrayList<>();

        quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                updateOrderDetails();
            }
        });

        ticketTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateOrderDetails();
            }
        });

        // 添加性别RadioGroup的监听器
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateOrderDetails();
            }
        });

        updateOrderDetails();

        Button orderButton = findViewById(R.id.orderButton);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }

    private void updateOrderDetails() {
        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityEditText.getText().toString());
        } catch (NumberFormatException e) {}

        RadioButton selectedGenderRadioButton = findViewById(genderRadioGroup.getCheckedRadioButtonId());
        String gender = selectedGenderRadioButton.getText().toString();

        RadioButton selectedTicketTypeRadioButton = findViewById(ticketTypeRadioGroup.getCheckedRadioButtonId());
        String ticketType = selectedTicketTypeRadioButton.getText().toString();

        int total = calculateTotal(ticketType, quantity);

        orderDetailsList.clear();
        String orderDetails = "性別: " + gender + "\n" +
                "票種: " + ticketType + "\n" + "張數: " + quantity + "張\n" +
                "金額: " + total + " 元\n\n";
        orderDetailsList.add(orderDetails);

        totalAmount = total;
        displayOrders();
    }

    private int calculateTotal(String ticketType, int quantity) {
        int price;
        if (ticketType.contains("成人")) {
            price = 500;
        } else if (ticketType.contains("孩童")) {
            price = 250;
        } else {
            price = 400;
        }
        return price * quantity;
    }

    private void displayOrders() {
        StringBuilder result = new StringBuilder();
        for (String order : orderDetailsList) {
            result.append(order);
        }
        resultTextView.setText(result.toString());
    }

    private void showConfirmationDialog() {
        String orderDetails = resultTextView.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("確認訂單");
        builder.setMessage("您確定要提交以下訂單嗎？\n\n" + orderDetails);
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, OrderResultActivity.class);
                intent.putExtra("orderDetails", orderDetails);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 使用者點擊取消時不執行任何操作
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
