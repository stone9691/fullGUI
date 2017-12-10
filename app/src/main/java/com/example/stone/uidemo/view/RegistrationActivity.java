package com.example.stone.uidemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stone.uidemo.R;

/**
 * A login screen that offers login via email/password.
 */
public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCreateAccount;
    private EditText mUsername;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mRepeatPassword;
    private TextView mSignIn;

    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mCreateAccount = findViewById(R.id.activity_registration_create_account);
        mCreateAccount.setOnClickListener(this);

        mUsername = findViewById(R.id.activity_registration_username);
        mEmail = findViewById(R.id.activity_registration_email);
        mPassword = findViewById(R.id.activity_registration_password);
        mRepeatPassword = findViewById(R.id.activity_registration_repeat_password);

        mSignIn = findViewById(R.id.activity_registration_sign_in);
        mSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.activity_registration_create_account:
                intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.activity_registration_sign_in:
                intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(this, getString(R.string.double_back_to_exit), Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}

