package base

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loma.BaseListBinding
import com.loma.R
import kotlinx.android.synthetic.main.li_binding_layout.view.*
import xui.*

abstract class BaseLiFragment<T> : BaseFragment() {
    lateinit var adapter: VMAdapter<T>
    lateinit var pager: Pager
    lateinit var binding: BaseListBinding
      var rootView :View?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.li_binding_layout, container, false)
        pager = Pager { loadMore() }
        adapter = VMAdapter(vm(), layoutId())
        adapter.itemClick { i -> onClick(i) }
        binding.li.setAdapter(adapter)
        binding.li.addOnScrollListener(pager)

        rootView=binding.root
        return rootView


    }

    fun setEmpty(){
        binding.li.visibility=View.GONE
        binding.tempView.setState(TempView.State.NULL)
        binding.li.tempView?.apply {
            setOnClickListener { initData() }
        }

    }

    fun setLoading(){
        binding.li.visibility=View.GONE
        binding.tempView.setState(TempView.State.LOADING)
        binding.li.tempView?.apply {
            setOnClickListener {   }
        }

    }
    fun  setNoTempView(){
        binding.li.visibility=View.VISIBLE
        binding.tempView.visibility=View.GONE
        binding.tempView.setState(TempView.State.NONE)

    }





    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (rootView == null) {
            return
        }
        if (isVisibleToUser&&adapter.itemCount==0) {
                initData()

        }
    }


    abstract fun layoutId(): Int
    abstract fun vm(): Int


//    open fun empty(): View {
//       return layoutInflater.inflate(R.layout.empty, null).apply {
//           setOnClickListener { initData() }
//
//       }
//
//
//
//    }

    abstract fun loadMore()
    abstract fun initData()
    abstract fun onClick(position: Int)




}