package xui

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView


import java.text.DecimalFormat
import java.util.Date


/**
 * Created by ma on 2015/7/10.
 * RecyclerView通用ViewHolder
 */
class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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


    fun setText(viewId: Int, value: String?): Holder {
        getTextView(viewId).text = value ?: ""
        return this@Holder

    }

    fun setText(viewId: Int, value: Int): Holder {
        getTextView(viewId).text = value.toString() + ""
        return this@Holder
    }

    fun setText(viewId: Int, value: Float): Holder {

        getTextView(viewId).text = DecimalFormat(".00").format(value.toDouble())
        return this@Holder
    }

    fun setText(viewId: Int, value: Double): Holder {
        getTextView(viewId).text = DecimalFormat(".00").format(value)
        return this@Holder
    }

    fun setText(viewId: Int, value: Long): Holder {
        getTextView(viewId).text = value.toString() + ""
        return this@Holder
    }

    fun setText(viewId: Int, value: Date?): Holder {
        getTextView(viewId).text = value?.toString() ?: ""
        return this@Holder
    }

    fun setChecked(viewId: Int, value: Boolean): Holder {
        getCheckBox(viewId).isChecked = value
        return this@Holder
    }

    fun setRadioChecked(viewId: Int, value: Boolean, text: String): Holder {
        getRiadioButton(viewId).isChecked = value
        getRiadioButton(viewId).text = text
        return this@Holder
    }


    fun setRadioChecked(viewId: Int, value: Boolean): Holder {
        getRiadioButton(viewId).isChecked = value
        return this@Holder
    }

    fun setChecked(viewId: Int, value: Boolean, text: String): Holder {
        getCheckBox(viewId).isChecked = value
        getCheckBox(viewId).text = text
        return this@Holder
    }

    fun setBackgroundColor(viewId: Int, color: Int): Holder {
        val view = getView<View>(viewId)
        view.setBackgroundColor(color)
        return this@Holder
    }

    fun setBackgroundResource(viewId: Int, backgroundRes: Int): Holder {
        val view = getView<View>(viewId)
        view.setBackgroundResource(backgroundRes)
        return this@Holder
    }

    fun setVisible(viewId: Int, visible: Boolean): Holder {
        val view = getView<View>(viewId)
        view.visibility = if (visible) View.VISIBLE else View.GONE
        return this@Holder
    }

    fun setOnClickListener(viewId: Int, listener: View.OnClickListener): Holder {
        val view = getView<View>(viewId)
        view.setOnClickListener(listener)
        return this@Holder
    }

    fun setOnTouchListener(viewId: Int, listener: View.OnTouchListener): Holder {
        val view = getView<View>(viewId)
        view.setOnTouchListener(listener)
        return this@Holder
    }

    fun setOnLongClickListener(viewId: Int, listener: View.OnLongClickListener): Holder {
        val view = getView<View>(viewId)
        view.setOnLongClickListener(listener)
        return this@Holder
    }

    fun setTag(viewId: Int, tag: Any): Holder {
        val view = getView<View>(viewId)
        view.tag = tag
        return this@Holder
    }


}