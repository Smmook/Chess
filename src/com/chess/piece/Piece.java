package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.square.Square;

import java.util.List;

/**
 * Clase abstracta que representa una pieza.
 */
public abstract class Piece {

    /**
     * Tipo de pieza.
     */
    private final PieceType pieceType;

    /**
     * Color de la pieza.
     */
    private final PieceColor pieceColor;

    /**
     * Casilla en la que se encuentra actualmente la pieza.
     */
    private Square currentSquare;

    /**
     * Constructor de la pieza.
     *
     * @param pieceType     tipo de la pieza
     * @param pieceColor    color de la pieza
     */
    public Piece(PieceType pieceType, PieceColor pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    /**
     * Metodo que devuelve todos los movimientos legales de la pieza en este momento.
     *
     * @return  lista con todas las casillas a las que se puede mover la pieza
     */
    public abstract  List<Location> getValidMoves();

    public void move(Location location) {
        Board board = Board.getInstance();
        getCurrentSquare().reset();
        Square square = board.getSquareMap().get(location);
        square.setCurrentPiece(this);
        square.setOccupied(true);
        setCurrentSquare(square);
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public void setCurrentSquare(Square currentSquare) {
        this.currentSquare = currentSquare;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "pieceType=" + pieceType +
                ", pieceColor=" + pieceColor +
                ", currentSquare=" + currentSquare +
                '}';
    }
}
