package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.drawer.stockapp.MainActivity;
import com.example.drawer.stockapp.R;

public class LoginActivity extends BascActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWight();
    }

    public void initWight(){
        TextView mRegister = (TextView) findViewById(R.id.register_txt);
        TextView mLogin = (TextView) findViewById(R.id.login_txt);
        TextView mForgetPassword = (TextView) findViewById(R.id.forget_password_txt);


        mForgetPassword.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_txt:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_txt:
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.forget_password_txt:
                intent = new Intent(this,ForgetPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
