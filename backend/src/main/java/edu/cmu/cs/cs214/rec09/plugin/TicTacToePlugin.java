package edu.cmu.cs.cs214.rec09.plugin;

import edu.cmu.cs.cs214.rec09.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec09.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec09.games.TicTacToe;


public class TicTacToePlugin implements GamePlugin<String> {
     private GameFramework framework;
    private TicTacToe game;

    @Override
    public String getGameName() {
        return "Tic-Tac-Toe";
    }

    @Override
    public int getGridWidth() {
        return TicTacToe.SIZE;
    }

    @Override
    public int getGridHeight() {
        return TicTacToe.SIZE;
    }

    @Override
    public void onRegister(GameFramework framework) {
        this.framework = framework;
    }

    @Override
    public void onNewGame() {
        game = new TicTacToe();
        for (int y = 0; y < getGridHeight(); y++) {
            for (int x = 0; x < getGridWidth(); x++) {
                framework.setSquare(x, y, null);
            }
        }
        framework.setFooterText("Player X's turn");
    }
    
    @Override
    public void onNewMove() {
        // Optional: Add code for what happens at the start of a new move
    }

    @Override
    public boolean isMoveValid(int x, int y) {
        return game.isValidPlay(x, y);
    }

    @Override
    public boolean isMoveOver() {
        // Optional: Implement if there's specific logic to end a move
        return false;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        game.play(x, y);
        framework.setSquare(x, y, game.currentPlayer().opponent().toString());
        if (game.isOver()) {
            framework.setFooterText(getGameOverMessage());
        } else {
            framework.setFooterText("Player " + game.currentPlayer() + "'s turn");
        }
    }

    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public String getGameOverMessage() {
        TicTacToe.Player winner = game.winner();
        if (winner != null) {
            return "Winner: " + winner;
        } else {
            return "The game is a draw.";
        }
    }

    @Override
    public void onGameClosed() {
        // Optional: Add any cleanup code if necessary
    }

    @Override
    public String currentPlayer() {
        return game.currentPlayer().toString();
    }
}
