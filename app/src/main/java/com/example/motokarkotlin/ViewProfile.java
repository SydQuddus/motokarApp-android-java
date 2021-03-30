package com.example.motokarkotlin;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.firebase.database.*;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProfile extends AppCompatActivity {

    DatabaseReference databaseAccount;
    private ImageView backButton;
    RegistrationCache cacheObj;
    LinearLayout provideHP, provideVLincense, provideGovID;
    String defaultHP, defaultGov, defaultV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        databaseAccount = FirebaseDatabase.getInstance().getReference("Accounts").child(cacheObj.username);

        final TextView profile_name = findViewById(R.id.profile_name);
        final CircleImageView profile_image = findViewById(R.id.profile_image);
        final TextView userProvide = findViewById(R.id.userProvide);
        final EditText aboutDescText = findViewById(R.id.aboutDescText);
        final EditText userAddress = findViewById(R.id.userAddress);

        provideHP = findViewById(R.id.provideHP);
        provideVLincense = findViewById(R.id.provideVLincense);
        provideGovID = findViewById(R.id.provideGovID);


        databaseAccount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profile_name.setText(dataSnapshot.child("firstName").getValue().toString() + " " + dataSnapshot.child("lastName").getValue().toString());
                Picasso.get().load(dataSnapshot.child("profilePicture").getValue().toString()).into(profile_image);

                userProvide.setText(dataSnapshot.child("firstName").getValue().toString() + " " + dataSnapshot.child("lastName").getValue().toString());

                if(dataSnapshot.child("profileDesc").exists()) {
                    aboutDescText.setText(dataSnapshot.child("profileDesc").getValue().toString());
                }

                if(dataSnapshot.child("userAddress").exists()) {
                    userAddress.setText(dataSnapshot.child("userAddress").getValue().toString());
                }

                defaultHP = dataSnapshot.child("phoneNumber").getValue().toString();
                if(defaultHP.equals("")){
                    provideHP.setVisibility(LinearLayout.GONE);
                }

                defaultV = dataSnapshot.child("vehLicense").getValue().toString();
                if(defaultV.equals("https://firebasestorage.googleapis.com/v0/b/motokar-project.appspot.com/o/defaultImage%2Fvlicense.png?alt=media&token=5ed1792a-2983-439d-9ff4-a810052d1ff0")){
                    provideVLincense.setVisibility(LinearLayout.GONE);
                }

                defaultGov = dataSnapshot.child("govID").getValue().toString();
                if(defaultGov.equals("https://firebasestorage.googleapis.com/v0/b/motokar-project.appspot.com/o/defaultImage%2Fidcard.png?alt=media&token=3486cbfc-a48c-4665-83c2-6c6d4223e0eb")){
                    provideGovID.setVisibility(LinearLayout.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("myTag","there was an error");
            }
        });


        //Set Back button
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseAccount.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        databaseAccount.child("profileDesc").setValue(aboutDescText.getText().toString());
                        databaseAccount.child("userAddress").setValue(userAddress.getText().toString());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                finish();
            }
        });

        //Change user joined date
        TextView joinDate = findViewById(R.id.joinDate);
        joinDate.setText("June 2019");

        //Change reviewer 1 details
        CircleImageView reviewer1_picture = findViewById(R.id.reviewer1_picture);
        reviewer1_picture.setImageResource(R.drawable.masha);
        TextView reviewer1_text = findViewById(R.id.reviewer1_text);
        reviewer1_text.setText("Masha");

        //Change reviewer 2 details
        CircleImageView reviewer2_picture = findViewById(R.id.reviewer2_picture);
        reviewer2_picture.setImageResource(R.drawable.jerome);
        TextView reviewer2_text = findViewById(R.id.reviewer2_text);
        reviewer2_text.setText("Jerome");

        //Change reviewer 3 details
        CircleImageView reviewer3_picture = findViewById(R.id.reviewer3_picture);
        reviewer3_picture.setImageResource(R.drawable.joshuack);
        TextView reviewer3_text = findViewById(R.id.reviewer3_text);
        reviewer3_text.setText("Joshua CK");

    }

}




