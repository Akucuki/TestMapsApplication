package com.pirkovitaliysoft.testmapsapplication.login;

import com.pirkovitaliysoft.testmapsapplication.common.ErrorMessages;

public interface LoginView {
    void hideActionBar(boolean hide);
    void showErrorToast(ErrorMessages messageType);
    String getEmailFieldText();
    String getPasswordFieldText();
    void navigateToMapActivity(String login);
}
