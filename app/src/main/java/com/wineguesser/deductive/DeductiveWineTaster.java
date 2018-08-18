package com.wineguesser.deductive;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class DeductiveWineTaster extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
