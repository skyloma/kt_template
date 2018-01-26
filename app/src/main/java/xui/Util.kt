package xui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Environment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.socks.library.KLog
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.NewThreadScheduler
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*


fun Any.log(msg: Any?) {


    if (msg != null) {

        KLog.e("zjt", msg.toString())
    } else {

        KLog.e("zjt", "log msg  null ")
    }


}
fun Context.logJson(msg: Any?) {
    if (msg != null) {
        Log.e("zjt", Gson().toJson(msg) )
    } else {
        Log.e("zjt", "log msg  null ")
    }


}


fun Date.getString17( ):String {

    val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return  df.format(this)

}

fun Date.getString10( ):String {

    val df = SimpleDateFormat("yyyy-MM-dd")
    return  df.format(this)

}

fun Context.toast(msg: Any?) {
    Toast.makeText(this, msg?.toString() ?: "", Toast.LENGTH_LONG).show()

}

inline fun <reified T : Any> String.getObject(): T? {


    return GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(this, T::class.java)

}

inline fun <reified T : Any> JsonElement.getObject(): T? {


    return GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(this, T::class.java)

}

inline fun <reified T : Any> String.getList(): List<T> {

    return GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(this, object: TypeToken<List<T>>() {}.type)

}

inline fun <reified T : Any> JsonElement.getList(): List<T>  {

     return  GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(this, object: TypeToken<List<T>>() {}.type)


}

/**
 * 检测网络是否可用
 *
 * @param context
 * @return
 */
fun Context.isNetWorkConnected( ): Boolean {
    val mConnectivityManager =   getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
    var file = File(cameraPath,
            "com_${System.currentTimeMillis()}.jpg")
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
        Picasso
                .with(context)
                .load(it)
                .into(this);
    }



}

    fun Activity.showMsg(msg: String): Unit {


    val builder = AlertDialog.Builder(this)
    builder.setTitle("提示信息")
    builder.setMessage(msg)

    builder.setPositiveButton("确定") { dialogInterface, i -> dialogInterface.dismiss() }
    builder.show()


}

  fun <T> Observable<T>.io_main(): Observable<T> {

    return compose(ObservableTransformer { upstream ->
        upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    })
}

fun <T> Single<T>.io_main(): Single<T> {

    return compose(SingleTransformer { upstream ->
        upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    })
}

  fun  Disposable.addTo(compositeDisposable: CompositeDisposable) {
      compositeDisposable.add(this)

}
