package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.common.LocationBuilder;
import com.chess.square.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa a la reina.
 */
public class Queen extends Piece {
    public Queen(PieceColor pieceColor) {
        super(PieceType.QUEEN, pieceColor);
    }

    @Override
    public List<Location> getValidMoves() {
        List<Location> moves = new ArrayList<>();

        getMoves(moves, 1, 1);
        getMoves(moves, 1, -1);
        getMoves(moves, -1, -1);
        getMoves(moves, -1, 1);
        getMoves(moves, 1, 0);
        getMoves(moves, -1, 0);
        getMoves(moves, 0, 1);
        getMoves(moves, 0, -1);

        return moves;
    }

    /**
     * Metodo de ayuda para calcular los movimientos legales.
     *
     * @param candidateMoves    lista de movimientos legales
     * @param fileOffset        offset de la columna
     * @param rankOffset        offset de la fila
     */
    private void getMoves(List<Location> candidateMoves, Integer fileOffset, Integer rankOffset) {
        Map<Location, Square> squareMap = Board.getInstance().getSquareMap();
        Location next = LocationBuilder.build(getCurrentSquare().getLocation(), fileOffset ,rankOffset);
        while (squareMap.containsKey(next)) {
            if (squareMap.get(next).isOccupied()) {
                if (squareMap.get(next).getCurrentPiece().getPieceColor().equals(this.getPieceColor())) {
                    break;
                }
                candidateMoves.add(next);
                break;
            }
            candidateMoves.add(next);
            next = LocationBuilder.build(next, fileOffset, rankOffset);
        }
    }
}
