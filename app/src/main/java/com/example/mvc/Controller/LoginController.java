package com.example.mvc.Controller;

import android.util.Patterns;

import com.example.mvc.Model.User;
import com.example.mvc.View.ILoginView;

public class LoginController implements ILoginController {
    ILoginView loginView;
    public LoginController(ILoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void OnLogin(String email, String Password){
        User user = new User(email, Password, email);
        if(email.isEmpty())
        {
            loginView.OnLoginError("Email Anda masih kosong");
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginView.OnLoginError("Masukan Email yang benar");
        }
        else if (Password.isEmpty()){
            loginView.OnLoginError("Password anda masih kosong");
        }
        else if (Password.length() < 6)
        {
            loginView.OnLoginError("Password anda harus lebih dari 6 karakter");
        }
        else {
            loginView.OnLoginError("Email dan Password Salah!!");
        }
    }

}
