package com.chess.run;

import com.chess.board.Board;
import com.chess.gui.GUI;

public class Run {
    public static void main(String[] args) {
        Board board = Board.getInstance();
        board.initPieces();
        Game game = Game.getInstance();
        GUI gui = new GUI();
        gui.update();
    }
}
