package base

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Li
import com.loma.BaseListBinding
import com.loma.R
import kotlinx.android.synthetic.main.li_binding_layout.view.*
import xui.Pager
import xui.TempView
import xui.VMAdapter

abstract class BaseLiActivity<T> : AppCompatActivity() {

    lateinit var adapter: VMAdapter<T>
    lateinit var pager: Pager
    lateinit var binding: BaseListBinding
    var isLoad = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView<BaseListBinding>(this, R.layout.li_binding_layout)
        pager = Pager { loadMore() }
        adapter = VMAdapter(vm(), layoutId())
        adapter.itemClick { i -> onClick(i) }
        binding.li.setAdapter(adapter)
        binding.li.addOnScrollListener(pager)


        isLoad=false


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

    abstract fun layoutId(): Int
    abstract fun vm(): Int
    abstract fun loadMore()
    abstract fun initData()
    abstract fun onClick(position: Int)
}
