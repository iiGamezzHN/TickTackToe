package com.example.cpuga.tick_tack_toe;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Initialize variables
    private Game game;
    public static int movesPlayed;
    private TextView winningPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridlayout = findViewById(R.id.grid_Layout);
        winningPlayer = findViewById(R.id.winning_player); // Will show who wins, or draw

        if (savedInstanceState != null) {
            game = (Game) savedInstanceState.getSerializable("gameTag");
            assert game != null; // No bugging notification about possible NullPointerException
            game.Restore(gridlayout); // Restore the game when tilting the device
            String win = savedInstanceState.getString("winTag");
            winningPlayer.setText(win);
        } else game = new Game();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putSerializable("gameTag", game);
        savedInstanceState.putString("winTag", winningPlayer.getText().toString());
    }

    /**
     * This method checks which button is pressed
     * then checks what the state of that button is
     * if it already has a cross or circle it won't do anything
     * if it's empty it put a cross for player 1 and a circle for player 2
     *
     * @param view
     */
    @SuppressLint("SetTextI18n")
    public void tileClicked(View view) {
        if (game.won() != GameState.IN_PROGRESS) {
            if (game.won() == GameState.PLAYER_ONE) {
                return;
            } else {
                return;
            }
        }

        int id = view.getId();
        int row = 0;
        int column = 0;

        if (id == R.id.button) {
            row = 0;
            column = 0;
        } else if (id == R.id.button2) {
            row = 0;
            column = 1;
        } else if (id == R.id.button3) {
            row = 0;
            column = 2;
        } else if (id == R.id.button4) {
            row = 1;
            column = 0;
        } else if (id == R.id.button5) {
            row = 1;
            column = 1;
        } else if (id == R.id.button6) {
            row = 1;
            column = 2;
        } else if (id == R.id.button7) {
            row = 2;
            column = 0;
        } else if (id == R.id.button8) {
            row = 2;
            column = 1;
        } else if (id == R.id.button9) {
            row = 2;
            column = 2;
        } else if (id == R.id.button10) {
            resetClicked(view);
        }

        TileState state = game.choose(row, column);

        Button button = (Button) view;

        switch (state) { // Check state of the button
            case CROSS:
                // do something
                button.setText("X");
                game.won();
                break;
            case CIRCLE:
                // do something
                button.setText("O");
                game.won();
                break;
            case INVALID:
                // do something different
                game.won();
                break;
        }
        if (game.GameOver()) { // Win function for setting TextView to who wins
            if (movesPlayed == 26) {
                Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();
                winningPlayer.setText("It's a draw!");
            } else if (state == TileState.CROSS) {
                Toast.makeText(this, "Player 1 won!", Toast.LENGTH_SHORT).show();
                winningPlayer.setText("Player 1 won!");
            } else if (state == TileState.CIRCLE) {
                Toast.makeText(this, "Player 2 won!", Toast.LENGTH_SHORT).show();
                winningPlayer.setText("Player 2 won!");
            }
        }
    }

    public void resetClicked(View view) { // Resets the game
        game = new Game();

        GridLayout gridlayout = findViewById(R.id.grid_Layout);
        for (int i = 0; i < gridlayout.getChildCount(); i++) {
            View child = gridlayout.getChildAt(i);
            Button button = (Button) child;
            button.setText(" ");
        }

        winningPlayer.setText("");
        movesPlayed = 0;
    }


}