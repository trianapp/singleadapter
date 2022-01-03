package app.trian.singleadapter

interface SingleAdapterItem<Model> {
    fun bindView(data:Model,position:Int,onClick:(data:Model,position:Int,arg:Any)->Unit)
}