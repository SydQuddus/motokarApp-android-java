/*
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

public class GarageBookingFragment extends Fragment implements View.OnClickListener {

    public void onClick(View view) {
        Intent intent;

        switch(view.getId()) {
            case R.id.rentedVehiclePicture1:
                intent = new Intent(getActivity(), ActivityBookingGarage.class);
                startActivity(intent);
                break;

        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view =  inflater.inflate(R.layout.fragment_garage_booking, container, false);

        ImageView rentedVehiclePicture1 = view.findViewById(R.id.rentedVehiclePicture1);
        rentedVehiclePicture1.setImageResource(R.drawable.protoncar);
        rentedVehiclePicture1.setOnClickListener(this);

        TextView rentedVehicleName1 = view.findViewById(R.id.rentedVehicleName1);
        rentedVehicleName1.setText("Proton Saga FLX - 2017");

        TextView rentedVehicleReview1 = view.findViewById(R.id.rentedVehicleReview1);
        rentedVehicleReview1.setText("RM7/hour");

        TextView rentedVehicleRentee1 = view.findViewById(R.id.rentedVehicleRentee1);
        rentedVehicleRentee1.setText("Booked By : Mr. Carl");

        TextView rentedVehicleDate1 = view.findViewById(R.id.rentedVehicleDate1);
        rentedVehicleDate1.setText("Date : Thursday, 10:00 a.m. 29th July, 2019");


        ImageView rentedVehiclePicture2 = view.findViewById(R.id.rentedVehiclePicture2);
        rentedVehiclePicture2.setImageResource(R.drawable.kancilcar);

        TextView rentedVehicleName2 = view.findViewById(R.id.rentedVehicleName2);
        rentedVehicleName2.setText("Perodua Kancil - 2005");

        TextView rentedVehicleReview2 = view.findViewById(R.id.rentedVehicleReview2);
        rentedVehicleReview2.setText("RM4/hour");

        TextView rentedVehicleRentee2 = view.findViewById(R.id.rentedVehicleRentee2);
        rentedVehicleRentee2.setText("Booked By : Mr. John");

        TextView rentedVehicleDate2 = view.findViewById(R.id.rentedVehicleDate2);
        rentedVehicleDate2.setText("Date : Wednesday, 11:00 a.m. 12th July, 2019");
        return view;
    }
}
*/

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

public class GarageBookingFragment extends Fragment implements View.OnClickListener {

    DatabaseReference vRef, mRef;
    GarageBookingAdapter garageBookingAdapter;
    RecyclerView garageBookingRecycler;
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

        final View view =  inflater.inflate(R.layout.fragment_garage_booking, container, false);
        garageBookingRecycler = view.findViewById(R.id.garageBookingRecycler);

        final ArrayList<String> vehicleIds = new ArrayList<>();

        final ArrayList<String> bookIdArray = new ArrayList<>();
        final ArrayList<String> bookStatusArray = new ArrayList<>();
        final ArrayList<String> startDateArray = new ArrayList<>();
        final ArrayList<String> endDateArray = new ArrayList<>();
        final ArrayList<String> messageArray = new ArrayList<>();
        final ArrayList<String> totalPriceArray = new ArrayList<>();
        final ArrayList<String> usernameArray = new ArrayList<>();
        final ArrayList<String> vehicleIdArray = new ArrayList<>();

        vRef = FirebaseDatabase.getInstance().getReference("Vehicle");
        vRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(snapshot.child("username").getValue().toString().equals(cacheObj.username)) {
                        vehicleIds.add(snapshot.child("vehicleId").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRef = FirebaseDatabase.getInstance().getReference("VehicleBook");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(vehicleIds.contains(snapshot.child("vehicleId").getValue().toString())){

                        bookIdArray.add(snapshot.child("bookId").getValue().toString());
                        bookStatusArray.add(snapshot.child("bookStatus").getValue().toString());
                        startDateArray.add(snapshot.child("startDate").getValue().toString());
                        endDateArray.add(snapshot.child("endDate").getValue().toString());
                        messageArray.add(snapshot.child("message").getValue().toString());
                        totalPriceArray.add(snapshot.child("totalPrice").getValue().toString());
                        usernameArray.add(snapshot.child("username").getValue().toString());
                        vehicleIdArray.add(snapshot.child("vehicleId").getValue().toString());
                    }

                }

                garageBookingRecycler.setHasFixedSize(true);
                garageBookingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                garageBookingAdapter = new GarageBookingAdapter(getContext(),
                        bookIdArray,
                        bookStatusArray,
                        startDateArray,
                        endDateArray,
                        messageArray,
                        totalPriceArray,
                        usernameArray,
                        vehicleIdArray
                         );
                garageBookingRecycler.setAdapter(garageBookingAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

        });

        return view;
    }
}



