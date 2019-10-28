package com.example.polling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewResult extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private ListView ListViewOfResults;
    private List<Answer> answerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);

        ListViewOfResults = findViewById(R.id.ListViewOfResults);
        answerList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                answerList.clear();

                for (DataSnapshot answerSnapshot : dataSnapshot.getChildren()){

                    Answer answerResult = answerSnapshot.getValue(Answer.class);

                    answerList.add(answerResult);

                }

                AnswerDataToRetrive answerDataToRetrive = new AnswerDataToRetrive(ViewResult.this, answerList);
                ListViewOfResults.setAdapter(answerDataToRetrive);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
