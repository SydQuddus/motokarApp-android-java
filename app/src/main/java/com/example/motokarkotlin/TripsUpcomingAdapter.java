package com.example.motokarkotlin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TripsUpcomingAdapter extends RecyclerView.Adapter<TripsUpcomingAdapter.ViewHolder> {

    private List<String> gVehicleName;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;

    // data is passed into the constructor
    TripsUpcomingAdapter(Context context, List<String> vNameData) {
        this.mInflater = LayoutInflater.from(context);
        this.gVehicleName = vNameData;
        this.mContext = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.listupcoming, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String vehicleNameString = gVehicleName.get(position);
        holder.rentedVehicleName.setText(vehicleNameString);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return gVehicleName.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView rentedVehicleName;

        ViewHolder(View itemView) {
            super(itemView);
            rentedVehicleName = itemView.findViewById(R.id.rentedVehicleName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            Intent intent = new Intent(mContext, ActivityUpcomingTrip.class);
            mContext.startActivity(intent);
        }


    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return gVehicleName.get(id);
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