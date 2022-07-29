package com.don.library.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    const val FORMAT_Y_M_D_H_M_S = "yyyy/MM/dd HH:mm:ss"
    const val FORMAT_Y_M_D_H_M = "yyyy/MM/dd HH:mm"
    const val FORMAT_Y_M_D = "yyyy/MM/dd"
    const val FORMAT_Y_M_DZH = "yyyy年MM月dd日"
    const val FORMAT_YMD = "yyyy-MM-dd"
    const val FORMAT_Y_M = "yyyy/MM"
    const val FORMAT_M_D_H_M_S = "MM/dd HH:mm:ss"
    const val FORMAT_M_D_H_M = "MM/dd HH:mm"
    const val FORMAT_M_D = "MM/dd"
    const val FORMAT_M_DZH = "MM月dd日"
    const val FORMAT_M_D_H_MZH = "MM月dd日 HH:mm"
    const val FORMAT_H_M_S = "HH:mm:ss"
    const val FORMAT_H_M = "HH:mm"
    const val FORMAT_MZH = "MM月"
    const val FORMAT_DZH = "dd日"

    private const val YEAR = 365 * 24 * 60 * 60
    private const val MONTH = 30 * 24 * 60 * 60
    private const val DAY = 24 * 60 * 60
    private const val HOUR = 60 * 60
    private const val MINUTE = 60

    private val mFormat by lazy { SimpleDateFormat() }

    // 获取某格式时间
    fun getFormatDate(timestamp: Long, format: String): String {
        try {
            mFormat.applyPattern(format)
            return mFormat.format(timestamp)
        } catch (e: Exception) {
        }
        mFormat.applyPattern(FORMAT_Y_M_D_H_M_S)
        return mFormat.format(timestamp)
    }

    // 获取简短时间
    fun getSimpleDate(timestamp: Long): String {
        return when {
            isSameDay(timestamp) -> "今天" + getFormatDate(timestamp, FORMAT_H_M)
            isSameMonth(timestamp) -> getFormatDate(timestamp, FORMAT_M_D_H_M)
            isSameYear(timestamp) -> getFormatDate(timestamp, FORMAT_M_D_H_M)
            else -> getFormatDate(timestamp, FORMAT_Y_M_D_H_M)
        }
    }

    // 获取描述型时间
    fun getDescriptionDate(timestamp: Long): String {
        var currentTime = System.currentTimeMillis()
        var time = (currentTime - timestamp) / 1000
        return when {
            time > DAY -> getFormatDate(timestamp, getSimpleDate(timestamp))
            time > HOUR -> (time / HOUR).toString() + "小时前"
            time > MINUTE -> (time / MINUTE).toString() + "分钟前"
            else -> "刚刚"
        }
    }

    // 是否同一年
    fun isSameYear(timestamp: Long): Boolean {
        var currentCalendar = Calendar.getInstance()
        var calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp

        var year = calendar[Calendar.YEAR]
        var currentYear = currentCalendar[Calendar.YEAR]
        return year == currentYear
    }

    // 是否同一月
    fun isSameMonth(timestamp: Long): Boolean {
        var currentCalendar = Calendar.getInstance()
        var calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp

        var year = calendar[Calendar.YEAR]
        var month = calendar[Calendar.MONTH]

        var currentYear = currentCalendar[Calendar.YEAR]
        var currentMonth = currentCalendar[Calendar.MONTH]

        return year == currentYear && month == currentMonth
    }

    // 是否是今天
    fun isSameDay(timestamp: Long): Boolean {
        var currentCalendar = Calendar.getInstance()
        var calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp

        var year = calendar[Calendar.YEAR]
        var month = calendar[Calendar.MONTH]
        var day = calendar[Calendar.DAY_OF_MONTH]

        var currentYear = currentCalendar[Calendar.YEAR]
        var currentMonth = currentCalendar[Calendar.MONTH]
        var currentDay = currentCalendar[Calendar.DAY_OF_MONTH]

        return year == currentYear && month == currentMonth && day == currentDay
    }

    /**
     * 是否是同一天
     */
    fun isSameDay(firstTimestamp: Long, secondTimestamp: Long): Boolean {
        val firstCalendar = Calendar.getInstance()
        val secondCalendar = Calendar.getInstance()

        firstCalendar.timeInMillis = firstTimestamp
        secondCalendar.timeInMillis = secondTimestamp

        return firstCalendar[Calendar.YEAR] == secondCalendar[Calendar.YEAR] &&
                firstCalendar[Calendar.MONTH] == secondCalendar[Calendar.MONTH] &&
                firstCalendar[Calendar.DAY_OF_MONTH] == secondCalendar[Calendar.DAY_OF_MONTH]
    }

    // 是否昨天
    fun isYesterday(timestamp: Long): Boolean {
        val yesterdayCalendar = Calendar.getInstance()
        yesterdayCalendar.add(Calendar.DAY_OF_MONTH, -1)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp

        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val yesterdayYear = yesterdayCalendar[Calendar.YEAR]
        val yesterdayMonth = yesterdayCalendar[Calendar.MONTH]
        val yesterdayDay = yesterdayCalendar[Calendar.DAY_OF_MONTH]

        return year == yesterdayYear && month == yesterdayMonth && day == yesterdayDay
    }

    // 是否前天
    fun isLastYesterday(timestamp: Long): Boolean {
        val yesterdayCalendar = Calendar.getInstance()
        yesterdayCalendar.add(Calendar.DAY_OF_MONTH, -2)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp

        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val yesterdayYear = yesterdayCalendar[Calendar.YEAR]
        val yesterdayMonth = yesterdayCalendar[Calendar.MONTH]
        val yesterdayDay = yesterdayCalendar[Calendar.DAY_OF_MONTH]

        return year == yesterdayYear && month == yesterdayMonth && day == yesterdayDay
    }

    // 是否和今天相差某天
    fun isDifferDay(timestamp: Long, differ: Int): Boolean {
        var dayCalendar = Calendar.getInstance()
        dayCalendar.timeInMillis = timestamp

        var calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, differ)

        var year = calendar[Calendar.YEAR]
        var month = calendar[Calendar.MONTH]
        var day = calendar[Calendar.DAY_OF_MONTH]

        var dayYear = dayCalendar[Calendar.YEAR]
        var dayMonth = dayCalendar[Calendar.MONTH]
        var dayDay = dayCalendar[Calendar.DAY_OF_MONTH]

        return year == dayYear && month == dayMonth && day == dayDay
    }

    // 字符串转时间戳
    fun formatToTimestamp(date: String?, format: String?): Long? {
        if (date.isNullOrEmpty() || format.isNullOrEmpty()) {
            return null
        }
        var simpleDateFormat = SimpleDateFormat(format)
        try {
            return simpleDateFormat.parse(date)?.time
        } catch (e: Exception) {
        }
        return null
    }

    /**
     * 根据日期获取当天是周几
     * @param timestamp 日期
     * @return 周几
     */
    fun dateToWeek(timestamp: Long): String {
        val weekDays = arrayOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")
        val cal = Calendar.getInstance()
        val date = Date(timestamp)
        try {
            cal.time = date
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val w = cal[Calendar.DAY_OF_WEEK] - 1
        return weekDays[w]
    }

    /**
     * 根据日期获取当天是周几
     * @param datetime 日期
     * @return 周几
     */
    fun dateToEndWeek(datetime: Long): String {
        val weekDays = arrayOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")
        val cal = Calendar.getInstance()
        val date = Date(datetime)
        try {
            cal.time = date
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        var w = if (cal[Calendar.HOUR_OF_DAY] == 0
            && cal[Calendar.MINUTE] == 0
            && cal[Calendar.SECOND] == 0
        ) {
            cal[Calendar.DAY_OF_WEEK] - 2
        } else {
            cal[Calendar.DAY_OF_WEEK] - 1
        }
        if (w < 0) {
            w = 6
        }
        return weekDays[w]
    }

    fun getCountDown(time: Long, isRetainHour: Boolean = false): String {
        val lastTime = time / 1000
        val day = (lastTime / (60 * 60 * 24)).toInt()
        val hours = (lastTime / (60 * 60) - day * 24).toInt()
        val leftSeconds = (lastTime % (60 * 60)).toInt()
        val minutes = leftSeconds / 60
        val seconds = leftSeconds % 60
        val buffer = StringBuffer()
        if (day > 0) {
            buffer.append(day)
            buffer.append("天")
        }
        if (hours > 0 || isRetainHour) {
            if (hours < 10) {
                buffer.append("0$hours")
            } else {
                buffer.append(hours)
            }
            buffer.append(":")
        }
        if (minutes < 10) {
            buffer.append("0$minutes")
        } else {
            buffer.append(minutes)
        }
        buffer.append(":")
        if (seconds < 10) {
            buffer.append("0$seconds")
        } else {
            buffer.append(seconds)
        }
        return buffer.toString()
    }
}
