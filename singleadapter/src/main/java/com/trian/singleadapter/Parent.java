package com.trian.singleadapter;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/*
 *
 *  TODO :: EXPERIMENTAL
 *  untuk diextends file item masih dalam percobaan
 *
 * */
public class Parent extends LinearLayout{
    private Button btn;

    public Parent(Context context) {
        super(context);
    }

    public Parent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Parent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Parent(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
