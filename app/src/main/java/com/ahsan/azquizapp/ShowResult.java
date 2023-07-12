package com.ahsan.azquizapp;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ShowResult extends Fragment {

    public ShowResult() {
        // Required empty public constructor
    }

    TextView t;
    public static ShowResult newInstance() {
        return new ShowResult();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_result, container, false);

        t= view.findViewById(R.id.neroo);


        DBHelper dbHelper = new DBHelper(getContext());

        Score recentScores = dbHelper.getRecentScores();

        if (recentScores != null) {
            int gainScore = recentScores.getGainScore();
            int totalScore = recentScores.getTotalScore();
            int wrongAnswers = totalScore - gainScore;

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Correct Answers: ").append(gainScore).append("\n");
            stringBuilder.append("Total Quiz Questions: ").append(totalScore).append("\n");
            stringBuilder.append("Wrong Answers: ").append(wrongAnswers).append("\n\n");

            if (wrongAnswers < 3) {
                stringBuilder.append("Reward :     Good").append("\n");
            } else {
                stringBuilder.append("Reward :     Improve").append("\n");
            }

            t.setText(stringBuilder.toString());

        } else {
            t.setText(" No results found!");
        }

        return view;
    }
}
