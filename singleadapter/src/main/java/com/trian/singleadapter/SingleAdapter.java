package com.trian.singleadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.trian.singleadapter.AnimationItem.getAnimation;

public class SingleAdapter<T> extends RecyclerView.Adapter<SingleAdapter.ViewHolder> {
    private static String TAG = SingleAdapter.class.getSimpleName()+" -> ";
    /**
     *  dataset for recyler item
     * */
    private List<T> mDataset = new ArrayList<>();

    /**
     * untuk menentukan apakah item sudah bisa di kasih animasi
     */
    private int lastPosition = -1;
    /**
    * event ketika item dari recycler di klik
    *
    * @onEdit
    * @onDetail
    * @onDelete
    *
    **/
    private onEventClick<T> onItemClick;

    /**
    * @+id dari layout item yang akan ditambahkan
    *
    * cont: R.layout.item
    *
    **/
    private int layoutId;

    /**
    * mendapatkan dimana recyclerview ini berada
    **/
    private Context context;

    /**
    * tipe animasi dari item
    **/
    private SingleAnimation animte;

    /**
    * recylerview for animation
    **/
    private RecyclerView mRecyclerView;

    /**
    * untuk animasi item dari recyclerview
    **/
    private Animation animation;


    /**
    * konstruktor untuk inisiasi adapter ada 2 tipe :
    * @parameter ketika diklik dan layout
    * @parameter ketika diklik dan dataset
    **/
    public SingleAdapter( int layoutId, @NonNull onEventClick<T> event) {
        if(event == null){
            throw new NullPointerException(TAG+" onClick null!");
        }
        this.layoutId = layoutId;
        this.onItemClick = event;
    }

    public SingleAdapter(int layoutId, @NonNull onEventClick<T> event, List<T> mDataset) {
        if(event == null){
            throw new NullPointerException(TAG+" onClick null!");
        }
        if(mDataset == null){
            throw new NullPointerException(TAG+" data set null!");
        }
        this.layoutId = layoutId;
        this.onItemClick = event;
        this.setData(mDataset);
    }
    /*
    * set rv
    *
    * */
    public SingleAdapter withRecyclerView(RecyclerView rv){
        if(rv == null){
            throw new NullPointerException("Recycler view onNull");
        }
        this.mRecyclerView = rv;
        this.mRecyclerView.setAdapter(this);
        return this;
    }
    /**
    * set dataset pada adapter
    * @setData untuk mengisi collections
    * @addData untuk menambah collections
    * */
    public void setData(List<T> mDataset) {
        if (this.mDataset == null) {
            this.mDataset = new ArrayList<>();
        }
        if (mDataset != null) {
            this.mDataset.addAll(mDataset);
        }
        notifyDataSetChanged();

    }

    public void addData(T mDataset) {
        if (this.mDataset == null) {
            this.mDataset = new ArrayList<>();
        } else {
            this.mDataset.add(mDataset);
        }
        notifyDataSetChanged();
    }
    /**
    *
    * membangun animasi
    *  secara default animasi menjadi fade_in
    *
    * */
    public void setAnimation(SingleAnimation animation) {
        if (animation == null) {
            throw new NullPointerException(TAG+" animation null!");
        } else {
            this.animte = animation;
        }
    }

    @NonNull
    @Override
    public SingleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        SingleAdapterRow<T> row = (SingleAdapterRow<T>) LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(row);

    }

    @Override
    public void onBindViewHolder(@NonNull SingleAdapter.ViewHolder holder, int position) {
        holder.mrow.bindView(mDataset.get(position), this.onItemClick, position);
        if (position > lastPosition) {
           holder.itemView.setAnimation(getAnimation(animte,context));
            lastPosition = position;
        }
    }



    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public SingleAdapterRow mrow;

        public ViewHolder(@NonNull SingleAdapterRow itemView) {
            super((View) itemView);
            mrow = itemView;
        }


    }
}
