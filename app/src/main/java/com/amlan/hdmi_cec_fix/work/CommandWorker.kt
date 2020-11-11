package com.amlan.hdmi_cec_fix.work

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

class CommandWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    private val disconnectHDMI = "settings put global hdmi_control_enabled 0"
    private val connectHDMI = "settings put global hdmi_control_enabled 1"


    override fun doWork(): Result {

        Log.e("amlan","work started ")
        runShellCommand(disconnectHDMI)
        Handler(Looper.getMainLooper()).postDelayed({
            runShellCommand(connectHDMI)
            Toast.makeText(applicationContext, "Set to HDMI audio", Toast.LENGTH_LONG).show()
        }, 10000)
        return Result.success()
    }

    @Throws(Exception::class)
    private fun runShellCommand(command: String) {
        val process = Runtime.getRuntime().exec(command)
        process.waitFor()
    }
}