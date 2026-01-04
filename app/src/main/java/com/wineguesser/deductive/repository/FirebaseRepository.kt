package com.wineguesser.deductive.repository

import com.google.firebase.database.FirebaseDatabase

abstract class FirebaseRepository {
    val databaseInstance: FirebaseDatabase = FirebaseDatabase.getInstance()
}
