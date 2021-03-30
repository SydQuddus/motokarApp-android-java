package com.example.motokarkotlin;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.android.gms.common.util.Base64Utils;
import com.google.firebase.database.*;
import com.squareup.picasso.Picasso;

public class inviteFriends extends AppCompatActivity {

    RegistrationCache cacheObj;

    DatabaseReference databaseAccount;

    private ImageView backButton;
    TextView userReferCode, mkWalletAmount;
    Button copyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);

        databaseAccount = FirebaseDatabase.getInstance().getReference("Accounts").child(cacheObj.username);

        //Set Back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        userReferCode = findViewById(R.id.userReferCode);
        mkWalletAmount = findViewById(R.id.mkWalletAmount);

        copyButton = findViewById(R.id.copyButton);

        databaseAccount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userReferCode.setText(dataSnapshot.child("userReferCode").getValue().toString());
                mkWalletAmount.setText(dataSnapshot.child("mkWalletAmount").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("myTag","there was an error");
            }
        });

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", userReferCode.getText().toString());

                Toast.makeText(inviteFriends.this, "Referral Code copied", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
