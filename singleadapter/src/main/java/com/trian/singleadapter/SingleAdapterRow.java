package com.trian.singleadapter;

public interface SingleAdapterRow<T> {
    void bindView(T data, onEventClick<T> eventClick, int position);
}
