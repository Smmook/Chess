package com.chess.board;

import com.chess.piece.Piece;
import com.chess.piece.PieceColor;
import com.chess.piece.PieceType;
import com.chess.run.Game;

public class MateObserver implements Observer {
    @Override
    public void update(Piece piece) {
        if (piece.getPieceType().equals(PieceType.KING)) {
            System.out.println("El rey " + piece.getPieceColor() + " ha sido comido");
            PieceColor winner = (piece.getPieceColor().equals(PieceColor.WHITE)) ? PieceColor.BLACK : PieceColor.WHITE;
            Game.getInstance().endGame(winner);
        }
    }
}
