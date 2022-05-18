package com.don.library.util

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import org.json.JSONArray
import org.json.JSONObject

object LogUtil {

    private const val TAG = "library"

    // 是否打印logger
    private var isLoggable = true

    // 过滤打印标签,不为空时,过滤包含的标签
    private val mFilterTags: MutableSet<String> by lazy {
        LinkedHashSet<String>()
    }

    init {
        var formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodCount(0)
            .methodOffset(7)
            .tag(TAG)
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return isLoggable
            }
        })
    }

    // 是否打印logger
    fun isLoggable(enable: Boolean): LogUtil {
        isLoggable = enable
        return this
    }

    // 添加过滤标签
    fun addFilter(filter: String): LogUtil {
        mFilterTags.add(filter)
        return this
    }

    // 移除过滤标签
    fun removeFilter(filter: String): LogUtil {
        mFilterTags.remove(filter)
        return this
    }

    // debug打印
    fun log(log: String?) {
        log(null, log)
    }

    // debug打印
    fun log(tag: String?, log: String?) {
        if (log.isNullOrEmpty()) {
            return
        }
        var tag = tag.apply {
            if (isNullOrEmpty()) {
//                BuildConfig.APPLICATION_ID
            } else {
                this
            }
        }
        if (mFilterTags.contains(tag)) {
            return
        }
        try {
            try {
                JSONObject(log)
                json(tag, log)
            } catch (e: Exception) {
                JSONArray(log)
                json(tag, log)
            }
        } catch (e: Exception) {
            logD(tag,log)
//            Logger.t(tag).d(log)
        }
    }

    private fun logD(tag:String?,log: String) {
        var msg= log
        val segmentSize = 3 * 1024
        val length = msg.length
        // 长度小于等于限制直接打印
        if (length <= segmentSize) {
            Logger.t(tag).d(msg)
        } else {
            // 循环分段打印日志
            while (msg.length> segmentSize) {
                val logContent: String = msg.substring(0, segmentSize)
                msg = msg.replace(logContent, "")
                Logger.t(tag).d(logContent)
            }
            // 打印剩余日志
            Logger.t(tag).d(msg)
        }
    }

    // json打印
    fun json(json: String?) {
        json(null, json)
    }

    // json打印
    fun json(tag: String?, json: String?) {
        if (json.isNullOrEmpty()) {
            return
        }
        var tag = tag.apply {
            if (isNullOrEmpty()) {
                TAG
            } else {
                this
            }
        }
        if (mFilterTags.contains(tag)) {
            return
        }
        var msg= json?:""
        val segmentSize = 3 * 1024
        val length = msg.length
        // 长度小于等于限制直接打印
        if (length <= segmentSize) {
            Logger.t(tag).json(msg)
        } else {
            // 循环分段打印日志
            while (msg.length> segmentSize) {
                val logContent: String = msg.substring(0, segmentSize)
                msg = msg.replace(logContent, "")
                Logger.t(tag).json(logContent)
            }
            // 打印剩余日志
            Logger.t(tag).json(msg)
        }
    }

    // xml打印
    fun logXml(xml: String?) {
        xml(null, xml)
    }

    // xml打印
    fun xml(tag: String?, xml: String?) {
        if (xml.isNullOrEmpty()) {
            return
        }
        var tag = tag.apply {
            if (isNullOrEmpty()) {
                TAG
            } else {
                this
            }
        }
        if (mFilterTags.contains(tag)) {
            return
        }
        Logger.t(tag).xml(xml)
    }
}