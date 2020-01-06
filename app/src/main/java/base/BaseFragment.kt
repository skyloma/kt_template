package base


import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.loma.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
open class BaseFragment : Fragment() {



    //进度条
     var progress: ProgressDialog? = null

      fun showProgress(message: String = "加载中") {
        if (progress == null) {
            progress = ProgressDialog(activity!!)
            progress!!.setMessage(message)
            progress!!.setCancelable(false)
        }
        progress!!.show()
    }

      fun closeProgress() {
        if (progress != null)
            progress!!.dismiss()
    }

}
