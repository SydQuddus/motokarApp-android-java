package com.example.motokarkotlin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.*;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityCurrentTrip extends AppCompatActivity implements View.OnClickListener{

    ImageView vehicleDetailsBack;
    Button buttonBook;
    ImageView vehiclepicture;
    TextView vehiclename, vehiclerate, vehiclenames, vehiclerates, transmissionnames, personName,
            rentDateStart, rentDateEnd;
    CircleImageView rentorProfileImage;
    String vehicleId, vehicleDuration;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.vehicleDetailsBack:
                finish();
                break;
            case R.id.ReviewCarButton:
                Intent intent = new Intent(this,reviewpage.class);
                intent.putExtra("vehicleId",vehicleId);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_trip);

        vehicleDetailsBack = findViewById(R.id.vehicleDetailsBack);
        vehicleDetailsBack.setOnClickListener(this);

        buttonBook = findViewById(R.id.ReviewCarButton);
        buttonBook.setOnClickListener(this);

        vehiclepicture = findViewById(R.id.vehiclepicture);
        vehiclename = findViewById(R.id.vehiclename);
        vehiclerate = findViewById(R.id.vehiclerate);
        vehiclenames = findViewById(R.id.vehiclenames);
        vehiclerates = findViewById(R.id.vehiclerates);
        transmissionnames = findViewById(R.id.transmissionnames);
        personName = findViewById(R.id.personName);
        rentDateStart = findViewById(R.id.rentDateStart);
        rentDateEnd = findViewById(R.id.rentDateEnd);
        rentorProfileImage = findViewById(R.id.rentorProfileImage);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                vehicleId = extras.getString("vehicleId");
                vehicleDuration = extras.getString("vehicleDuration");
            }
        } else {
            vehicleId = savedInstanceState.getSerializable("vehicleId").toString();
            vehicleDuration = savedInstanceState.getSerializable("vehicleDuration").toString();
        }


        DatabaseReference vehicleRef = FirebaseDatabase.getInstance().getReference("Vehicle").child(vehicleId);

        vehicleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Picasso.get().load(dataSnapshot.child("vehiclePicture").getValue().toString()).into(vehiclepicture);
                vehiclename.setText(dataSnapshot.child("vehicleName").getValue().toString());
                vehiclerate.setText("RM" + dataSnapshot.child("vehicleRate").getValue().toString() + "/HOUR");
                vehiclenames.setText(dataSnapshot.child("vehicleConfig").getValue().toString());
                vehiclerates.setText(dataSnapshot.child("vehicleSeats").getValue().toString() + " seats");
                transmissionnames.setText(dataSnapshot.child("vehicleTransmission").getValue().toString());

                DatabaseReference accountsRef = FirebaseDatabase.getInstance().getReference("Accounts").child(dataSnapshot.child("username").getValue().toString());

                accountsRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        personName.setText(dataSnapshot.child("firstName").getValue().toString() + " " + dataSnapshot.child("lastName").getValue().toString());
                        Picasso.get().load(dataSnapshot.child("profilePicture").getValue().toString()).into(rentorProfileImage);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }

                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

        });

        String[] dates = vehicleDuration.split(" - ");

        rentDateStart.setText(dates[0]);
        rentDateEnd.setText(dates[1]);
    }
}
