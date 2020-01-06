package xui

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

import com.loma.R


class TempView : LinearLayout {
    private var mExampleString: String? = null
    private var mExampleDrawable: Int = 0
    private var progressBar: ProgressBar? = null
    private var emptyLayout: LinearLayout? = null
    private var lp: LinearLayout.LayoutParams? = null

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    enum class State {
        NONE, LOADING, NULL, ERR
    }

    fun setState(state: State) {

        when (state) {
            TempView.State.NONE -> {this.visibility = View.GONE
            progressBar!!.visibility = View.GONE
                    emptyLayout!!.visibility = View.GONE}
            TempView.State.LOADING -> {
                this.visibility = View.VISIBLE
                progressBar!!.visibility = View.VISIBLE
                emptyLayout!!.visibility = View.GONE
            }
            TempView.State.NULL -> {
                this.visibility = View.VISIBLE
                progressBar!!.visibility = View.GONE
                emptyLayout!!.visibility = View.VISIBLE
            }
        }
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(attrs, R.styleable.TempView, defStyle, 0)
        mExampleString = a.getString(R.styleable.TempView_tempTip)
        mExampleDrawable = a.getResourceId(R.styleable.TempView_tempTipIcon, R.drawable.nofound)

        gravity = Gravity.CENTER
        orientation = LinearLayout.VERTICAL

        lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        progressBar = ProgressBar(this.context, null, android.R.attr.progressBarStyle)
        progressBar!!.layoutParams = lp

        addView(progressBar)
        emptyLayout = LinearLayout(this.context)

        emptyLayout!!.gravity = Gravity.CENTER
        emptyLayout!!.orientation = LinearLayout.VERTICAL
        val imageView = ImageView(this.context)

        imageView.layoutParams = lp
        imageView.setBackgroundResource(mExampleDrawable)

        emptyLayout!!.addView(imageView)
        val textView = TextView(context)
        textView.layoutParams = lp
        textView.text = if (TextUtils.isEmpty(mExampleString)) "没有数据" else mExampleString
        emptyLayout!!.addView(textView)
        addView(emptyLayout)
        setState(TempView.State.NONE)

        a.recycle()


    }


}
