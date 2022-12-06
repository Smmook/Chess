package com.chess.gui;

import com.chess.board.Board;
import com.chess.common.File;
import com.chess.common.Location;
import com.chess.piece.Piece;
import com.chess.piece.PieceColor;
import com.chess.piece.PieceType;
import com.chess.run.Game;
import com.chess.square.Square;
import com.chess.square.SquareColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Clase del JFrame.
 */
public class GUI extends JFrame {
    private final JButton[][] guiSquares;

    /**
     * Constructor de la clase.
     */
    public GUI() {
        Board board = Board.getInstance();

        setTitle("Ajedrez");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 700);

        JPanel startPane = new JPanel();
        setContentPane(startPane);
        startPane.setLayout(null);

        JPanel mainPane = new JPanel(new GridLayout(9, 9));
        mainPane.setBorder(new EmptyBorder(0, 0, 64, 64));
        Square[][] boardSquares = board.getBoardSquares();
        guiSquares = new JButton[8][8];
        for (int i = 0; i < guiSquares.length; i++) {
            for (int j = 0; j < guiSquares[i].length; j++) {
                JButton btn = new JButton();
                btn.setSize(64, 64);
                btn.setBorder(null);
                Square square = boardSquares[i][j];
                if (square.getSquareColor().equals(SquareColor.LIGHT)) {
                    btn.setBackground(new Color(240, 217, 181));
                } else {
                    btn.setBackground(new Color(181, 136, 99));
                }
                btn.addActionListener(e -> {
                    resetColor();
                    Game game = Game.getInstance();
                    if (game.getPieceSelected() != null && (!square.isOccupied() || !square.getCurrentPiece().getPieceColor().equals(game.getTurn()))) {
                        // Habia pieza seleccionada
                        System.out.println("Habia pieza seleccionada");
                        Map<Location, Square> squareMap = board.getSquareMap();
                        for (Location move : game.getMoves()) {
                            Square validSquare = squareMap.get(move);
                            if (validSquare.equals(square)) {
                                Square oldSquare = game.getPieceSelected().getCurrentSquare();
                                board.movePiece(game.getPieceSelected(), square.getLocation());
                                System.out.println("Se pasa turno");
                                System.out.println(oldSquare);
                                game.nextTurn();
                                update();
                                break;
                            }
                        }
                        game.setPieceSelected(null);
                        game.setMoves(null);
                    } else if (square.isOccupied() && square.getCurrentPiece().getPieceColor().equals(game.getTurn())) {
                        // Se selecciona una pieza que se puede mover
                        System.out.println(square);
                        square.getjButton().setBackground(new Color(174, 177, 136));
                        List<Location> moves = square.getCurrentPiece().getValidMoves();
                        game.setMoves(moves);
                        game.setPieceSelected(square.getCurrentPiece());
                        Map<Location, Square> squareMap = board.getSquareMap();
                        for (Location move : moves) {
                            Square validSquare = squareMap.get(move);
                            validSquare.getjButton().setBackground(new Color(174, 177, 136));
                        }
                    } else {
                        // Se selecciona una casilla no valida o una pieza que no se puede mover
                        game.setMoves(null);
                        game.setPieceSelected(null);
                    }
                });
                guiSquares[i][j] = btn;
                square.setjButton(btn);
            }
        }

        mainPane.add(new JLabel(""));
        for (File file : File.values()) {
            mainPane.add(new JLabel(file.toString(), SwingConstants.CENTER));
        }
        for (int i = 0; i < guiSquares.length; i++) {
            mainPane.add(new JLabel(String.valueOf(8-i), SwingConstants.CENTER));
            for (int j = 0; j < guiSquares[i].length; j++) {
                mainPane.add(guiSquares[i][j]);
            }
        }

        JLabel initLabel = new JLabel("Ajedrez por Diego Arenas.", SwingConstants.CENTER);
        initLabel.setBounds(50, 50, 600, 100);
        initLabel.setBorder(new LineBorder(Color.BLACK));
        startPane.add(initLabel);

        JButton initButton = new JButton("Empezar!");
        initButton.setBounds(300, 250, 100, 50);
        initButton.addActionListener(e -> {
            setContentPane(mainPane);
            setVisible(true);
        });
        startPane.add(initButton);

        setVisible(true);
    }

    private void resetColor() {
        Square[][] boardSquares = Board.getInstance().getBoardSquares();
        for (int i = 0; i < guiSquares.length; i++) {
            for (int j = 0; j < guiSquares[i].length; j++) {
                Square square = boardSquares[i][j];
                if (square.getSquareColor().equals(SquareColor.LIGHT)) {
                    guiSquares[i][j].setBackground(new Color(240, 217, 181));
                } else {
                    guiSquares[i][j].setBackground(new Color(181, 136, 99));
                }
            }
        }
    }

    public void update() {
        Square[][] boardSquares = Board.getInstance().getBoardSquares();
        String path = null;
        for (int i = 0; i < guiSquares.length; i++) {
            for (int j = 0; j < guiSquares[i].length; j++) {
                if (boardSquares[i][j].isOccupied()) {
                    Piece piece = boardSquares[i][j].getCurrentPiece();
                    switch (piece.getPieceType()) {
                        case ROOK -> {
                            if (piece.getPieceColor().equals(PieceColor.WHITE)) {
                                path = "/WRook.png";
                            } else {
                                path = "/BRook.png";
                            }
                        }
                        case KNIGHT -> {
                            if (piece.getPieceColor().equals(PieceColor.WHITE)) {
                                path = "/WKnight.png";
                            } else {
                                path = "/BKnight.png";
                            }
                        }
                        case BISHOP -> {
                            if (piece.getPieceColor().equals(PieceColor.WHITE)) {
                                path = "/WBishop.png";
                            } else {
                                path = "/BBishop.png";
                            }
                        }
                        case QUEEN -> {
                            if (piece.getPieceColor().equals(PieceColor.WHITE)) {
                                path = "/WQueen.png";
                            } else {
                                path = "/BQueen.png";
                            }
                        }
                        case PAWN -> {
                            if (piece.getPieceColor().equals(PieceColor.WHITE)) {
                                path = "/WPawn.png";
                            } else {
                                path = "/BPawn.png";
                            }
                        }
                        case KING -> {
                            if (piece.getPieceColor().equals(PieceColor.WHITE)) {
                                path = "/WKing.png";
                            } else {
                                path = "/BKing.png";
                            }
                        }
                    }
                    try {
                        BufferedImage bufimg = ImageIO.read(getClass().getResource(path));
                        guiSquares[i][j].setIcon(new ImageIcon(bufimg));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    guiSquares[i][j].setIcon(null);
                }
            }
        }
        getContentPane().validate();
        getContentPane().repaint();
    }
}
