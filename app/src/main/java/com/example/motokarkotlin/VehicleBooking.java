package com.example.motokarkotlin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.example.motokarkotlin.Model.VehicleBook;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.*;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class VehicleBooking extends AppCompatActivity implements View.OnClickListener{

    String vehicleId = "",username = "",vehicleName = "",yearString = "",pictureString = "",vehicleDateStartString = "",
            vehicleDateEndString = "",rateDayString = "";

    RegistrationCache cacheObj;

    TextView priceText, vehicleDateStart, vehicleDateEnd;
    TextInputEditText messageText;

    public void onClick(View view){

        switch(view.getId()){
            case R.id.vehicleBookingBack:
                finish();
                break;
            case R.id.submitBookButton:
                bookVehicle();
                break;
        }
    }

    public void bookVehicle(){

        DatabaseReference vehicleBookDB = FirebaseDatabase.getInstance().getReference("VehicleBook");

        String bookId = "book" + UUID.randomUUID().toString();

        VehicleBook vehicleBook = new VehicleBook(bookId,
                "booked",
                vehicleDateEndString,
                vehicleDateStartString,
                priceText.getText().toString(),
                cacheObj.username,
                vehicleId,
                messageText.getText().toString());

        vehicleBookDB.child(bookId).setValue(vehicleBook);
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(VehicleBooking.this);
        dlgAlert.setMessage("Your request has been sent to the owner of the vehicle, you will be notified on your booking status shortly.");
        dlgAlert.setTitle("Booking Successful");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_booking);

        ImageView vehicleBookingBack = findViewById(R.id.vehicleBookingBack);
        vehicleBookingBack.setOnClickListener(this);
        Button submitBookButton = findViewById(R.id.submitBookButton);
        submitBookButton.setOnClickListener(this);

        TextView vehicleNameMain = findViewById(R.id.vehicleNameMain);
        final TextView vehicleYearBy = findViewById(R.id.vehicleYearBy);
        vehicleDateStart = findViewById(R.id.vehicleDateStart);
        vehicleDateEnd = findViewById(R.id.vehicleDateEnd);
        //TextView locationText = findViewById(R.id.locationText);
        priceText = findViewById(R.id.priceText);
        ImageView vehicleImage = findViewById(R.id.vehicleImage);
        messageText = findViewById(R.id.messageText);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                vehicleId = extras.getString("vehicleId");
                username = extras.getString("username");
                vehicleName = extras.getString("vehicleName");
                pictureString = extras.getString("vehiclePicture");
                yearString = extras.getString("vehicleYear");
                vehicleDateStartString = extras.getString("vehicleDateStartString");
                vehicleDateEndString = extras.getString("vehicleDateEndString");
                rateDayString = extras.getString("rateDayString");

            }
        } else {
            vehicleId = savedInstanceState.getSerializable("vehicleId").toString();
            username = savedInstanceState.getSerializable("username").toString();
            vehicleName = savedInstanceState.getSerializable("vehicleName").toString();
            pictureString = savedInstanceState.getSerializable("vehiclePicture").toString();
            yearString = savedInstanceState.getSerializable("vehicleYear").toString();
            vehicleDateStartString = savedInstanceState.getSerializable("vehicleDateStartString").toString();
            vehicleDateEndString = savedInstanceState.getSerializable("vehicleDateEndString").toString();
            rateDayString = savedInstanceState.getSerializable("rateDayString").toString();
        }

        DatabaseReference userVehicle = FirebaseDatabase.getInstance().getReference("Accounts").child(username);

        userVehicle.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                vehicleYearBy.setText("Year " + yearString + '\n' + "By " + dataSnapshot.child("firstName").getValue().toString() + " " + dataSnapshot.child("lastName").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        vehicleNameMain.setText(vehicleName);

        vehicleDateStart.setText("Start: " + vehicleDateStartString);
        vehicleDateEnd.setText("End: " + vehicleDateEndString);
        //locationText.setText("Senai, Johor, Malaysia");

        SimpleDateFormat myFormat = new SimpleDateFormat("E, d MMM yyyy");

        long days = 0;

        try {
            Date date1 = myFormat.parse(vehicleDateStartString);
            Date date2 = myFormat.parse(vehicleDateEndString);
            long diff = date2.getTime() - date1.getTime();
            days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
        }

        String priceTextString = "RM" + Long.toString(Long.parseLong(rateDayString) * days) + ".00";
        priceText.setText(priceTextString);
        Picasso.get().load(pictureString).into(vehicleImage);
    }

}
