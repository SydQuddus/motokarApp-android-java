package com.example.motokarkotlin.ViewHolder;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.motokarkotlin.R;

public class VehicleViewHolder extends RecyclerView.ViewHolder {

    //public LinearLayout list_vehicle;
    public TextView vehicleName, vehicleRate;

    public VehicleViewHolder(View itemView) {
        super(itemView);

        //list_vehicle = itemView.findViewById(R.id.list_vehicle);
        vehicleName = itemView.findViewById(R.id.vehicleName);
        vehicleRate = itemView.findViewById(R.id.vehicleRate);
    }

//    public void setVehicleName(String string) {
//        vehicleName = mView.findViewById(R.id.vehicleName);
//        vehicleName.setText(string);
//    }
//    public void setVehicleRate(String string) {
//        vehicleRate = mView.findViewById(R.id.vehicleRate);
//        vehicleRate.setText(string);
//    }

}
