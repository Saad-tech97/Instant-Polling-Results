package com.example.polling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AnswerSubmit extends AppCompatActivity {

    TextView PollAnswer1, pollQuestion, pollType;
    ListView listViewAnswer;
    RadioGroup radioGroup;
    RadioButton radio1, radio2,radio3, radio4;
    DatabaseReference databaseAnswer;
    RatingBar ratingBar;
    String progress;
    List<Answer> answers;
    String Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_submit);

        PollAnswer1 = findViewById(R.id.PollAnswer);
        pollQuestion = findViewById(R.id.pollquestion);
        pollType = findViewById(R.id.pollType);
        listViewAnswer = findViewById(R.id.lisViewAnswer);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);
        radioGroup = findViewById(R.id.radioGroup);
        ratingBar = findViewById(R.id.rating);

        answers = new ArrayList<>();

        Intent intent = getIntent();

        String id = intent.getStringExtra(MainActivity.POLL_ID);
        String name = intent.getStringExtra(MainActivity.POLL_NAME);
        String polltpe = intent.getStringExtra(MainActivity.POLL_TYPE);
        String pollQues = intent.getStringExtra(MainActivity.POLL_QUESTION);
        String opt1 = intent.getStringExtra(MainActivity.POLL_OPTION1);
        String opt2 = intent.getStringExtra(MainActivity.POLL_OPTION2);
        String opt3 = intent.getStringExtra(MainActivity.POLL_OPTION3);
        String opt4 = intent.getStringExtra(MainActivity.POLL_OPTION4);

        databaseAnswer = FirebaseDatabase.getInstance().getReference("Answer").child(id);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                progress = String.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(), progress, Toast.LENGTH_LONG).show();
            }
        });

        PollAnswer1.setText(name);
        pollQuestion.setText("Question : " + pollQues);
        pollType.setText("Type : " + polltpe);
        radio1.setText(opt1);
        radio2.setText(opt2);
        radio3.setText(opt3);
        radio4.setText(opt4);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = findViewById(checkedId);
                Text = checkedRadioButton.getText().toString();
                Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void SubmitAnswer(View view) {

        saveAnswer();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseAnswer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                answers.clear();

                for (DataSnapshot answerSnapshot : dataSnapshot.getChildren()){

                    Answer answer = answerSnapshot.getValue(Answer.class);
                    answers.add(answer);

                }

                AnswerDataToRetrive answerDataToRetrive = new AnswerDataToRetrive(AnswerSubmit.this, answers);
                listViewAnswer.setAdapter(answerDataToRetrive);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void saveAnswer(){

        String Type = pollType.getText().toString();
        String Question = pollQuestion.getText().toString();
        String AnswerOption = "Answer : " + Text.trim();
        String Rating = "Rating : " + progress.toString().trim();

            String pollId = databaseAnswer.push().getKey();

            Answer answer = new Answer(pollId, Question, Type, AnswerOption, Rating);

            databaseAnswer.child(pollId).setValue(answer);

            Toast.makeText(getApplicationContext(), "Answer Submited!", Toast.LENGTH_LONG).show();

    }

}
