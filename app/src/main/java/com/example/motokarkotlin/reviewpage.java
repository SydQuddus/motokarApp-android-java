package com.example.motokarkotlin;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.example.motokarkotlin.Model.Review;
import com.google.firebase.database.*;

import java.util.UUID;

public class reviewpage extends AppCompatActivity implements View.OnClickListener {

    private ImageView backButton;
    EditText text;
    RatingBar ratingBar;
    String vehicleId;
    RegistrationCache cacheObj;

    public void onClick(View view){

        switch(view.getId()){
            case R.id.submitReview:
                submitReview();
                break;
        }
    }

    public void submitReview(){

        DatabaseReference reviewDB = FirebaseDatabase.getInstance().getReference("Review");

        String reviewId = "review" + UUID.randomUUID().toString();

        Review review = new Review(reviewId,
                text.getText().toString(),
                Integer.toString(Math.round(ratingBar.getRating())),
                cacheObj.username,
                vehicleId);

        reviewDB.child(reviewId).setValue(review);

        final DatabaseReference vehicleDB = FirebaseDatabase.getInstance().getReference("Vehicle").child(vehicleId);

        vehicleDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                float curReview = Float.parseFloat(dataSnapshot.child("vehicleReview").getValue().toString());
                float cumReview = Float.parseFloat(dataSnapshot.child("vehicleReviewCum").getValue().toString());

                int numReview;

                if(Math.round(cumReview) == 0){
                    numReview = 1;
                }else{
                    numReview = Math.round((cumReview / curReview) + 1);
                }

                int newCumReview = Math.round(cumReview) + Math.round(ratingBar.getRating());
                String newCumReviewString = Integer.toString(newCumReview);
                int newCurReview = Math.round(newCumReview / numReview);
                String newCurReviewString = Integer.toString(newCurReview);

                vehicleDB.child("vehicleReview").setValue(newCurReviewString);
                vehicleDB.child("vehicleReviewCum").setValue(newCumReviewString);

                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(reviewpage.this);
                dlgAlert.setMessage("We always appreciate our users opinion.");
                dlgAlert.setTitle("Thank you for reviewing");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //dismiss the dialog
                            }
                        });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewpage);

        Button submitBookButton = findViewById(R.id.submitReview);
        submitBookButton.setOnClickListener(this);

        backButton = (ImageView) findViewById(R.id.vehicleBookingBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        text = findViewById(R.id.text);
        ratingBar = findViewById(R.id.ratingBar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                vehicleId = extras.getString("vehicleId");
            }
        } else {
            vehicleId = savedInstanceState.getSerializable("vehicleId").toString();
        }
    }
}
