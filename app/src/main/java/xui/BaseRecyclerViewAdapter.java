package xui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 2015/7/10.
 * RecyclerView通用数据绑定
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected final int layoutResId;

   public List<T> data=new ArrayList<>();


    public T lastItem() {
        return data.get(data.size() - 1);
    }


    public BaseRecyclerViewAdapter(int layoutResId) {
        this.layoutResId = layoutResId;
    }





    public void add(T t) {
        insert(t, data.size());
    }

    public void insert(T t, int position) {
        data.add(position, t);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, data.size());
    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());

    }

    public void clear() {
        int size = data.size();
        data.clear();
        notifyItemRangeRemoved(0, size);
    }


    public void addAll(List<T> list) {
        if (list.isEmpty()) {
            return;
        }
        int startIndex = data.size();
        data.addAll(startIndex, list);
        notifyItemRangeChanged(startIndex, data.size());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public T getItem(int position) {
        if (position >= data.size()) return null;
        return data.get(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);
        BaseViewHolder vh = new BaseViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {

        if (onItemClickListener != null) {
            holder.itemView.setClickable(true);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick( position);
                }
            });

        }


        onBind(holder, getItem(position), position);


    }


    /**
     * @param holder
     * @param item
     * @param position
     */
    public abstract void onBind(BaseViewHolder holder, T item, int position);

    private OnItemClickListener onItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick( int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

}