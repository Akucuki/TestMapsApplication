package com.pirkovitaliysoft.testmapsapplication.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import com.pirkovitaliysoft.testmapsapplication.R;
import com.pirkovitaliysoft.testmapsapplication.databinding.ActivityLoginBinding;
import com.pirkovitaliysoft.testmapsapplication.common.ErrorMessages;
import com.pirkovitaliysoft.testmapsapplication.map.MapActivity;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private ActivityLoginBinding binding;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new LoginPresenter(this);
        presenter.onCreate();

        binding.loginButton.setOnClickListener(view -> presenter.onLoginButtonClick());
    }

    @Override
    public void hideActionBar(boolean hide) {
        if(hide) {
            getSupportActionBar().hide();
        }else{
            getSupportActionBar().show();
        }
    }

    @Override
    public void showErrorToast(ErrorMessages messageType) {
        String errorMessage;
        final Resources res = getResources();
        switch (messageType){
            case EMPTY_FIELDS:
                errorMessage = res.getString(R.string.error_empty_fields);
                break;
            case SHORT_PASSWORD:
                errorMessage = res.getString(R.string.error_short_password);
                break;
            case NO_INTERNET:
                errorMessage = res.getString(R.string.error_no_internet);
                break;
            case INVALID_EMAIL:
                errorMessage = res.getString(R.string.error_invalid_email);
                break;
            default:
                errorMessage = res.getString(R.string.error_default);
        }
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getEmailFieldText() {
        return binding.emailField.getText().toString();
    }

    @Override
    public String getPasswordFieldText() {
        return binding.passwordField.getText().toString();
    }

    @Override
    public void navigateToMapActivity(String login) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("login", login);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}