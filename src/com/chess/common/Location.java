package com.chess.common;

import java.util.Objects;

/**
 * Clase que define las coordenadas del tablero.
 */
public class Location {
    /**
     * Columna.
     */
    private final File file;

    /**
     * Fila.
     */
    private final Integer rank;

    /**
     * Location constructor.
     *
     * @param file columna
     * @param rank fila
     */
    public Location(File file, Integer rank) {
        this.file = file;
        this.rank = rank;
    }

    public File getFile() {
        return file;
    }

    public Integer getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return file == location.file && rank.equals(location.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Location{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }

}
