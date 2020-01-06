package xui;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 2015/7/10.
 * RecyclerView通用数据绑定
 *
 */
public       class LiAdapter<T> extends RecyclerView.Adapter<VH>{


    protected final int layoutResId;

    public void setData(List<T> data) {
        this.data = data == null ? new ArrayList<T>() : data;
    }

    public List<T> getData() {

        return data;
    }

    protected List<T> data;


 
    private OnItemClickListener onItemClickListener = null;

    public T lastItem() {
        return  data.get(data.size()-1);
    }

    public interface OnItemClickListener {
        void onItemClick(  int position);
    }


    Binder<T> binder;

    public LiAdapter(int layoutResId, Binder<T> binder) {
        this. layoutResId= layoutResId;
        this.binder=binder;
    }





    public void add(T t) {
        insert(t, data.size());
    }

    public void insert(T t , int position) {
        data.add(position, t);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        int size = data.size();
        data.clear();
        notifyItemRangeRemoved(0, size);
    }


    public void addAll(List<T> list) {
        if (list.isEmpty()){
            return;
        }
        int startIndex = data.size();
        data.addAll(startIndex, list);
        notifyItemRangeInserted(startIndex, list.size());
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
    public VH onCreateViewHolder(ViewGroup viewGroup,  int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {

        if (onItemClickListener != null) {
            holder.itemView.setClickable(true);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick( position);
                }
            });

        }



       binder. onbind(holder,  getItem(position), position);



    }




    public interface Binder<T>{

         void onbind (VH holder, T item ,int position);
    }



    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

}