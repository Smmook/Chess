package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.common.LocationBuilder;
import com.chess.square.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa al alfil.
 */
public class Bishop extends Piece {

    public Bishop(PieceColor pieceColor) {
        super(PieceType.BISHOP, pieceColor);
    }

    @Override
    public List<Location> getValidMoves() {
        Map<Location, Square> squareMap = Board.getInstance().getSquareMap();
        List<Location> moves = new ArrayList<>();
        Location current = this.getCurrentSquare().getLocation();

        getMoves(moves, 1, 1);
        getMoves(moves,  1, -1);
        getMoves(moves, -1, -1);
        getMoves(moves, -1, 1);

        return moves;
    }

    /**
     * Metodo de ayuda para calcular los movimientos legales.
     *
     * @param moves             lista de movimientos legales
     * @param fileOffset        offset de la columna
     * @param rankOffset        offset de la fila
     */
    private void getMoves(List<Location> moves, Integer fileOffset, Integer rankOffset) {
        Map<Location, Square> squareMap = Board.getInstance().getSquareMap();
        Location next = LocationBuilder.build(getCurrentSquare().getLocation(), fileOffset ,rankOffset);
        while (squareMap.containsKey(next)) {
            if (squareMap.get(next).isOccupied()) {
                if (squareMap.get(next).getCurrentPiece().getPieceColor().equals(this.getPieceColor())) {
                    break;
                }
                moves.add(next);
                break;
            }
            moves.add(next);
            next = LocationBuilder.build(next, fileOffset, rankOffset);
        }
    }
}
