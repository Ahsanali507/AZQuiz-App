package com.ahsan.azquizapp;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Random;

public class TakingQuiz extends Fragment {

    DBHelper db;
    char skyletter[] = {'b', 'd', 'f', 'h', 'k', 'l', 't'};
    char grassletter[] = {'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z'};
    char rootletter[] = {'g', 'j', 'p', 'q', 'y'};
    int correctAns = 0;
    int totalQuestions = 0;
    TextView tx, ex;
    Button sky, grass, root, next, end;
    Random rand;
    int n;

    public TakingQuiz() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taking_quiz, container, false);

        tx = view.findViewById(R.id.textView);
        ex = view.findViewById(R.id.textView3);
        sky = view.findViewById(R.id.button5);
        grass = view.findViewById(R.id.button6);
        root = view.findViewById(R.id.button8);
        next = view.findViewById(R.id.button4);
        end = view.findViewById(R.id.button10);

        db = new DBHelper(getContext());
        rand = new Random();
        n = rand.nextInt(3);
        if (n == 0) {
            char i = skyletter[rand.nextInt(skyletter.length)];
            tx.setText(String.valueOf(i));
        } else if (n == 1) {
            char i = grassletter[rand.nextInt(grassletter.length)];
            tx.setText(String.valueOf(i));
        } else if (n == 2) {
            char i = rootletter[rand.nextInt(rootletter.length)];
            tx.setText(String.valueOf(i));
        }
        totalQuestions++;

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalQuestions++;
                ex.setText("");
                n = rand.nextInt(3);
                if (n == 0) {
                    char i = skyletter[rand.nextInt(skyletter.length)];
                    tx.setText(String.valueOf(i));
                } else if (n == 1) {
                    char i = grassletter[rand.nextInt(grassletter.length)];
                    tx.setText(String.valueOf(i));
                } else if (n == 2) {
                    char i = rootletter[rand.nextInt(rootletter.length)];
                    tx.setText(String.valueOf(i));
                }
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addScores(correctAns,totalQuestions);
                ex.setText("Quiz Is Finished");
            }
        });

        sky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (n == 0) {
                    ex.setText("Go To Next Question");
                    correctAns++;
                } else {
                    ex.setText("Go To Next Question");
                    //ex.setText("Your answer is not right");

                }
            }
        });
        grass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (n == 1) {
                    ex.setText("Go To Next Question");
                    //ex.setText("Your answer is right");
                    correctAns++;
                } else {
                    ex.setText("Go To Next Question");
                    //ex.setText("Your answer is not right");
                }
            }
        });
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (n == 2) {
                    ex.setText("Go To Next Question");
                    //ex.setText("Your answer is right");
                    correctAns++;
                } else {
                    ex.setText("Go To Next Question");
                    //ex.setText("Your answer is not right");
                }
            }
        });

        return view;
    }
}
