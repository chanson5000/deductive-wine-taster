package com.wineguesser.deductive.repository;

import com.google.firebase.database.FirebaseDatabase;

abstract class FirebaseRepository {

   final FirebaseDatabase mDatabase;

   FirebaseRepository() {
       mDatabase = FirebaseDatabase.getInstance();
   }

   FirebaseDatabase getDatabaseInstance() {
       return mDatabase;
   }
}
