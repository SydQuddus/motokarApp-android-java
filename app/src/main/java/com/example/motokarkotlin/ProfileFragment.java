package com.example.motokarkotlin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.firebase.database.*;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    DatabaseReference databaseAccount;
    RegistrationCache cacheObj;

    public void onClick(View view) {
        Intent intent;

        switch(view.getId()) {
            case R.id.view_profile_button:
                intent = new Intent(getActivity(), ViewProfile.class);
                startActivity(intent);
                break;
            case R.id.personalInfo:
                intent = new Intent(getActivity(), EditPersonalInfo.class);
                startActivity(intent);
                break;
            case R.id.mkWallet:
                intent = new Intent(getActivity(), MKwallet.class);
                startActivity(intent);
                break;
            case R.id.changePass:
                intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
                break;
            case R.id.inviteFriends:
                intent = new Intent(getActivity(), inviteFriends.class);
                startActivity(intent);
                break;
            case R.id.safetyCenter:
                intent = new Intent(getActivity(), safetyCenter.class);
                startActivity(intent);
                break;
            case R.id.feedback:
                intent = new Intent(getActivity(), feedback.class);
                startActivity(intent);
                break;
            case R.id.logout:
                intent = new Intent(getActivity(), LoginPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String username;

        databaseAccount = FirebaseDatabase.getInstance().getReference("Accounts").child(cacheObj.username);

        final View view = inflater.inflate(R.layout.profile_fragment, container, false);

        final TextView profile_name = view.findViewById(R.id.profile_name);
        final TextView profile_email = view.findViewById(R.id.profile_email);
        final CircleImageView profile_image = view.findViewById(R.id.profile_image);

        databaseAccount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profile_name.setText(dataSnapshot.child("firstName").getValue().toString() + " " + dataSnapshot.child("lastName").getValue().toString());
                profile_email.setText(dataSnapshot.child("emailAddress").getValue().toString());
                Picasso.get().load(dataSnapshot.child("profilePicture").getValue().toString()).into(profile_image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("myTag","there was an error");
            }
        });

        //intent to view profile
        TextView view_profile_button = view.findViewById(R.id.view_profile_button);
        view_profile_button.setOnClickListener(this);

        //intent to edit personal info
        LinearLayout personalInfo = view.findViewById(R.id.personalInfo);
        personalInfo.setOnClickListener(this);

        //intent to edit personal info
        LinearLayout mkWallet = view.findViewById(R.id.mkWallet);
        mkWallet.setOnClickListener(this);

        //intent to edit notifications setting
        LinearLayout changePass = view.findViewById(R.id.changePass);
        changePass.setOnClickListener(this);

        //intent to edit invite friends
        LinearLayout inviteFriends = view.findViewById(R.id.inviteFriends);
        inviteFriends.setOnClickListener(this);

        //intent safetyCenter
        LinearLayout safetyCenter = view.findViewById(R.id.safetyCenter);
        safetyCenter.setOnClickListener(this);

        //intent to feedback
        LinearLayout feedback = view.findViewById(R.id.feedback);
        feedback.setOnClickListener(this);

        //intent to logout
        LinearLayout logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(this);

        return view;
    }
}
