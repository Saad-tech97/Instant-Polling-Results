package com.example.polling;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class PollDataRetrive extends ArrayAdapter<Poll> {

    private Activity context;
    private List<Poll> listOfPolls;
    private TextView pollTimer;
    private DatabaseReference databaseReference;

    public PollDataRetrive(Activity context, List<Poll> listOfPolls){

        super(context, R.layout.list_view, listOfPolls);
        this.context = context;
        this.listOfPolls = listOfPolls;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        final View listitem = inflater.inflate(R.layout.list_view, null, true);

        databaseReference = FirebaseDatabase.getInstance().getReference("Answer");
        pollTimer = listitem.findViewById(R.id.timer);
        TextView pollType= listitem.findViewById(R.id.typeText);
        TextView pollQuestion = listitem.findViewById(R.id.questionText);

        Poll poll = listOfPolls.get(position);

        pollType.setText(poll.getType());
        pollQuestion.setText(poll.getQuestion());

//        CountDown();
        return listitem;
    }
    public void CountDown(){
        new CountDownTimer(30100, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                pollTimer.setText("00 : " + String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                pollTimer.setText("00 : 00");
                Toast.makeText(getContext(),"Poll session expired, make a new poll!", Toast.LENGTH_LONG).show();
            }
        }.start();
    }

}
