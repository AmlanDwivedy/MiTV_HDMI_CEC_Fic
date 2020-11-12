# MiTV HDMI CEC Fix

This is a fix for my Mi TV. When I connect my soundbar to the tv its not automatically not getting connected to HDMI ARC because of some platform issue.
Thats why I fixed the issue, without waiting for Mi to fix it.


##HDMI-CEC Control Service

https://source.android.com/devices/tv/hdmi-cec

ADB commands to enable and disable HDMI-ARC

###**Enable**
adb shell settings put global hdmi_control_enabled 1

###**Disable**
adb shell settings put global hdmi_control_enabled 0


![Screenshot](device-2020-11-12-180412.png)
