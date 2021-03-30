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

public class TripsPastFragment extends Fragment implements View.OnClickListener {

    public void onClick(View view) {
        Intent intent;

        switch(view.getId()) {
            case R.id.rentedVehiclePicture1:
                intent = new Intent(getActivity(), ActivityPastTrip.class);
                startActivity(intent);
                break;

        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view =  inflater.inflate(R.layout.activity_trips_past_fragment, container, false);

        ImageView rentedVehiclePicture1 = view.findViewById(R.id.rentedVehiclePicture1);
        rentedVehiclePicture1.setImageResource(R.drawable.toyotacar);
        rentedVehiclePicture1.setOnClickListener(this);

        TextView rentedVehicleName1 = view.findViewById(R.id.rentedVehicleName1);
        rentedVehicleName1.setText("Toyota Vois - 2014");

        TextView rentedVehicleReview1 = view.findViewById(R.id.rentedVehicleReview1);
        rentedVehicleReview1.setText("Review");

        TextView rentedVehicleRentee1 = view.findViewById(R.id.rentedVehicleRentee1);
        rentedVehicleRentee1.setText("Owned By : Mr. Abdullah");

        TextView rentedVehicleDate1 = view.findViewById(R.id.rentedVehicleDate1);
        rentedVehicleDate1.setText("Date : Thursday, 10:00 a.m. 29th July, 2019");


        ImageView rentedVehiclePicture2 = view.findViewById(R.id.rentedVehiclePicture2);
        rentedVehiclePicture2.setImageResource(R.drawable.motorbike1);

        TextView rentedVehicleName2 = view.findViewById(R.id.rentedVehicleName2);
        rentedVehicleName2.setText("Yamaha - 2015");

        TextView rentedVehicleReview2 = view.findViewById(R.id.rentedVehicleReview2);
        rentedVehicleReview2.setText("Reviewed");

        TextView rentedVehicleRentee2 = view.findViewById(R.id.rentedVehicleRentee2);
        rentedVehicleRentee2.setText("Owned By : Mr. Rahim");

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TripsPastFragment extends Fragment implements View.OnClickListener {

    DatabaseReference mRef;
    TripsPastAdapter tripsPastAdapter;
    RecyclerView tripsPastRecycler;
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

        final View view =  inflater.inflate(R.layout.activity_trips_past_fragment, container, false);
        tripsPastRecycler = view.findViewById(R.id.tripsPastRecycler);

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        final SimpleDateFormat myFormat = new SimpleDateFormat("E, d MMM yyyy");
        myFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        final String todayDate = myFormat.format(today);

        final ArrayList<String> vehicleIdArray = new ArrayList<>();
        final ArrayList<String> vehicleDurationArray = new ArrayList<>();
        final ArrayList<String> vehiclePriceArray = new ArrayList<>();
        mRef = FirebaseDatabase.getInstance().getReference("VehicleBook");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    int dateCompare = 0;

                    try {
                        Date date1 = myFormat.parse(todayDate);
                        Date date2 = myFormat.parse(snapshot.child("endDate").getValue().toString());

                        dateCompare = date1.compareTo(date2);

                    } catch (ParseException e) {

                    }

                    if (cacheObj.username.equals(snapshot.child("username").getValue().toString()) && dateCompare > 0 && snapshot.child("bookStatus").getValue().toString().equals("accepted")) {
                        vehicleIdArray.add(snapshot.child("vehicleId").getValue().toString());
                        vehicleDurationArray.add(snapshot.child("startDate").getValue().toString() + " - " + snapshot.child("endDate").getValue().toString());
                        vehiclePriceArray.add(snapshot.child("totalPrice").getValue().toString());
                    }
                }

                tripsPastRecycler.setHasFixedSize(true);
                tripsPastRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                tripsPastAdapter = new TripsPastAdapter(getContext(), vehicleIdArray, vehicleDurationArray, vehiclePriceArray);
                tripsPastRecycler.setAdapter(tripsPastAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

        });

        return view;
    }
}



