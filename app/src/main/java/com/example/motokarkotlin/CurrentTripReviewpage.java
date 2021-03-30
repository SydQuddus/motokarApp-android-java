package com.example.motokarkotlin;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CurrentTripReviewpage extends AppCompatActivity implements View.OnClickListener {

    private ImageView backButton;

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.submitCurrentReview:
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
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
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_trip_reviewpage);

        Button submitBookButton = findViewById(R.id.submitCurrentReview);
        submitBookButton.setOnClickListener(this);


        backButton = (ImageView) findViewById(R.id.vehicleBookingBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
