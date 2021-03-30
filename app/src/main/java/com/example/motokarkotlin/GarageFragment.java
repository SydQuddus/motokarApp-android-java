package com.example.motokarkotlin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.*;
import java.util.ArrayList;


public class GarageFragment extends Fragment implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.AddVehicleButton:
                Intent intent = new Intent(getContext(),addVehicle.class);
                startActivity(intent);
                break;
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_garage_vehicle:
                    selectedFragment = new GarageVehicleFragment();
                    break;
                case R.id.navigation_garage_booking:
                    selectedFragment = new GarageBookingFragment();
                    break;
                /*case R.id.navigation_garage_history:
                    selectedFragment = new GarageHistoryFragment();
                    break;*/
            }

            getChildFragmentManager().beginTransaction().replace(R.id.mainGarageFrame, selectedFragment).commit();
            return true;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_garage_main, container, false);
        BottomNavigationView garageNavView = view.findViewById(R.id.garageNavView);
        garageNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        GarageVehicleFragment childFragment = new GarageVehicleFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.mainGarageFrame, childFragment).commit();

        Button AddVehicleButton = view.findViewById(R.id.AddVehicleButton);
        AddVehicleButton.setOnClickListener(this);

        return view;
    }


}
