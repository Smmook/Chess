package com.chess.board;

import com.chess.piece.Piece;

/**
 * Interfaz que implementan los observadores concretos.
 */
public interface Observer {
    /**
     * Metodo que se ejecuta cuando se produce un movimiento.
     * @param piece pieza que ha sido movida
     */
    void update(Piece piece);
}
