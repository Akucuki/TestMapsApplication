package com.pirkovitaliysoft.testmapsapplication.login;

import android.util.Patterns;

import androidx.annotation.Nullable;

import com.pirkovitaliysoft.testmapsapplication.common.ErrorMessages;

public class LoginPresenter {
    @Nullable
    private LoginView view;

    public LoginPresenter(@Nullable LoginView view) {
        this.view = view;
    }

    public void onCreate(){
        view.hideActionBar(true);
    }

    public void onLoginButtonClick(){
        String email, password;
        email = view.getEmailFieldText().trim();
        password = view.getPasswordFieldText();

        if(email.length() == 0 || password.length() == 0){
            view.showErrorToast(ErrorMessages.EMPTY_FIELDS);
            return;
        }

        if(!isEmail(email)){
            view.showErrorToast(ErrorMessages.INVALID_EMAIL);
            return;
        }

        if(password.trim().length() < 5){
            view.showErrorToast(ErrorMessages.SHORT_PASSWORD);
            return;
        }

        view.navigateToMapActivity(email);
    }

    private boolean isEmail(String str){
        if (str == null)
            return false;
        return Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    public void onDetach(){
        view = null;
    }
}
