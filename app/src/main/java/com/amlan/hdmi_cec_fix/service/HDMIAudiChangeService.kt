package com.amlan.hdmi_cec_fix.service

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast


class HDMIAudiChangeService : IntentService("HDMIAudiChangeService") {

    private val disconnectHDMI = "settings put global hdmi_control_enabled 0"
    private val connectHDMI = "settings put global hdmi_control_enabled 1"

    override fun onHandleIntent(intent: Intent?) {

        Log.e("amlan", "started Service")
        runShellCommand(disconnectHDMI)
        Handler(Looper.getMainLooper()).postDelayed({
            runShellCommand(connectHDMI)
            Toast.makeText(applicationContext, "Set to HDMI audio", Toast.LENGTH_LONG).show()
        }, 10000)

    }

    @Throws(Exception::class)
    private fun runShellCommand(command: String) {
        val process = Runtime.getRuntime().exec(command)
        process.waitFor()
    }
}