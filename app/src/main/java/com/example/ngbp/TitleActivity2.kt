package com.example.ngbp

import android.content.Context
import android.content.Intent
import android.net.sip.SipErrorCode.TIME_OUT
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_title2.*

class TitleActivity2 : AppCompatActivity() {

    var t = Handler() // https://stackoverflow.com/questions/64289287/turning-off-a-postdelayed-handler
    val runnable = Runnable {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title2)

        titleVersionTextView.setText("V." + VERSION)

        t.postDelayed(runnable, 3000)

        TitleActivityLayout.setOnClickListener() { // click before timer expires ONLY RUN ON A CLICK
            t.removeCallbacks(runnable)
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        
        val sp = getSharedPreferences("RATINGS", Context.MODE_PRIVATE)
        val isDarkModeOn = sp.getBoolean("darkMode", true)
        if (isDarkModeOn) { // go dark mode THIS IS ALWAYS RUN
            // t.removeCallbacks(runnable)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

    }
}