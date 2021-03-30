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

public class RegistrationP2 extends AppCompatActivity implements View.OnClickListener{

    ImageView backButton;
    Button buttonNext;
    EditText emailInput;
    RegistrationCache cacheObj;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_p2);

        email = cacheObj.emailAddress;

        ///Set Back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);

        //Set inputText
        emailInput = findViewById(R.id.emailInput);

        emailInput.setText(email);
    }

    //RegisterPart2
    private void registerUserP2(){
        String email = emailInput.getText().toString();

        if(email.isEmpty()){
            emailInput.setError("Email address is required");
            emailInput.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailInput.setError("Please enter a valid email address");
            emailInput.requestFocus();
            return;
        }

        cacheObj.emailAddress = email;

        Intent intent = new Intent(RegistrationP2.this,RegistrationP2b.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                finish();
                break;
            case R.id.buttonNext:
                registerUserP2();
                break;
        }
    }
}
