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

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    private List<String> gIdArray, gNameArray, gRateArray, gCumRateArray, gReviewArray,
            gPictureArray, gConfigArray, gDescriptionArray, gDoorsArray, gRateDayArray,
            gRateWeekArray, gRateMonthArray, gSeatsArray, gTransmissionArray, gYearArray,
            gUsernameArray;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;

    // data is passed into the constructor
    ExploreAdapter(Context context,
                   List<String> idArray,
                   List<String> nameArray,
                   List<String> rateArray,
                   List<String> cumRateArray,
                   List<String> reviewArray,
                   List<String> pictureArray,
                   List<String> configArray,
                   List<String> descriptionArray,
                   List<String> doorsArray,
                   List<String> rateDayArray,
                   List<String> rateWeekArray,
                   List<String> rateMonthArray,
                   List<String> seatsArray,
                   List<String> transmissionArray,
                   List<String> yearArray,
                   List<String> usernameArray
                   ) {
        this.mInflater = LayoutInflater.from(context);
        this.gIdArray = idArray;
        this.gNameArray = nameArray;
        this.gRateArray = rateArray;
        this.gCumRateArray = cumRateArray;
        this.gReviewArray = reviewArray;
        this.gPictureArray = pictureArray;
        this.gConfigArray = configArray;
        this.gDescriptionArray = descriptionArray;
        this.gDoorsArray = doorsArray;
        this.gRateDayArray = rateDayArray;
        this.gRateWeekArray = rateWeekArray;
        this.gRateMonthArray = rateMonthArray;
        this.gSeatsArray = seatsArray;
        this.gTransmissionArray = transmissionArray;
        this.gYearArray = yearArray;
        this.gUsernameArray = usernameArray;
        this.mContext = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_explore_vehicles, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        String vehicleNameString = gNameArray.get(position);
        String vehicleRateString = "RM" + gRateArray.get(position) + "/hour";
        String vehicleRCString = gCumRateArray.get(position);
        String vehicleReviewString = gReviewArray.get(position);
        String vehiclePictureString = gPictureArray.get(position);
        Picasso.get().load(vehiclePictureString).into(holder.vehiclePicture);

        holder.vehicleName.setText(vehicleNameString);
        holder.vehicleRate.setText(vehicleRateString);
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
        return gIdArray.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView vehicleName, vehicleRate, ratingNum;
        ImageView ratingVehicle1, ratingVehicle2, ratingVehicle3, ratingVehicle4, ratingVehicle5;
        ImageView vehiclePicture;

        ViewHolder(View itemView) {
            super(itemView);
            vehicleName = itemView.findViewById(R.id.vehicleName);
            vehicleRate = itemView.findViewById(R.id.vehicleRate);
            ratingNum = itemView.findViewById(R.id.ratingNum);
            ratingVehicle1 = itemView.findViewById(R.id.ratingVehicle1);
            ratingVehicle2 = itemView.findViewById(R.id.ratingVehicle2);
            ratingVehicle3 = itemView.findViewById(R.id.ratingVehicle3);
            ratingVehicle4 = itemView.findViewById(R.id.ratingVehicle4);
            ratingVehicle5 = itemView.findViewById(R.id.ratingVehicle5);
            vehiclePicture = itemView.findViewById(R.id.vehiclePicture);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

//            if (mClickListener != null)
//                mClickListener.onItemClick(view, getAdapterPosition());

            Intent intent = new Intent(mContext, VehicleDetailsActivity.class);
            intent.putExtra("vehicleId", gIdArray.get(getAdapterPosition()));
            intent.putExtra("vehicleName", gNameArray.get(getAdapterPosition()));
            intent.putExtra("vehiclePicture", gPictureArray.get(getAdapterPosition()));
            intent.putExtra("vehicleReview", gReviewArray.get(getAdapterPosition()));
            intent.putExtra("vehicleRate", gRateArray.get(getAdapterPosition()));
            intent.putExtra("vehicleConfig", gConfigArray.get(getAdapterPosition()));
            intent.putExtra("vehicleDescription", gDescriptionArray.get(getAdapterPosition()));
            intent.putExtra("vehicleDoors", gDoorsArray.get(getAdapterPosition()));
            intent.putExtra("vehicleRateDay", gRateDayArray.get(getAdapterPosition()));
            intent.putExtra("vehicleRateWeek", gRateWeekArray.get(getAdapterPosition()));
            intent.putExtra("vehicleRateMonth", gRateMonthArray.get(getAdapterPosition()));
            intent.putExtra("vehicleSeats", gSeatsArray.get(getAdapterPosition()));
            intent.putExtra("vehicleTransmission", gTransmissionArray.get(getAdapterPosition()));
            intent.putExtra("vehicleYear", gYearArray.get(getAdapterPosition()));
            intent.putExtra("username", gUsernameArray.get(getAdapterPosition()));

            mContext.startActivity(intent);
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return gIdArray.get(id);
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
