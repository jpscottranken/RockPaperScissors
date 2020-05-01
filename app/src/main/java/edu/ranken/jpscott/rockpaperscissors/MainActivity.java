package edu.ranken.jpscott.rockpaperscissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //******************************************************************
    //  Declare and initialize program constants
    final int ROCK      =   1;
    final int PAPER     =   2;
    final int SCISSORS  =   3;

    //******************************************************************
    //  Declare program variables
    boolean  choiceMade;                    //  If true, computer has "chosen"
    int      computerChoice;                //  Computer choice of R, P, or S
    int      userChoice;                    //  User choice of R, P, or S
    int      totalComputerWins;             //  Total number games computer won
    int      totalUserWins;                 //  Total number games user won
    int      totalTieGames;                 //  Total number games tied
    String   computer;                      //
    String   user;                          //
    String   winner;                        //  Winner of current game

    Random rand = new Random();             //  "Seed the rand"
    ImageButton imageButtonRock;
    ImageButton imageButtonPaper;
    ImageButton imageButtonScissors;
    TextView    textViewResults;
    Button      buttonTotals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButtonRock     = findViewById(R.id.imageButtonRock);
        imageButtonPaper    = findViewById(R.id.imageButtonPaper);
        imageButtonScissors = findViewById(R.id.imageButtonScissors);
        textViewResults     = findViewById(R.id.textViewResults);
        buttonTotals        = findViewById(R.id.buttonTotals);

        buttonTotals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callTotalsActivity();
            }
        });

        //  Begin a new RPS Game
        beginNewGame();

        imageButtonRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userChoseRock(view);
            }
        });

        imageButtonPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userChosePaper(view);
            }
        });

        imageButtonScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userChoseScissors(view);
            }
        });
    }

    public void callTotalsActivity()
    {
        // Create a Bundle object
        Bundle extras = new Bundle();
        extras.putInt("totalComputerWins", totalComputerWins);
        extras.putInt("totalUserWins",     totalUserWins);
        extras.putInt("totalTieGames",     totalTieGames);

        // Create and initialize an intent
        Intent intent = new Intent(getApplicationContext(),
                                   TotalsActivity.class);

        // Attach the bundle to the Intent object
        intent.putExtras(extras);

        // Start the activity
        startActivity(intent);
    }

    public void beginNewGame()
    {
        //  Computer has not yet chosen
        choiceMade = false;

        //  Generate random number (1 - 3)
        //  representing computer choice.
        generateComputerChoice();

        //  generate appropriate string
        //  (ROCK, PAPER, or SCISSORS)
        //  for computer choosing 1, 2, or 3.
        generateComputerString();

        //  Computer has now made its choice
        choiceMade = true;
    }

    //******************************************************************
    //  This method generates a 1, 2, or 3
    //  symbolizing the computer choice.
    public void generateComputerChoice()
    {   //  Begin public void generateComputerChoice()
        //  Generate a random number (1 - 3) for the computer pick where:
        //  1 means the computer "picked" ROCK, a
        //  2 means the ocmputer "picked" PAPER, and a
        //  3 means the computer "picked" SCISSORS.
        computerChoice = rand.nextInt(3 - 1) + 1;
        //computerChoice = rand.nextInt(3) + 1;
    }   //  End   public void generateComputerChoice()

    //******************************************************************
    //  This method associates the string ROCK
    //  with the computerChoice being a 1, the
    //  string PAPER with the computerChoice
    //  being a 2, and the string SCISSORS with
    //  the computerChoice being a 3.  Those
    //  SHOULD BE the only possibles.  But, just
    //  in case, the default is arbitrarily just
    //  set to ROCK.
    public void generateComputerString()
    {
        switch (computerChoice)
        {
            case 1:
                computer = "ROCK";
                break;

            case 2:
                computer = "PAPER";
                break;

            case 3:
                computer = "SCISSORS";
                break;

            //  This should NEVER be hit
            default:
                computer = "ROCK";
                break;
        }
    }

    //******************************************************************
    //  Method called if user chose ROCK
    public void userChoseRock(View v)
    {   //  Begin public void userChoseRock(View v)
        user        =   "ROCK";
        userChoice  =   1;

        //  Make sure the computer has chosen
        //  If not, call method beginNewGame()
        //  to force computer choice to be made.
        if (!choiceMade)
        {   //  Begin if (!choiceMade)
            beginNewGame();
        }   //  End   if (!choiceMade)

        //  Determine who won the current game
        if (computerChoice == ROCK)
        {   //  Begin if (computerChoice == ROCK)
            winner = "TIE GAME";        //  Both chose ROCK
            ++totalTieGames;
        }   //  End   if (computerChoice == ROCK)
        else if (computerChoice == PAPER)
        {   //  Begin else if (computerChoice == PAPER)
            winner = "COMPUTER";        //  PAPER covers ROCK
            ++totalComputerWins;
        }   //  End   else if (computerChoice == PAPER)
        else if (computerChoice == SCISSORS)
        {   //  Begin else if (computerChoice == SCISSORS)
            winner = "USER";            //  ROCK crushes SCISSORS
            ++totalUserWins;
        }   //  End   else if (computerChoice == SCISSORS)

        //  Current game over.  Call method to show results
        showResults();
    }   //  End   public void userChoseRock(View v)

    //******************************************************************
    //  Method called if user chose PAPER
    public void userChosePaper(View v)
    {   //  Begin public void userChosePaper(View v)
        user = "PAPER";
        userChoice = 2;

        //  Make sure the computer has chosen
        //  If not, call method beginNewGame()
        //  to force computer choice to be made.
        if (!choiceMade) {   //  Begin if (!choiceMade)
            beginNewGame();
        }   //  End   if (!choiceMade)

        //  Determine who won the current game
        if (computerChoice == ROCK) {   //  Begin if (computerChoice == ROCK)
            winner = "USER";            //  PAPER covers ROCK
            ++totalUserWins;
        }   //  End   if (computerChoice == ROCK)
        else if (computerChoice == PAPER) {   //  Begin else if (computerChoice == PAPER)
            winner = "TIE GAME";        //  Both chose PAPER
            ++totalTieGames;
        }   //  End   else if (computerChoice == PAPER)
        else if (computerChoice == SCISSORS) {   //  Begin else if (computerChoice == SCISSORS)
            winner = "COMPUTER";        //  SCISSORS cuts PAPER
            ++totalComputerWins;
        }   //  End   else if (computerChoice == SCISSORS);

        //  Current game over.  Call method to show results
        showResults();
    }   //  End   public void userChosePaper(View v)

    //******************************************************************
    //  Method called if user chose SCISSORS
    public void userChoseScissors(View v)
    {   //  Begin public void userChoseScissors(View v)
        user        =   "SCISSORS";
        userChoice  =   3;

        //  Make sure the computer has chosen
        //  If not, call method beginNewGame()
        //  to force computer choice to be made.
        if (!choiceMade)
        {   //  Begin if (!choiceMade)
            beginNewGame();
        }   //  End   if (!choiceMade)

        //  Determine who won the current game
        if (computerChoice == ROCK)
        {   //  Begin if (computerChoice == ROCK)
            winner = "COMPUTER";            //  ROCK crushes SCISSORS
            ++totalComputerWins;
        }   //  End   if (computerChoice == ROCK)
        else if (computerChoice == PAPER)
        {   //  Begin else if (computerChoice == PAPER)
            winner = "USER";            //  SCISSORS cuts PAPER
            ++totalUserWins;
        }   //  End   else if (computerChoice == PAPER)
        else if (computerChoice == SCISSORS)
        {   //  Begin else if (computerChoice == SCISSORS)
            winner = "TIE GAME";        //  Both chose SCISSORS
            ++totalTieGames;
        }   //  End   else if (computerChoice == SCISSORS

        //  Current game over.  Call method to show results
        showResults();
    }   //  End   public void userChoseScissors(View v)

    //******************************************************************
    //  Method called to show current individual and total RPS stats
    public void showResults()
    {   //  Begin public void showResults()
        //  Show current game stats
        String outputStr  = "Computer chose:\t" +  computer +   "\n";
               outputStr += "The user chose:\t" +  user     +   "\n";
               outputStr += "The winner  is:\t" +  winner   +   "\n";

        //  Set textViewResults to outputStr
        textViewResults.setText(outputStr);
        beginNewGame();
    }   //  End   public void showResults()
}
