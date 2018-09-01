package com.omrital.timerview

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.widget.TextView
import java.util.*

interface TimerViewType {
    fun start()
    fun stop()
    fun reset()
}

class TimerView: TextView, TimerViewType {
    private var timer: Timer? = null
    private val interval = 100L
    private var counter = 0
    private var isRunning = false

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context?) {
        setDefaultText()
    }

    override fun start() {
        if(isRunning) {
            return
        }
        timer = Timer()
        startTimer()
    }

    override fun stop() {
        isRunning = false
        timer?.cancel()
    }

    override fun reset() {
        stop()
        counter = 0
        setDefaultText()
    }

    private fun setDefaultText() {
        text = context.resources.getString(R.string.default_timer_display)
    }

    private fun startTimer() {
        isRunning = true

        timer?.scheduleAtFixedRate(object: TimerTask() {
            override fun run() {
                counter++
                renderTimer()
            }
        }, 0, interval)
    }

    private fun renderTimer() {
        var millis = counter
        val minutes: Int = millis/600
        millis -= (minutes * 600)
        val seconds: Int = millis/10
        millis -= (seconds * 10)

        var minutesDisplay = minutes.toString()
        if(minutes < 10) {
            minutesDisplay = "0$minutes"
        }

        var secondsDisplay = seconds.toString()
        if(seconds < 10) {
            secondsDisplay = "0$seconds"
        }

        var millisDisplay = millis.toString()
        if(millis < 10) {
            millisDisplay = "0$millis"
        }

        Handler(Looper.getMainLooper()).post {
            text = "$minutesDisplay:$secondsDisplay:$millisDisplay"
        }
    }
}