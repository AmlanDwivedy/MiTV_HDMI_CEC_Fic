package com.amlan.hdmi_cec_fix.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.amlan.hdmi_cec_fix.service.HDMIAudiChangeService;

public class ServiceStarter extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("amlan"," On Received");
        Intent i = new Intent("com.amlan.hdmi_cec_fix.service");
        i.setClass(context, HDMIAudiChangeService.class);
        context.startService(i);
    }
}