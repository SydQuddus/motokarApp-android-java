package com.example.motokarkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import android.content.DialogInterface
import android.widget.Toast
import android.widget.Button

class ActivityUpcomingTrip : AppCompatActivity() , View.OnClickListener {

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
        setContentView(R.layout.activity_upcoming_trip)
        supportFragmentManager.beginTransaction().replace(R.id.vehicleListDetailsScroll, UpcomingTripsDetailsFragment()).commit()

        val vehicleDetailsBack = findViewById(R.id.vehicleDetailsBack) as ImageView
        vehicleDetailsBack.setOnClickListener(this)

        val button = findViewById<Button>(R.id.CancelBookingButton)

        button.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle(R.string.dialogTitle)
            //set message for alert dialog
            builder.setMessage(R.string.dialogMessage)
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //performing positive action
            builder.setPositiveButton("Yes"){dialogInterface, which ->
                Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
            }
            //performing cancel action
            builder.setNeutralButton("Cancel"){dialogInterface , which ->
                Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
            }
            //performing negative action
            builder.setNegativeButton("No"){dialogInterface, which ->
                Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

    }
}
