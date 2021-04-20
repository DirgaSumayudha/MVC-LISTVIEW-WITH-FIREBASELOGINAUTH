package com.example.mvc.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvc.R;
import com.example.mvc.Model.UserRegister;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, registeruser;
    private EditText editTextfullname, editTextage, editTextemail, editTextpassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        banner = (TextView)findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registeruser = (Button)findViewById(R.id.registerUser);
        registeruser.setOnClickListener(this);

        editTextfullname = (EditText)findViewById(R.id.fullname);
        editTextage = (EditText)findViewById(R.id.age);
        editTextemail = (EditText)findViewById(R.id.email);
        editTextpassword = (EditText)findViewById(R.id.password);

        progressBar = (ProgressBar)findViewById(R.id.progressbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerUser:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        String  fullname = editTextfullname.getText().toString().trim();
        String age = editTextage.getText().toString().trim();

        if(fullname.isEmpty()){
            editTextfullname.setError("Full Name Is Required");
            editTextfullname.requestFocus();
            return;
        }
        if(age.isEmpty()){
            editTextage.setError("Age Is Required");
            editTextage.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextemail.setError("Email is Required");
            editTextemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Please provide valid email!!");
            editTextemail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextpassword.setError("Password is Required");
            editTextpassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextpassword.setError("Password minimal 6 karakter");
            editTextpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    UserRegister user = new UserRegister(fullname, age, email);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterUser.this,"User Has been Registered!", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);

                            }
                            else{
                                Toast.makeText(RegisterUser.this, "Failed to Register", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(RegisterUser.this, "Failed to Register", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}