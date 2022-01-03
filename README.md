# SingleAdapter
- Reusable Recyclerview Adapter


### install
`build.gradle` root project
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
`build.gradle` application module
```gradle
    dependencies {
	    ...
	    implementation 'com.github.trianapp:singleadapter:v1.0.21'
    }
```

### How to use
`MainActivity.kt`
```kotlin
class MainActivity : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv)

       val adapter = SingleAdapter<ItemModel>(R.layout.item_view){
               item,position, args ->
           //on item clicked
           Toast.makeText(this,item.name,LENGTH_LONG).show()
       }

        //
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = adapter

        adapter.setData(listOf(
            ItemModel(name = "item 1", code = 1),
            ItemModel(name = "item 2", code = 2),
            ItemModel(name = "item 3", code = 3),
            ItemModel(name = "item 4", code = 4),
            ItemModel(name = "item 5", code = 5),
        ))
        

    }
}

```
`item_view.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<app.trian.application.ItemView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"

    >
   <LinearLayout
       android:id="@+id/ly_parent"
       android:layout_marginTop="16dp"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:orientation="vertical">
       <TextView
           android:id="@+id/tv"
           android:layout_width="match_parent"
           android:text="hai"
           android:layout_height="wrap_content"/>
   </LinearLayout>
</app.trian.application.ItemView>
```
`ItemView.kt`
```kotlin
class ItemView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs), SingleAdapterItem<ItemModel>{
    private lateinit var text:TextView
    private lateinit var parent:LinearLayout
    override fun onFinishInflate() {
        super.onFinishInflate()
        text = findViewById(R.id.tv)
        parent = findViewById(R.id.ly_parent)

    }
    override fun bindView(data: ItemModel, position: Int, onClick: (data: ItemModel,position:Int, arg: Any) -> Unit) {
        text.text = data.name
        parent.setOnClickListener {
            onClick(data,position,1)
        }

    }
}
```
`ItemModel.kt`
```kotlin

data class ItemModel(
    var name:String,
    var code:Int
)

```


