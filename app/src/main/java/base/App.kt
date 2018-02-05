package base

import android.app.Application

import com.loma.BuildConfig

import io.objectbox.BoxStore
import db.MyObjectBox
import io.objectbox.android.AndroidObjectBrowser
import kotlin.properties.Delegates

class App : Application() {
    companion object {
        var instance: App by Delegates.notNull()
    }

    lateinit var db: BoxStore
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = MyObjectBox.builder().androidContext(this).build()
        if (BuildConfig.DEBUG) {
            AndroidObjectBrowser(db).start(this)
        }
    }
}
