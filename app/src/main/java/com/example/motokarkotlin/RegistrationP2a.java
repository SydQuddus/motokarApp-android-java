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

public class RegistrationP2a extends AppCompatActivity implements View.OnClickListener{

    ImageView backButton;
    Button buttonNext;
    EditText hpnoInput;
    RegistrationCache cacheObj;
    String hpno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_p2a);

        hpno = cacheObj.phoneNumber;

        ///Set Back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);

        //Set inputText
        hpnoInput = findViewById(R.id.hpnoInput);

        hpnoInput.setText(hpno);
    }

    //RegisterPart2a
    private void registerUserP2a(){
        String hpno = hpnoInput.getText().toString();

        if(!hpno.isEmpty()){
            cacheObj.phoneNumber = hpno;
        }

        Intent intent = new Intent(RegistrationP2a.this,RegistrationP4.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                finish();
                break;
            case R.id.buttonNext:
                registerUserP2a();
                break;
        }
    }
}
