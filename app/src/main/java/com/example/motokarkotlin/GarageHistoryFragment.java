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

public class GarageHistoryFragment extends Fragment implements View.OnClickListener {

    public void onClick(View view) {
        Intent intent;

        switch(view.getId()) {
            case R.id.rentedVehiclePicture1:
                intent = new Intent(getActivity(), ActivityHistoryGarage.class);
                startActivity(intent);
                break;

        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view =  inflater.inflate(R.layout.garage_history_fragment, container, false);

        ImageView rentedVehiclePicture1 = view.findViewById(R.id.rentedVehiclePicture1);
        rentedVehiclePicture1.setImageResource(R.drawable.protoncar);
        rentedVehiclePicture1.setOnClickListener(this);

        TextView rentedVehicleName1 = view.findViewById(R.id.rentedVehicleName1);
        rentedVehicleName1.setText("Proton Saga FLX - 2017");

        TextView rentedVehicleReview1 = view.findViewById(R.id.rentedVehicleReview1);
        rentedVehicleReview1.setText("Review");

        TextView rentedVehicleRentee1 = view.findViewById(R.id.rentedVehicleRentee1);
        rentedVehicleRentee1.setText("Rented By : Mr. Roy");

        TextView rentedVehicleDate1 = view.findViewById(R.id.rentedVehicleDate1);
        rentedVehicleDate1.setText("Date : Thursday, 10:00 a.m. 29th July, 2019");


        ImageView rentedVehiclePicture2 = view.findViewById(R.id.rentedVehiclePicture2);
        rentedVehiclePicture2.setImageResource(R.drawable.kancilcar);

        TextView rentedVehicleName2 = view.findViewById(R.id.rentedVehicleName2);
        rentedVehicleName2.setText("Perodua Kancil - 2005");

        TextView rentedVehicleReview2 = view.findViewById(R.id.rentedVehicleReview2);
        rentedVehicleReview2.setText("Reviewed");

        TextView rentedVehicleRentee2 = view.findViewById(R.id.rentedVehicleRentee2);
        rentedVehicleRentee2.setText("Rented By : Mr. Chris");

        TextView rentedVehicleDate2 = view.findViewById(R.id.rentedVehicleDate2);
        rentedVehicleDate2.setText("Date : Wednesday, 11:00 a.m. 12th July, 2019");


        ImageView rentedVehiclePicture3 = view.findViewById(R.id.rentedVehiclePicture3);
        rentedVehiclePicture3.setImageResource(R.drawable.gtr);

        TextView rentedVehicleName3 = view.findViewById(R.id.rentedVehicleName3);
        rentedVehicleName3.setText("Nissan GTR - 2014");

        TextView rentedVehicleReview3 = view.findViewById(R.id.rentedVehicleReview3);
        rentedVehicleReview3.setText("Review");

        TextView rentedVehicleRentee3 = view.findViewById(R.id.rentedVehicleRentee3);
        rentedVehicleRentee3.setText("Rented By : Mr. Ferguson");

        TextView rentedVehicleDate3 = view.findViewById(R.id.rentedVehicleDate3);
        rentedVehicleDate3.setText("Date : Saturday, 10:00 p.m. 10th July, 2019");
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
import com.google.firebase.database.*;

import java.util.ArrayList;

public class GarageHistoryFragment extends Fragment implements View.OnClickListener {

    DatabaseReference mRef;
    GarageHistoryAdapter garageHistoryAdapter;
    RecyclerView garageHistoryRecycler;

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

        final View view =  inflater.inflate(R.layout.garage_history_fragment, container, false);
        garageHistoryRecycler = view.findViewById(R.id.garageHistoryRecycler);

        final ArrayList<String> vehicleNameArray = new ArrayList<>();
        mRef = FirebaseDatabase.getInstance().getReference("Vehicle");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    vehicleNameArray.add(snapshot.child("vehicleName").getValue().toString());
                }

                garageHistoryRecycler.setHasFixedSize(true);
                garageHistoryRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                garageHistoryAdapter = new GarageHistoryAdapter(getContext(), vehicleNameArray);
                garageHistoryRecycler.setAdapter(garageHistoryAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

        });

//        ImageView rentedVehiclePicture1 = view.findViewById(R.id.rentedVehiclePicture1);
//        rentedVehiclePicture1.setImageResource(R.drawable.gtr);
//        rentedVehiclePicture1.setOnClickListener(this);
//
//        TextView rentedVehicleName1 = view.findViewById(R.id.rentedVehicleName1);
//        rentedVehicleName1.setText("Nissan GTR - 2014");
//
//        TextView rentedVehicleReview1 = view.findViewById(R.id.rentedVehicleReview1);
//        rentedVehicleReview1.setText("RM150/hour");
//
//        ImageView rentedVehiclePicture2 = view.findViewById(R.id.rentedVehiclePicture2);
//        rentedVehiclePicture2.setImageResource(R.drawable.protoncar);
//
//        TextView rentedVehicleName2 = view.findViewById(R.id.rentedVehicleName2);
//        rentedVehicleName2.setText("Proton Saga FLX - 2017");
//
//        TextView rentedVehicleReview2 = view.findViewById(R.id.rentedVehicleReview2);
//        rentedVehicleReview2.setText("RM7/hour");
//
//
//        ImageView rentedVehiclePicture3 = view.findViewById(R.id.rentedVehiclePicture3);
//        rentedVehiclePicture3.setImageResource(R.drawable.kancilcar);
//
//        TextView rentedVehicleName3 = view.findViewById(R.id.rentedVehicleName3);
//        rentedVehicleName3.setText("Perodua Kancil - 2005");
//
//        TextView rentedVehicleReview3 = view.findViewById(R.id.rentedVehicleReview3);
//        rentedVehicleReview3.setText("RM4.5/hour");
        return view;
    }
}








