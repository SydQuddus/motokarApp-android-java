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


public class TripsFragment extends Fragment{

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_trips_current:
                    selectedFragment = new TripsCurrentFragment();
                    break;
//                case R.id.navigation_trips_upcoming:
//                    selectedFragment = new TripsUpcomingFragment();
//                    break;
                case R.id.navigation_trips_past:
                    selectedFragment = new TripsPastFragment();
                    break;

            }

            getChildFragmentManager().beginTransaction().replace(R.id.mainTripsFrame, selectedFragment).commit();
            return true;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_trips_main, container, false);
        BottomNavigationView tripsNavView = view.findViewById(R.id.tripsNavView);
        tripsNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        TripsCurrentFragment childFragment = new TripsCurrentFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.mainTripsFrame, childFragment).commit();

        return view;
    }


}
