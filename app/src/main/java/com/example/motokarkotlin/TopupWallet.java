package com.example.motokarkotlin;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.firebase.database.*;

public class TopupWallet extends AppCompatActivity implements View.OnClickListener{

    private ImageView backButton;
    private ImageView topupButton;

    EditText topupRequest;

    DatabaseReference databaseAccount;

    RegistrationCache cacheObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup_wallet);

        databaseAccount = FirebaseDatabase.getInstance().getReference("Accounts").child(cacheObj.username);

        //Set Back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        //Set Save Button
        topupButton = findViewById(R.id.topupButton);
        topupButton.setOnClickListener(this);

        //Set topup button
        topupRequest = findViewById(R.id.topupRequest);
        topupRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                finish();
                break;
            case R.id.topupButton:
                topup();
                break;
        }
    }

    private void topup() {
        //Set topup request
        final String topup = topupRequest.getText().toString();

        if(topup.isEmpty()){
            topupRequest.setError("Please enter amount");
            topupRequest.requestFocus();
            return;
        }

        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setTitle("Please follow following steps:");
        dlgAlert.setMessage("1. You have to online transfer the desired amount.\n2. Account Details:\n  a. Maybank\n  b. Account Number: 3978124671.\n  c. Recipient: MotoKar Virtual Bank.\n  d. Reference: <insert your username>.\n  e. Additional Reference: Top Up + <Amount>");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        databaseAccount.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                databaseAccount.child("topupRequest").setValue(topup);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}