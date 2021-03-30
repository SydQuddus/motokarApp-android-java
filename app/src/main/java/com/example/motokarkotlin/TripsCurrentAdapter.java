package com.example.motokarkotlin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TripsCurrentAdapter extends RecyclerView.Adapter<TripsCurrentAdapter.ViewHolder> {

    private List<String> gVehicleId, gVehicleDuration;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;

    // data is passed into the constructor
    TripsCurrentAdapter(Context context, List<String> vehicleId, List<String> vehicleDuration) {
        this.mInflater = LayoutInflater.from(context);
        this.gVehicleId = vehicleId;
        this.gVehicleDuration = vehicleDuration;
        this.mContext = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.listcurrent, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        String vehicleIdString = gVehicleId.get(position);
        String vehicleDurationString = gVehicleDuration.get(position);

        DatabaseReference vehicleRef = FirebaseDatabase.getInstance().getReference("Vehicle").child(vehicleIdString);

        vehicleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Picasso.get().load(dataSnapshot.child("vehiclePicture").getValue().toString()).into(holder.rentedVehiclePicture);
                holder.rentedVehicleName.setText(dataSnapshot.child("vehicleName").getValue().toString());
                holder.rentalPrice.setText("RM" + dataSnapshot.child("vehicleRate").getValue().toString() + "/HOUR");

                DatabaseReference accountsRef = FirebaseDatabase.getInstance().getReference("Accounts").child(dataSnapshot.child("username").getValue().toString());

                accountsRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        holder.personName.setText(dataSnapshot.child("firstName").getValue().toString() + " " + dataSnapshot.child("lastName").getValue().toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }

                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

        });

        holder.rentDuration.setText(vehicleDurationString);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return gVehicleId.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView rentedVehiclePicture;
        TextView rentedVehicleName, personName, rentDuration, rentalPrice;

        ViewHolder(View itemView) {
            super(itemView);
            rentedVehiclePicture = itemView.findViewById(R.id.rentedVehiclePicture);
            rentalPrice = itemView.findViewById(R.id.rentalPrice);
            rentedVehicleName = itemView.findViewById(R.id.rentedVehicleName);
            personName = itemView.findViewById(R.id.personName);
            rentDuration = itemView.findViewById(R.id.rentDuration);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            Intent intent = new Intent(mContext, ActivityCurrentTrip.class);
            intent.putExtra("vehicleId",gVehicleId.get(getAdapterPosition()));
            intent.putExtra("vehicleDuration",gVehicleDuration.get(getAdapterPosition()));
            mContext.startActivity(intent);
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return gVehicleId.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
