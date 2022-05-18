package com.don.library.manager

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.don.library.constants.Tag
import com.don.library.util.LogUtil
import java.util.*


/**
 * Activity管理类
 */
object ActivityManager {

    private val mActivities: LinkedList<Activity> by lazy {
        LinkedList<Activity>()
    }

    // 添加activity
    fun addActivity(activity: Activity) {
        LogUtil.log(Tag.ACTIVITY_MANAGER, "addActivity:${activity::class.java.canonicalName}")
        mActivities.addLast(activity)
    }

    // 移除activity
    fun removeActivity(activity: Activity) {
        LogUtil.log(Tag.ACTIVITY_MANAGER, "removeActivity:${activity::class.java.canonicalName}")
        mActivities.remove(activity)
    }

    fun getLastActivity(): Activity {
        return mActivities.last
    }

    // 关闭所有已添加的activity
    fun finishAll() {
        mActivities.forEach {
            if (!it.isDestroyed) {
                it.finish()
            }
        }
        mActivities.clear()
    }

    // 判断当前是否在运行
    fun isAppRunning(context: Context): Boolean {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val list = am.getRunningTasks(1000)
        for (info in list) {
            if (info.topActivity?.packageName == context.packageName || info.baseActivity?.packageName == context.packageName) {
                return true
            }
        }
        return false
    }

    // 获取TopActivity
    fun getTopActivity(context: Context): ComponentName? {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return am.getRunningTasks(1)[0].topActivity
    }

    fun <T> isTopActivity(context: Context, cls: Class<T>): Boolean {
        val cn = getTopActivity(context)
        return cn?.className == cls.canonicalName
    }

    // 跳转桌面
    fun intentHome(context: Context) {
        var intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        context.startActivity(intent)
    }

    // 跳转系统的辅助功能界面
    fun intentToAccessiblity(context: Context) {
        context.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
    }

    // 显示添加帐户创建一个新的帐户屏幕
    fun intentToAddAccount(context: Context) {
        context.startActivity(Intent(Settings.ACTION_ADD_ACCOUNT))
    }

    // 飞行模式，无线网和网络设置界面
    fun intentToAirplanMode(context: Context) {
        context.startActivity(Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS))
    }

    // 跳转Wifi列表设置
    fun intentToWifi(context: Context) {
        context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
    }

    // 跳转WifiIp设置
    fun intentToWifiIp(context: Context) {
        context.startActivity(Intent(Settings.ACTION_WIFI_IP_SETTINGS))
    }

    // 跳转APN设置界面
    fun intentToAPN(context: Context) {
        context.startActivity(Intent(Settings.ACTION_APN_SETTINGS))
    }

    // 跳转开发人员选项界面
    fun intentToApplicationDevelopment(context: Context) {
        context.startActivity(Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS))
    }


    // 根据包名跳转到系统自带的应用程序信息界面
    fun intentToApplicationDetail(context: Context, packageName: String) {
        context.startActivity(
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:$packageName")
            )
        )
    }

    // 跳转到应用程序界面
    fun intentToApplication(context: Context) {
        context.startActivity(Intent(Settings.ACTION_APPLICATION_SETTINGS))
    }

    // 跳转系统的蓝牙设置界面
    fun intentToBluetooth(context: Context) {
        context.startActivity(Intent(Settings.ACTION_BLUETOOTH_SETTINGS))
    }

    // 跳转到移动网络设置界面
    fun intentToDataRoaming(context: Context) {
        context.startActivity(Intent(Settings.ACTION_DATA_ROAMING_SETTINGS))
    }

    // 跳转日期时间设置界面
    fun intentToDateTime(context: Context) {
        context.startActivity(Intent(Settings.ACTION_DATE_SETTINGS))
    }

    // 跳转日期时间设置界面
    fun intentToDeviceInfo(context: Context) {
        context.startActivity(Intent(Settings.ACTION_DEVICE_INFO_SETTINGS))
    }

    // 跳转手机显示界面
    fun intentToDisplay(context: Context) {
        context.startActivity(Intent(Settings.ACTION_DISPLAY_SETTINGS))
    }

    // 跳转互动屏保界面
    fun intentToDream(context: Context) {
        context.startActivity(Intent(Settings.ACTION_DREAM_SETTINGS))
    }

    // 跳转语言和输入设备
    fun intentToInputMethod(context: Context) {
        context.startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
    }

    // 跳转到语言界面
    fun intentToLanguage(context: Context) {
        context.startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
    }

    // 跳转存储设置界面
    fun intentToStorage(context: Context) {
        context.startActivity(Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS))
    }

    // 跳转位置服务界面(管理已安装的应用程序)
    fun intentToLocationSource(context: Context) {
        context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }

    //  跳转到显示设置选择网络运营商
    fun intentToNetworkOperator(context: Context) {
        context.startActivity(Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS))
    }

    //  显示NFC共享设置
    fun intentToNFCShare(context: Context) {
        context.startActivity(Intent(Settings.ACTION_NFCSHARING_SETTINGS))
    }

    //  显示NFC设置
    fun intentToNFC(context: Context) {
        context.startActivity(Intent(Settings.ACTION_NFC_SETTINGS))
    }

    //  跳转到备份和重置界面
    fun intentToPrivacy(context: Context) {
        context.startActivity(Intent(Settings.ACTION_PRIVACY_SETTINGS))
    }

    //  跳转快速启动设置界面
    fun intentToQuickLauch(context: Context) {
        context.startActivity(Intent(Settings.ACTION_QUICK_LAUNCH_SETTINGS))
    }

    //  跳转搜索设置界面
    fun intentToSearch(context: Context) {
        context.startActivity(Intent(Settings.ACTION_SEARCH_SETTINGS))
    }

    //  跳转安全设置界面
    fun intentToSecurity(context: Context) {
        context.startActivity(Intent(Settings.ACTION_SECURITY_SETTINGS))
    }

    //  跳转设置界面
    fun intentToSetting(context: Context) {
        context.startActivity(Intent(Settings.ACTION_SETTINGS))
    }

    //  跳转账户同步界面
    fun intentToSync(context: Context) {
        context.startActivity(Intent(Settings.ACTION_SYNC_SETTINGS))
    }

    //  跳转用户字典界面
    fun intentToUserDictionary(context: Context) {
        context.startActivity(Intent(Settings.ACTION_USER_DICTIONARY_SETTINGS))
    }

    //  跳转允许其他应用在上层
    fun intentToManageOverlay(context: Context) {
        context.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION))
    }

    //  跳转市场
    fun intentToMarket(context: Context) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=${context.packageName}")
            )
        )
    }

    // 跳转到通知列表
    fun intentToNotification(context: Context) {
        val intent = Intent()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
            intent.putExtra("app_package", context.packageName)
            intent.putExtra("app_uid", context.applicationInfo.uid)
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.data = Uri.parse("package:" + context.packageName)
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (Build.VERSION.SDK_INT >= 9) {
                intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                intent.data = Uri.fromParts("package", context.packageName, null)
            } else if (Build.VERSION.SDK_INT <= 8) {
                intent.action = Intent.ACTION_VIEW
                intent.setClassName(
                    "com.android.settings",
                    "com.android.setting.InstalledAppDetails"
                )
                intent.putExtra("com.android.settings.ApplicationPkgName", context.packageName)
            }
        }
        context.startActivity(intent)
    }

    // 打电话
    @SuppressLint("MissingPermission")
    fun intentToCall(context: Context, phone: String) {
        var intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone"))
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        context.startActivity(intent)
    }

    // 跳转到打电话
    fun intentToDial(context: Context, phone: String) {
        context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))
    }

    // 跳转到短信
    fun intentToSms(context: Context, phone: String, body: String? = null) {
        var intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$phone"))
        body?.apply {
            intent.putExtra("sms_body", body)
        }
        context.startActivity(intent)
    }

    // 跳转到邮件
    fun intentToEmail(
        context: Context,
        email: String,
        subject: String,
        body: String,
        vararg otherEmail: String
    ) {
        var intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, body)
        otherEmail?.apply {
            intent.putExtra(Intent.EXTRA_CC, this)
        }
        context.startActivity(Intent.createChooser(intent, "请选择邮件类应用"))
    }

    // 跳转浏览器
    fun intentToBrowser(context: Context, websit: String) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("$websit")))
    }

    // 跳转到允许安装外部程序
    fun intentToAllowInstall(activity: AppCompatActivity, requestCode: Int) {
        val intent = Intent()
        intent.data = Uri.parse("package:${activity.packageName}")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.action = Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES
        } else {
            intent.action = Settings.ACTION_SECURITY_SETTINGS
        }
        activity.startActivityForResult(intent, requestCode)
    }
}