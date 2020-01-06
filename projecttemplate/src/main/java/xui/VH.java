package xui;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;



import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;


/**
 * Created by ma on 2015/7/10.
 * RecyclerView通用ViewHolder
 */
public class VH extends RecyclerView.ViewHolder {
    private SparseArray<View> sparseArray;


    public VH(View itemView) {
        super(itemView);
        this.sparseArray = new SparseArray<View>();

    }

    public <T extends View> T getView(int id) {
        View childView = sparseArray.get(id);
        if (childView == null) {
            childView = itemView.findViewById(id);
            sparseArray.put(id, childView);
        }
        return (T) childView;
    }

    public View getConvertView() {
        return itemView;
    }

    public TextView getTextView(int viewId) {
        return getView(viewId);
    }


    public RecyclerView getRecyclerView(int viewId) {
        return getView(viewId);
    }

    public CheckBox getCheckBox(int viewId) {
        return getView(viewId);
    }
    public RadioButton getRiadioButton(int viewId) {
        return getView(viewId);
    }
    public Button getButton(int viewId) {
        return getView(viewId);
    }


    public ImageView getImageView(int viewId) {
        return getView(viewId);
    }


    public VH setText(int viewId, String value) {
        getTextView(viewId).setText(value == null ? "" : value);
        return VH.this;

    }

    public VH setText(int viewId, int value) {
        getTextView(viewId).setText(value + "");
        return VH.this;
    }

    public VH setText(int viewId, float value) {

        getTextView(viewId).setText(new DecimalFormat(".00").format(value));
        return VH.this;
    }

    public VH setText(int viewId, double value) {
        getTextView(viewId).setText(new DecimalFormat(".00").format(value));
        return VH.this;
    }

    public VH setText(int viewId, long value) {
        getTextView(viewId).setText(value + "");
        return VH.this;
    }

    public VH setText(int viewId, Date value) {
        getTextView(viewId).setText(value == null ? "" : value.toString());
        return VH.this;
    }

    public VH setChecked(int viewId, boolean value) {
        getCheckBox(viewId).setChecked(value);
        return VH.this;
    }

    public VH setRadioChecked(int viewId, boolean value, String text) {
        getRiadioButton(viewId).setChecked(value);
        getRiadioButton(viewId).setText(text);
        return VH.this;
    }




    public VH setRadioChecked(int viewId, boolean value) {
        getRiadioButton(viewId).setChecked(value);
        return VH.this;
    }

    public VH setChecked(int viewId, boolean value, String text) {
        getCheckBox(viewId).setChecked(value);
        getCheckBox(viewId).setText(text);
        return VH.this;
    }

    public VH setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return VH.this;
    }

    public VH setBackgroundResource(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return VH.this;
    }

    public VH setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return VH.this;
    }

    public VH setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return VH.this;
    }

    public VH setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return VH.this;
    }

    public VH setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return VH.this;
    }

    public VH setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return VH.this;
    }


//    public VH setImage(int viewId, File file) {
//
//        if (file != null) {
//            Glide.with(itemView.getContext())
//                    .load(file)
//                    .placeholder(R.drawable.pictures_no)
//                    .error(R.drawable.pictures_no)
//                    .override(300, 300).centerCrop()
//                    .into(getImageView(viewId));
//        }
//        return VH.this;
//
//    }

//    public VH setImage(int viewId, String url) {
//
//
//        if (!TextUtils.isEmpty(url)) {
//
//            .with(itemView.getContext())
//                    .load(url)
//                    .placeholder(R.drawable.pictures_no)
//                    .error(R.drawable.pictures_no)
//                    .override(300, 300)
//                    .centerCrop()
//                    .into(getImageView(viewId));
//
//
//        }
//        return VH.this;
//
//    }
//
//    public VH setImage(int viewId, int drawableId) {
//        Glide.with(itemView.getContext())
//                .load(drawableId)
//                .into(getImageView(viewId));
//        return VH.this;
//    }


}