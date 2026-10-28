package edu.qcc.et570.gamescore;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int homePoints = 0;
    int homeGames = 0;
    int homeSets = 0;

    TextView homePointsText;
    TextView homeGamesText;
    TextView homeSetsText;

    int visitorPoints = 0;
    int visitorGames = 0;
    int visitorSets = 0;

    TextView visitorPointsText;
    TextView visitorGamesText;
    TextView visitorSetsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homePointsText = findViewById(R.id.tv_usaw_count);
        homeGamesText = findViewById(R.id.tv_home_games);
        homeSetsText = findViewById(R.id.tv_home_sets);

        visitorPointsText = findViewById(R.id.tv_visitor_count);
        visitorGamesText = findViewById(R.id.tv_visitor_games);
        visitorSetsText = findViewById(R.id.tv_visitor_sets);

        updateScores();
    }

    private String getPointScore(int points, int opponentPoints) {
        if (points >= 3 && opponentPoints >= 3) {
            if (points == opponentPoints) return "Deuce";
            if (points == opponentPoints + 1) return "Adv";
            if (opponentPoints == points + 1) return "";
        }

        switch (points) {
            case 0: return "Love";
            case 1: return "15";
            case 2: return "30";
            case 3: return "40";
            default: return "Win";
        }
    }

    private void checkGameWin() {
        if (homePoints >= 4 && homePoints >= visitorPoints + 2) {
            homeGames++;
            homePoints = 0;
            visitorPoints = 0;
            checkSetWin();
        }
        else if (visitorPoints >= 4 && visitorPoints >= homePoints + 2) {
            visitorGames++;
            homePoints = 0;
            visitorPoints = 0;
            checkSetWin();
        }
    }

    private void checkSetWin() {
        if (homeGames >= 6 && homeGames >= visitorGames + 2) {
            homeSets++;
            homeGames = 0;
            visitorGames = 0;
        } else if (visitorGames >= 6 && visitorGames >= homeGames + 2) {
            visitorSets++;
            homeGames = 0;
            visitorGames = 0;
        }
    }

    private void updateScores() {
        homePointsText.setText(getPointScore(homePoints, visitorPoints));
        visitorPointsText.setText(getPointScore(visitorPoints, homePoints));

        // MODIFIED: Using string format resources (format_games, format_sets)
        homeGamesText.setText(getString(R.string.format_games, homeGames));
        homeSetsText.setText(getString(R.string.format_sets, homeSets));

        visitorGamesText.setText(getString(R.string.format_games, visitorGames));
        visitorSetsText.setText(getString(R.string.format_sets, visitorSets));
    }

    public void onUsawClicked(View view) {
        homePoints++;
        checkGameWin();
        updateScores();
    }

    public void onVisitorClicked(View view) {
        visitorPoints++;
        checkGameWin();
        updateScores();
    }
}