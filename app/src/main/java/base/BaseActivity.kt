package base


import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.loma.R
import com.socks.library.KLog
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.dialog_progress.view.*
import xui.DelayedProgressDialog
import xui.log


abstract class BaseActivity : AppCompatActivity() {


    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityStack.add(this)
        val mActionBar = supportActionBar
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true)
            mActionBar.elevation = 0f
        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    fun showLoading() {

//       AlertDialog.Builder(this).apply {
//             setContentView(R.layout.dialog_progress)
//             progressDialog= show()
//         }

        progressDialog= ProgressDialog.show(this, // context
                "", // title
                "Loading. Please wait...", // message
                true)

    }


    fun closeLoading() {
        progressDialog?.let {
            if (it.isShowing)
            it.cancel()

        }
    }


    fun showDialog(msg: String, onClickListener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("提示信息")
        builder.setMessage(msg)
        builder.setNegativeButton(android.R.string.cancel) { dialogInterface, i -> dialogInterface.dismiss() }
        builder.setPositiveButton(android.R.string.ok, onClickListener)
        builder.show()


    }


    override fun onDestroy() {
        super.onDestroy()



    }


    fun hideSoftInput() {
        val v = currentFocus
        if (v != null && v.windowToken != null) {
            val manager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            val isOpen = manager.isActive
            if (isOpen) {
                manager.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }


}
