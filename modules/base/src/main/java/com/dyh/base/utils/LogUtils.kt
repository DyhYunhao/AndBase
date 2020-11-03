package com.dyh.base.utils

import android.util.Log

/*********************************************
 * @author daiyh
 * 创建日期：2020-11-3
 * 描述： 日志通用类，默认tag为使用的类名
 *********************************************
 */
class LogUtils {
    companion object {
        //类名
        @JvmField
        var className: String? = null

        //方法名
        @JvmField
        var methodName: String? = null

        //行数
        @JvmField
        var lineNumber: Int? = 0

        //日志每段显示的行数
        //一般v级的log很长，但是Android底层logcat的内核代码里设置单条日志的长度一般4*1024
        @JvmField
        val MAX_LINE_NUM: Int = 4000

        @JvmStatic
        private fun createLog(log: String): String {
            var buffer = StringBuffer()
            buffer.append(LogUtils.methodName)
            buffer.append("(").append(LogUtils.className).append(":")
                .append(LogUtils.lineNumber)
                .append(")")
            buffer.append(log)
            return buffer.toString()
        }

        @JvmStatic
        private fun getMethodName(sElement: Array<StackTraceElement>) {
            className = sElement[1].fileName
            methodName = sElement[1].methodName
            lineNumber = sElement[1].lineNumber
        }

        @JvmStatic
        public fun e(message: String) {
            getMethodName(Throwable().stackTrace)
            Log.e(className, createLog(message))
        }

        @JvmStatic
        public fun i(message: String) {
            getMethodName(Throwable().stackTrace)
            Log.i(className, createLog(message))
        }

        @JvmStatic
        public fun d(message: String) {
            getMethodName(Throwable().stackTrace)
            Log.d(className, createLog(message))
        }

        @JvmStatic
        public fun v(message: String) {
            getMethodName(Throwable().stackTrace)
            // 处理数据量较大，日志显示不全
            val strLength = message.length
            var start = 0
            var end: Int = LogUtils.MAX_LINE_NUM
            for (i in 0..99) {
                //剩下的文本还是大于规定长度则继续重复截取并输出
                if (strLength > end) {
                    Log.v(
                        className,
                        createLog(message.substring(start, end))
                    )
                    start = end
                    end += LogUtils.MAX_LINE_NUM
                } else {
                    Log.v(
                        className,
                        createLog(message.substring(start, strLength))
                    )
                    break
                }
            }
        }

        @JvmStatic
        public fun w(message: String) {
            getMethodName(Throwable().stackTrace)
            Log.w(className, createLog(message))
        }

        /**
         *将记录优先级ASSERT(中断级别)错误，并可以（根据系统配置）发送错误报告，并立即终止程序
         * What a Terrible Failure: Report an exception that should never happen.
         */
        @JvmStatic
        public fun wtf(message: String) {
            getMethodName(Throwable().stackTrace)
            Log.wtf(className, createLog(message))
        }
    }

}