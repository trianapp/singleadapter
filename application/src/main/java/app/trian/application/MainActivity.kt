package app.trian.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.trian.singleadapter.AnimatedItem
import app.trian.singleadapter.SingleAdapter
import app.trian.singleadapter.SingleAdapterAnimation


class MainActivity : AppCompatActivity() {
    lateinit var rv:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById(R.id.rv)

       val adapter = SingleAdapter<ItemModel>(
           layoutId = R.layout.item_view){
               item,position, args ->
           Toast.makeText(this,item.name,LENGTH_LONG).show()

       }

        rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv.adapter = adapter

        adapter.setData(listOf(
            ItemModel(name = "item 1", code = 1),
            ItemModel(name = "item 2", code = 2),
            ItemModel(name = "item 3", code = 3),
            ItemModel(name = "item 4", code = 4),
            ItemModel(name = "item 5", code = 5),
        ))


    }
}