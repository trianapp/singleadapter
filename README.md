# SingleAdapter
- Satu Adapter yang bisa digunakan berulang-ulang

Cara Pakai

```java
SingeAdapter<PromoModel> promo = new SingeAdapter<>(R.layout.item_food_promo, onClick);
```
```R.layout.item_food_promo``` adalah layout item yang akan di tampilkan di recylerview dan ```onClick``` adalah :

```java
private onEventClick<PromoModel> onClick = new onEventClick<PromoModel>() {
        @Override
        public void onEdit(PromoModel payload, int position) {

        }

        @Override
        public void onDetail(PromoModel payload, int position) {

        }

        @Override
        public void onDelete(PromoModel payload, int position) {

        }
    };
```
untuk item recycler harus membuat kelas/class baru dengan ```java extends Parent  implement SingleAdapterRow<Model> ``` Seperti :

```java
class ItemPromo extends Parent implements SingleAdapterRow<PromoModel> {

}
```

```Parent``` bisa diganti View lainnya misal ```LinearLayout``` atau View lain

xml layout itemnya menjadi:

```xml
 <com.trian.damai.ui.home.ItemPromo
        android:layout_height="wrap_content"
        android:layout_width="wrap_content">

        <androidx.cardview.widget.CardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
         ...
```

kemudian implement ``` binView``` dan ```onFinishInflate ```

```java
    Button btn ;
    TextView tv;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
       //disini inisiasi view
        btn = (Button) findViewById(R.id.button);
        tv = (Button) findViewById(R.id.textview);

    }

    @Override
    public void bindView(PromoModel data, onEventClick<PromoModel> eventClick, int position) {
        // disini setdata ke view
        // contoh:
        tv.setText(data.getTitle());
        btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            eventClick.onDetail(data,position);
                        }
                    });
        }
    }
```
Untuk setdata
``java
    List<PromoModel> data = new ArrayList<>();
    adapter.setData(data);
```
Untuk menambah data
 ```java
    PromoModel data = new PromoModel();
    adapter.addData(data);
 ```

#Extra
Kita bisa menambahkan animasi di adapternya

```java
    adapter.setAnimation(SingeAdapter.SingleAnimation.fade_in);

```
#Cara install

Tambahkan JitPack repository ke build file

```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Tambahkan dependency
```gradle
    dependencies {
	    ...
	    implementation 'com.github.triandamai:singleadapter:v1.0.2'
    }
```

# Next
- databinding support
- more animation


