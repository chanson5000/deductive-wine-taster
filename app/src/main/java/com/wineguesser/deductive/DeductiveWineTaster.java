package com.wineguesser.deductive;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.FirebaseDatabase;

import timber.log.Timber;

public class DeductiveWineTaster extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        MobileAds.initialize(this, getString(R.string.app_add_id));
    }
}
