package com.example.motokarkotlin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GarageVehicleAdapter extends RecyclerView.Adapter<GarageVehicleAdapter.ViewHolder> {

    private List<String> gId, gVehicleName,gVehicleRateArray,gCumRateArray, gReviewArray,
            gVehiclePictureArray, gYear, gConfig, gTransmission, gSeats;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;

    // data is passed into the constructor
    GarageVehicleAdapter(Context context, List<String> vId, List<String> vNameData, List<String> vRateData, List<String> cumRateArray,
                         List<String> reviewArray, List<String> vPictureData, List<String> vYear,
                         List<String> vConfig, List<String> vTransmission, List<String> vSeats) {
        this.mInflater = LayoutInflater.from(context);
        this.gId = vId;
        this.gVehicleName = vNameData;
        this.gVehicleRateArray = vRateData;
        this.gCumRateArray = cumRateArray;
        this.gReviewArray = reviewArray;
        this.gVehiclePictureArray = vPictureData;
        this.gYear = vYear;
        this.gConfig = vConfig;
        this.gTransmission = vTransmission;
        this.gSeats = vSeats;
        this.mContext = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.listvehicle, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String vehicleNameString = gVehicleName.get(position);
        String vehicleRateString = "RM" + gVehicleRateArray.get(position) + "/hour";
        String vehicleRCString = gCumRateArray.get(position);
        String vehicleReviewString = gReviewArray.get(position);
        String vehiclePictureString = gVehiclePictureArray.get(position);
        Picasso.get().load(vehiclePictureString).into(holder.rentedVehiclePicture);

        holder.rentedVehicleName.setText(vehicleNameString);
        holder.rentedVehicleReview.setText(vehicleRateString);
        holder.ratingNum.setText(vehicleRCString);

        if(vehicleReviewString.equals("0.5")){
            holder.ratingVehicle1.setImageResource(R.drawable.ic_star_half_black_24dp);
            holder.ratingVehicle2.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.ratingVehicle3.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.ratingVehicle4.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.ratingVehicle5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(vehicleReviewString.equals("1")){
            holder.ratingVehicle1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle2.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.ratingVehicle3.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.ratingVehicle4.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.ratingVehicle5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(vehicleReviewString.equals("1.5")){
            holder.ratingVehicle1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle2.setImageResource(R.drawable.ic_star_half_black_24dp);
            holder.ratingVehicle3.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.ratingVehicle4.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.ratingVehicle5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(vehicleReviewString.equals("2")){
            holder.ratingVehicle1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle2.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle3.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.ratingVehicle4.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.ratingVehicle5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(vehicleReviewString.equals("2.5")){
            holder.ratingVehicle1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle2.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle3.setImageResource(R.drawable.ic_star_half_black_24dp);
            holder.ratingVehicle4.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.ratingVehicle5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(vehicleReviewString.equals("3")){
            holder.ratingVehicle1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle2.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle3.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle4.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.ratingVehicle5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(vehicleReviewString.equals("3.5")){
            holder.ratingVehicle1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle2.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle3.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle4.setImageResource(R.drawable.ic_star_half_black_24dp);
            holder.ratingVehicle5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(vehicleReviewString.equals("4")){
            holder.ratingVehicle1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle2.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle3.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle4.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(vehicleReviewString.equals("4.5")){
            holder.ratingVehicle1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle2.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle3.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle4.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle5.setImageResource(R.drawable.ic_star_half_black_24dp);
        }else if(vehicleReviewString.equals("5")){
            holder.ratingVehicle1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle2.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle3.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle4.setImageResource(R.drawable.ic_star_black_24dp);
            holder.ratingVehicle5.setImageResource(R.drawable.ic_star_black_24dp);
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return gVehicleName.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView rentedVehicleName,rentedVehicleReview,ratingNum;
        ImageView ratingVehicle1, ratingVehicle2, ratingVehicle3, ratingVehicle4, ratingVehicle5;
        ImageView rentedVehiclePicture;

        ViewHolder(View itemView) {
            super(itemView);
            rentedVehicleName = itemView.findViewById(R.id.rentedVehicleName);
            rentedVehicleReview = itemView.findViewById(R.id.rentedVehicleReview);
            ratingNum = itemView.findViewById(R.id.ratingNum);
            ratingVehicle1 = itemView.findViewById(R.id.ratingVehicle1);
            ratingVehicle2 = itemView.findViewById(R.id.ratingVehicle2);
            ratingVehicle3 = itemView.findViewById(R.id.ratingVehicle3);
            ratingVehicle4 = itemView.findViewById(R.id.ratingVehicle4);
            ratingVehicle5 = itemView.findViewById(R.id.ratingVehicle5);
            rentedVehiclePicture = itemView.findViewById(R.id.rentedVehiclePicture);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            Intent intent = new Intent(mContext, ActivityVehicleGarage.class);
            intent.putExtra("vehicleId",gId.get(getAdapterPosition()));
            intent.putExtra("vehiclePicture",gVehiclePictureArray.get(getAdapterPosition()));
            intent.putExtra("vehicleName",gVehicleName.get(getAdapterPosition()));
            intent.putExtra("vehicleYear",gYear.get(getAdapterPosition()));
            intent.putExtra("vehicleConfig",gConfig.get(getAdapterPosition()));
            intent.putExtra("vehicleTransmission",gTransmission.get(getAdapterPosition()));
            intent.putExtra("vehicleSeats",gSeats.get(getAdapterPosition()));
            intent.putExtra("vehicleRate",gVehicleRateArray.get(getAdapterPosition()));
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
