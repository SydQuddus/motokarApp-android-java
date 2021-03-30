package com.example.motokarkotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.motokarkotlin.R
import kotlinx.android.synthetic.main.vehicle_details_fragment.*

class VehicleDetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.vehicle_details_fragment, container, false)
//
//        val vehicleNameSec = view.findViewById(R.id.vehicleNameSec) as TextView
//        val yearByText = view.findViewById(R.id.yearByText) as TextView
//        val specText1 = view.findViewById(R.id.specText1) as TextView
//        val specText2 = view.findViewById(R.id.specText2) as TextView
//        val specText3 = view.findViewById(R.id.specText3) as TextView
//        val specText4 = view.findViewById(R.id.specText4) as TextView
//        val vehicleDateStart = view.findViewById(R.id.vehicleDateStart) as TextView
//        val vehicleDateEnd = view.findViewById(R.id.vehicleDateEnd) as TextView
//        val locationText = view.findViewById(R.id.locationText) as TextView
//        val hourPrice = view.findViewById(R.id.hourPrice) as TextView
//        val dayPrice = view.findViewById(R.id.dayPrice) as TextView
//        val weekPrice = view.findViewById(R.id.weekPrice) as TextView
//        val monthPrice = view.findViewById(R.id.monthPrice) as TextView
//        val descText = view.findViewById(R.id.descText) as TextView
//        val ownerNameText = view.findViewById(R.id.ownerNameText) as TextView
//        val reviewName = view.findViewById(R.id.reviewName) as TextView
//        val reviewText = view.findViewById(R.id.reviewText) as TextView
//        val rating1 = view.findViewById(R.id.rating1) as ImageView
//        val rating2 = view.findViewById(R.id.rating2) as ImageView
//        val rating3 = view.findViewById(R.id.rating3) as ImageView
//        val rating4 = view.findViewById(R.id.rating4) as ImageView
//        val rating5 = view.findViewById(R.id.rating5) as ImageView
//        val userRating1 = view.findViewById(R.id.userRating1) as ImageView
//        val userRating2 = view.findViewById(R.id.userRating2) as ImageView
//        val userRating3 = view.findViewById(R.id.userRating3) as ImageView
//        val userRating4 = view.findViewById(R.id.userRating4) as ImageView
//        val userRating5 = view.findViewById(R.id.userRating5) as ImageView
//        val ownerPicture = view.findViewById(R.id.ownerPicture) as ImageView
//        val reviewPicture = view.findViewById(R.id.reviewPicture) as ImageView
//        val locationMapPicture = view.findViewById(R.id.locationMapPicture) as ImageView
//        val vehiclePicture = view.findViewById(R.id.vehiclePicture) as ImageView
//
//        vehicleNameSec.setText("Perodua Myvi")
//        yearByText.setText("Year 2018 | By Mr Carl")
//        specText1.setText("AUTO")
//        specText2.setText("HATCHBACK")
//        specText3.setText("4 DOORS")
//        specText4.setText("5 SEATS")
//        vehicleDateStart.setText("Start: Tue, 16 Jul 2019, 02:00 PM")
//        vehicleDateEnd.setText("End: Wed, 17 Jul 2019, 02:00 PM")
//        locationText.setText("Senai, Johor, Malaysia")
//        hourPrice.setText("RM 6.00")
//        dayPrice.setText("RM 120.00")
//        weekPrice.setText("RM 700.00")
//        monthPrice.setText("RM 1400.00")
//        descText.setText("Very comfortable car. Prices are the cheapest on the market, and this car is very efficient and fuel saving.")
//        ownerNameText.setText("Mr Carl")
//        reviewName.setText("Tish Simone")
//        reviewText.setText("Thank you for renting this car! Great price and very comfy!")
//
//        rating1.setImageResource(R.drawable.ic_star_black_24dp)
//        rating2.setImageResource(R.drawable.ic_star_black_24dp)
//        rating3.setImageResource(R.drawable.ic_star_black_24dp)
//        rating4.setImageResource(R.drawable.ic_star_black_24dp)
//        rating5.setImageResource(R.drawable.ic_star_half_black_24dp)
//        userRating1.setImageResource(R.drawable.ic_star_black_24dp)
//        userRating2.setImageResource(R.drawable.ic_star_black_24dp)
//        userRating3.setImageResource(R.drawable.ic_star_black_24dp)
//        userRating4.setImageResource(R.drawable.ic_star_black_24dp)
//        userRating5.setImageResource(R.drawable.ic_star_half_black_24dp)
//
//        ownerPicture.setImageResource(R.drawable.mrcarl)
//        reviewPicture.setImageResource(R.drawable.memum)
//
//        locationMapPicture.setImageResource(R.drawable.senai)
//
//        vehiclePicture.setImageResource(R.drawable.myvi2)
//
        return view
    }
}
