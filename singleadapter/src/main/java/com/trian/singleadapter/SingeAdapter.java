package com.trian.singleadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SingeAdapter<T> extends RecyclerView.Adapter<SingeAdapter.ViewHolder> {

    /**
     * dataset for recyler item
     */
    private List<T> mDataset = new ArrayList<>();

    /**
     * untuk menentukan apakah item sudah bisa di kasih animasi
     */
    private int lastPosition = -1;
    /*
     * event ketika item dari recycler di klik
     *
     * @method{onEdit,onDetail,onDelete}
     *
     * */
    private onEventClick<T> onItemClick;
    /*
     * id layout item dari recyclerview
     *
     * cont: R.layout.item
     *
     * */
    private int layoutId;
    /*
     * mendapatkan dimana recyclerview ini berada
     * */
    private Context context;
    /*
     * tipe animasi dari item
     * */
    private SingleAnimation animte;

    public enum SingleAnimation {
        fade_in,
        fade_out,
        slide_left,
        slide_right,
    }

    /*
     * untuk animasi item dari recyclerview
     * */
    private Animation animation;


    /*
     * konstruktor untuk inisiasi adapter ada 2 tipe :
     * @ parameter ketika diklik dan layout
     * @ parameter ketika diklik dan dataset
     * */
    public SingeAdapter(@IntegerRes int layoutId, @NonNull onEventClick<T> event) {
        this.layoutId = layoutId;
        this.onItemClick = event;
    }

    public SingeAdapter(@IntegerRes int layoutId, @NonNull onEventClick<T> event, List<T> mDataset) {
        this.layoutId = layoutId;
        this.onItemClick = event;
        this.setData(mDataset);
    }

    /*
     * set dataset pada adapter
     * @ setData untuk mengisi collections
     * @ addData untuk menambah collections
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

    /*
     *
     * membangun animasi /set animasi kepada item
     *
     *  secara default animasi menjadi fade_in
     *
     * */
    public void setAnimation(SingleAnimation animation) {
        if (animation == null) {
            throw new NullPointerException("Tolong ya kalo manggil method parameternya diisi jangan null :(");
        } else {
            this.animte = animation;
        }
    }

    @NonNull
    @Override
    public SingeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        SingleAdapterRow<T> row = (SingleAdapterRow<T>) LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(row);

    }

    @Override
    public void onBindViewHolder(@NonNull SingeAdapter.ViewHolder holder, int position) {
        holder.mrow.bindView(mDataset.get(position), this.onItemClick, position);
        setAnimation(holder.itemView, position);
    }

    private void setAnimation(View itemView, int position) {
        if (position > lastPosition) {
            if (animte != null) {
                switch (animte) {
                    case fade_in:
                        animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
                        break;
                    case fade_out:
                        animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
                        break;
                    case slide_left:
                        animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
                        break;
                    case slide_right:
                        animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
                        break;
                    default:
                        animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
                }

            } else {
                animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            }
            itemView.setAnimation(animation);
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
