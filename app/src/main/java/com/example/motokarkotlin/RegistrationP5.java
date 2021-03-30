package com.example.motokarkotlin;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.firebase.database.*;

public class RegistrationP5 extends AppCompatActivity implements View.OnClickListener{

    private ImageView backButton;
    Button buttonSignUp;
    EditText referredBy;

    RegistrationCache cacheObj;

    DatabaseReference databaseAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_p5);

        databaseAccount = FirebaseDatabase.getInstance().getReference("Accounts");


        ///Set Back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        //Set Button SignUp
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(this);

        referredBy = findViewById(R.id.referredBy);
    }

    @Override
    public void onClick(View view){

        switch(view.getId()){
            case R.id.backButton:
                finish();
                break;
            case R.id.buttonSignUp:
                registerUserP5();
                break;
        }
    }

    private void registerUserP5(){
        final String userName = cacheObj.username;
        final String firstName = cacheObj.firstName;
        final String lastName = cacheObj.lastName;
        final String birthdate = cacheObj.userBirthdate;
        final String emailAddress = cacheObj.emailAddress;
        final String phoneNumber = cacheObj.phoneNumber;
        final String userPassword = cacheObj.userPassword;
        final String profilePicture = "https://firebasestorage.googleapis.com/v0/b/motokar-project.appspot.com/o/defaultImage%2FdefaultProfilePicture.png?alt=media&token=dbb84abc-4824-4594-bd9e-9abd8f883095";
        final String govID = "https://firebasestorage.googleapis.com/v0/b/motokar-project.appspot.com/o/defaultImage%2Fidcard.png?alt=media&token=3486cbfc-a48c-4665-83c2-6c6d4223e0eb";
        final String vehLicense = "https://firebasestorage.googleapis.com/v0/b/motokar-project.appspot.com/o/defaultImage%2Fvlicense.png?alt=media&token=5ed1792a-2983-439d-9ff4-a810052d1ff0";
        final String mkWalletAmount = "";
        final String userReferCode = cacheObj.username + "MKRefer";

        final String referby = referredBy.getText().toString();

        databaseAccount.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Accounts accounts = new Accounts(userName, firstName, lastName, birthdate, emailAddress, phoneNumber,
                        userPassword, profilePicture, govID, vehLicense, mkWalletAmount, userReferCode);

                databaseAccount.child(cacheObj.username).setValue(accounts);

                if(!referby.isEmpty()){
                    databaseAccount.child(cacheObj.username).child("referredBy").setValue(referby);
                    Toast.makeText(RegistrationP5.this, "referral code accepted", Toast.LENGTH_SHORT).show();
                }


                cacheObj.firstName = "";
                cacheObj.lastName = "";
                cacheObj.userBirthdate = "";
                cacheObj.emailAddress = "";
                cacheObj.phoneNumber = "";
                cacheObj.userPassword = "";

                Intent intent = new Intent(RegistrationP5.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*//Set referred by
        if(!referBy.isEmpty()){
            databaseAccount.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    databaseAccount.child(cacheObj.username).child("referredBy").setValue(referBy);

                    Toast.makeText(RegistrationP5.this, "referral code accepted", Toast.LENGTH_SHORT).show();

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("myTag","there was an error");
                }
            });
            return;
        }*/
    }

}
