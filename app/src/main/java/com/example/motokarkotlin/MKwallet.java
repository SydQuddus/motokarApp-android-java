package com.example.motokarkotlin;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.firebase.database.*;
import com.squareup.picasso.Picasso;

public class MKwallet extends AppCompatActivity implements View.OnClickListener{

    private ImageView backButton;
    private ImageView withdrawButton;

    DatabaseReference databaseAccount;

    RegistrationCache cacheObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mkwallet);

        databaseAccount = FirebaseDatabase.getInstance().getReference("Accounts").child(cacheObj.username);

        //Set WalletValue
        final TextView walletValue = findViewById(R.id.walletValue);

        databaseAccount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                walletValue.setText(dataSnapshot.child("mkWalletAmount").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("myTag","there was an error");
            }
        });

        //Set Back button
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        //Set Top Up Button
        ImageView topupButton = findViewById(R.id.topupButton);
        topupButton.setOnClickListener(this);

        //Set withdraw button
        ImageView withdrawButton = findViewById(R.id.withdrawButton);
        withdrawButton.setOnClickListener(this);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.backButton:
                finish();
                break;
            case R.id.topupButton:
                Intent topup = new Intent(MKwallet.this,TopupWallet.class);
                startActivity(topup);
                break;

            case R.id.withdrawButton:
                Intent withdraw = new Intent(MKwallet.this,WithdrawWallet.class);
                startActivity(withdraw);
                break;
        }
    }

}
