package com.example.motokarkotlin;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.util.Log;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;
import com.google.firebase.storage.*;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class addVehicle extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText CarName;
    EditText vRateHour;
    EditText vRateDay;
    EditText vRateWeek;
    EditText vRateMonth;
    EditText MakeYear;
    Spinner vType;
    Spinner vTransmission;
    Spinner vSeats;
    EditText vDesc;


    RegistrationCache cacheObj;
    DatabaseReference databaseAccount;
    ImageView img;
    Button savebtn;
    StorageReference vpic;
    private StorageTask uploadTask;
    public Uri imguri = null;
    String vehiclePicture = "https://firebasestorage.googleapis.com/v0/b/motokar-project.appspot.com/o/vehicleicon.png?alt=media&token=9edc71f0-8083-4c8d-8e3b-6e9b159b2a3e";
    private ImageView backButton;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicle);

        progressDialog = new ProgressDialog(this);
        //Getting the ID

        CarName = (EditText)findViewById(R.id.CarNameField);
        vRateHour = (EditText)findViewById(R.id.RatesFieldHour);
        vRateDay = (EditText)findViewById(R.id.RatesFieldDay);
        vRateWeek= (EditText)findViewById(R.id.RatesFieldWeek);
        vRateMonth=(EditText)findViewById(R.id.RatesFieldMonth);
        MakeYear=(EditText)findViewById(R.id.rentDateTime);
        vType=(Spinner)findViewById(R.id.carType);
        vTransmission=(Spinner)findViewById(R.id.CarTransmission);
        vSeats=(Spinner)findViewById(R.id.CarSeatDetails);
        vDesc=(EditText)findViewById(R.id.DescriptionText);


        //Set Database and storage
        vpic = FirebaseStorage.getInstance().getReference("VehiclePic");
        img = (ImageView)findViewById(R.id.imgView);
        savebtn = (Button) findViewById(R.id.VehicleConfirmButton);
        Picasso.get().load(vehiclePicture).into(img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filechooser();
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(addVehicle.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    fileuploader();
                }
            }
        });


        Spinner carTypeSpinner = findViewById(R.id.carType);
        Spinner carTransmissionSpinner = findViewById(R.id.CarTransmission);
        Spinner carSeatsSpinner = findViewById(R.id.CarSeatDetails);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.cartype,
                R.layout.gender_spinner_layout
        );

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.transmission,
                R.layout.gender_spinner_layout
        );

        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(
                this,
                R.array.seats,
                R.layout.gender_spinner_layout
        );
        adapter.setDropDownViewResource(R.layout.gender_dropdown_layout);
        carTypeSpinner.setAdapter(adapter);
        carTypeSpinner.setOnItemSelectedListener(this);

        adapter1.setDropDownViewResource(R.layout.gender_dropdown_layout);
        carTransmissionSpinner.setAdapter(adapter1);
        carTransmissionSpinner.setOnItemSelectedListener(this);

        adapter2.setDropDownViewResource(R.layout.gender_dropdown_layout);
        carSeatsSpinner.setAdapter(adapter2);
        carSeatsSpinner.setOnItemSelectedListener(this);


        backButton = (ImageView) findViewById(R.id.vehicleBookingBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.getItemAtPosition(position).equals("tap to select")) {
            //do nothing
        } else {
            String item = parent.getItemAtPosition(position).toString();
            //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();

            //Do here for send to database

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void fileuploader() {

        progressDialog.setMessage("Saving data...");
        progressDialog.show();

        if(imguri != null){

            final StorageReference Ref = vpic.child(System.currentTimeMillis()+"."+getExtension(imguri));

            uploadTask = Ref.putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Upload URL
                        Ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                vehiclePicture = String.valueOf(uri);
                                databaseAccount = FirebaseDatabase.getInstance().getReference("Vehicle");

                                String vehicleId = "vehicle" + UUID.randomUUID().toString();

                                Vehicle vehicle = new Vehicle(
                                        cacheObj.username,
                                        vType.getSelectedItem().toString(),
                                        vDesc.getText().toString(),
                                        CarName.getText().toString(),
                                        vehiclePicture,
                                        vRateHour.getText().toString(),
                                        vRateDay.getText().toString(),
                                        vRateMonth.getText().toString(),
                                        vRateWeek.getText().toString(),
                                        "0",
                                        "0",
                                        vSeats.getSelectedItem().toString(),
                                        vTransmission.getSelectedItem().toString(),
                                        MakeYear.getText().toString(),
                                        vehicleId,
                                        "0"
                                );

                                databaseAccount.child(vehicleId).setValue(vehicle);
                                progressDialog.dismiss();
                                Toast.makeText(addVehicle.this, "Vehicle added successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(addVehicle.this, "An error occurred", Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{

            databaseAccount = FirebaseDatabase.getInstance().getReference("Vehicle");

            String vehicleId = "vehicle" + UUID.randomUUID().toString();

            Vehicle vehicle = new Vehicle(
                    cacheObj.username,
                    vType.getSelectedItem().toString(),
                    vDesc.getText().toString(),
                    CarName.getText().toString(),
                    vehiclePicture,
                    vRateHour.getText().toString(),
                    vRateDay.getText().toString(),
                    vRateMonth.getText().toString(),
                    vRateWeek.getText().toString(),
                    "0",
                    "0",
                    vSeats.getSelectedItem().toString(),
                    vTransmission.getSelectedItem().toString(),
                    MakeYear.getText().toString(),
                    vehicleId,
                    "0"
            );

            databaseAccount.child(vehicleId).setValue(vehicle);
            progressDialog.dismiss();
            Toast.makeText(addVehicle.this, "Vehicle added successfully", Toast.LENGTH_SHORT).show();

        }

    }

    private void filechooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imguri=data.getData();
            img.setImageURI(imguri);
            Log.d("myTag","requestCode: " + requestCode + ", resultCode: " + resultCode);
        }
        else{
            Log.d("myTag","requestCode: " + requestCode + ", resultCode: " + resultCode);
        }
    }

}
