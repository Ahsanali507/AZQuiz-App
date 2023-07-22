package com.ahsan.azquizapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class ShowResult extends Fragment {

    public ShowResult() {
        // Required empty public constructor
    }

    LinearLayout resultLayout;
    TextView txt;

    public static ShowResult newInstance() {
        return new ShowResult();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_result, container, false);

        resultLayout = view.findViewById(R.id.resultLayout);
        txt = view.findViewById(R.id.neroo);
        DBHelper dbHelper = new DBHelper(getContext());
        ArrayList<Score> scores = dbHelper.getAllScores();

        if (!scores.isEmpty()) {
            StringBuilder resultBuilder = new StringBuilder();

            for (Score score : scores) {
                int gainScore = score.getGainScore();
                int totalScore = score.getTotalScore();
                int wrongAnswers = totalScore - gainScore;

                resultBuilder.append("Attempt Record:").append("\n");
                resultBuilder.append("Correct Answers: ").append(gainScore).append("\n");
                resultBuilder.append("Total Quiz Questions: ").append(totalScore).append("\n");
                resultBuilder.append("Wrong Answers: ").append(wrongAnswers).append("\n\n");
            }

            txt.setText(resultBuilder.toString());
        } else {
            txt.setText(" No results found!");
        }

        return view;
    }
}
