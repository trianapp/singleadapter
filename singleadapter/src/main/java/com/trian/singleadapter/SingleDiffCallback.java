package com.trian.singleadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;
import java.util.Objects;

public class SingleDiffCallback<Model> extends DiffUtil.Callback {
    private List<Model> oldModels;
    private List<Model> newModels;


    public SingleDiffCallback(@NonNull List<Model> oldModels, @NonNull List<Model> newModels) {
        this.oldModels = oldModels;
        this.newModels = newModels;
    }

    @Override
    public int getOldListSize() {
        return oldModels == null ? 0 : oldModels.size();
    }

    @Override
    public int getNewListSize() {
        return newModels == null ? 0 : newModels.size();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldModels.get(oldItemPosition).equals(newModels.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.deepEquals(oldModels.get(oldItemPosition), newModels.get(newItemPosition));
    }

}
