/*package com.example.motokarkotlin;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TripsCurrentFragment extends Fragment implements View.OnClickListener{


    public void onClick(View view) {
        Intent intent;

        switch(view.getId()) {
            case R.id.rentedVehiclePicture1:
                intent = new Intent(getActivity(), ActivityCurrentTrip.class);
                startActivity(intent);
                break;

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        final View view =  inflater.inflate(R.layout.activity_trips_current_fragment, container, false);

        ImageView rentedVehiclePicture1 = view.findViewById(R.id.rentedVehiclePicture1);
        rentedVehiclePicture1.setImageResource(R.drawable.myvicar);
        rentedVehiclePicture1.setOnClickListener(this);

        TextView rentedVehicleName1 = view.findViewById(R.id.rentedVehicleName1);
        rentedVehicleName1.setText("Perodua Myvi - 2013");

        TextView rentedVehicleReview1 = view.findViewById(R.id.rentedVehicleReview1);
        rentedVehicleReview1.setText("Review");

        TextView rentedVehicleRentee1 = view.findViewById(R.id.rentedVehicleRentee1);
        rentedVehicleRentee1.setText("Return Location: Melana Apartment, Skudai, Johor");

        TextView rentedVehicleDate1 = view.findViewById(R.id.rentedVehicleDate1);
        rentedVehicleDate1.setText("Return Time: Friday, 11:00 a.m. 22th July, 2019");




        return view;
    }
}*/


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
import java.util.concurrent.TimeUnit;

public class TripsCurrentFragment extends Fragment implements View.OnClickListener {

    DatabaseReference mRef;
    TripsCurrentAdapter tripsCurrentAdapter;
    RecyclerView tripsCurrentRecycler;
    RegistrationCache cacheObj;


    public void onClick(View view) {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view =  inflater.inflate(R.layout.activity_trips_current_fragment, container, false);
        tripsCurrentRecycler = view.findViewById(R.id.tripsCurrentRecycler);

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        final SimpleDateFormat myFormat = new SimpleDateFormat("E, d MMM yyyy");
        myFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        final String todayDate = myFormat.format(today);

        final ArrayList<String> vehicleIdArray = new ArrayList<>();
        final ArrayList<String> vehicleDurationArray = new ArrayList<>();
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

                    if (cacheObj.username.equals(snapshot.child("username").getValue().toString()) && dateCompare <= 0 && snapshot.child("bookStatus").getValue().toString().equals("accepted")) {
                        vehicleIdArray.add(snapshot.child("vehicleId").getValue().toString());
                        vehicleDurationArray.add(snapshot.child("startDate").getValue().toString() + " - " + snapshot.child("endDate").getValue().toString());
                    }
                }

                tripsCurrentRecycler.setHasFixedSize(true);
                tripsCurrentRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                tripsCurrentAdapter = new TripsCurrentAdapter(getContext(),
                        vehicleIdArray,
                        vehicleDurationArray);
                tripsCurrentRecycler.setAdapter(tripsCurrentAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

        });

        return view;
    }
}



