package com.trian.singleadapter;

import android.content.Context;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

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
    private int positionAnim = 0;
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
    private SingleAnimation animate;

    /**
    * recylerview for animation
    **/
    private RecyclerView mRecyclerView;


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
    public SingleAdapter<T> withRecyclerView(RecyclerView rv){
        if(rv == null){
            throw new NullPointerException("Recycler view onNull");
        }
        this.mRecyclerView = rv;
        this.mRecyclerView.setAdapter(this);
        return (SingleAdapter<T>) this;
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

            this.mDataset.clear();
            this.mDataset.addAll(mDataset);

        notifyDataSetChanged();

    }
    public void setData(T mDataset) {
        if (this.mDataset == null) {
            this.mDataset = new ArrayList<>();
        }

            this.mDataset.clear();
            this.mDataset.add(mDataset);

        notifyDataSetChanged();
    }
    public void addData(T mDataset) {
        if (this.mDataset == null) {
            this.mDataset = new ArrayList<>();
        }
        this.mDataset.add(mDataset);

        notifyDataSetChanged();
    }

    public void addData(List<T> mDataset){
        if (this.mDataset == null) {
            this.mDataset = new ArrayList<>();
        }
        this.mDataset.addAll(mDataset);

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
            this.animate = animation;
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
            Handler handler = new Handler(Looper.myLooper());
            handler.postDelayed(() -> {
                startAnimation(holder.itemView,position);
            },800);
            lastPosition = position;
        }
    }

    private void startAnimation(View itemview,final int pos) {
        positionAnim = pos;
        Animation animation =getAnimation(animate,context);
        animation.setInterpolator(context, android.R.interpolator.bounce);

        itemview.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                positionAnim++;
                if(positionAnim < mDataset.size()){
                    if(itemview != null){
                        startAnimation(itemview,positionAnim);
                    }
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
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
