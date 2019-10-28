package com.example.polling;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AnswerDataToRetrive extends ArrayAdapter<Answer> {

    private Activity context;
    private List<Answer> answersList;
    private TextView questionId, typeOfPoll, selectedAnswer, ratingOfPoll;

    public AnswerDataToRetrive(Activity context, List<Answer> answersList){

        super(context, R.layout.list_view_poll_result, answersList);
        this.context = context;
        this.answersList = answersList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View listitem2 = inflater.inflate(R.layout.list_view_poll_result, null, true);

        questionId = listitem2.findViewById(R.id.questionid);
        typeOfPoll = listitem2.findViewById(R.id.typeofpoll);
        selectedAnswer = listitem2.findViewById(R.id.selectedAnswer);
        ratingOfPoll = listitem2.findViewById(R.id.ratingOfPoll);

        Answer answerView = answersList.get(position);

        questionId.setText(answerView.getQuestionPoll());
        typeOfPoll.setText(answerView.getTypePoll());
        selectedAnswer.setText(answerView.getOption());
        ratingOfPoll.setText(answerView.getRating());

        return listitem2;
    }
}
