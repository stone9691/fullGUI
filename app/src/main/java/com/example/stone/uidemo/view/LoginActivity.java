package com.example.stone.uidemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.stone.uidemo.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSignIn;
    private TextView mTextForget;
    private TextView mTextCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mSignIn = findViewById(R.id.activity_login_button_sign_in);
        mSignIn.setOnClickListener(this);

        mTextForget = findViewById(R.id.activity_login_text_forget);
        mTextForget.setOnClickListener(this);

        mTextCreate = findViewById(R.id.activity_login_text_create);
        mTextCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.activity_login_button_sign_in:
                intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.activity_login_text_forget:
                break;
            case R.id.activity_login_text_create:
                intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                this.finish();
                break;
            default:
                break;
        }
    }
}

