package com.example.motokarkotlin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static java.security.AccessController.getContext;

public class VehicleDetailsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener{

    String vehicleId = "",vehicleName = "",reviewString = "",pictureString = "",transmissionString = "",configString = "",doorsString = "",
            seatsString = "",rateString = "",rateDayString = "",rateWeekString = "",
            rateMonthString = "",yearString = "",descriptionString = "";
    String username = ""; //this is for database reference ID
    int calType = 0;
    String vehicleDateStartString = "", vehicleDateEndString = "";

    TextView vehicleDateStart, vehicleDateEnd;

    ReviewAdapter reviewAdapter;
    RecyclerView review_list;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.vehicleDetailsBack:
                finish();
                break;
            case R.id. buttonBook:
                Intent intent = new Intent(VehicleDetailsActivity.this,VehicleBooking.class);
                intent.putExtra("vehicleId", vehicleId);
                intent.putExtra("username", username);
                intent.putExtra("vehicleName", vehicleName);
                intent.putExtra("vehiclePicture", pictureString);
                intent.putExtra("vehicleYear", yearString);
                intent.putExtra("vehicleDateStartString", vehicleDateStartString);
                intent.putExtra("vehicleDateEndString", vehicleDateEndString);
                intent.putExtra("rateDayString", rateDayString);
                startActivity(intent);
                break;
            case R.id.tripSetStartDate:
                calType = 0;
                showDatePickerDialog();
                break;
            case R.id.tripSetEndDate:
                calType = 1;
                showDatePickerDialog();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        month = month + 1;
        String date = dayOfMonth + "/" + month + "/" + year;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date formattedDate = formatter.parse(date);

            if(calType == 0){
                SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy");
                vehicleDateStartString = dateFormat.format(formattedDate);
                vehicleDateStart.setText("Start: " + vehicleDateStartString);
            }else if(calType == 1){
                SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy");
                vehicleDateEndString = dateFormat.format(formattedDate);
                vehicleDateEnd.setText("End: " + vehicleDateEndString);
            }

        } catch (ParseException e) {
        }
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);

        TextView vehicleNameSec = findViewById(R.id.vehicleNameSec);
        final TextView yearByText = findViewById(R.id.yearByText);
        TextView specText1 = findViewById(R.id.specText1);
        TextView specText2 = findViewById(R.id.specText2);
        //TextView specText3 = findViewById(R.id.specText3);
        TextView specText4 = findViewById(R.id.specText4);
        vehicleDateStart = findViewById(R.id.vehicleDateStart);
        vehicleDateEnd = findViewById(R.id.vehicleDateEnd);
        //TextView locationText = findViewById(R.id.locationText);
        TextView hourPrice = findViewById(R.id.hourPrice);
        TextView dayPrice = findViewById(R.id.dayPrice);
        TextView weekPrice = findViewById(R.id.weekPrice);
        TextView monthPrice = findViewById(R.id.monthPrice);
        TextView descText = findViewById(R.id.descText);
        final TextView ownerNameText = findViewById(R.id.ownerNameText);
//        TextView reviewName = findViewById(R.id.reviewName);
//        TextView reviewText = findViewById(R.id.reviewText);
        ImageView rating1 = findViewById(R.id.rating1);
        ImageView rating2 = findViewById(R.id.rating2);
        ImageView rating3 = findViewById(R.id.rating3);
        ImageView rating4 = findViewById(R.id.rating4);
        ImageView rating5 = findViewById(R.id.rating5);
//        ImageView userRating1 = findViewById(R.id.userRating1);
//        ImageView userRating2 = findViewById(R.id.userRating2);
//        ImageView userRating3 = findViewById(R.id.userRating3);
//        ImageView userRating4 = findViewById(R.id.userRating4);
//        ImageView userRating5 = findViewById(R.id.userRating5);
        final ImageView ownerPicture = findViewById(R.id.ownerPicture);
//        ImageView reviewPicture = findViewById(R.id.reviewPicture);
        //ImageView locationMapPicture = findViewById(R.id.locationMapPicture);
        ImageView vehiclePicture = findViewById(R.id.vehiclePicture);
        TextView tripSetStartDate = findViewById(R.id.tripSetStartDate);
        tripSetStartDate.setOnClickListener(this);
        TextView tripSetEndDate = findViewById(R.id.tripSetEndDate);
        tripSetEndDate.setOnClickListener(this);

        review_list = findViewById(R.id.review_list);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                vehicleId = extras.getString("vehicleId");
                vehicleName = extras.getString("vehicleName");
                pictureString = extras.getString("vehiclePicture");
                reviewString = extras.getString("vehicleReview");
                transmissionString = extras.getString("vehicleTransmission");
                configString = extras.getString("vehicleConfig");
                doorsString = extras.getString("vehicleDoors");
                seatsString = extras.getString("vehicleSeats");
                rateString = extras.getString("vehicleRate");
                rateDayString = extras.getString("vehicleRateDay");
                rateWeekString = extras.getString("vehicleRateWeek");
                rateMonthString = extras.getString("vehicleRateMonth");
                yearString = extras.getString("vehicleYear");
                descriptionString = extras.getString("vehicleDescription");
                username = extras.getString("username");
            }
        } else {
            vehicleId = savedInstanceState.getSerializable("vehicleId").toString();
            vehicleName = savedInstanceState.getSerializable("vehicleName").toString();
            pictureString = savedInstanceState.getSerializable("vehiclePicture").toString();
            reviewString = savedInstanceState.getSerializable("vehicleReview").toString();
            transmissionString = savedInstanceState.getSerializable("vehicleTransmission").toString();
            configString = savedInstanceState.getSerializable("vehicleConfig").toString();
            doorsString = savedInstanceState.getSerializable("vehicleDoors").toString();
            seatsString = savedInstanceState.getSerializable("vehicleSeats").toString();
            rateString = savedInstanceState.getSerializable("vehicleRate").toString();
            rateDayString = savedInstanceState.getSerializable("vehicleRateDay").toString();
            rateWeekString = savedInstanceState.getSerializable("vehicleRateWeek").toString();
            rateMonthString = savedInstanceState.getSerializable("vehicleRateMonth").toString();
            yearString = savedInstanceState.getSerializable("vehicleYear").toString();
            descriptionString = savedInstanceState.getSerializable("vehicleDescription").toString();
            username = savedInstanceState.getSerializable("username").toString();
        }


        DatabaseReference userVehicle = FirebaseDatabase.getInstance().getReference("Accounts").child(username);

        userVehicle.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                yearByText.setText("Year " + yearString + " | By " + dataSnapshot.child("firstName").getValue().toString() + " " + dataSnapshot.child("lastName").getValue().toString());
                ownerNameText.setText(dataSnapshot.child("firstName").getValue().toString() + " " + dataSnapshot.child("lastName").getValue().toString());
                Picasso.get().load(dataSnapshot.child("profilePicture").getValue().toString()).into(ownerPicture);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        final ArrayList<String> idArray = new ArrayList<>();
        final ArrayList<String> usernameArray = new ArrayList<>();
        final ArrayList<String> contentArray = new ArrayList<>();
        final ArrayList<String> reviewArray = new ArrayList<>();

        DatabaseReference vehicleReview = FirebaseDatabase.getInstance().getReference("Review");

        vehicleReview.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(vehicleId.equals(snapshot.child("vehicleId").getValue().toString())) {
                        idArray.add(snapshot.child("reviewId").getValue().toString());
                        usernameArray.add(snapshot.child("username").getValue().toString());
                        contentArray.add(snapshot.child("reviewContent").getValue().toString());
                        reviewArray.add(snapshot.child("reviewRate").getValue().toString());
                    }
                }

                review_list.setHasFixedSize(true);
                review_list.setLayoutManager(new LinearLayoutManager(VehicleDetailsActivity.this));
                reviewAdapter = new ReviewAdapter(VehicleDetailsActivity.this,
                        idArray,
                        usernameArray,
                        contentArray,
                        reviewArray
                );
                review_list.setAdapter(reviewAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        vehicleNameSec.setText(vehicleName);
        Picasso.get().load(pictureString).into(vehiclePicture);

        specText1.setText(transmissionString);
        specText2.setText(configString);
        //specText3.setText(doorsString + " Doors");
        specText4.setText(seatsString);

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();

        SimpleDateFormat date = new SimpleDateFormat("E, d MMM yyyy");
        date.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        vehicleDateStartString = date.format(today);
        vehicleDateEndString = date.format(tomorrow);

        vehicleDateStart.setText("Start: " + vehicleDateStartString);
        vehicleDateEnd.setText("End: " + vehicleDateEndString);

        //locationText.setText("Senai, Johor, Malaysia");
        hourPrice.setText("RM " + rateString +".00");
        dayPrice.setText("RM " + rateDayString +".00");
        weekPrice.setText("RM " + rateWeekString +".00");
        monthPrice.setText("RM " + rateMonthString +".00");
        descText.setText(descriptionString);
//        reviewName.setText("Tish Simone");
//        reviewText.setText("Thank you for renting this car! Great price and very comfy!");

        if(reviewString.equals("0.5")){
            rating1.setImageResource(R.drawable.ic_star_half_black_24dp);
            rating2.setImageResource(R.drawable.ic_star_border_black_24dp);
            rating3.setImageResource(R.drawable.ic_star_border_black_24dp);
            rating4.setImageResource(R.drawable.ic_star_border_black_24dp);
            rating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewString.equals("1")){
            rating1.setImageResource(R.drawable.ic_star_black_24dp);
            rating2.setImageResource(R.drawable.ic_star_border_black_24dp);
            rating3.setImageResource(R.drawable.ic_star_border_black_24dp);
            rating4.setImageResource(R.drawable.ic_star_border_black_24dp);
            rating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewString.equals("1.5")){
            rating1.setImageResource(R.drawable.ic_star_black_24dp);
            rating2.setImageResource(R.drawable.ic_star_half_black_24dp);
            rating3.setImageResource(R.drawable.ic_star_border_black_24dp);
            rating4.setImageResource(R.drawable.ic_star_border_black_24dp);
            rating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewString.equals("2")){
            rating1.setImageResource(R.drawable.ic_star_black_24dp);
            rating2.setImageResource(R.drawable.ic_star_black_24dp);
            rating3.setImageResource(R.drawable.ic_star_border_black_24dp);
            rating4.setImageResource(R.drawable.ic_star_border_black_24dp);
            rating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewString.equals("2.5")){
            rating1.setImageResource(R.drawable.ic_star_black_24dp);
            rating2.setImageResource(R.drawable.ic_star_black_24dp);
            rating3.setImageResource(R.drawable.ic_star_half_black_24dp);
            rating4.setImageResource(R.drawable.ic_star_border_black_24dp);
            rating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewString.equals("3")){
            rating1.setImageResource(R.drawable.ic_star_black_24dp);
            rating2.setImageResource(R.drawable.ic_star_black_24dp);
            rating3.setImageResource(R.drawable.ic_star_black_24dp);
            rating4.setImageResource(R.drawable.ic_star_border_black_24dp);
            rating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewString.equals("3.5")){
            rating1.setImageResource(R.drawable.ic_star_black_24dp);
            rating2.setImageResource(R.drawable.ic_star_black_24dp);
            rating3.setImageResource(R.drawable.ic_star_black_24dp);
            rating4.setImageResource(R.drawable.ic_star_half_black_24dp);
            rating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewString.equals("4")){
            rating1.setImageResource(R.drawable.ic_star_black_24dp);
            rating2.setImageResource(R.drawable.ic_star_black_24dp);
            rating3.setImageResource(R.drawable.ic_star_black_24dp);
            rating4.setImageResource(R.drawable.ic_star_black_24dp);
            rating5.setImageResource(R.drawable.ic_star_border_black_24dp);
        }else if(reviewString.equals("4.5")){
            rating1.setImageResource(R.drawable.ic_star_black_24dp);
            rating2.setImageResource(R.drawable.ic_star_black_24dp);
            rating3.setImageResource(R.drawable.ic_star_black_24dp);
            rating4.setImageResource(R.drawable.ic_star_black_24dp);
            rating5.setImageResource(R.drawable.ic_star_half_black_24dp);
        }else if(reviewString.equals("5")){
            rating1.setImageResource(R.drawable.ic_star_black_24dp);
            rating2.setImageResource(R.drawable.ic_star_black_24dp);
            rating3.setImageResource(R.drawable.ic_star_black_24dp);
            rating4.setImageResource(R.drawable.ic_star_black_24dp);
            rating5.setImageResource(R.drawable.ic_star_black_24dp);
        }

//        userRating1.setImageResource(R.drawable.ic_star_black_24dp);
//        userRating2.setImageResource(R.drawable.ic_star_black_24dp);
//        userRating3.setImageResource(R.drawable.ic_star_black_24dp);
//        userRating4.setImageResource(R.drawable.ic_star_black_24dp);
//        userRating5.setImageResource(R.drawable.ic_star_half_black_24dp);

//        reviewPicture.setImageResource(R.drawable.memum);

        //locationMapPicture.setImageResource(R.drawable.senai);

        TextView priceText = findViewById(R.id.priceText);
        priceText.setText("RM" + rateString + '\n' + "per hour");

        ImageView vehicleDetailsBack = findViewById(R.id.vehicleDetailsBack);
        vehicleDetailsBack.setOnClickListener(this);

        Button buttonBook = findViewById(R.id.buttonBook);
        buttonBook.setOnClickListener(this);

    }
}
