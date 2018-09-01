package com.omrital.timerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        startButton.setOnClickListener {
            timer.start()
        }

        stopButton.setOnClickListener {
            timer.stop()
        }

        resetButton.setOnClickListener {
            timer.reset()
        }
    }
}
