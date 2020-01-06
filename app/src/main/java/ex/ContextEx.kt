package xui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Environment
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


fun Context.toast(msg: Any?) {
    Toast.makeText(this, msg?.toString() ?: "", Toast.LENGTH_LONG).show()

}

fun Activity.inflate(layoutId: Int): View {
    return getWindow().getLayoutInflater().inflate(layoutId, null)

}

fun Fragment.inflate(layoutId: Int): View {
    var view = activity?.let {
        it.getWindow()?.getLayoutInflater()?.inflate(layoutId, null)
    }
    return view!!
}


/**
 * 检测网络是否可用
 *
 * @param context
 * @return
 */
fun Context.isNetWorkConnected(): Boolean {
    val mConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val mNetworkInfo = mConnectivityManager.activeNetworkInfo
    if (mNetworkInfo != null) {
        return mNetworkInfo.isAvailable
    }

    return false
}

/**
 * @MethodName: toAlbum
 * *
 * @Description: 将图片暴露在相册目录
 * *
 * @param ac
 * *
 * @param path
 * *
 * @return
 */
fun Context.toAlbum(file: File) {
    val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
    val uri = Uri.fromFile(file)
    intent.data = uri
    this.sendBroadcast(intent)
}

fun Bitmap.toFile(): File {

    var cameraPath = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).absolutePath + "/Camera/")
    if (!cameraPath.exists()) {
        cameraPath.mkdir()
    }
    var file = File(cameraPath, "com_${System.currentTimeMillis()}.jpg")
    try {
        val bos = BufferedOutputStream(FileOutputStream(file))
        this.compress(Bitmap.CompressFormat.JPEG, 100, bos)
        bos.flush()
        bos.close()


    } catch (e: IOException) {
        e.printStackTrace()
    }

    return file

}

fun ImageView.setFile(file: File?) {
    file?.let {
        Picasso.with(context).load(it).into(this);
    }


}

fun Activity.showMsg(msg: String): Unit {
    val builder = AlertDialog.Builder(this)
    builder.setTitle("提示信息")
    builder.setMessage(msg)

    builder.setPositiveButton("确定") { dialogInterface, i -> dialogInterface.dismiss() }
    builder.show()


}