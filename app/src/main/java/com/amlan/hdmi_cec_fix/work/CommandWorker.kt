package com.amlan.hdmi_cec_fix.work

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.amlan.hdmi_cec_fix.MainActivity

class CommandWorker(val appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    private val disconnectHDMI = "settings put global hdmi_control_enabled 0"
    private val connectHDMI = "settings put global hdmi_control_enabled 1"


    override fun doWork(): Result {

        Handler(Looper.getMainLooper()).post {
            Toast.makeText(applicationContext, "command run started ", Toast.LENGTH_LONG).show()
        }

        Log.e("amlan", "work started ")
        runShellCommand(disconnectHDMI)
        Handler(Looper.getMainLooper()).postDelayed({
            runShellCommand(connectHDMI)
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(applicationContext, "Set to HDMI audio", Toast.LENGTH_LONG).show()
            }

        }, 10000)
        return Result.success()
    }


    private fun runShellCommand(command: String) {
        Handler(Looper.getMainLooper()).post {
            val process = Runtime.getRuntime().exec(command)
            val response = process.waitFor()
            Log.e("amlan", "response: $response")
        }
    }
}