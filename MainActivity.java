package com.example.polling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String POLL_NAME = "poll Answer";
    public static final String POLL_ID = "poll id";
    public static final String POLL_OPTION1 = "poll option1";
    public static final String POLL_OPTION2 = "poll option2";
    public static final String POLL_OPTION3 = "poll option3";
    public static final String POLL_OPTION4 = "poll option4";
    public static final String POLL_TYPE = "poll type";
    public static final String POLL_QUESTION = "poll question";

    private DatabaseReference databaseReference;
    private ListView listView;
    private List<Poll> pollList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.ListView);
        pollList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Poll");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Poll poll1 = pollList.get(position);
                Intent new1 = new Intent(getApplicationContext(), AnswerSubmit.class);

                new1.putExtra(POLL_ID, poll1.getQuestionId());
                new1.putExtra(POLL_NAME, poll1.getName());
                new1.putExtra(POLL_OPTION1, poll1.getAddOption1());
                new1.putExtra(POLL_OPTION2, poll1.getAddOption2());
                new1.putExtra(POLL_OPTION3, poll1.getAddOption3());
                new1.putExtra(POLL_OPTION4, poll1.getAddOption4());
                new1.putExtra(POLL_TYPE, poll1.getType());
                new1.putExtra(POLL_QUESTION, poll1.getQuestion());

                startActivity(new1);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                pollList.clear();

                for (DataSnapshot pollSnapshot : dataSnapshot.getChildren()){

                    Poll poll = pollSnapshot.getValue(Poll.class);

                    pollList.add(poll);
                }

                PollDataRetrive pollDataRetrive = new PollDataRetrive(MainActivity.this, pollList);
                listView.setAdapter(pollDataRetrive);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void Create(View view) {

        Intent intent = new Intent(this, CreateNewPoll.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_results, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.result:
                startActivity(new Intent(MainActivity.this, ResultActivity.class));
                return true;

                default:
        }

        return super.onOptionsItemSelected(item);
    }
}
