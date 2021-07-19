package com.example.covid_tracker_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private Button mBtnLogin;
    private EditText mEtLoginEmail;
    private EditText mEtLoginPassword;
    private CheckBox mCbLoginRemember;
    private String ValidEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        int Status = PreferenceHelper.getIntFromPreference(LoginActivity.this, "Status");
        if (Status == 1){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmailValid() && isPasswordValid()) {
                    String Email = mEtLoginEmail.getText().toString();
                    String Password = mEtLoginPassword.getText().toString();
                    PreferenceHelper.writeStringToPreference(LoginActivity.this, "Email", Email);
                    PreferenceHelper.writeStringToPreference(LoginActivity.this, "Password", Password);
                    if (mCbLoginRemember.isChecked()) {
                        PreferenceHelper.writeIntToPreference(LoginActivity.this, "Status", 1);
                    } else {
                        PreferenceHelper.writeIntToPreference(LoginActivity.this, "Status", 0);
                    }
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isPasswordValid() {
        if (mEtLoginPassword.getText().toString().length() >= 6) {
            return true;
        } else {
            mEtLoginPassword.setError("Password is very short");
            return false;
        }
    }

    private boolean isEmailValid() {
        if (mEtLoginEmail.getText().toString().length() >= 1 && mEtLoginEmail.getText().toString().matches(ValidEmail)) {
            return true;
        } else {
            mEtLoginEmail.setError("InValid Email");
            return false;
        }
    }

    private void initViews() {
        mBtnLogin = findViewById(R.id.btnLogin);
        mEtLoginEmail = findViewById(R.id.etLoginEmail);
        mEtLoginPassword = findViewById(R.id.etLoginPassword);
        mCbLoginRemember = findViewById(R.id.cbLoginRemember);
    }
}