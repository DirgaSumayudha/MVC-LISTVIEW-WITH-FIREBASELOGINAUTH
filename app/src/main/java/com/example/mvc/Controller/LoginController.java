package com.example.mvc.Controller;

import com.example.mvc.Model.User;
import com.example.mvc.View.ILoginView;

public class LoginController implements ILoginController {
    ILoginView loginView;
    public LoginController(ILoginView loginView){ this.loginView = loginView; }

    @Override
    public void OnLogin(String email, String Password){
        User user = new User(email, Password);
        int loginCode = user.isValid();
        if (loginCode == 0)
        {
            loginView.OnLoginError("Email Anda masih kosong");
        }
        else if (loginCode == 1) {
            loginView.OnLoginError("Masukan Email yang benar");
        }
        else if (loginCode == 2){
            loginView.OnLoginError("Password anda masih kosong");
        }
        else if (loginCode == 3)
        {
            loginView.OnLoginError("Password anda harus lebih dari 6 karakter");
        }
        else {
            loginView.OnLoginError("Email dan Password Salah!!");
        }
    }

}
