package com.example.ngbp

import android.content.Intent
import android.net.sip.SipErrorCode.TIME_OUT
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_title2.*


class TitleActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title2)

        titleVersionTextView.setText("V." + VERSION)

        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 3000.toLong())

        TitleActivityLayout.setOnClickListener() {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}