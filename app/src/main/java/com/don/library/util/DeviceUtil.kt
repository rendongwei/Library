package com.don.library.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Environment.getRootDirectory
import java.io.File
import java.io.FileInputStream
import java.lang.reflect.Method
import java.util.*


object DeviceUtil {

    private val KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"
    private val KEY_FLYME_VERSION_NAME = "ro.build.display.id"
    private val FLYME = "flyme"
    private val ZTEC2016 = "zte c2016"
    private val ZUKZ1 = "zuk z1"
    private val ESSENTIAL = "essential"
    private val MEIZUBOARD = arrayOf("m9", "M9", "mx", "MX")
    private val BRAND = Build.BRAND.toLowerCase()

    private var mMiuiVersionName: String? = null
    private var mFlymeVersionName: String? = null

    init {
        var properties = Properties()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            var fileInputStream: FileInputStream? = null
            try {
                fileInputStream = FileInputStream(File(getRootDirectory(), "build.prop"))
                properties.load(fileInputStream)
            } catch (e: Exception) {
            } finally {
                fileInputStream?.apply {
                    try {
                        close()
                    } catch (e: Exception) {
                    }
                }
            }
        }
        var clzSystemProperties: Class<*>? = null
        try {
            clzSystemProperties = Class.forName("android.os.SystemProperties")
            val getMethod = clzSystemProperties.getDeclaredMethod("get", String::class.java)
            mMiuiVersionName = getLowerCaseName(properties, getMethod, KEY_MIUI_VERSION_NAME)
            mFlymeVersionName = getLowerCaseName(properties, getMethod, KEY_FLYME_VERSION_NAME)
        } catch (e: Exception) {
        }
    }

    // 是否平板
    fun isTablet(context: Context): Boolean {
        return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    // 是否Flyme系统
    fun isFlyme(): Boolean {
        return !mFlymeVersionName.isNullOrEmpty() && mFlymeVersionName?.contains(FLYME) ?: false
    }

    // 是否MIUI系统
    fun isMIUI(): Boolean {
        return !mMiuiVersionName.isNullOrEmpty()
    }

    // 是否MUUI系统 version = v11
    fun isMIUI(version: String): Boolean {
        return version == mMiuiVersionName
    }

    // 是否魅族
    fun isMeizu(): Boolean {
        return isPhone(MEIZUBOARD) || isFlyme()
    }

    // 是否小米
    fun isXiaomi(): Boolean {
        return Build.MANUFACTURER.toLowerCase() == "xiaomi"
    }

    // 是否vivo
    fun isVivo(): Boolean {
        return BRAND.contains("vivo") || BRAND.contains("bbk")
    }

    // 是否oppo
    fun isOppo(): Boolean {
        return BRAND.contains("oppo")
    }

    // 是否华为
    fun isHuawei(): Boolean {
        return BRAND.contains("huawei") || BRAND.contains("honor")
    }

    // 是否爱立信
    fun isEssentialPhone(): Boolean {
        return BRAND.contains("essential")
    }

    // 是否ZUKZ1
    fun isZUKZ1(): Boolean {
        val board = Build.MODEL
        return board != null && board.toLowerCase().contains(ZUKZ1)
    }

    // 是否ZTKC2016
    fun isZTKC2016(): Boolean {
        val board = Build.MODEL
        return board != null && board.toLowerCase().contains(ZTEC2016)
    }

    // 是否是手机
    private fun isPhone(boards: Array<String>): Boolean {
        val board = Build.BOARD ?: return false
        for (board1 in boards) {
            if (board == board1) {
                return true
            }
        }
        return false
    }

    private fun getLowerCaseName(p: Properties, get: Method, key: String): String? {
        var name = p.getProperty(key)
        if (name == null) {
            try {
                name = get.invoke(null, key) as String?
            } catch (ignored: Exception) {
            }
        }
        if (name != null) name = name.toLowerCase()
        return name
    }
}