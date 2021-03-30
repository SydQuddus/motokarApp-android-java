package com.example.motokarkotlin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.*;


public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    EditText usernameInput, userPassword;
    RegistrationCache cacheObj;
    DatabaseReference databaseAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        databaseAccount = FirebaseDatabase.getInstance().getReference("Accounts");

        findViewById(R.id.signup).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);

        //Set input Text
        usernameInput = findViewById(R.id.usernameInput);
        userPassword = findViewById(R.id.userPassword);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signup:
                startActivity(new Intent(this, RegistrationP1.class));
                break;
            case R.id.login:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = usernameInput.getText().toString();
        String password = userPassword.getText().toString();

        if(email.isEmpty()) {
            usernameInput.setError("username is required");
            usernameInput.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            userPassword.setError("password is required");
            userPassword.requestFocus();
            return;
        }

        //Validate User
        databaseAccount.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(usernameInput.getText().toString()).exists()) {
                    Accounts accounts = dataSnapshot.child(usernameInput.getText().toString()).getValue(Accounts.class);

                    if(accounts.getUserPassword().equals(userPassword.getText().toString())) {
                        cacheObj.username = usernameInput.getText().toString();
                        Intent mainActivity = new Intent(LoginPage.this,MainActivity.class);
                        startActivity(mainActivity);
                        finish();
                    }
                    else {
                        userPassword.setError("Password entered is incorrect");
                        userPassword.requestFocus();
                    }

                } else {
                    usernameInput.setError("Account does not exists");
                    usernameInput.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

