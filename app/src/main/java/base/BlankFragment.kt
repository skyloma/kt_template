package base


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.loma.R
import kotlinx.android.synthetic.main.fragment_blank.*
import xui.TempView


/**
 * A simple [Fragment] subclass.
 */
class BlankFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)
//        view.findViewById<View>(R.id.shownull).setOnClickListener {tempView.setState(TempView.State.NULL)  }
        return view
    }

}// Required empty public constructor
