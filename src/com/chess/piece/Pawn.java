package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.common.LocationBuilder;
import com.chess.square.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Clase que represnta un peon.
 */
public class Pawn extends Piece {

    /**
     * Almacena si el peon se ha movido antes.
     */
    private boolean isFirstMove = true;

    public Pawn(PieceColor pieceColor) {
        super(PieceType.PAWN, pieceColor);
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }

    /**
     * Metodo de ayuda para tener en cuenta la direccion en la que se mueve el peon.
     *
     * @return  1 si es blanco y -1 si es negro
     */
    private Integer dir() {
        return (this.getPieceColor().equals(PieceColor.WHITE)) ? 1 : -1;
    }

    @Override
    public List<Location> getValidMoves() {
        Map<Location, Square> squareMap = Board.getInstance().getSquareMap();
        List<Location> moves = new ArrayList<>();
        Location current = this.getCurrentSquare().getLocation();
        Location front = LocationBuilder.build(current, 0, dir());
        if (squareMap.containsKey(front) && !squareMap.get(front).isOccupied()) {
            moves.add(front);
            if (isFirstMove) {
                Location another = LocationBuilder.build(current, 0, 2*dir());
                if (squareMap.containsKey(another) && !squareMap.get(another).isOccupied()) {
                    moves.add(LocationBuilder.build(current, 0, 2*dir()));
                }
            }
        }
        Location toRight = LocationBuilder.build(current, 1, dir());
        Location toLeft = LocationBuilder.build(current, -1, dir());
        checkDiagonal(moves, toRight);
        checkDiagonal(moves, toLeft);
        return moves;
    }

    /**
     * Metodo de ayuda para calcular los movimientos legales.
     *
     * @param moves     lista de movimientos legales
     * @param candidate casilla a revisar
     */
    private void checkDiagonal(List<Location> moves, Location candidate) {
        Map<Location, Square> squareMap = Board.getInstance().getSquareMap();
        if (squareMap.containsKey(candidate) && squareMap.get(candidate).isOccupied() ) {
            if (!squareMap.get(candidate).getCurrentPiece().getPieceColor().equals(this.getPieceColor())) {
                moves.add(candidate);
            }
        }
    }
}
