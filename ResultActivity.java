package com.example.polling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private DatabaseReference databaseReference2;
    private ListView ListViewResults;
    private List<Answer> answerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ListViewResults = findViewById(R.id.ListViewOfResults);
        answerList = new ArrayList<>();

        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Answer");
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                answerList.clear();

                for (DataSnapshot answerSnapshot : dataSnapshot.getChildren()){

                    Answer newAnswer = answerSnapshot.getValue(Answer.class);
                    answerList.add(newAnswer);

                }

                AnswerDataToRetrive retriveAnswer = new AnswerDataToRetrive(ResultActivity.this, answerList);
                ListViewResults.setAdapter(retriveAnswer);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
