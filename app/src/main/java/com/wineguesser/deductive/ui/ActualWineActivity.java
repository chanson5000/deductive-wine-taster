package com.wineguesser.deductive.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wineguesser.deductive.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActualWineActivity extends AppCompatActivity{

    private static final String WINNING_WINE_ID = "WINNING_WINE_ID";

    private Context mContext;

    @BindView(R.id.textView_our_guess)
    TextView mTextViewWineGuess;

    String mWinningWineId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_wine);
        mContext = this;
        ButterKnife.bind(this);
        Intent parentIntent = getIntent();

        if (parentIntent != null && parentIntent.hasExtra(WINNING_WINE_ID)) {
            Bundle bundle = parentIntent.getExtras();
            if (bundle != null && bundle.getString(WINNING_WINE_ID) != null) {
                mWinningWineId = bundle.getString(WINNING_WINE_ID);

                ValueEventListener listener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Object dataObject = dataSnapshot.child(mWinningWineId).child("variety").getValue();
                        if (dataObject != null) {
                            mTextViewWineGuess.setText(dataObject.toString());
                        } else {
                            Toast.makeText(mContext, "Unable to retrieve varietal name.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(mContext, "Unable to retrieve varietal name.", Toast.LENGTH_SHORT).show();
                    }
                };

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference("/redVarietalInfo");
                databaseReference.addListenerForSingleValueEvent(listener);
            }
        }
    }
}
