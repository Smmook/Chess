package com.chess.run;

import com.chess.board.Board;
import com.chess.gui.GUI;
import com.chess.thread.ThreadClass;

public class Run {
    public static void main(String[] args) {
        Thread myThread = new Thread(new ThreadClass());
        myThread.start();

        Board board = Board.getInstance();
        board.initPieces();
        Game game = Game.getInstance();
        GUI gui = new GUI();
        gui.update();

    }
}
