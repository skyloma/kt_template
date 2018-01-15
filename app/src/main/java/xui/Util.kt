package xui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

fun Context.log(msg: Any?) {
    if (msg == null) {
        Log.e("zjt", msg)
    } else {
        Log.e("zjt", "log msg  null ")
    }


}
fun Context.toast(msg: Any?) {
    Toast.makeText(this,msg?.toString()?:"",Toast.LENGTH_LONG).show()

}

