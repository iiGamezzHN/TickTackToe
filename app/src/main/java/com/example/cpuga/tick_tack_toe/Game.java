package com.example.cpuga.tick_tack_toe;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.io.Serializable;

public class Game implements Serializable {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;
    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed = 0;
    private Boolean gameOver;
    private GameState draw;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;

    }

    public void Restore(GridLayout gridLayout) {

        int but = 0;
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++) {
                TileState state = board[i][j];
                View child = gridLayout.getChildAt(but);
                Button button = (Button) child;

                switch (state) {
                    case CROSS:
                        // do something
                        button.setText("X");
                        but += 1;
                        break;
                    case CIRCLE:
                        // do something
                        button.setText("O");
                        but += 1;
                        break;
                    case BLANK:
                        // do something different
                        but += 1;
                        break;
                }
            }
    }

    public TileState choose(int row, int column) {
        movesPlayed += 1;
        //Log.d("Count",Integer.toString(movesPlayed));

        if (board[row][column] == TileState.BLANK) {
            if (playerOneTurn) {
                board[row][column] = TileState.CROSS;
                playerOneTurn = false;
                return TileState.CROSS;
            } else {
                board[row][column] = TileState.CIRCLE;
                playerOneTurn = true;
                return TileState.CIRCLE;
            }

        } else {
            board[row][column] = TileState.INVALID;
            return TileState.INVALID;
        }
    }

    public GameState won() {
        if (movesPlayed == 26) {
            MainActivity.movesPlayed = 26;
            gameOver = true;
        }

        for (int i = 0; i < BOARD_SIZE; i++)
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i] &&
                    board[0][i] == TileState.CROSS) { // Vertical win P1
                gameOver = true;
                return GameState.PLAYER_ONE;
            } else if (board[0][i] == board[1][i] && board[0][i] == board[2][i] &&
                    board[0][i] == TileState.CIRCLE) { // Vertical win P2
                gameOver = true;
                return GameState.PLAYER_TWO;
            } else if (board[i][0] == board[i][1] && board[i][0] == board[i][2] &&
                    board[i][0] == TileState.CROSS) { // Horizontal win P1
                gameOver = true;
                return GameState.PLAYER_ONE;
            } else if (board[i][0] == board[i][1] && board[i][0] == board[i][2] &&
                    board[i][0] == TileState.CIRCLE) { // Horizontal win P2
                gameOver = true;
                return GameState.PLAYER_TWO;
            }

        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] !=
                TileState.BLANK) {  // Diagonal Left-Right P1
            if (board[0][0] == TileState.CROSS) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            } else {
                gameOver = true; // Diagonal Left-Right P2
                return GameState.PLAYER_TWO;
            }
        } else if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] !=
                TileState.BLANK) { // Diagonal Right-Left P1
            if (board[0][2] == TileState.CROSS) {
                gameOver = true;;
                return GameState.PLAYER_ONE;
            } else {
                gameOver = true;; // Diagonal Right-Left P1
                return GameState.PLAYER_TWO;
            }
        } else {
            movesPlayed += 1;
            return GameState.IN_PROGRESS;
        }
    }

    public boolean GameOver() {
        return gameOver;
    }
}