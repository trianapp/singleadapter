package com.trian.singleadapter;

public interface onEventClick<T> {
    void onEdit(T payload, int position);

    void onDetail(T payload, int position);

    void onDelete(T payload, int position);
}
