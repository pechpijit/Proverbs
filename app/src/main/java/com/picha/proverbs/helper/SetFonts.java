package com.picha.proverbs.helper;

import android.app.Application;

import com.picha.proverbs.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class SetFonts extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Mitr.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
