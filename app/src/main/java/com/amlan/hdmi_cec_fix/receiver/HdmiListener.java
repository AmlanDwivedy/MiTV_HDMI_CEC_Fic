package com.amlan.hdmi_cec_fix.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.amlan.hdmi_cec_fix.R;

import static android.media.AudioManager.ACTION_HDMI_AUDIO_PLUG;
import static android.media.AudioManager.EXTRA_AUDIO_PLUG_STATE;

public class HdmiListener extends BroadcastReceiver {


    private static String HDMIINTENT = "android.intent.action.HDMI_PLUGGED";

    @Override
    public void onReceive(Context context, Intent intent) {
        // pause video
        String action = intent.getAction();

        switch (action) {
            case ACTION_HDMI_AUDIO_PLUG:
                // EXTRA_AUDIO_PLUG_STATE: 0 - UNPLUG, 1 - PLUG
                Toast.makeText(context, "HDMI PLUGGED OR UNPLUGGED", Toast.LENGTH_LONG).show();
//                Log.d("MainActivity", "ACTION_HDMI_AUDIO_PLUG " + intent.getIntExtra(EXTRA_AUDIO_PLUG_STATE, -1));
//                ((TextView) (findViewById(R.id.textView))).setText(((TextView) (findViewById(R.id.textView))).getText().toString().concat("At " + System.nanoTime() + ": " + intent.getIntExtra(EXTRA_AUDIO_PLUG_STATE, -1) + "\n"));
                break;


        }
    }
}