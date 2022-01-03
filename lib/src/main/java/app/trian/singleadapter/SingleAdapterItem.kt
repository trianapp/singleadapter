package app.trian.singleadapter

/**
* Item
* Created at 03/01/22 17.27
* Created by Trian Damai
*/

interface SingleAdapterItem<Model> {
    /**
     * bind data into view.
     *
     * <p>when adapter call <code>onCreateViewHolder</code> it will call this method.</p>
     */
    fun bindView(data:Model,position:Int,onClick:(data:Model,position:Int,arg:Any)->Unit)
}