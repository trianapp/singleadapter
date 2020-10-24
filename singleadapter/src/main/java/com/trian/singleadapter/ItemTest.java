package com.trian.singleadapter;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class ItemTest extends Parent implements SingleAdapterRow<String>{
   Button btn;
    public ItemTest(Context context) {
        super(context);
    }

    public ItemTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ItemTest(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void bindView(String data, onEventClick<String> eventClick, int position) {
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                eventClick.onClick(onEventType.onDetail,data,position);
            }
        });
    }
}
