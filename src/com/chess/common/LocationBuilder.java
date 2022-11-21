package com.chess.common;

/**
 * Clase de ayuda que construye Location nuevas a partir de una ya existente y un offset.
 */
public final class LocationBuilder {
    /**
     * Array de posibles valores de la columna.
     */
    private static final File[] files = File.values();

    /**
     * Metodo de construye la Location.
     *
     * @param current       location ya existente
     * @param fileOffset    offset de columna
     * @param rankOffset    offset de fila
     * @return Location
     */
    public static Location build(Location current, Integer fileOffset, Integer rankOffset) {
        Integer newRank = current.getRank() + rankOffset;
        File newFile;
        try {
            newFile = files[current.getFile().ordinal() + fileOffset];
        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("Error en LocationBuilder. File offset se sale del limite");
            return null;
        }
        return new Location(newFile, newRank);
    }
}
