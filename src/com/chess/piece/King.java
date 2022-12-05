package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.common.LocationBuilder;
import com.chess.square.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa al rey.
 */
public class King extends Piece {
    /**
     * Almacena si el rey se ha movido antes.
     */
    private boolean isFirstMove = true;

    /**
     * Constructor de la clase.
     *
     * @param pieceColor color de la pieza
     */
    public King(PieceColor pieceColor) {
        super(PieceType.KING, pieceColor);
    }

    /**
     * Metodo para mover la pieza.
     * @param location casilla a la que se quiere mover la pieza
     */
    @Override
    public void move(Location location) {
        if (isFirstMove) {
            int offSet = location.getFile().ordinal() - getCurrentSquare().getLocation().getFile().ordinal();
            System.out.println(offSet);
            if (Math.abs(offSet) > 1) {
                float sign = Math.signum(offSet);
                int rookOffset = (sign > 0) ? 3 : -4;
                int rookMove = (sign > 0) ? -2 : 3;
                Rook rook = (Rook) Board.getInstance().getSquareMap().get(LocationBuilder.build(getCurrentSquare().getLocation(), rookOffset, 0)).getCurrentPiece();
                System.out.println(rook);
                rook.move(LocationBuilder.build(rook.getCurrentSquare().getLocation(), rookMove, 0));
            }
        }

        super.move(location);
        isFirstMove = false;
    }

    /**
     * Metodo que devuelve todos los movimientos legales de la pieza en este momento.
     *
     * @return lista con todas las casillas a las que se puede mover la pieza
     */
    @Override
    public List<Location> getValidMoves() {
        List<Location> moves = new ArrayList<>();

        getMoves(moves, 0, 1);
        getMoves(moves, 1, 1);
        getMoves(moves, 1, 0);
        getMoves(moves, 1, -1);
        getMoves(moves, 0, -1);
        getMoves(moves, -1, -1);
        getMoves(moves, -1, 0);
        getMoves(moves, -1, 1);

        if (isFirstMove) {
            checkShortCastle(moves);
            checkLongCastle(moves);
        }

        return moves;
    }

    /**
     * Metodo que comprueba si se puede hacer el enroque corto.
     * @param moves lista de movimientos
     */
    private void checkShortCastle(List<Location> moves) {
        Map<Location, Square> squareMap = Board.getInstance().getSquareMap();

        //Enroque corto
        for (int i = 1; i < 2; i++) {
            Square square = squareMap.get(LocationBuilder.build(getCurrentSquare().getLocation(), i, 0));
            if (square.isOccupied()) return;
        }
        Square square = squareMap.get(LocationBuilder.build(getCurrentSquare().getLocation(), 3, 0));
        if (square.isOccupied()) {
            Piece piece = square.getCurrentPiece();
            if (piece.getPieceType().equals(PieceType.ROOK)) {
                Rook rook = (Rook) piece;
                if (rook.isFirstMove()) {
                    moves.add(LocationBuilder.build(getCurrentSquare().getLocation(), 2, 0));
                }
            }
        }
    }

    /**
     * Metodo que comprueba si se puede hacer el enroque largo.
     * @param moves lista de movimientos
     */
    private void checkLongCastle(List<Location> moves) {
        Map<Location, Square> squareMap = Board.getInstance().getSquareMap();

        //Enroque largo
        for (int i = -1; i > -3; i--) {
            Square square = squareMap.get(LocationBuilder.build(getCurrentSquare().getLocation(), i, 0));
            if (square.isOccupied()) return;
        }
        Square square = squareMap.get(LocationBuilder.build(getCurrentSquare().getLocation(), -4, 0));
        if (square.isOccupied()) {
            Piece piece = square.getCurrentPiece();
            if (piece.getPieceType().equals(PieceType.ROOK)) {
                Rook rook = (Rook) piece;
                if (rook.isFirstMove()) {
                    moves.add(LocationBuilder.build(getCurrentSquare().getLocation(), -2, 0));
                }
            }
        }
    }

    /**
     * Metodo de ayuda para calcular los movimientos legales.
     *
     * @param moves         lista de movimientos legales
     * @param fileOffset    offset de la columna
     * @param rankOffset    offset de la fila
     */
    private void getMoves(List<Location> moves, Integer fileOffset, Integer rankOffset) {
        Map<Location, Square> squareMap = Board.getInstance().getSquareMap();
        Location move = LocationBuilder.build(getCurrentSquare().getLocation(), fileOffset, rankOffset);
        if (squareMap.containsKey(move)) {
            if (!squareMap.get(move).isOccupied() || !squareMap.get(move).getCurrentPiece().getPieceColor().equals(this.getPieceColor())) {
                moves.add(move);
            }
        }
    }
}
