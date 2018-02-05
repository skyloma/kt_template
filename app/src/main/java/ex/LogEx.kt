package com.safframework.log

import android.content.Context
import android.util.Log
import com.loma.BuildConfig
import ex.LoggerPrinter

/**
 * Created by loma on 2018/1/31.
 */
  fun Any.log(  )   {
    if ( BuildConfig.LOG_DEBUG){
        var  t=  "zjt"
        apply {
            val s = getMethodNames1()

            if ( toString().contains("\n")) {
                Log.d(t, String.format(s, toString().replace("\n".toRegex(), "\n║ ")))
            } else {
                Log.d(t, String.format(s, toString()))
            }
        }


    }

}


infix fun Any.log(   tag: String? )   {
    if ( BuildConfig.LOG_DEBUG){
        var  t=tag?:"zjt"
        apply {
            val s = getMethodNames1()

            if ( toString().contains("\n")) {
                Log.d(t, String.format(s, toString().replace("\n".toRegex(), "\n║ ")))
            } else {
                Log.d(t, String.format(s, toString()))
            }
        }


    }

}



inline fun getMethodNames1(): String {
    val sElements = Thread.currentThread().stackTrace

    var stackOffset = LoggerPrinter.getStackOffset(sElements)

    stackOffset++
    val builder = StringBuilder()

    builder.append(LoggerPrinter.TOP_BORDER).append(LoggerPrinter.BR)

    // 添加当前线程名
    builder.append("║ " + "Thread: " + Thread.currentThread().name).append(LoggerPrinter.BR)
            .append(LoggerPrinter.MIDDLE_BORDER).append(LoggerPrinter.BR)
            // 添加类名、方法名、行数
            .append("║ ")
            .append(sElements[stackOffset].className)
            .append(".")
            .append(sElements[stackOffset].methodName)
            .append(" ")
            .append(" (")
            .append(sElements[stackOffset].fileName)
            .append(":")
            .append(sElements[stackOffset].lineNumber)
            .append(")")
            .append(LoggerPrinter.BR)
            .append(LoggerPrinter.MIDDLE_BORDER).append(LoggerPrinter.BR)
            // 添加打印的日志信息
            .append("║ ").append("%s").append(LoggerPrinter.BR)
            .append(LoggerPrinter.BOTTOM_BORDER).append(LoggerPrinter.BR)

    return builder.toString()
}
