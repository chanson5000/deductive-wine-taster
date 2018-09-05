package com.wineguesser.deductive.repository;

import com.google.firebase.database.FirebaseDatabase;

abstract class FirebaseRepository implements DatabaseContract {

   final FirebaseDatabase mDatabase;

   FirebaseRepository() {
       mDatabase = FirebaseDatabase.getInstance();
   }


}
