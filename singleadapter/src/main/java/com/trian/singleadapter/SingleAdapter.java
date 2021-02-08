package com.trian.singleadapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.trian.singleadapter.AnimationItem.getAnimation;

public class SingleAdapter<T> extends RecyclerView.Adapter<SingleAdapter.ViewHolder> {
    private static String TAG = SingleAdapter.class.getSimpleName() + " -> ";
    /**
     * dataset for recyler item
     *
     * @see ArrayList
     * @see List
     */
    private List<T> mDataset = new ArrayList<>();

    /**
     * untuk menentukan apakah item sudah bisa di kasih animasi
     */
    private int lastPosition = -1;
    private int positionAnim = 0;
    /**
     * event ketika item dari recycler di klik
     *
     * @see onEventClick
     **/
    private onEventClick<T> onItemClick;

    /**
     * @+id dari layout item yang akan ditambahkan
     * <p>
     * cont: R.layout.item
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
     *
     * @param layoutId ketika diklik dan layout
     * @param event    ketika diklik dan dataset
     **/
    public SingleAdapter(int layoutId, @NonNull onEventClick<T> event) {
        if (event == null) {
            throw new NullPointerException(TAG + " onClick null!");
        }
        this.layoutId = layoutId;
        this.onItemClick = event;
    }


    /**
     * konstruktor untuk inisiasi adapter ada 2 tipe :
     *
     * @param layoutId ketika diklik dan layout
     * @param event    ketika diklik dan dataset
     * @param mDataset List data
     * @see List
     * @see ArrayList
     **/


    public SingleAdapter(int layoutId, @NonNull onEventClick<T> event, List<T> mDataset) {
        if (event == null) {
            throw new NullPointerException(TAG + " onClick null!");
        }
        if (mDataset == null) {
            throw new NullPointerException(TAG + " data set null!");
        }
        this.layoutId = layoutId;
        this.onItemClick = event;
        this.setData(mDataset);
    }


    /**
     * set dataset pada adapter
     *
     * @param mDataset data untuk mengisi collections
     * @see List
     * @see ArrayList
     */
    public void setData(List<T> mDataset) {

        if (this.mDataset == null) {
            this.mDataset = new ArrayList<>();
        }

        this.mDataset.clear();
        this.mDataset.addAll(mDataset);
        notifyDataSetChanged();


    }


    /**
     * set dataset pada adapter apakah data sudah ada
     *
     * @param mDataset data untuk mengisi collections
     * @see List
     * @see ArrayList
     */
    public void setDataWithDiff(List<T> mDataset) {

        if (this.mDataset == null) {
            this.mDataset = new ArrayList<>();
        }

        this.diffCheck(mDataset);


    }


    /**
     * set object to dataset pada adapter
     *
     * @param mDataset data untuk menambah collections
     * @see List
     * @see ArrayList
     */
    public void setData(T mDataset) {
        if (this.mDataset == null) {
            this.mDataset = new ArrayList<>();
        }

        this.mDataset.clear();
        this.mDataset.add(mDataset);
        notifyDataSetChanged();
    }

    /**
     * set object to dataset pada adapter dengan check apakah data sudah ada
     *
     * @param mDataset data untuk menambah collections
     * @see List
     * @see ArrayList
     */
    public void setDataWithDiff(T mDataset) {
        if (this.mDataset == null) {
            this.mDataset = new ArrayList<>();
        }
        List<T> data = new ArrayList<>();
        data.add(mDataset);
        diffCheck(data);
    }

    /**
     * set object to dataset pada adapter
     *
     * @param mDataset data untuk menambah collections
     * @see List
     * @see ArrayList
     */
    public void addData(T mDataset) {
        if (this.mDataset == null) {
            this.mDataset = new ArrayList<>();
        }
        this.mDataset.add(mDataset);

        notifyDataSetChanged();
    }

    /**
     * set collection to dataset pada adapter
     *
     * @param mDataset data untuk mengisi collections
     * @see List
     * @see ArrayList
     */
    public void addData(List<T> mDataset) {
        if (this.mDataset == null) {
            this.mDataset = new ArrayList<>();
        }

        this.mDataset.addAll(mDataset);


        notifyDataSetChanged();
    }

    /***
     * cek apakah diff util ada
     * */
    private void diffCheck(@NonNull List<T> data) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SingleDiffCallback<>(this.mDataset, data));
        diffResult.dispatchUpdatesTo(SingleAdapter.this);
    }

    /**
     * membangun animasi
     * secara default animasi menjadi fade_in
     */
    public void setAnimation(SingleAnimation animation) {
        if (animation == null) {
            throw new NullPointerException(TAG + " animation null!");
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
                startAnimation(holder.itemView, position);
            }, 800);
            lastPosition = position;
        }
    }

    private void startAnimation(View itemview, final int pos) {
        positionAnim = pos;
        Animation animation = getAnimation(animate, context);
        animation.setInterpolator(context, android.R.interpolator.bounce);

        itemview.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                positionAnim++;
                if (positionAnim < mDataset.size()) {
                    if (itemview != null) {
                        startAnimation(itemview, positionAnim);
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
