package com.chess.run;

import com.chess.board.Board;
import com.chess.common.File;
import com.chess.common.Location;
import com.chess.gui.GUI;
import com.chess.piece.PieceBuilder;
import com.chess.piece.PieceColor;
import com.chess.piece.PieceType;
import com.chess.piece.Rook;

public class Run {
    public static void main(String[] args) {
        Board board = Board.getInstance();
        Rook rook = (Rook) PieceBuilder.build(PieceType.ROOK, PieceColor.WHITE, new Location(File.A, 5));
        PieceBuilder.build(PieceType.PAWN, PieceColor.BLACK, new Location(File.A, 7));
//        board.initPieces();
        Game game = Game.getInstance();
        GUI gui = new GUI();
        gui.update();
    }
}
