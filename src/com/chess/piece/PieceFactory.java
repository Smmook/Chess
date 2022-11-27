package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.square.Square;

import java.util.Map;

public final class PieceFactory {
    public static Piece build(PieceType pieceType, PieceColor color, Location location) {
        Map<Location, Square> squareMap = Board.getInstance().getSquareMap();
        if (!squareMap.containsKey(location)) {
            System.err.println("Location no pertenece al tablero.");
            throw new RuntimeException();
        }
        Square square = squareMap.get(location);
        if (square.isOccupied()) {
            System.err.println("La casilla esta ocupada.");
            throw new RuntimeException();
        }
        Piece piece = null;
        switch (pieceType) {
            case ROOK -> piece = new Rook(color);
            case KING -> piece = new King(color);
            case PAWN -> piece = new Pawn(color);
            case QUEEN -> piece = new Queen(color);
            case BISHOP -> piece = new Bishop(color);
            case KNIGHT -> piece = new Knight(color);
        }
        square.setCurrentPiece(piece);
        square.setOccupied(true);
        piece.setCurrentSquare(square);
        return piece;
    }
}
