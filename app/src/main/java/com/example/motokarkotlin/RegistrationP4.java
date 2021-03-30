package com.example.motokarkotlin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.firebase.database.*;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.Calendar;

public class RegistrationP4 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener{

    private ImageView backButton;
    Button buttonNext;
    EditText birthdateInput;

    RegistrationCache cacheObj;

    String date;

    String birthdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_p4);

        birthdate = cacheObj.userBirthdate;

        ///Set Back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        //Set Birthdate input
        birthdateInput = findViewById(R.id.birthdateInput);
        birthdateInput.setOnClickListener(this);

        //Set buttonNext
        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);

        birthdateInput.setText(birthdate);

    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        date = dayOfMonth + "/" + month + "/" + year;
        birthdateInput.setText(date);

    }

    @Override
    public void onClick(View view){

        String dateinput = birthdateInput.getText().toString();

        switch(view.getId()){
            case R.id.backButton:
                finish();
                break;
            case R.id.birthdateInput:
                showDatePickerDialog();
                break;
            case R.id.buttonNext:
                if(!dateinput.isEmpty()){
                    cacheObj.userBirthdate = date;
                }

                Intent intent = new Intent(RegistrationP4.this,RegistrationP5.class);
                startActivity(intent);
                break;
        }
    }
}
