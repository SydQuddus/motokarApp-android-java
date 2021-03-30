package com.example.motokarkotlin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class GarageVehicleFragment extends Fragment implements View.OnClickListener {

    DatabaseReference mRef;
    GarageVehicleAdapter garageVehicleAdapter;
    RecyclerView garageVehicleRecycler;
    RegistrationCache cacheObj;

    public void onClick(View view) {
//        Intent intent;
//
//        switch(view.getId()) {
//            case R.id.rentedVehiclePicture1:
//                intent = new Intent(getActivity(), ActivityVehicleGarage.class);
//                startActivity(intent);
//                break;
//
//        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view =  inflater.inflate(R.layout.activity_garage_vehicle_fragment, container, false);
        garageVehicleRecycler = view.findViewById(R.id.garageVehicleRecycler);

        final ArrayList<String> vehicleIdArray = new ArrayList<>();
        final ArrayList<String> vehicleNameArray = new ArrayList<>();
        final ArrayList<String> vehicleRateArray = new ArrayList<>();
        final ArrayList<String> vehicleRCArray = new ArrayList<>();
        final ArrayList<String> vehicleReviewArray = new ArrayList<>();
        final ArrayList<String> vehiclePictureArray = new ArrayList<>();
        final ArrayList<String> vehicleYearArray = new ArrayList<>();
        final ArrayList<String> vehicleConfigArray = new ArrayList<>();
        final ArrayList<String> vehicleTransmissionArray = new ArrayList<>();
        final ArrayList<String> vehicleSeatsArray = new ArrayList<>();

        mRef = FirebaseDatabase.getInstance().getReference("Vehicle");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (cacheObj.username.equals(snapshot.child("username").getValue().toString())) {
                        vehicleIdArray.add(snapshot.child("vehicleId").getValue().toString());
                        vehicleNameArray.add(snapshot.child("vehicleName").getValue().toString());
                        vehicleRateArray.add(snapshot.child("vehicleRate").getValue().toString());
                        vehicleRCArray.add(snapshot.child("vehicleReviewCum").getValue().toString());
                        vehicleReviewArray.add(snapshot.child("vehicleReview").getValue().toString());
                        vehiclePictureArray.add(snapshot.child("vehiclePicture").getValue().toString());
                        vehicleYearArray.add(snapshot.child("vehicleYear").getValue().toString());
                        vehicleConfigArray.add(snapshot.child("vehicleConfig").getValue().toString());
                        vehicleTransmissionArray.add(snapshot.child("vehicleTransmission").getValue().toString());
                        vehicleSeatsArray.add(snapshot.child("vehicleSeats").getValue().toString());
                    }
                }

                garageVehicleRecycler.setHasFixedSize(true);
                garageVehicleRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                garageVehicleAdapter = new GarageVehicleAdapter(getContext(),
                        vehicleIdArray,
                        vehicleNameArray,
                        vehicleRateArray,
                        vehicleRCArray,
                        vehicleReviewArray,
                        vehiclePictureArray,
                        vehicleYearArray,
                        vehicleConfigArray,
                        vehicleTransmissionArray,
                        vehicleSeatsArray
                         );

                garageVehicleRecycler.setAdapter(garageVehicleAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        return view;
    }
}


