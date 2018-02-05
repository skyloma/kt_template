package xui

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by loma on 2018/1/31.
 */
fun Date.getString17( ):String {

    val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return  df.format(this)

}

fun Date.getString10( ):String {

    val df = SimpleDateFormat("yyyy-MM-dd")
    return  df.format(this)

}
