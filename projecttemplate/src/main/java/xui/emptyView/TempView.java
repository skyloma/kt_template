package xui.emptyView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ma.R;


public class TempView extends LinearLayout {
    private String mExampleString;
    private int                       mExampleDrawable;
    private ProgressBar               progressBar;
    private LinearLayout              emptyLayout;
    private LinearLayout.LayoutParams lp;

    public TempView(Context context) {
        super(context);
        init(null, 0);
    }

    public TempView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TempView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private int State;

    public void setState(int state) {
        State = state;
        switch (state) {
            case 0:
                this.setVisibility(GONE);
                break;
            case 1:
                this.setVisibility(VISIBLE);
                progressBar.setVisibility(VISIBLE);
                emptyLayout.setVisibility(GONE);
                break;
            case 2:
                this.setVisibility(VISIBLE);
                progressBar.setVisibility(GONE);
                emptyLayout.setVisibility(VISIBLE);
                break;
        }
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TempView, defStyle, 0);
        mExampleString = a.getString(R.styleable.TempView_tempTip);
        mExampleDrawable = a.getResourceId(
                R.styleable.TempView_tempTipIcon,  R.drawable.box_empty);

        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);

        lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressBar = new ProgressBar(this.getContext(), null, android.R.attr.progressBarStyle);
        progressBar.setLayoutParams(lp);

        addView(progressBar);
        emptyLayout = new LinearLayout(this.getContext());

        emptyLayout.setGravity(Gravity.CENTER);
        emptyLayout.setOrientation(VERTICAL);
        ImageView imageView = new ImageView(this.getContext());

        imageView.setLayoutParams(lp);
        imageView.setBackgroundResource(  mExampleDrawable);

        emptyLayout.addView(imageView);
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(lp);
        textView.setText(TextUtils.isEmpty(mExampleString)?"没有数据":mExampleString);
        emptyLayout.addView(textView);
        addView(emptyLayout);
        setState(0);
        a.recycle();


    }


}
