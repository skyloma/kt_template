package ex

import android.app.Activity
import android.content.Context
import base.App
import io.objectbox.BoxStore

/**
 * Created by loma on 2018/1/31.
 */
fun  Context.getApp():App{
    return  App.instance
}
