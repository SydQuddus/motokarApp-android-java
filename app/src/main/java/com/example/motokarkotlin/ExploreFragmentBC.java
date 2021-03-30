package com.example.motokarkotlin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExploreFragmentBC extends Fragment implements View.OnClickListener{

    ExploreAdapter exploreAdapter;
    StoriesAdapter storiesAdapter;

    public void onClick(View view){

        //Intent intent;

        switch(view.getId()){
//            case R.id.vehicle1:
//                intent = new Intent(getActivity(), VehicleDetailsActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.reviewPicture1:
//                intent = new Intent(getActivity(), StoriesActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.reviewPicture2:
//                intent = new Intent(getActivity(), StoriesActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.reviewPicture3:
//                intent = new Intent(getActivity(), StoriesActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.reviewPicture4:
//                intent = new Intent(getActivity(), StoriesActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.reviewPicture5:
//                intent = new Intent(getActivity(), StoriesActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.reviewPicture6:
//                intent = new Intent(getActivity(), StoriesActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.reviewPicture7:
//                intent = new Intent(getActivity(), StoriesActivity.class);
//                startActivity(intent);
//                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view =  inflater.inflate(R.layout.explore_fragment, container, false);

        //ImageView filterButton = view.findViewById(R.id.filterButton);

        //LinearLayout vehicle1 = view.findViewById(R.id.vehicle1);

//        TextView reviewName1 = view.findViewById(R.id.reviewName1);
//        TextView reviewName2 = view.findViewById(R.id.reviewName2);
//        TextView reviewName3 = view.findViewById(R.id.reviewName3);
//        TextView reviewName4 = view.findViewById(R.id.reviewName4);
//        TextView reviewName5 = view.findViewById(R.id.reviewName5);
//        TextView reviewName6 = view.findViewById(R.id.reviewName6);
//        TextView reviewName7 = view.findViewById(R.id.reviewName7);

//        CircleImageView reviewPicture1 = view.findViewById(R.id.reviewPicture1);
//        CircleImageView reviewPicture2 = view.findViewById(R.id.reviewPicture2);
//        CircleImageView reviewPicture3 = view.findViewById(R.id.reviewPicture3);
//        CircleImageView reviewPicture4 = view.findViewById(R.id.reviewPicture4);
//        CircleImageView reviewPicture5 = view.findViewById(R.id.reviewPicture5);
//        CircleImageView reviewPicture6 = view.findViewById(R.id.reviewPicture6);
//        CircleImageView reviewPicture7 = view.findViewById(R.id.reviewPicture7);

//        ImageView vehiclePicture1 = view.findViewById(R.id.vehiclePicture1);
//        TextView nameYear1 = view.findViewById(R.id.nameYear1);
//        TextView price1 = view.findViewById(R.id.price1);
//        TextView ratingNum1 = view.findViewById(R.id.ratingNum1);

//        ImageView ratingVehicle11 = view.findViewById(R.id.ratingVehicle11);
//        ImageView ratingVehicle12 = view.findViewById(R.id.ratingVehicle12);
//        ImageView ratingVehicle13 = view.findViewById(R.id.ratingVehicle13);
//        ImageView ratingVehicle14 = view.findViewById(R.id.ratingVehicle14);
//        ImageView ratingVehicle15 = view.findViewById(R.id.ratingVehicle15);


//        nameYear1.setText("Perodua Myvi");
//        price1.setText("RM6/hour");
//        ratingNum1.setText("220");


//        reviewName1.setText("Masha");
//        reviewName2.setText("Gwen Kelly");
//        reviewName3.setText("Mr Carl");
//        reviewName4.setText("Joshua CK");
//        reviewName5.setText("Jimmy Chan");
//        reviewName6.setText("Tish Simone");
//        reviewName7.setText("Jerome");

//        ratingVehicle11.setImageResource(R.drawable.ic_star_black_24dp);
//        ratingVehicle12.setImageResource(R.drawable.ic_star_black_24dp);
//        ratingVehicle13.setImageResource(R.drawable.ic_star_black_24dp);
//        ratingVehicle14.setImageResource(R.drawable.ic_star_black_24dp);
//        ratingVehicle15.setImageResource(R.drawable.ic_star_half_black_24dp);

//        reviewPicture1.setImageResource(R.drawable.masha);
//        reviewPicture2.setImageResource(R.drawable.kelly);
//        reviewPicture3.setImageResource(R.drawable.mrcarl);
//        reviewPicture4.setImageResource(R.drawable.joshuack);
//        reviewPicture5.setImageResource(R.drawable.jimmy);
//        reviewPicture6.setImageResource(R.drawable.memum);
//        reviewPicture7.setImageResource(R.drawable.jerome);

//        vehiclePicture1.setImageResource(R.drawable.myvi2);
//        vehicle1.setOnClickListener(this);

//        reviewPicture1.setOnClickListener(this);
//        reviewPicture2.setOnClickListener(this);
//        reviewPicture3.setOnClickListener(this);
//        reviewPicture4.setOnClickListener(this);
//        reviewPicture5.setOnClickListener(this);
//        reviewPicture6.setOnClickListener(this);
//        reviewPicture7.setOnClickListener(this);

        ArrayList<String> vehicleNames = new ArrayList<>();
        vehicleNames.add("Perodua Kancil");
        vehicleNames.add("Proton Iswara");
        vehicleNames.add("Toyota Alphard");

        RecyclerView vehicle_list = view.findViewById(R.id.vehicle_list);
        vehicle_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        //exploreAdapter = new ExploreAdapter(getActivity(), vehicleNames);
        vehicle_list.setAdapter(exploreAdapter);

        ArrayList<String> storiesNames = new ArrayList<>();
        storiesNames.add("Masha");
        storiesNames.add("Gwen Kelly");
        storiesNames.add("Jimmy Chan");

        //RecyclerView stories_list = view.findViewById(R.id.stories_list);
        //stories_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));
        //stories_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        //storiesAdapter = new StoriesAdapter(getActivity(), storiesNames);
        //stories_list.setAdapter(storiesAdapter);

        return view;
    }
}
