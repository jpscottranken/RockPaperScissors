package edu.ranken.jpscott.rockpaperscissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TotalsActivity extends AppCompatActivity {
    TextView textViewTotals;
    Button buttonReturnToMainPage;

    Integer totalComputerWins;
    Integer totalUserWins;
    Integer totalTieGames;
    String  result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totals);
        //  Get references to widgets
        textViewTotals          = findViewById(R.id.textViewTotals);
        buttonReturnToMainPage  =   findViewById(R.id.buttonReturnToMainPage);

        buttonReturnToMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Get the intent in the target activity
        Intent intent = getIntent();

        // Get the attached bundle from the intent
        Bundle extras = intent.getExtras();

        //Extract the stored data from the bundle
        if (extras != null) {
            if (extras.containsKey("totalComputerWins"))
            {
                totalComputerWins = extras.getInt("totalComputerWins", 0);
            }

            if (extras.containsKey("totalUserWins"))
            {
                totalUserWins = extras.getInt("totalUserWins", 0);
            }

            if (extras.containsKey("totalTieGames"))
            {
                totalTieGames = extras.getInt("totalTieGames", 0);
            }

            result  = "\nComputer Wins: "   + totalComputerWins + "\n";
            result += "\nUser Wins: "       + totalUserWins     + "\n";
            result += "\nTie Games: "       + totalTieGames     + "\n";

            textViewTotals.setText(result);
        }
    }
}
