package com.wineguesser.deductive;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.FirebaseDatabase;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

@SuppressWarnings("WeakerAccess")
public class DeductiveWineTaster extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        MobileAds.initialize(this, BuildConfig.AdMobAppIdKey);
    }
}
