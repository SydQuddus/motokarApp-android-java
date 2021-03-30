package com.example.motokarkotlin;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.firebase.database.*;

public class RegistrationP2b extends AppCompatActivity implements View.OnClickListener{

    ImageView backButton;
    Button buttonNext;
    EditText usernameInput;
    RegistrationCache cacheObj;
    String username;

    DatabaseReference databaseAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_p2b);

        databaseAccount = FirebaseDatabase.getInstance().getReference("Accounts");

        username = cacheObj.username;

        ///Set Back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);

        //Set inputText
        usernameInput = findViewById(R.id.usernameInput);

        usernameInput.setText(username);
    }

    //RegisterPart2b
    private void registerUserP2b(){
        final String username = usernameInput.getText().toString();

        if(username.isEmpty()){
            usernameInput.setError("username is required");
            usernameInput.requestFocus();
            return;
        }

        databaseAccount.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(usernameInput.getText().toString()).exists()) {
                    usernameInput.setError("Username is taken");
                    usernameInput.requestFocus();
                    return;
                } else {

                    cacheObj.username = username;

                    Intent intent = new Intent(RegistrationP2b.this,RegistrationP3.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                finish();
                break;
            case R.id.buttonNext:
                registerUserP2b();
                break;
        }
    }
}
