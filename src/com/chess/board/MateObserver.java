package com.chess.board;

import com.chess.piece.Piece;
import com.chess.piece.PieceType;

public class MateObserver implements Observer {
    @Override
    public void update(Piece piece) {
        if (piece.getPieceType().equals(PieceType.KING)) {
            System.out.println("El rey " + piece.getPieceColor() + " ha sido comido");
            // TODO: 15/12/2022 implementar el fin del juego
        }
    }
}
