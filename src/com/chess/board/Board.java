package com.chess.board;

import com.chess.common.File;
import com.chess.common.Location;
import com.chess.piece.*;
import com.chess.square.Square;
import com.chess.square.SquareColor;

import java.util.*;

public class Board {
    private static final int BOARD_SIZE = 8;
    private static Board instance;
    private final Square[][] boardSquares = new Square[BOARD_SIZE][BOARD_SIZE];
    private final Map<Location, Square> squareMap;
    private final List<Observer> observers;

    private Board() {
        squareMap = new HashMap<>();
        observers = new ArrayList<>();
        observers.add(new MateObserver());
        for (int i = 0; i < boardSquares.length; i++) {
            SquareColor currentColor = (i % 2 == 0) ? SquareColor.LIGHT : SquareColor.DARK;
            for (File file : File.values()) {
                Location location = new Location(file, BOARD_SIZE - i);
                Square square = new Square(location, currentColor);
                squareMap.put(location, square);
                boardSquares[i][file.ordinal()] = square;
                currentColor = (currentColor == SquareColor.LIGHT) ? SquareColor.DARK : SquareColor.LIGHT;
            }
        }
    }

    public static Board getInstance() {
        if (instance == null) instance = new Board();
        return instance;
    }

    public void initPieces() {
        PieceFactory.build(PieceType.ROOK, PieceColor.WHITE, new Location(File.A, 1));
        PieceFactory.build(PieceType.ROOK, PieceColor.WHITE, new Location(File.H, 1));
        PieceFactory.build(PieceType.ROOK, PieceColor.BLACK, new Location(File.H, 8));
        PieceFactory.build(PieceType.ROOK, PieceColor.BLACK, new Location(File.A, 8));

        PieceFactory.build(PieceType.KNIGHT, PieceColor.WHITE, new Location(File.B, 1));
        PieceFactory.build(PieceType.KNIGHT, PieceColor.WHITE, new Location(File.G, 1));
        PieceFactory.build(PieceType.KNIGHT, PieceColor.BLACK, new Location(File.G, 8));
        PieceFactory.build(PieceType.KNIGHT, PieceColor.BLACK, new Location(File.B, 8));

        PieceFactory.build(PieceType.BISHOP, PieceColor.WHITE, new Location(File.C, 1));
        PieceFactory.build(PieceType.BISHOP, PieceColor.WHITE, new Location(File.F, 1));
        PieceFactory.build(PieceType.BISHOP, PieceColor.BLACK, new Location(File.F, 8));
        PieceFactory.build(PieceType.BISHOP, PieceColor.BLACK, new Location(File.C, 8));

        PieceFactory.build(PieceType.QUEEN, PieceColor.WHITE, new Location(File.D, 1));
        PieceFactory.build(PieceType.QUEEN, PieceColor.BLACK, new Location(File.D, 8));

        PieceFactory.build(PieceType.KING, PieceColor.WHITE, new Location(File.E, 1));
        PieceFactory.build(PieceType.KING, PieceColor.BLACK, new Location(File.E, 8));

        for (File file : File.values()) {
            PieceFactory.build(PieceType.PAWN, PieceColor.WHITE, new Location(file, 2));
            PieceFactory.build(PieceType.PAWN, PieceColor.BLACK, new Location(file, 7));
        }
    }

    public Square[][] getBoardSquares() {
        return boardSquares;
    }

    public Map<Location, Square> getSquareMap() {
        return squareMap;
    }

    public void movePiece(Piece piece, Location to) {
        Square toSquare = squareMap.get(to);
        if (toSquare.isOccupied()) {
           for (Observer observer : observers) {
               observer.update(toSquare.getCurrentPiece());
           }
        }
        piece.move(to);
    }


    // Todos los metodos restantes son para hacer pruebas.

    public void printBoard() {
        String text = "";
        for (int i = 0; i < boardSquares.length; i++) {
            System.out.print(BOARD_SIZE - i + " | ");
            for (Square square : boardSquares[i]) {
                if (square.isOccupied()) {
                    text = String.valueOf(square.getCurrentPiece().getPieceType().toString().charAt(0));
                } else {
                    text = "-";
                }
                System.out.print("[" + text + "]");
            }
            System.out.println();
        }

        System.out.print("    ");
        for (File file : File.values()) {
            System.out.print(" " + file + " ");
        }
        System.out.println();
    }

    public void printBoard(Piece piece) {
        List<Location> moves = piece.getValidMoves();
        List<Square> squares = new ArrayList<>();
        for (Location move : moves) {
            squares.add(squareMap.get(move));
        }

        String text = "";
        for (int i = 0; i < boardSquares.length; i++) {
            System.out.print(BOARD_SIZE - i + " | ");
            for (Square square : boardSquares[i]) {
                if (square.isOccupied()) {
                    text = String.valueOf(square.getCurrentPiece().getPieceType().toString().charAt(0));
                    if (squares.contains(square)) {
                        System.out.print("[ " + text + "*]");
                    } else {
                        System.out.print("[ " + text + " ]");
                    }
                } else {
                    if (squares.contains(square)) {
                        text = "x";
                    } else {
                        text = "-";
                    }
                    System.out.print("[ " + text + " ]");
                }
            }
            System.out.println();
        }
        System.out.print("    ");
        for (File file : File.values()) {
            System.out.print("  " + file.name() + "  ");
        }
    }

}
