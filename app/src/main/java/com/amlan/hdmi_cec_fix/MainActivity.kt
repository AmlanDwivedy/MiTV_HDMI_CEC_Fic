package com.amlan.hdmi_cec_fix

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager.ACTION_HDMI_AUDIO_PLUG
import android.media.AudioManager.EXTRA_AUDIO_PLUG_STATE
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieCompositionFactory


class MainActivity : Activity() {

    // adb shell dumpsys hdmi_control

    private val disconnectHDMI = "settings put global hdmi_control_enabled 0"
    private val connectHDMI = "settings put global hdmi_control_enabled 1"
    private lateinit var animView: LottieAnimationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animView = findViewById(R.id.animationView)
        toggleHDMIAudio()

    }

    private fun toggleHDMIAudio() {
        runShellCommand(disconnectHDMI)
        Handler(Looper.getMainLooper()).postDelayed({
            runShellCommand(connectHDMI)
            Toast.makeText(applicationContext, "Set to HDMI audio", Toast.LENGTH_LONG).show()
            finish()
        }, 10000)
    }

    @Throws(Exception::class)
    private fun runShellCommand(command: String) {
        val process = Runtime.getRuntime().exec(command)
        process.waitFor()
    }

    private val eventReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            // pause video
            val action = intent.action
            when (action) {
                ACTION_HDMI_AUDIO_PLUG -> {
                    // EXTRA_AUDIO_PLUG_STATE: 0 - UNPLUG, 1 - PLUG
                    Toast.makeText(
                        applicationContext,
                        "HDMI PLUGGED OR UNPLUGGED",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d(
                        "MainActivity",
                        "ACTION_HDMI_AUDIO_PLUG " + intent.getIntExtra(
                            EXTRA_AUDIO_PLUG_STATE,
                            -1
                        )
                    )

                    toggleHDMIAudio()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(eventReceiver)
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter()
        filter.addAction(ACTION_HDMI_AUDIO_PLUG)
        registerReceiver(eventReceiver, filter)
    }
}

