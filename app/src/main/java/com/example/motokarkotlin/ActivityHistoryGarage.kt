package com.example.motokarkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class ActivityHistoryGarage : AppCompatActivity() , View.OnClickListener {

    override fun onClick(view: View) {


        when (view.id) {
            R.id.vehicleDetailsBack -> {
                finish()
                //val intent = Intent(this, MainActivity::class.java)
                //startActivity(intent)
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_garage)
        supportFragmentManager.beginTransaction().replace(R.id.vehicleListDetailsScroll, HistoryGarageDetailsFragment()).commit()

        val vehicleDetailsBack = findViewById(R.id.vehicleDetailsBack) as ImageView
        vehicleDetailsBack.setOnClickListener(this)

//        val buttonBook = findViewById(R.id.ReviewRenteePastButton) as Button
//        buttonBook.setOnClickListener({
//            val intent2 = Intent(this,rentorReviewpage::class.java)
//            startActivity(intent2)
//
//        })
    }
}