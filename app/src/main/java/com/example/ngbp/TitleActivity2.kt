package com.example.ngbp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_title2.*

class TitleActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title2)

        titleVersionTextView.setText("V." + VERSION)

        TitleActivityLayout.setOnClickListener() {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}