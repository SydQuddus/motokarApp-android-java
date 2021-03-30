package com.example.motokarkotlin;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class RegistrationP1 extends AppCompatActivity implements View.OnClickListener{

    private ImageView backButton;
    Button buttonNext;
    EditText fNameInput, lNameInput;
    RegistrationCache cacheObj;
    String fName, lName;

    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_p1);

        fName = cacheObj.firstName;
        lName = cacheObj.lastName;

        // Initialize Firebase Auth
        //mAuth = FirebaseAuth.getInstance();

        //Set Back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);

        //Set inputText
        fNameInput = findViewById(R.id.fNameInput);
        lNameInput = findViewById(R.id.lNameInput);

        fNameInput.setText(fName);
        lNameInput.setText(lName);

    }

    //RegisterPart1
    private void registerUserP1(){

        String fName = fNameInput.getText().toString();
        String lName = lNameInput.getText().toString();

        if(fName.isEmpty()){
            fNameInput.setError("First Name is required");
            fNameInput.requestFocus();
            return;
        }

        if(lName.isEmpty()){
            lNameInput.setError("Last Name is required");
            lNameInput.requestFocus();
            return;
        }

        cacheObj.firstName = fName;
        cacheObj.lastName = lName;

        Intent intent = new Intent(RegistrationP1.this,RegistrationP2.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                finish();
                break;
            case R.id.buttonNext:
                registerUserP1();
                break;
        }
    }
}
