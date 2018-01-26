package base


import android.app.Activity

import java.util.Stack


object  ActivityStack   : Stack<Activity>() {

    // 退出栈顶Activity
    fun removeActivity() {
        var activity: Activity = lastElement()
        removeActivity(activity)


    }

    // 将Activity移除
    fun removeActivity(activity: Activity) {
            activity.finish()
            remove(activity)

    }


    // 退出栈中所有除了cls的Activity
    fun removeOther(cls: Class<*>) {
        if (!isEmpty())
            while (true) {
                val activity = lastElement()
                if (activity.javaClass != cls)
                    removeActivity(activity)
            }


    }

    // 退出栈中所有Activity
    fun removeAllActivity() {

        this.forEach {
            removeActivity(it)
        }
//        if (!isEmpty())
//            while (true) {
//                val activity = lastElement() ?: break
//                removeActivity(activity)
//            }




    }

    fun exit() {
        removeAllActivity()
        try {
            android.os.Process.killProcess(android.os.Process.myPid())    //获取PID
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            System.exit(0)
        }

        //常规java、c#的标准退出法，返回值为0代表正常退出
    }


}