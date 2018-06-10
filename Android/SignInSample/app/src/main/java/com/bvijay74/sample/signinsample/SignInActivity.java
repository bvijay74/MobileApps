package com.bvijay74.sample.signinsample;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        etEmail.addTextChangedListener(new SignInActivity.EditTextFormat(etEmail));
        etPassword.addTextChangedListener(new SignInActivity.EditTextFormat(etPassword));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    public void onSignIn(View view) {
        if (!isValidEmail(etEmail.getText()) || !isValidPassword(etPassword.getText())) {
            return;
        }

        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
        startActivity(intent);

        Bundle bundle = new Bundle();
        bundle.putBoolean("NewUser", false);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void onSignUp(View view) {
        if (!isValidEmail(etEmail.getText()) || !isValidPassword(etPassword.getText())) {
            return;
        }

        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
        startActivity(intent);

        Bundle bundle = new Bundle();
        bundle.putBoolean("NewUser", true);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private boolean isValidEmail(CharSequence email) {
        if (email == null || email.length() == 0) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    private boolean isValidPassword(CharSequence key) {
        String strKey = key.toString();

        if (key.length() >= 8) {
            return true;
        }

        return false;
    }

    private class EditTextFormat implements TextWatcher {
        private EditText etTextBox;

        private EditTextFormat(EditText etTextBox) {
            this.etTextBox = etTextBox;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int inputType = etTextBox.getInputType();
            boolean validEntry = true;

            if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                if (s.length() == 0) {
                    Drawable background = getResources().getDrawable(R.drawable.border_empty_entry);
                    etTextBox.setBackground(background);
                    return;
                }
                validEntry = isValidPassword(s);
            } else if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)) {
                if (s.length() == 0) {
                    Drawable background = getResources().getDrawable(R.drawable.border_empty_entry);
                    etTextBox.setBackground(background);
                    return;
                }
                validEntry = isValidEmail(s);
            }

            if (validEntry) {
                Drawable background = getResources().getDrawable(R.drawable.border_valid_entry);
                etTextBox.setBackground(background);
            } else {
                Drawable background = getResources().getDrawable(R.drawable.border_invalid_entry);
                etTextBox.setBackground(background);
            }
        }
    }
}
