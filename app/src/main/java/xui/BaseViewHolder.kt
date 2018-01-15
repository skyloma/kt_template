package xui

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.SparseArray
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView


import java.io.File
import java.text.DecimalFormat
import java.util.Date


/**
 * Created by ma on 2015/7/10.
 * RecyclerView通用ViewHolder
 */
class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val sparseArray: SparseArray< View>

    val convertView: View
        get() = itemView


    init {
        this.sparseArray = SparseArray()

    }

  inline  fun <reified  T : View> getView(id: Int): T {
        var childView: View? = sparseArray.get(id)
        if (childView == null) {
            childView = itemView.findViewById(id)
            sparseArray.put(id, childView)
        }
        return childView as T
    }

    fun getTextView(viewId: Int): TextView {
        return getView(viewId)
    }


    fun getRecyclerView(viewId: Int): RecyclerView {
        return getView(viewId)
    }

    fun getCheckBox(viewId: Int): CheckBox {
        return getView(viewId)
    }

    fun getRiadioButton(viewId: Int): RadioButton {
        return getView(viewId)
    }

    fun getButton(viewId: Int): Button {
        return getView(viewId)
    }


    fun getImageView(viewId: Int): ImageView {
        return getView(viewId)
    }


    fun setText(viewId: Int, value: String?): BaseViewHolder {
        getTextView(viewId).text = value ?: ""
        return this@BaseViewHolder

    }

    fun setText(viewId: Int, value: Int): BaseViewHolder {
        getTextView(viewId).text = value.toString() + ""
        return this@BaseViewHolder
    }

    fun setText(viewId: Int, value: Float): BaseViewHolder {

        getTextView(viewId).text = DecimalFormat(".00").format(value.toDouble())
        return this@BaseViewHolder
    }

    fun setText(viewId: Int, value: Double): BaseViewHolder {
        getTextView(viewId).text = DecimalFormat(".00").format(value)
        return this@BaseViewHolder
    }

    fun setText(viewId: Int, value: Long): BaseViewHolder {
        getTextView(viewId).text = value.toString() + ""
        return this@BaseViewHolder
    }

    fun setText(viewId: Int, value: Date?): BaseViewHolder {
        getTextView(viewId).text = value?.toString() ?: ""
        return this@BaseViewHolder
    }

    fun setChecked(viewId: Int, value: Boolean): BaseViewHolder {
        getCheckBox(viewId).isChecked = value
        return this@BaseViewHolder
    }

    fun setRadioChecked(viewId: Int, value: Boolean, text: String): BaseViewHolder {
        getRiadioButton(viewId).isChecked = value
        getRiadioButton(viewId).text = text
        return this@BaseViewHolder
    }


    fun setRadioChecked(viewId: Int, value: Boolean): BaseViewHolder {
        getRiadioButton(viewId).isChecked = value
        return this@BaseViewHolder
    }

    fun setChecked(viewId: Int, value: Boolean, text: String): BaseViewHolder {
        getCheckBox(viewId).isChecked = value
        getCheckBox(viewId).text = text
        return this@BaseViewHolder
    }

    fun setBackgroundColor(viewId: Int, color: Int): BaseViewHolder {
        val view = getView<View>(viewId)
        view.setBackgroundColor(color)
        return this@BaseViewHolder
    }

    fun setBackgroundResource(viewId: Int, backgroundRes: Int): BaseViewHolder {
        val view = getView<View>(viewId)
        view.setBackgroundResource(backgroundRes)
        return this@BaseViewHolder
    }

    fun setVisible(viewId: Int, visible: Boolean): BaseViewHolder {
        val view = getView<View>(viewId)
        view.visibility = if (visible) View.VISIBLE else View.GONE
        return this@BaseViewHolder
    }

    fun setOnClickListener(viewId: Int, listener: View.OnClickListener): BaseViewHolder {
        val view = getView<View>(viewId)
        view.setOnClickListener(listener)
        return this@BaseViewHolder
    }

    fun setOnTouchListener(viewId: Int, listener: View.OnTouchListener): BaseViewHolder {
        val view = getView<View>(viewId)
        view.setOnTouchListener(listener)
        return this@BaseViewHolder
    }

    fun setOnLongClickListener(viewId: Int, listener: View.OnLongClickListener): BaseViewHolder {
        val view = getView<View>(viewId)
        view.setOnLongClickListener(listener)
        return this@BaseViewHolder
    }

    fun setTag(viewId: Int, tag: Any): BaseViewHolder {
        val view = getView<View>(viewId)
        view.tag = tag
        return this@BaseViewHolder
    }


}