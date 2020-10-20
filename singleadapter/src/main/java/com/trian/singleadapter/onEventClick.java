package com.trian.singleadapter;

/*
 * semua method disini semua sama menampung ketika item di klik
 *  tapi kadang ada item yang punya 3 tombol = 3 aksi seperti hapus,ubah,lihat detail
 * */
public interface onEventClick<T> {
    /**
    * ketika item di klik
    *
    * */
    void onClick(onEventType eventType,T payload, int position);
}

