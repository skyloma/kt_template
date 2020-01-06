package android.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class Li extends RecyclerView {
    private View emptyView;

    public Li(Context context) {
        super(context);

    }

    public Li(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Li(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {

        @Override
        public void onChanged() {
            super.onChanged();
            checkIfEmpty();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            checkIfEmpty();

        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            checkIfEmpty();
        }
    };

    private void checkIfEmpty() {
        if (emptyView != null) {
            if (getAdapter() != null) {
                if (getParent() instanceof ViewGroup) {
                    if (getAdapter().getItemCount() == 0) {
                        ((ViewGroup) getParent()).addView(emptyView);
                    } else {
                        ((ViewGroup) getParent()).removeView(emptyView);
                    }
                }


            }
        }


    }

    @Override
    public void setAdapter(Adapter adapter) {

        Adapter oldAdapter = getAdapter();
        if (oldAdapter != null)
            oldAdapter.unregisterAdapterDataObserver(observer);
        super.setAdapter(adapter);
        adapter.registerAdapterDataObserver(observer);

        checkIfEmpty();

    }


    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;

    }
}
