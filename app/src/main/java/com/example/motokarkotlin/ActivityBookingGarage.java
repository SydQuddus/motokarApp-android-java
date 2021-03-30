package com.example.motokarkotlin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.motokarkotlin.Model.VehicleBook;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.*;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.UUID;

public class ActivityBookingGarage extends AppCompatActivity implements View.OnClickListener{

    ImageView vehiclepicture;
    TextView vehiclename, vehiclerate, personName, dateRentStart, dateRentEnd, messageText;
    CircleImageView rentorProfileImage;

    String bookId, status, startDate, endDate, message, totalPrice, username, vehicleId;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.vehicleDetailsBack:
                finish();
                break;
            case R.id.CancelBookingButton:
                DatabaseReference vehicleBookDB = FirebaseDatabase.getInstance().getReference("VehicleBook").child(bookId);

                vehicleBookDB.child("bookStatus").setValue("accepted");
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ActivityBookingGarage.this);
                dlgAlert.setMessage("A notification is sent to the user, thank you for using our service.");
                dlgAlert.setTitle("Vehicle Booking Accepted");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //dismiss the dialog
                            }
                        });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_garage);

        ImageView vehicleDetailsBack = findViewById(R.id.vehicleDetailsBack);
        vehicleDetailsBack.setOnClickListener(this);

        Button button = findViewById(R.id.CancelBookingButton);
        button.setOnClickListener(this);

        vehiclepicture = findViewById(R.id.vehiclepicture); //
        vehiclename = findViewById(R.id.vehiclename); //
        vehiclerate = findViewById(R.id.vehiclerate); //
        personName = findViewById(R.id.personName);
        dateRentStart = findViewById(R.id.dateRentStart); //
        dateRentEnd = findViewById(R.id.dateRentEnd); //
        rentorProfileImage = findViewById(R.id.rentorProfileImage);
        messageText = findViewById(R.id.messageText);

        View v = findViewById(R.id.bottomNav);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                bookId = extras.getString("bookId");
                status = extras.getString("status");
                startDate = extras.getString("startDate");
                endDate = extras.getString("endDate");
                message = extras.getString("message");
                totalPrice = extras.getString("totalPrice");
                username = extras.getString("username");
                vehicleId = extras.getString("vehicleId");

            }
        } else {
            bookId = savedInstanceState.getSerializable("bookId").toString();
            status = savedInstanceState.getSerializable("status").toString();
            startDate = savedInstanceState.getSerializable("startDate").toString();
            endDate = savedInstanceState.getSerializable("endDate").toString();
            message = savedInstanceState.getSerializable("message").toString();
            totalPrice = savedInstanceState.getSerializable("totalPrice").toString();
            username = savedInstanceState.getSerializable("username").toString();
            vehicleId = savedInstanceState.getSerializable("vehicleId").toString();
        }

        if(status.equals("booked")){
            v.setVisibility(View.VISIBLE);
        }

        messageText.setText(message);
        dateRentStart.setText(startDate);
        dateRentEnd.setText(endDate);

        DatabaseReference vehicleRef = FirebaseDatabase.getInstance().getReference("Vehicle")
                .child(vehicleId);

        vehicleRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                vehiclename.setText(dataSnapshot.child("vehicleName").getValue().toString());
                vehiclerate.setText("RM" + dataSnapshot.child("vehicleRate").getValue().toString() + "/hour");
                Picasso.get().load(dataSnapshot.child("vehiclePicture").getValue().toString()).into(vehiclepicture);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference accountsRef = FirebaseDatabase.getInstance().getReference("Accounts")
                .child(username);

        accountsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                personName.setText(dataSnapshot.child("firstName").getValue().toString() + " " + dataSnapshot.child("lastName").getValue().toString());
                Picasso.get().load(dataSnapshot.child("profilePicture").getValue().toString()).into(rentorProfileImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
