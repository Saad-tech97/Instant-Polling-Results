package com.example.polling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateNewPoll extends AppCompatActivity {

    private EditText editText1, editText2, editText3, editText4, editText5, editText6;
    private EditText name, email;
    private ImageView image1, image2, image3, image4, image5, image6, image7;
    private DatabaseReference databaseReference;
    private TextView timer;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_poll);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        seekBar = findViewById(R.id.seekbar);
        timer = findViewById(R.id.timer);

        databaseReference = FirebaseDatabase.getInstance().getReference("Poll");

        seekBar.setMax(60000);
        seekBar.setProgress(0);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void updateTimer(int secondsLeft){

        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (secondString == "0"){

            secondString = "00";

        }

        timer.setText(Integer.toString(minutes) + " : " + Integer.toString(seconds));

    }

    public void CreatePoll(View view) {

        String Question = editText1.getText().toString().trim();
        String Type = editText2.getText().toString().trim();
        String Name = name.getText().toString().trim();
        String Email = email.getText().toString().trim();

        if (TextUtils.isEmpty(Name)){

            Toast.makeText(this, "Field Empty!", Toast.LENGTH_LONG).show();

        }

        if (TextUtils.isEmpty(Email)){

            Toast.makeText(this, "Field Empty!", Toast.LENGTH_LONG).show();

        }

        if (TextUtils.isEmpty(Question)){

            Toast.makeText(this, "Field Empty!", Toast.LENGTH_LONG).show();

        }

        if (TextUtils.isEmpty(Type)){

            Toast.makeText(this, "Field Empty!", Toast.LENGTH_LONG).show();

        }
        else {

            PollData();
            Toast.makeText(this, "Poll Created!", Toast.LENGTH_LONG).show();

        }

        new CountDownTimer(seekBar.getProgress() * 1000, 1000){


            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    public void PollData(){

        String Question = editText1.getText().toString().trim();
        String Type = editText2.getText().toString().trim();
        String Name = name.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String AddOpttion1 = editText3.getText().toString().trim();
        String AddOpttion2 = editText4.getText().toString().trim();
        String AddOpttion3 = editText5.getText().toString().trim();
        String AddOpttion4 = editText6.getText().toString().trim();

        String id = databaseReference.push().getKey();

        Poll poll = new Poll(Name, Email, id, Question, Type, AddOpttion1, AddOpttion2, AddOpttion3, AddOpttion4);

        databaseReference.child(Name + " created a poll").setValue(poll);


    }

    public void add1(View view) {

        editText4.setVisibility(View.VISIBLE);
        image2.setVisibility(View.VISIBLE);
        image5.setVisibility(View.VISIBLE);

    }
    public void add2(View view) {

        editText5.setVisibility(View.VISIBLE);
        image3.setVisibility(View.VISIBLE);
        image6.setVisibility(View.VISIBLE);

    }
    public void add3(View view) {

        editText6.setVisibility(View.VISIBLE);
        image4.setVisibility(View.VISIBLE);
        image7.setVisibility(View.VISIBLE);

    }


    public void remove1(View view) {

        editText4.setVisibility(View.GONE);
        image5.setVisibility(View.GONE);
        image2.setVisibility(View.GONE);

    }

    public void remove2(View view) {

        editText5.setVisibility(View.GONE);
        image6.setVisibility(View.GONE);
        image3.setVisibility(View.GONE);

    }

    public void remove3(View view) {

        editText6.setVisibility(View.GONE);
        image7.setVisibility(View.GONE);
        image4.setVisibility(View.GONE);

    }

}
