package app.trian.singleadapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.recyclerview.widget.RecyclerView


/*
* Single adapter
* Created at 03/01/22 12.55
* Created by Trian Damai
*/

class SingleAdapter<Model>(
    var dataSet:List<Model> = mutableListOf(),
    private var layoutId:Int,
    var onClick:(item:Model,position:Int,args:Any)->Unit
):RecyclerView.Adapter<SingleAdapter.ViewHolder<Model>>() {

    lateinit var context:Context
    /**
     * assign new data and notify data changed.
     *
     * <p>if you want to add data without losing existing data use @see SingleAdapter.addData</p>.
     *
     * @see List
     */
    @SuppressLint("NotifyDataSetChanged")
    fun setData(data:List<Model>){
        dataSet = emptyList()
        dataSet = data
        notifyDataSetChanged()
    }

    /**
     * assign new data and notify data changed.
     *
     * <p>if you want to replace data exist with new data use @see SingleAdapter.setData.</p>
     *
     * @see List
     */
    @SuppressLint("NotifyDataSetChanged")
    fun addData(data: List<Model>){
        dataSet = dataSet + data
        notifyDataSetChanged()
    }


    class ViewHolder<T>(itemView: SingleAdapterItem<T>) : RecyclerView.ViewHolder(itemView as (View))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<Model> {
        context = parent.context
        val item =  LayoutInflater.from(parent.context).inflate(layoutId,parent,false) as SingleAdapterItem<Model>
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder<Model>, position: Int) {
        (holder.itemView as SingleAdapterItem<Model>).apply {
            bindView(dataSet[position],position, onClick)
        }

    }

    override fun getItemCount(): Int = dataSet.size


}