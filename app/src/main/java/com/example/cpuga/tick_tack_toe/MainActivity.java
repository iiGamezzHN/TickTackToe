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
    private Game game;
    public static int movesPlayed;
    private TextView winningPlayer;
    private String vs;
    private Button friend;
    private Button computer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridlayout = findViewById(R.id.grid_Layout);
        winningPlayer = findViewById(R.id.winning_player);

        vs = "Friend";
        friend = findViewById(R.id.vs_friend);
        computer = findViewById(R.id.vs_computer);

        friend.setBackgroundResource(R.color.selected);

        if (savedInstanceState != null) {
            game = (Game) savedInstanceState.getSerializable("gameTag");
            assert game != null; // No bugging notification about possible NullPointerException
            game.Restore(gridlayout);
            String win = savedInstanceState.getString("winTag");
            winningPlayer.setText(win);
        } else game = new Game();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putSerializable("gameTag", game);
        savedInstanceState.putString("winTag", winningPlayer.getText().toString());
    }

    public void vsFriend(View view) {
        if(vs != "Friend") {
            // Do something
            vs = "Friend";
            resetClicked(view);
            computer.setBackgroundResource(android.R.drawable.btn_default);
            friend.setBackgroundResource(R.color.selected);
        }
    }

    public void vsComputer(View view) {
        if(vs != "Computer") {
            // Do something
            vs = "Computer";
            resetClicked(view);
            friend.setBackgroundResource(android.R.drawable.btn_default);
            computer.setBackgroundResource(R.color.selected);
        }
    }

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
        }

        else if (id == R.id.button2) {
            row = 0;
            column = 1;
        }

        else if (id == R.id.button3) {
            row = 0;
            column = 2;
        }

        else if (id == R.id.button4) {
            row = 1;
            column = 0;
        }

        else if (id == R.id.button5) {
            row = 1;
            column = 1;
        }

        else if (id == R.id.button6) {
            row = 1;
            column = 2;
        }

        else if (id == R.id.button7) {
            row = 2;
            column = 0;
        }

        else if (id == R.id.button8) {
            row = 2;
            column = 1;
        }

        else if (id == R.id.button9) {
            row = 2;
            column = 2;
        }

        else if (id == R.id.button10) {
            resetClicked(view);
        }

        TileState state = game.choose(row,column);

        Button button = (Button) view;

        switch(state) {
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
        if (game.GameOver()) {
            if (movesPlayed == 26) {
                Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();
                winningPlayer.setText("It's a draw!");
            }else if (state == TileState.CROSS) {
                Toast.makeText(this, "Player 1 won!", Toast.LENGTH_SHORT).show();
                winningPlayer.setText("Player 1 won!");
            }else if (state == TileState.CIRCLE) {
                Toast.makeText(this, "Player 2 won!", Toast.LENGTH_SHORT).show();
                winningPlayer.setText("Player 2 won!");
            }
        }
    }

    public void resetClicked(View view) {
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