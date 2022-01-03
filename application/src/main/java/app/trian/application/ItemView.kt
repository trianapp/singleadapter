package app.trian.application

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import app.trian.singleadapter.SingleAdapterItem


class ItemView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs), SingleAdapterItem<ItemModel>{
    private lateinit var text:TextView
    private lateinit var parent:LinearLayout
    override fun onFinishInflate() {
        super.onFinishInflate()
        text = findViewById(R.id.tv)
        parent = findViewById(R.id.ly_parent)

    }
    override fun bindView(
        data: ItemModel,
        position: Int,
        onClick: (data: ItemModel,position:Int, arg: Any) -> Unit
    ) {
        text.text = data.name
        parent.setOnClickListener {
            onClick(data,position,1)
        }

    }
}