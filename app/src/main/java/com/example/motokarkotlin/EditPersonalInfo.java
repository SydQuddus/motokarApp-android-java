package com.example.motokarkotlin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.Calendar;

public class EditPersonalInfo extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener { //AdapterView.OnItemSelectedListener {

    RegistrationCache cacheObj;

    DatabaseReference databaseAccount;

    private ImageView backButton;
    private TextView buttonSave;

    CircleImageView profile_image;
    EditText fNameInput, lNameInput, emailInput, phoneNumberInput;
    TextView profilePictureStatus;

    //Set for tap actions
    private LinearLayout profilePicture;
    private LinearLayout govID;
    private LinearLayout vLicense;
    //Spinner genderSpinner;
    private TextView datetext;
    //String userGender;
    String defaultProfilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_info);

        databaseAccount = FirebaseDatabase.getInstance().getReference("Accounts").child(cacheObj.username);

        ///Set Back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener((View.OnClickListener) this);

        //Set Save Button
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener((View.OnClickListener) this);

        //Set profile picture
        profilePicture = findViewById(R.id.profilePicture);
        profilePicture.setOnClickListener(this);

        //Set govID
        govID = findViewById(R.id.govID);
        govID.setOnClickListener(this);

        //Set vLicense
        vLicense = findViewById(R.id.vLicense);
        vLicense.setOnClickListener(this);

        profile_image = findViewById(R.id.profile_image);
        profilePictureStatus = findViewById(R.id.profilePictureStatus);
        fNameInput = findViewById(R.id.fNameInput);
        lNameInput = findViewById(R.id.lNameInput);
        datetext = findViewById(R.id.birthdateInput);
        emailInput = findViewById(R.id.emailInput);
        phoneNumberInput = findViewById(R.id.phoneNumberInput);

        //setValue from Database
        databaseAccount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Picasso.get().load(dataSnapshot.child("profilePicture").getValue().toString()).into(profile_image);
                fNameInput.setText(dataSnapshot.child("firstName").getValue().toString());
                lNameInput.setText(dataSnapshot.child("lastName").getValue().toString());
                datetext.setText(dataSnapshot.child("userBirthdate").getValue().toString());
                emailInput.setText(dataSnapshot.child("emailAddress").getValue().toString());
                phoneNumberInput.setText(dataSnapshot.child("phoneNumber").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("myTag","there was an error");
            }
        });

        //Set Birthdate
        findViewById(R.id.birthdateInput).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        //change profile picture status
        databaseAccount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                defaultProfilePicture = dataSnapshot.child("profilePicture").getValue().toString();
                if(defaultProfilePicture.equals("https://firebasestorage.googleapis.com/v0/b/motokar-project.appspot.com/o/defaultImage%2FdefaultProfilePicture.png?alt=media&token=dbb84abc-4824-4594-bd9e-9abd8f883095")){
                    profilePictureStatus.setText("change profile picture");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("myTag","there was an error");
            }
        });

        /*//Set Spinner
        Spinner spinnerGender = findViewById(R.id.spinnerGender);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.gender_items,
                R.layout.gender_spinner_layout
        );
        adapter.setDropDownViewResource(R.layout.gender_dropdown_layout);
        spinnerGender.setAdapter(adapter);
        spinnerGender.setOnItemSelectedListener(this);*/
    }

    /*@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.getItemAtPosition(position).equals("tap to select")) {
            //do nothing
        } else {
            String item = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
            userGender = item;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/

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
        String date = dayOfMonth + "/" + month + "/" + year;
        datetext.setText(date);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                finish();
                break;
            case R.id.buttonSave:
                saveInfo();
                break;
            case R.id.profilePicture:
                Intent pp = new Intent(EditPersonalInfo.this,UploadProfilePicture.class);
                startActivity(pp);
                break;
            case R.id.govID:
                Intent gov = new Intent(EditPersonalInfo.this,UploadGovID.class);
                startActivity(gov);
                break;
            case R.id.vLicense:
                Intent veh = new Intent(EditPersonalInfo.this,UploadVLicense.class);
                startActivity(veh);
                break;
        }
    }

    private void saveInfo() {
        final String firstName = fNameInput.getText().toString();
        final String lastName = lNameInput.getText().toString();
        //final String gender = userGender;
        final String birthdate = datetext.getText().toString();
        final String emailAddress = emailInput.getText().toString();
        final String phoneNumber = phoneNumberInput.getText().toString();

        if(firstName.isEmpty()){
            fNameInput.setError("firstname is required");
            fNameInput.requestFocus();
            return;
        }

        if(lastName.isEmpty()){
            lNameInput.setError("lastname is required");
            lNameInput.requestFocus();
            return;
        }

        /*if(gender.equals("Male") || gender.equals("Female")) {
            databaseAccount.child("gender").setValue(gender);
            return;
        }*/

        if(birthdate.isEmpty()){
            datetext.setError("birthdate is required");
            datetext.requestFocus();
            return;
        }

        if(emailAddress.isEmpty()){
            emailInput.setError("email is required");
            emailInput.requestFocus();
            return;
        }

        if(phoneNumber.isEmpty()){
            phoneNumberInput.setError("phone number is required");
            phoneNumberInput.requestFocus();
            return;
        }

        //Update Value to database
        databaseAccount.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //databaseAccount.child("profilePicture").setValue(profile_image);
                databaseAccount.child("firstName").setValue(firstName);
                databaseAccount.child("lastName").setValue(lastName);
                databaseAccount.child("userBirthdate").setValue(birthdate);
                databaseAccount.child("emailAddress").setValue(emailAddress);
                databaseAccount.child("phoneNumber").setValue(phoneNumber);

                Toast.makeText(EditPersonalInfo.this, "Account updated", Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("myTag","there was an error");
            }
        });
    }

}