package com.example.cpuga.tick_tack_toe;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridlayout = findViewById(R.id.grid_Layout);

        if (savedInstanceState != null) {
            game = (Game) savedInstanceState.getSerializable("gameTag");
            game.Restore(gridlayout);
        } else game = new Game();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("gameTag", game);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void tileClicked(View view) {
        if (game.won() != GameState.IN_PROGRESS) {
            if (game.won() == GameState.PLAYER_ONE) { // DOESN'T WORK
                Log.d(" ", "Player1");
                return;
            } else {
                Log.d(" ", "Player2");
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
    }



    public void resetClicked(View view) {
        game = new Game();

        GridLayout gridlayout = findViewById(R.id.grid_Layout);
        for (int i = 0; i < gridlayout.getChildCount(); i++) {
            View child = gridlayout.getChildAt(i);
            Button button = (Button) child;
            button.setText(" ");
        }
    }


}