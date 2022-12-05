package com.chess.piece;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.common.LocationBuilder;
import com.chess.square.Square;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa a la torre.
 */
public class Rook extends Piece {
    /**
     * Almacena si la torre se ha movido antes.
     */
    private boolean isFirstMove = true;

    public Rook(PieceColor pieceColor) {
        super(PieceType.ROOK, pieceColor);
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    @Override
    public void move(Location location) {
        super.move(location);
        isFirstMove = false;
    }

    @Override
    public List<Location> getValidMoves() {
        List<Location> candidateMoves = new ArrayList<>();

        getMoves(candidateMoves, 1, 0);
        getMoves(candidateMoves, -1, 0);
        getMoves(candidateMoves, 0, 1);
        getMoves(candidateMoves, 0, -1);

        return candidateMoves;
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
