package com.chess.square;

import com.chess.common.Location;
import com.chess.piece.Piece;

import javax.swing.*;

public class Square {
    private final Location location;
    private final SquareColor squareColor;
    private JButton jButton;
    private Piece currentPiece;
    private boolean isOccupied;

    public Square(Location location, SquareColor squareColor) {
        this.location = location;
        this.squareColor = squareColor;
        this.isOccupied = false;
    }

    public JButton getjButton() {
        return jButton;
    }

    public void setjButton(JButton jButton) {
        this.jButton = jButton;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Location getLocation() {
        return location;
    }

    public SquareColor getSquareColor() {
        return squareColor;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }

    @Override
    public String toString() {
        return "Square{" +
                "location=" + location +
                ", squareColor=" + squareColor +
                ", isOccupied=" + isOccupied +
                '}';
    }


}
