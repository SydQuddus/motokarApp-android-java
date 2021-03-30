package com.example.motokarkotlin;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.firebase.database.*;
import com.squareup.picasso.Picasso;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener{

    RegistrationCache cacheObj;

    DatabaseReference databaseAccount;

    private ImageView backButton;
    private TextView buttonSave;

    EditText currentPassInput, newPassInput, confirmNewPassInput;

    String oldPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        databaseAccount = FirebaseDatabase.getInstance().getReference("Accounts").child(cacheObj.username);

        ///Set Back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((View.OnClickListener) this);

        //Set Save Button
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener((View.OnClickListener) this);

        currentPassInput = findViewById(R.id.currentPassInput);
        newPassInput = findViewById(R.id.newPassInput);
        confirmNewPassInput = findViewById(R.id.confirmNewPassInput);

        databaseAccount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    oldPassword = dataSnapshot.child("userPassword").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("myTag","there was an error");
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                finish();
                break;
            case R.id.buttonSave:
                savePassword();
                break;
        }
    }

    private void savePassword() {
        final String currentPass = currentPassInput.getText().toString();
        final String newPass = newPassInput.getText().toString();
        final String confirmNewPass = confirmNewPassInput.getText().toString();

        if(currentPass.isEmpty()){
            currentPassInput.setError("please enter current password");
            currentPassInput.requestFocus();
            return;
        }

        if(newPass.isEmpty()){
            newPassInput.setError("please enter new password");
            newPassInput.requestFocus();
            return;
        }

        if(confirmNewPass.isEmpty()){
            confirmNewPassInput.setError("please confirm your new password");
            confirmNewPassInput.requestFocus();
            return;
        }

        if(!oldPassword.equals(currentPassInput.getText().toString())) {
            currentPassInput.setError("current password entered is wrong");
            currentPassInput.requestFocus();
            return;
        }

        if(newPass.length()<6){
            newPassInput.setError("Minimum length of password should be 6");
            newPassInput.requestFocus();
            return;
        }

        if(!confirmNewPass.matches(newPass)){
            confirmNewPassInput.setError("new password did not match");
            confirmNewPassInput.requestFocus();
            return;
        }

        databaseAccount.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseAccount.child("userPassword").setValue(newPass);
                Toast.makeText(ChangePassword.this, "password has been changed", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("myTag","there was an error");
            }
        });
    }
}
