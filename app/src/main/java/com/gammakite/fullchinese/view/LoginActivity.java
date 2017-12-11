package com.gammakite.fullchinese.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gammakite.fullchinese.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSignIn;
    private TextView mTextForget;
    private TextView mTextCreate;

    private long firstTime = 0;

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
                intent = new Intent(LoginActivity.this, PasswordResetActivity.class);
                startActivity(intent);
                this.finish();
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

