package com.example.fastandroidnetworking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_utama.*

class Utama : AppCompatActivity() {
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utama)



        Btn1.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

        Btn2.setOnClickListener {
            val intent = Intent(context, marquee ::class.java)
            startActivity(intent)
        }

        Btn3.setOnClickListener {
            val intent = Intent(context, pengumuman_masjid ::class.java)
            startActivity(intent)
        }

        Btn4.setOnClickListener {
            val intent = Intent(context, slideshow ::class.java)
            startActivity(intent)
        }

        Btn5.setOnClickListener {
            val intent = Intent(context, tagline ::class.java)
            startActivity(intent)
        }

        Btn6.setOnClickListener {
            val intent = Intent(context, identitas_masjid::class.java)
            startActivity(intent)
        }

    }
}
