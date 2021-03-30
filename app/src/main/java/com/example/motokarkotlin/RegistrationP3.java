package com.example.motokarkotlin;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.motokarkotlin.Cache.RegistrationCache;

public class RegistrationP3 extends AppCompatActivity implements View.OnClickListener{

    private ImageView backButton;
    Button buttonNext;
    EditText passwordInput, confirmPassInput;
    RegistrationCache cacheObj;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_p3);

        password = cacheObj.userPassword;

        ///Set Back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);

        //Set inputText
        passwordInput = findViewById(R.id.passwordInput);
        confirmPassInput = findViewById(R.id.confirmPassInput);

        passwordInput.setText(password);
    }

    //RegisterPart3
    private void registerUserP3(){
        String password = passwordInput.getText().toString();
        String confirmPass = confirmPassInput.getText().toString();

        if(password.isEmpty()){
            passwordInput.setError("password is required");
            passwordInput.requestFocus();
            return;
        }

        if(password.length()<6){
            passwordInput.setError("Minimum length of password should be 6");
            passwordInput.requestFocus();
            return;
        }

        if(!confirmPass.matches(password)){
            confirmPassInput.setError("password did not match");
            confirmPassInput.requestFocus();
            return;
        }

        cacheObj.userPassword = password;

        Intent intent = new Intent(RegistrationP3.this,RegistrationP2a.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                finish();
                break;
            case R.id.buttonNext:
                registerUserP3();
                break;
        }
    }
}
