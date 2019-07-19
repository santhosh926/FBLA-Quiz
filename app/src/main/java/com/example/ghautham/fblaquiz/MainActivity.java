package com.example.ghautham.fblaquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView num, scoreshow, question;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4;
    ArrayList<QuestionObj> list;
    int count, choice;
    static int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.id_btn);
        num = findViewById(R.id.id_num);
        scoreshow = findViewById(R.id.id_score);
        question = findViewById(R.id.id_question);
        rg = findViewById(R.id.id_rg);
        list = new ArrayList<>();
        rb1 = findViewById(R.id.id_opt1);
        rb2 = findViewById(R.id.id_opt2);
        rb3 = findViewById(R.id.id_opt3);
        rb4 = findViewById(R.id.id_opt4);
        count = 0;
        score = 0;
        choice = 0;


        list.add(new QuestionObj("Who founded FBLA?", "Hamden L. Forkner", "Edward D. Miller", "Conrad N. Hilton", "Harry Truman", 0));
        list.add(new QuestionObj("When was the first high school FBLA chapter held?", "1945", "1942", "1953", "1956", 1));
        list.add(new QuestionObj("Where is the FBLA headquarters currently located?", "Washington, D.C.", "New York City, NY", "Reston, VA", "Chicago, IL", 2));
        list.add(new QuestionObj("How many high school members are there?", "196,950", "300,000", "230,000", "320,000", 0));
        list.add(new QuestionObj("Which of the following is NOT a sponsor for FBLA?", "API", "Geico", "IBM", "Visa", 2));
        list.add(new QuestionObj("Which dates are the FBLA National Leadership Conference (NLC) for 2018?", "June 3rd - June 6th", "June 28th - July 1st", "May 30th - June 2nd", "June 13th - June 16th", 1));
        list.add(new QuestionObj("Where is the FBLA National Leadership Conference (NLC) for 2018?", "Anaheim, California", "San Antonio, Texas", "Baltimore, Maryland", "Salt Lake City, Utah", 1));
        list.add(new QuestionObj("Who is the CEO of FBLA?", "Jean M. Buckley", "Adam Johnson", "Connor Evans", "Eugene Allen", 0));
        list.add(new QuestionObj("Which of the following is NOT an administrative region?", "Eastern Region", "Mountain Plains Region", "Southern Region", "Northeast Region", 3));
        list.add(new QuestionObj("Which of the following is NOT a color of FBLA?", "Navy", "Blue", "Maroon", "Gold", 2));
        list.add(new QuestionObj("What charitable organization does FBLA primarily support?", "Hugs For Brady", "Eden Autism", "Red Cross", "March for Dimes", 3));
        list.add(new QuestionObj("How many FBLA administrative regions are there?", "5", "3", "2", "7", 0));
        list.add(new QuestionObj("Who is the current FBLA national president?", "Sean Parker", "Eu Ro Wang", "Eduardo Saverin", "Tyler Winklevoss", 1));
        list.add(new QuestionObj("When is the March of Dimes World Prematurity Day?", "December 17", "November 30", "December 3", "November 17", 3));
        list.add(new QuestionObj("What kind of attire is acceptable for FBLA activities (SLC & REC)?", "No Dress Code", "Business Casual", "Business Formal", "Casual", 2));
        list.add(new QuestionObj("What month is FBLA-PBL week held?", "February", "January", "December", "April", 0));
        list.add(new QuestionObj("What is the official FBLA website?", "fbla.org", "fbla-pbl.org", "fblaofficial.org", "fbla.com", 1));
        list.add(new QuestionObj("Which word is NOT part of the FBLA-PBL emblem?", "Service", "Education", "Progress", "Success ", 3));
        list.add(new QuestionObj("Where was the first FBLA chapter held?", "New Jersey", "Virginia", "Iowa", "Texas", 2));
        list.add(new QuestionObj("What is the first line of the FBLA creed?", "\"A penny saved is a penny earned\"", "\"Knowledge is power\"", "\"Business is booming\"", "\"I believe education is the right of every person\"", 3));

        question.setText(list.get(0).getQ());
        rb1.setText(list.get(0).getOne());
        rb2.setText(list.get(0).getTwo());
        rb3.setText(list.get(0).getThree());
        rb4.setText(list.get(0).getFour());
        btn.setEnabled(false);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked())
                    btn.setEnabled(true);
                else
                    btn.setEnabled(false);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb1.isChecked()) choice = 0;
                else if (rb2.isChecked()) choice = 1;
                else if (rb3.isChecked()) choice = 2;
                else if (rb4.isChecked()) choice = 3;


                if (choice == list.get(count).getCorrect()) {
                    score += 100;
                    scoreshow.setText("" + score);
                    Toast.makeText(getApplicationContext(), "CORRECT!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "INCORRECT!", Toast.LENGTH_SHORT).show();
                }

                rg.clearCheck();

                count++;
                if (count < list.size()) {
                    num.setText("" + (count + 1));
                    question.setText(list.get(count).getQ());

                    rb1.setText(list.get(count).getOne());
                    rb2.setText(list.get(count).getTwo());
                    rb3.setText(list.get(count).getThree());
                    rb4.setText(list.get(count).getFour());
                } else {
                    Intent intent = new Intent(MainActivity.this, FinalActivity.class);
                    intent.putExtra("SCORE_KEY", Integer.toString(score));
                    intent.putExtra("NAME_KEY", LoginActivity.username);
                    startActivity(intent);
                }

            }
        });
    }
}