package com.example.motokarkotlin;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.Arrays;
import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {

    private List<String> gUsernameArray;
    private List<String> gImagesArray;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    Context gContext;

    // data is passed into the constructor
    StoriesAdapter(Context context, List<String> usernameArray, List<String> imagesArray) {
        this.mInflater = LayoutInflater.from(context);
        this.gUsernameArray = usernameArray;
        this.gImagesArray = imagesArray;
        this.gContext = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_explore_stories, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String storiesNameString = gUsernameArray.get(position);
        holder.storiesName.setText(storiesNameString);

        String storiesUsername = gUsernameArray.get(position);

        DatabaseReference userStories = FirebaseDatabase.getInstance().getReference("Accounts").child(storiesUsername);

        userStories.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    holder.storiesName.setText(dataSnapshot.child("firstName").getValue().toString() + " " + dataSnapshot.child("lastName").getValue().toString());
                    Picasso.get().load(dataSnapshot.child("profilePicture").getValue().toString()).into(holder.reviewPicture);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }

        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return gUsernameArray.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView storiesName;
        CircleImageView reviewPicture;

        ViewHolder(View itemView) {
            super(itemView);
            storiesName = itemView.findViewById(R.id.storiesName);
            reviewPicture = itemView.findViewById(R.id.reviewPicture);
            reviewPicture.setOnClickListener(this);
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            Intent intent = new Intent(gContext, StoriesActivity.class);
            intent.putExtra("imagesString",gImagesArray.get(getAdapterPosition()));
            gContext.startActivity(intent);
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return gUsernameArray.get(id);
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
