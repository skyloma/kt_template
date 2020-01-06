package xui


import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by ma on 2015/7/10.
 * RecyclerView通用数据绑定
 */
class VMAdapter<T> @JvmOverloads constructor(val vm: Int,val layoutId: Int   ) : RecyclerView.Adapter<VMAdapter.BindingHolder>() {



    var data: MutableList<T> = mutableListOf()
    fun lastItem(): T {
        return data[data.size - 1]
    }


    fun add(t: T) {
        insert(t, data.size)
    }

    fun insert(t: T, position: Int) {
        data.add(position, t)
        notifyItemInserted(position)
    }

    fun remove(position: Int) {


        data.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, data.size)
    }

    fun clear() {
        val size = data.size
        data.clear()
        notifyItemRangeRemoved(0, size)
    }


    fun addAll(list: List<T>) {
        if (list.isEmpty()) {
            return
        }
        val startIndex = data.size
        data.addAll(startIndex, list)
        notifyItemRangeInserted(startIndex, list.size)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    fun getItem(position: Int): T {
        (position in 0..data.size - 1).let {
            return data[position]
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BindingHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(viewGroup.context), layoutId, viewGroup, false)
        val holder = BindingHolder(binding!!.root)
        holder.binding=binding
        return holder
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {


        if (itemClickDoing != null) {
            holder.itemView.isClickable = true
            holder.itemView.setOnClickListener { v -> itemClickDoing?.invoke(position) }
        }
        holder.binding?.setVariable(vm, data.get(position))
        holder.binding?.executePendingBindings()


    }





    fun itemClick(doing: (Int) -> Unit) {
        itemClickDoing = doing
    }

    private var itemClickDoing: ((Int) -> Unit)? = { i -> }


    class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ViewDataBinding? = null
    }


}