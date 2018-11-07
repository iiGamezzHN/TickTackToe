package com.example.cpuga.tick_tack_toe;

import android.util.Log;

public class Game {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;

    }

    public TileState choose(int row, int column) {
        if (board[row][column] == TileState.BLANK) {
            if (playerOneTurn) {
                board[row][column] = TileState.CROSS;
                playerOneTurn = false;
                return TileState.CROSS;
            }

            else {
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
        for(int i=0; i<BOARD_SIZE; i++)
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == TileState.CROSS) {
                Log.d("Message","P1 Won!");
            }
            else if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == TileState.CIRCLE) {
                Log.d("Message","P2 Won!");
            }
            else if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == TileState.CROSS) {
                Log.d("Message","P1 Won!");
            }
            else if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == TileState.CIRCLE) {
                Log.d("Message","P2 Won!");
            }

        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != TileState.BLANK) {
            if (board[0][0] == TileState.CROSS) {
                Log.d("Message","P1 Won!");
                return GameState.PLAYER_ONE;
            }

            else {
                Log.d("Message", "P2 Won!");
                return GameState.PLAYER_TWO;
            }
        }

        else if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != TileState.BLANK) {
            if (board[0][2] == TileState.CROSS) {
                Log.d("Message","P1 Won!");
                return GameState.PLAYER_ONE;
            }

            else {
                Log.d("Message", "P2 Won!");
                return GameState.PLAYER_TWO;
            }
        }

        else {
            movesPlayed += 1;
            return GameState.IN_PROGRESS;
        }
    }
}