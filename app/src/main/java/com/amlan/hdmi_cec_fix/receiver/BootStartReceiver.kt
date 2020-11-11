package com.amlan.hdmi_cec_fix.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.amlan.hdmi_cec_fix.work.CommandWorker

class BootStartReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.e("amlan", " On Received")

        val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<CommandWorker>().build()
        WorkManager
            .getInstance(context)
            .enqueue(uploadWorkRequest)
    }
}