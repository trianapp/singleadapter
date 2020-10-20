package com.trian.singleadapter;

/**
 * @interface untuk mem- bind data ke view
 * */
public interface SingleAdapterRow<T> {
    /**
    * adapter onBindViewHolder akan otomatis mempassing ke binView dan @bindView yang akan memasang ke View nya
    *
    * */
    void bindView(T data, onEventClick<T> eventClick, int position);
}
