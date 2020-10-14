package com.trian.singleadapter;

/*
 * semua method disini semua sama menampung ketika item di klik
 *  tapi kadang ada item yang punya 3 tombol = 3 aksi seperti hapus,ubah,lihat detail
 * */
public interface onEventClick<T> {
    /*
     * ketika item ingin di ubah
     *
     * */
    void onEdit(T payload, int position);
    /*
     * ketika melihat detail
     *
     * */

    void onDetail(T payload, int position);
    /*
     * ketika item ingin di hapus
     *
     * */

    void onDelete(T payload, int position);
}
