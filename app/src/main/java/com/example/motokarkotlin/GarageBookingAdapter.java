package com.example.motokarkotlin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.List;

public class GarageBookingAdapter extends RecyclerView.Adapter<GarageBookingAdapter.ViewHolder> {

    private List<String> gBookIdArray,gBookStatusArray,gStartDateArray,gEndDateArray,
                        gMessageArray,gTotalPriceArray,gUsernameArray,gVehicleIdArray;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;

    // data is passed into the constructor
    GarageBookingAdapter(Context context,
                         List<String> bookIdArray,
                         List<String> bookStatusArray,
                         List<String> startDateArray,
                         List<String> endDateArray,
                         List<String> messageArray,
                         List<String> totalPriceArray,
                         List<String> usernameArray,
                         List<String> vehicleIdArray
                         ) {
        this.mInflater = LayoutInflater.from(context);
        this.gBookIdArray = bookIdArray;
        this.gBookStatusArray = bookStatusArray;
        this.gStartDateArray = startDateArray;
        this.gEndDateArray = endDateArray;
        this.gMessageArray = messageArray;
        this.gTotalPriceArray = totalPriceArray;
        this.gUsernameArray = usernameArray;
        this.gVehicleIdArray = vehicleIdArray;
        this.mContext = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.listbooking, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        DatabaseReference vehicleRef = FirebaseDatabase.getInstance().getReference("Vehicle")
                .child(gVehicleIdArray.get(position));

        vehicleRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                holder.rentedVehicleName.setText(dataSnapshot.child("vehicleName").getValue().toString());
                holder.rentalDate.setText("RM" + dataSnapshot.child("vehicleRate").getValue().toString() + "/hour");
                Picasso.get().load(dataSnapshot.child("vehiclePicture").getValue().toString()).into(holder.rentedVehiclePicture);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference accountsRef = FirebaseDatabase.getInstance().getReference("Accounts")
                .child(gUsernameArray.get(position));

        accountsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                holder.personName.setText(dataSnapshot.child("firstName").getValue().toString() + " " + dataSnapshot.child("lastName").getValue().toString());
                Picasso.get().load(dataSnapshot.child("profilePicture").getValue().toString()).into(holder.rentorProfileImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return gBookIdArray.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView rentedVehiclePicture;
        TextView rentedVehicleName,rentalDate,personName;
        CircleImageView rentorProfileImage;


        ViewHolder(View itemView) {
            super(itemView);
            rentedVehicleName = itemView.findViewById(R.id.rentedVehicleName);
            rentalDate = itemView.findViewById(R.id.rentalDate);
            rentedVehiclePicture = itemView.findViewById(R.id.rentedVehiclePicture);
            personName = itemView.findViewById(R.id.personName);
            rentorProfileImage = itemView.findViewById(R.id.rentorProfileImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            Intent intent = new Intent(mContext, ActivityBookingGarage.class);
            intent.putExtra("bookId",gBookIdArray.get(getAdapterPosition()));
            intent.putExtra("status",gBookStatusArray.get(getAdapterPosition()));
            intent.putExtra("startDate",gStartDateArray.get(getAdapterPosition()));
            intent.putExtra("endDate",gEndDateArray.get(getAdapterPosition()));
            intent.putExtra("message",gMessageArray.get(getAdapterPosition()));
            intent.putExtra("totalPrice",gTotalPriceArray.get(getAdapterPosition()));
            intent.putExtra("username",gUsernameArray.get(getAdapterPosition()));
            intent.putExtra("vehicleId",gVehicleIdArray.get(getAdapterPosition()));
            mContext.startActivity(intent);
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return gBookIdArray.get(id);
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
