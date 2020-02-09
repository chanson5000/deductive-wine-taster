//package com.wineguesser.deductive.repository;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//import com.wineguesser.deductive.model.wine.red.RedWineVarietal;
//import com.wineguesser.deductive.model.wine.white.WhiteWineVarietal;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
//import timber.log.Timber;
//
//@Singleton
//public class VarietalRepositoryFFSImpl implements VarietalRepository {
//
//    private final FirebaseFirestore database;
//
//    public VarietalRepositoryFFSImpl(FirebaseFirestore database) {
//        this.database = database;
//    }
//
//    public List<RedWineVarietal> getAllRedWines() {
//        List<RedWineVarietal> redWineVarietals = new ArrayList<>();
//
//        database.collection("redWineVarietals").get().addOnCompleteListener(
//                task -> {
//                    if (task.isSuccessful() && task.getResult() != null) {
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            Timber.d("Found %s", document);
//                        }
//                    } else {
//                        Timber.e("Error getting all red wines.");
//                    }
//                }
//        );
//
//        return redWineVarietals;
//    }
//
//    public List<WhiteWineVarietal> getAllWhiteWines() {
//
//        return Collections.singletonList(new WhiteWineVarietal());
//    }
//}
