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
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<String> gIdArray, gUsernameArray, gContentArray, gReviewArray;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;

    // data is passed into the constructor
    ReviewAdapter(Context context,
                  List<String> idArray,
                  List<String> usernameArray,
                  List<String> contentArray,
                  List<String> reviewArray
                   ) {
        this.mInflater = LayoutInflater.from(context);
        this.gIdArray = idArray;
        this.gReviewArray = reviewArray;
        this.gContentArray = contentArray;
        this.gUsernameArray = usernameArray;
        this.mContext = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_review, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        String reviewContentString = gContentArray.get(position);
        String reviewRateString = gReviewArray.get(position);
        String reviewUsername = gUsernameArray.get(position);

        DatabaseReference userReview = FirebaseDatabase.getInstance().getReference("Accounts").child(reviewUsername);

        userReview.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                holder.reviewName.setText(dataSnapshot.child("firstName").getValue().toString() + " " + dataSnapshot.child("lastName").getValue().toString());
                Picasso.get().load(dataSnapshot.child("profilePicture").getValue().toString()).into(holder.reviewPicture);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        holder.reviewText.setText(reviewContentString);

        if(reviewRateString.equals("0.5")){
            holder.userRating1.setImageResource(R.drawable.ic_star_half_black_24dp);
            holder.userRating2.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.userRating3.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.userRating4.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.userRating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewRateString.equals("1")){
            holder.userRating1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating2.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.userRating3.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.userRating4.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.userRating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewRateString.equals("1.5")){
            holder.userRating1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating2.setImageResource(R.drawable.ic_star_half_black_24dp);
            holder.userRating3.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.userRating4.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.userRating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewRateString.equals("2")){
            holder.userRating1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating2.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating3.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.userRating4.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.userRating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewRateString.equals("2.5")){
            holder.userRating1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating2.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating3.setImageResource(R.drawable.ic_star_half_black_24dp);
            holder.userRating4.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.userRating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewRateString.equals("3")){
            holder.userRating1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating2.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating3.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating4.setImageResource(R.drawable.ic_star_border_black_24dp);
            holder.userRating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewRateString.equals("3.5")){
            holder.userRating1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating2.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating3.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating4.setImageResource(R.drawable.ic_star_half_black_24dp);
            holder.userRating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewRateString.equals("4")){
            holder.userRating1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating2.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating3.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating4.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewRateString.equals("4.5")){
            holder.userRating1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating2.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating3.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating4.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating5.setImageResource(R.drawable.ic_star_half_black_24dp);
        }else if(reviewRateString.equals("5")){
            holder.userRating1.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating2.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating3.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating4.setImageResource(R.drawable.ic_star_black_24dp);
            holder.userRating5.setImageResource(R.drawable.ic_star_black_24dp);
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return gIdArray.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView reviewPicture;
        TextView reviewName, reviewText;
        ImageView userRating1,userRating2,userRating3,userRating4,userRating5;

        ViewHolder(View itemView) {
            super(itemView);
            reviewPicture = itemView.findViewById(R.id.reviewPicture);
            reviewName = itemView.findViewById(R.id.reviewName);
            reviewText = itemView.findViewById(R.id.reviewText);
            userRating1 = itemView.findViewById(R.id.userRating1);
            userRating2 = itemView.findViewById(R.id.userRating2);
            userRating3 = itemView.findViewById(R.id.userRating3);
            userRating4 = itemView.findViewById(R.id.userRating4);
            userRating5 = itemView.findViewById(R.id.userRating5);
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
