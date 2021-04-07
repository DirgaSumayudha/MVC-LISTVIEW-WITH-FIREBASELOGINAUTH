package com.example.mvc.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvc.Controller.ILoginController;
import com.example.mvc.Controller.LoginController;
import com.example.mvc.R;

public class MainActivity extends AppCompatActivity implements ILoginView {

    EditText email, password;
    Button loginb;
    ILoginController loginController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginController = new LoginController( this);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        loginb = (Button)findViewById(R.id.loginb);

        loginb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (email.getText().toString().equals("kelompok5@gmail.com") && password.getText().toString().equals("123456789")) {
                    openafterLogin();
                }
                else {
                    loginController.OnLogin(email.getText().toString().trim(),password.getText().toString().trim());
                }
            }
        });
    }
    public void openafterLogin(){
        Intent intent = new Intent(this, afterlogin2.class);
        startActivity(intent);
    }
    @Override
    public  void OnLoginSuccess (String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void OnLoginError(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}