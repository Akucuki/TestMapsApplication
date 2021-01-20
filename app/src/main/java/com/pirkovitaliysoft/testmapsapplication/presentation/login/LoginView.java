package com.pirkovitaliysoft.testmapsapplication.presentation.login;

import android.app.Activity;
import android.widget.TextView;

import com.pirkovitaliysoft.testmapsapplication.presentation.common.ErrorMessages;

public interface LoginView {
    void hideActionBar(boolean hide);
    void showErrorToast(ErrorMessages messageType);
    String getEmailFieldText();
    String getPasswordFieldText();
    void navigateToMapActivity(String login);
}
