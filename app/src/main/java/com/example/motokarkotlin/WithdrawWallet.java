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

public class WithdrawWallet extends AppCompatActivity implements View.OnClickListener {

    private ImageView backButton;
    private ImageView withdrawButton;

    EditText withdrawRequest;

    DatabaseReference databaseAccount;

    RegistrationCache cacheObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_wallet);

        databaseAccount = FirebaseDatabase.getInstance().getReference("Accounts").child(cacheObj.username);

        //Set Back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        //Set Save Button
        withdrawButton = findViewById(R.id.withdrawButton);
        withdrawButton.setOnClickListener((View.OnClickListener) this);

        //Set withdraw button
        withdrawRequest = findViewById(R.id.withdrawRequest);
        withdrawRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                finish();
                break;
            case R.id.withdrawButton:
                withdraw();
                break;
        }
    }

    private void withdraw() {
        //Set withdraw request
        final String withdraw = withdrawRequest.getText().toString();

        if(withdraw.isEmpty()){
            withdrawRequest.setError("Please enter amount");
            withdrawRequest.requestFocus();
            return;
        }

        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setTitle("Withdraw Request");
        dlgAlert.setMessage("Withdraw request is accepted. Your money will be withdraw soon. Contact us if you are having any difficulties.");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        databaseAccount.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                databaseAccount.child("withdrawRequest").setValue(withdraw);
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