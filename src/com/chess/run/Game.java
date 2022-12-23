package com.chess.run;

import com.chess.board.Board;
import com.chess.common.File;
import com.chess.common.Location;
import com.chess.gui.GUI;
import com.chess.piece.*;

import javax.swing.*;
import java.util.List;

/**
 * Clase que ejecuta el programa.
 */
public class Game {
    private static Game instance = null;
    private PieceColor turn;
    private Piece pieceSelected;
    private List<Location> currentValidMoves;

    private Game() {
        turn = PieceColor.WHITE;
        pieceSelected = null;
        currentValidMoves = null;
    }

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    public List<Location> getMoves() {
        return currentValidMoves;
    }

    public void setMoves(List<Location> currentValidMoves) {
        this.currentValidMoves = currentValidMoves;
    }

    public void nextTurn() {
        turn = (turn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

    public PieceColor getTurn() {
        return turn;
    }

    public Piece getPieceSelected() {
        return pieceSelected;
    }

    public void setPieceSelected(Piece pieceSelected) {
        this.pieceSelected = pieceSelected;
    }

    public void endGame(PieceColor winner) {
        JOptionPane.showConfirmDialog(null, "Ganan las piezas " + winner + "!", "Fin del juego", JOptionPane.DEFAULT_OPTION);
        GUI.getInstance().dispose();
    }
}
