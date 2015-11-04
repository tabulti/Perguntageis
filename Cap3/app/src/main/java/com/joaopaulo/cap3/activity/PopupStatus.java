package com.joaopaulo.cap3.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.joaopaulo.cap3.R;

/**
 * Created by Gustavo on 03/11/2015.
 */
public class PopupStatus extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_status);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (height*.4));

    }
}
