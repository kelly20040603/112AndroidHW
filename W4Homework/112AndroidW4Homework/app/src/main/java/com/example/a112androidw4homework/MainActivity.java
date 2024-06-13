package com.example.a112androidw4homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn_Click(View view) {

        EditText editAccount, editPassword;
        editAccount = (EditText) findViewById(R.id.e1);
        editPassword = (EditText) findViewById(R.id.e2);

        String account, password;
        account = editAccount.getText().toString();
        password = editPassword.getText().toString();

        TextView txv3 = (TextView) findViewById(R.id.t3);

        /*
        帳號：aaa
        密碼：123
        */

        if(account.equals("aaa")) {
            if (password.equals("123")) { txv3.setText("登入成功"); }
            else { txv3.setText("密碼錯誤"); }
        }
        else {
            if(password.equals("123")) { txv3.setText("帳號錯誤"); }
            else { txv3.setText("帳號密碼錯誤"); }
        }
    }
}
