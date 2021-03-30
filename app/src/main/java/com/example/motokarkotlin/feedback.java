package com.example.motokarkotlin;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.motokarkotlin.R.id.givefeedbackbutton;
import static com.example.motokarkotlin.R.id.text;

public class feedback extends AppCompatActivity {

    private ImageView backButton;
    private ImageView givefeedbackbutton;
    private ImageView reportbugbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //Set Back button
        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Set givefeedbackbutton
        givefeedbackbutton = findViewById(R.id.givefeedbackbutton);
        givefeedbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipient = "motokarbypenta@gmail.com";
                String subject = "Feedback for MotoKar";
                String message = "Hi There, I would like to give a feedback regarding MotoKar, My feedback is...";

                sendEmailFeedback(recipient, subject, message);

            }
        });
        //Set reportbugbutton
        reportbugbutton = findViewById(R.id.reportbugbutton);
        reportbugbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipient = "motokarbypenta@gmail.com";
                String subject = "MotoKar Bugs Report";
                String message = "Hi There, I would like to report a bug regarding MotoKar, the bugs are...";

                sendEmailBugs(recipient, subject, message);

            }
        });
    }

    private void sendEmailFeedback(String recipient, String subject, String message) {
        Intent mEmailIntent = new Intent(Intent.ACTION_SEND);
        mEmailIntent.setData(Uri.parse("mailto:"));
        mEmailIntent.setType("text/plain");
        mEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        mEmailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mEmailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(mEmailIntent, "Choose an email client"));
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void sendEmailBugs(String recipient, String subject, String message) {
        Intent mEmailIntent = new Intent(Intent.ACTION_SEND);
        mEmailIntent.setData(Uri.parse("mailto:"));
        mEmailIntent.setType("text/plain");
        mEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        mEmailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mEmailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(mEmailIntent, "Choose an email client"));
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
