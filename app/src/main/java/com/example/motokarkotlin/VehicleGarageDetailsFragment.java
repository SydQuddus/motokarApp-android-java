package com.example.motokarkotlin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.firebase.database.*;
import com.squareup.picasso.Picasso;
import org.w3c.dom.Text;

public class VehicleGarageDetailsFragment extends Fragment {

    RegistrationCache cacheObj;
    DatabaseReference databaseAccount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        databaseAccount = FirebaseDatabase.getInstance().getReference("Vehicle");

        final View view =  inflater.inflate(R.layout.fragment_vehicle_garage_details, container, false);

        final ImageView vImage = view.findViewById(R.id.vehiclepicture);
        final TextView vName = view.findViewById(R.id.vehiclename);
        final TextView vRate = view.findViewById(R.id.vehiclerate);
        final TextView MakeYear = view.findViewById(R.id.rentDateTime);
        final TextView vType = view.findViewById(R.id.PickupLocDetails);
        final TextView vTransmission = view.findViewById(R.id.durationDetails);
        final TextView vSeats = view.findViewById(R.id.RentDurationDetails);

        databaseAccount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (cacheObj.username.equals(snapshot.child("username").getValue().toString())) {

                        vName.setText(dataSnapshot.child("vehicleName").getValue().toString());
                        vRate.setText(dataSnapshot.child("vehicleRate").getValue().toString());
                        MakeYear.setText(dataSnapshot.child("vehicleYear").getValue().toString());
                        vType.setText(dataSnapshot.child("vehicleConfig").getValue().toString());
                        vTransmission.setText(dataSnapshot.child("vehicleTransmission").getValue().toString());
                        vSeats.setText(dataSnapshot.child("vehicleSeats").getValue().toString());
                        Picasso.get().load(dataSnapshot.child("vehiclePicture").getValue().toString()).into(vImage);


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.d("myTag","there was an error");

            }
        });
        return view;
    }
}
