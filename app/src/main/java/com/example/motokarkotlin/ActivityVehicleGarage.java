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

public class ActivityVehicleGarage extends AppCompatActivity implements View.OnClickListener{

    ImageView vehiclepicture;
    TextView vehiclename, manufactureYear, carType, transmissionType, seatText, vehiclePrice;

    String vehicleId, vehiclePicture, vehicleName, vehicleYear,
            vehicleConfig, vehicleTransmission, vehicleSeats, vehicleRate;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.vehicleDetailsBack:
                finish();
                break;
//            case R.id.DeleteCarButton:
//                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_garage);

        ImageView vehicleDetailsBack = findViewById(R.id.vehicleDetailsBack);
        vehicleDetailsBack.setOnClickListener(this);

//        Button button = findViewById(R.id.DeleteCarButton);
//        button.setOnClickListener(this);

        vehiclepicture = findViewById(R.id.vehiclepicture);
        vehiclename = findViewById(R.id.vehiclename);
        manufactureYear = findViewById(R.id.manufactureYear);
        carType = findViewById(R.id.carType);
        vehiclePrice = findViewById(R.id.vehiclePrice);
        transmissionType = findViewById(R.id.transmissionType);
        seatText = findViewById(R.id.seatText);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                vehicleId = extras.getString("vehicleId");
                vehiclePicture = extras.getString("vehiclePicture");
                vehicleName = extras.getString("vehicleName");
                vehicleYear = extras.getString("vehicleYear");
                vehicleConfig = extras.getString("vehicleConfig");
                vehicleTransmission = extras.getString("vehicleTransmission");
                vehicleSeats = extras.getString("vehicleSeats");
                vehicleRate = extras.getString("vehicleRate");
            }
        } else {
            vehicleId = savedInstanceState.getSerializable("vehicleId").toString();
            vehiclePicture = savedInstanceState.getSerializable("vehiclePicture").toString();
            vehicleName = savedInstanceState.getSerializable("vehicleName").toString();
            vehicleYear = savedInstanceState.getSerializable("vehicleYear").toString();
            vehicleConfig = savedInstanceState.getSerializable("vehicleConfig").toString();
            vehicleTransmission = savedInstanceState.getSerializable("vehicleTransmission").toString();
            vehicleSeats = savedInstanceState.getSerializable("vehicleSeats").toString();
            vehicleRate = savedInstanceState.getSerializable("vehicleRate").toString();
        }

        Picasso.get().load(vehiclePicture).into(vehiclepicture);
        vehiclename.setText(vehicleName);
        manufactureYear.setText(vehicleYear);
        carType.setText(vehicleConfig);
        transmissionType.setText(vehicleTransmission);
        seatText.setText(vehicleSeats);
        vehiclePrice.setText("RM" + vehicleRate + "/HOUR");

    }
}
