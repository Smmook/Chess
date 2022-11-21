package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.common.LocationBuilder;
import com.chess.square.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Knight extends Piece {

    public Knight(PieceColor pieceColor) {
        super(PieceType.KNIGHT, pieceColor);
    }

    @Override
    public List<Location> getValidMoves() {
        List<Location> moves = new ArrayList<>();

        getMoves(moves, 1, 2);
        getMoves(moves, 2, 1);
        getMoves(moves, 2, -1);
        getMoves(moves, 1, -2);
        getMoves(moves, -1, -2);
        getMoves(moves, -2, -1);
        getMoves(moves, -2, 1);
        getMoves(moves, -1, 2);

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
        Location move = LocationBuilder.build(getCurrentSquare().getLocation(), fileOffset, rankOffset);
        if (squareMap.containsKey(move)) {
            if (!squareMap.get(move).isOccupied() || !squareMap.get(move).getCurrentPiece().getPieceColor().equals(this.getPieceColor())) {
                moves.add(move);
            }
        }
    }
}
