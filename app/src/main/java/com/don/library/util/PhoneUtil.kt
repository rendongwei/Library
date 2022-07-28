package com.don.library.util

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.telephony.TelephonyManager
import java.net.NetworkInterface

object PhoneUtil {

    fun getAndroidId(context: Context): String {
        try {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        } catch (e: Exception) {
        }
        return ""
    }

    fun getPhoneBrand(): String {
        return android.os.Build.BRAND
    }

    fun getPhoneModel(): String {
        return android.os.Build.MODEL
    }

    fun getPhoneRelease(): String {
        return android.os.Build.VERSION.RELEASE
    }

    fun getPhoneSDK(): Int {
        return android.os.Build.VERSION.SDK_INT
    }

    @SuppressLint("MissingPermission")
    fun getPhoneImei(context: Context): String {
        try {
            return (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).deviceId
        } catch (e: Exception) {
        }
        return ""
    }

    fun getPhoneMac(): String {
        var mac = ""
        NetworkInterface.getNetworkInterfaces().toList().forEach {
            if (it.name.equals("wlan0", ignoreCase = true)) {
                val stringBuilder = StringBuilder()
                it.hardwareAddress?.forEach { b ->
                    stringBuilder.append(String.format("%02x", b))
                }
                mac = stringBuilder.toString()
            }
        }
        return mac
    }
}