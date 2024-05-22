package com.example.menudemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button[] mainCourseButtons;
    private Button[] sideDishButtons;
    private Button[] drinkButtons;
    private TextView lblMainCourseOutput;
    private TextView lblSideDishOutput;
    private TextView lblDrinkOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnerFood = findViewById(R.id.spinnerFood);
        mainCourseButtons = new Button[]{
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4)
        };
        sideDishButtons = new Button[]{
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8)
        };
        drinkButtons = new Button[]{
                findViewById(R.id.button9),
                findViewById(R.id.button10),
                findViewById(R.id.button11)
        };
        lblMainCourseOutput = findViewById(R.id.lblMainCourseOutput);
        lblSideDishOutput = findViewById(R.id.lblSideDishOutput);
        lblDrinkOutput = findViewById(R.id.lblDrinkOutput);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.food_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFood.setAdapter(adapter);

        spinnerFood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // 主餐
                        setButtonOptions(mainCourseButtons, getResources().getStringArray(R.array.main_course_options), lblMainCourseOutput);
                        setButtonOptions(sideDishButtons, new String[]{}, lblSideDishOutput);
                        setButtonOptions(drinkButtons, new String[]{}, lblDrinkOutput);
                        break;
                    case 1: // 附餐
                        setButtonOptions(mainCourseButtons, new String[]{}, lblMainCourseOutput);
                        setButtonOptions(sideDishButtons, getResources().getStringArray(R.array.side_dish_options), lblSideDishOutput);
                        setButtonOptions(drinkButtons, new String[]{}, lblDrinkOutput);
                        break;
                    case 2: // 飲料
                        setButtonOptions(mainCourseButtons, new String[]{}, lblMainCourseOutput);
                        setButtonOptions(sideDishButtons, new String[]{}, lblSideDishOutput);
                        setButtonOptions(drinkButtons, getResources().getStringArray(R.array.drink_options), lblDrinkOutput);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setButtonClickListeners(mainCourseButtons, lblMainCourseOutput);
        setButtonClickListeners(sideDishButtons, lblSideDishOutput);
        setButtonClickListeners(drinkButtons, lblDrinkOutput);
    }

    private void setButtonOptions(Button[] buttons, String[] options, TextView outputLabel) {
        for (int i = 0; i < buttons.length; i++) {
            if (i < options.length) {
                buttons[i].setText(options[i]);
                buttons[i].setVisibility(View.VISIBLE);
            } else {
                buttons[i].setVisibility(View.GONE);
            }
        }
    }

    private void setButtonClickListeners(Button[] buttons, TextView lblOutput) {
        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selectedOption = ((Button) v).getText().toString();
                    lblOutput.setText(selectedOption);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.toU) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getOutputText())
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(MainActivity.this, NextActivity.class);
                            intent.putExtra("output", getOutputText());
                            startActivity(intent);
                        }
                    });
            builder.create().show();
            return true;
        } else if (item.getItemId() == R.id.toD) {
            // 取消操作
            clearSelections();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // 新增方法來取得所有選項的文字
    private String getOutputText() {
        return  "您的選擇：" + "\n" +
                "主餐：" + lblMainCourseOutput.getText().toString() + "\n" +
                "附餐：" + lblSideDishOutput.getText().toString() + "\n" +
                "飲料：" + lblDrinkOutput.getText().toString();
    }

    // 清空所有選項的文字
    private void clearSelections() {
        lblMainCourseOutput.setText("");
        lblSideDishOutput.setText("");
        lblDrinkOutput.setText("");
    }
}
