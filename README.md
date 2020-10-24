# SingleAdapter
- Satu Adapter yang bisa digunakan berulang-ulang

- Cara Pakai

```java
SingleAdapter<PromoModel> promoAdapter = new SingleAdapter<>(R.layout.item_food_promo, onEventClick).withRecyclerView(rv);
```
```R.layout.item_food_promo``` adalah layout item yang akan di tampilkan di recylerview dan ```onClick``` adalah :

```java
   private onEventClick<PromoModel> onEventClick = new onEventClick<PromoModel>() {
        @Override
        public void onClick(onEventType eventType, String payload, int position) {

        }
    };
```
untuk item recycler harus membuat kelas/class baru dengan ``` extends Parent  implement SingleAdapterRow<Model> ``` Seperti :

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
         btn.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 eventClick.onClick(onEventType.onDetail,data,position);
             }
         });
    }
```
# Untuk men-set data ke adapter

  - Di konstruktor langsung

```java
   List<PromoModel> data = new ArrayList<>();
   SingleAdapter<PromoMode> adapter = new SingleAdapter<>(R.layout.item_food_promo, p,data);
```
   - Atau gunakan method ```setData``` dan ```addData```

   setData
```java
  //data
  List<PromoModel> data = new ArrayList<>();
  PromoModel model = new ArrayList<>();

  //setdata
  adapter.setData(data);
  adapter.setData(model);
```
   addData
 ```java
 //data
    List<PromoModel> data = new ArrayList<>();
    PromoModel model = new PromoModel();
//adddata
    adapter.addData(data);
    adapter.addData(model);
 ```

# Extra
- Kita bisa menambahkan animasi di adapternya(secara default menggunakan fade_in)
- Animasi yang tersedia

```SingleAnimation.fade_in```
```SingleAnimation.fade_out```
```SingleAnimation.slide_in_left```
```SingleAnimation.slide_out_right```
Untuk menentukan animasi gunakan:
```java
  adapter.setAnimation(SingleAdapter.SingleAnimation.fade_in);

```
# Cara install

- Tambahkan JitPack repository ke build file

```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
- Tambahkan dependency
```gradle
    dependencies {
	    ...
	    implementation 'com.github.triandamai:singleadapter:v1.0.4'
    }
```

# Next
- mendukung dataBinding
- menambah animasi
- swipe item
- transisi perubahan setiap item


