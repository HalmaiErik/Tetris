package Tiles;

import java.awt.Color;

/**
 * Each piece is made of tiles in a specific formation
 */

public enum TileTypes {
    /**
     * Piece I
     */
    TypeI(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MAX), 4, 4, 1, new boolean[][] {
            {
                    false,	false,	false,	false,
                    true,	true,	true,	true,
                    false,	false,	false,	false,
                    false,	false,	false,	false,
            },
            {
                    false,	false,	true,	false,
                    false,	false,	true,	false,
                    false,	false,	true,	false,
                    false,	false,	true,	false,
            },
            {
                    false,	false,	false,	false,
                    false,	false,	false,	false,
                    true,	true,	true,	true,
                    false,	false,	false,	false,
            },
            {
                    false,	true,	false,	false,
                    false,	true,	false,	false,
                    false,	true,	false,	false,
                    false,	true,	false,	false,
            }
    }),

    /**
     * Piece J
     */
    TypeJ(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX), 3, 3, 2, new boolean[][] {
            {
                    true,	false,	false,
                    true,	true,	true,
                    false,	false,	false,
            },
            {
                    false,	true,	true,
                    false,	true,	false,
                    false,	true,	false,
            },
            {
                    false,	false,	false,
                    true,	true,	true,
                    false,	false,	true,
            },
            {
                    false,	true,	false,
                    false,	true,	false,
                    true,	true,	false,
            }
    }),

    /**
     * Piece L
     */
    TypeL(new Color(BoardPanel.COLOR_MAX, 127, BoardPanel.COLOR_MIN), 3, 3, 2, new boolean[][] {
            {
                    false,	false,	true,
                    true,	true,	true,
                    false,	false,	false,
            },
            {
                    false,	true,	false,
                    false,	true,	false,
                    false,	true,	true,
            },
            {
                    false,	false,	false,
                    true,	true,	true,
                    true,	false,	false,
            },
            {
                    true,	true,	false,
                    false,	true,	false,
                    false,	true,	false,
            }
    }),

    /**
     * Piece O
     */
    TypeO(new Color(BoardPanel.COLOR_MAX, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN), 2, 2, 2, new boolean[][] {
            {
                    true,	true,
                    true,	true,
            },
            {
                    true,	true,
                    true,	true,
            },
            {
                    true,	true,
                    true,	true,
            },
            {
                    true,	true,
                    true,	true,
            }
    }),

    /**
     * Piece S
     */
    TypeS(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN), 3, 3, 2, new boolean[][] {
            {
                    false,	true,	true,
                    true,	true,	false,
                    false,	false,	false,
            },
            {
                    false,	true,	false,
                    false,	true,	true,
                    false,	false,	true,
            },
            {
                    false,	false,	false,
                    false,	true,	true,
                    true,	true,	false,
            },
            {
                    true,	false,	false,
                    true,	true,	false,
                    false,	true,	false,
            }
    }),

    /**
     * Piece T
     */
    TypeT(new Color(128, BoardPanel.COLOR_MIN, 128), 3, 3, 2, new boolean[][] {
            {
                    false,	true,	false,
                    true,	true,	true,
                    false,	false,	false,
            },
            {
                    false,	true,	false,
                    false,	true,	true,
                    false,	true,	false,
            },
            {
                    false,	false,	false,
                    true,	true,	true,
                    false,	true,	false,
            },
            {
                    false,	true,	false,
                    true,	true,	false,
                    false,	true,	false,
            }
    }),

    /**
     * Piece Z
     */
    TypeZ(new Color(BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN, BoardPanel.COLOR_MIN), 3, 3, 2, new boolean[][] {
            {
                    true,	true,	false,
                    false,	true,	true,
                    false,	false,	false,
            },
            {
                    false,	false,	true,
                    false,	true,	true,
                    false,	true,	false,
            },
            {
                    false,	false,	false,
                    true,	true,	false,
                    false,	true,	true,
            },
            {
                    false,	true,	false,
                    true,	true,	false,
                    true,	false,	false,
            }
    });

    private Color colorPiece;
    private int spawnRow;
    private int spawnCol;

    //dimension of piece's matrix: a matrix of size dimensionXdimension
    private int dimension;
    //nr of rows that have true in tiles matrix (when rotation = 0)
    private int row;
    //nr of columns that have true in tiles matrix (when rotation = 0)
    private int col;

    //tile matrix for this piece
    private boolean[][] tiles;

    /**
     * Creates a new piece
     * @param color
     * @param dimension
     * @param cols
     * @param rows
     * @param tiles
     */
    private TileTypes(Color color, int dimension, int cols, int rows, boolean[][] tiles) {
        this.colorPiece = color;
        this.dimension = dimension;
        this.tiles = tiles;
        this.col = col;
        this.row = row;

        this.spawnCol = 5 - (dimension >> 1);
        this.spawnRow = 0;
    }

    /**
     * Gets color of piece.
     * @return colorPiece
     */
    public Color getColorPiece() {
        return colorPiece;
    }

    /**
     * Gets dimension of piece
     * @return dimension
     */
    public int getDimension() {
        return dimension
    }

    /**
     * Gets spawn row of piece
     * @return spawnRow
     */
    public int getSpawnRow() {
        return spawnRow;
    }

    /**
     * Gets spawn column of piece
     * @return spawnCol
     */
    public int getSpawnColumn() {
        return spawnCol;
    }

    /**
     * Gets number of rows that have true in tiles matrix (when rotation = 0)
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets number of columns that have true in tiles matrix (when rotation = 0)
     * @return col
     */
    public int getCol() {
        return col;
    }

    /**
     * Checks if the given coordinates and rotation is already occupied
     * @param x The x coordinate of the piece
     * @param y The y corrdinate of the piece
     * @param rotation The rotation of the piece
     * @return true if occupied, false if not
     */
    public boolean isOccupied(int x, int y, int rotation) {
        return tiles[rotation][y * dimension + x];
    }


    /**
     * Returns the number of empty columns on the left of the array of tiles
     * @param rotation The rotation of the piece
     * @return Number of empty columns on the left
     */
    public int getLeftEmpty(int rotation) {
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                if (isOccupied(x, y, rotation))
                    return x;
            }
        }
        return -1;
    }

    /**
     * Returns the number of empty columns on the right of the array of tiles
     * @param rotation The rotation of the piece
     * @return Number of empty columns on the right
     */
    public int getRightEmpty(int rotation) {
        for (int x = dimension - 1; x >= 0; x--) {
            for (int y = 0; y < dimension; y++) {
                if(isOccupied(x, y, rotation))
                    return dimension - x
            }
        }
        return -1;
    }

    /**
     * Returns the number of empty rows above the array of tiles
     * @param rotation The rotation of the piece
     * @return Number of empty rows above
     */
    public int getAboveEmpty(int rotation) {
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if(isOccupied(x, y, rotation))
                    return y
            }
        }
        return -1;
    }

    /**
     * Returns the number of empty rows below the array of tiles
     * @param rotation The rotation of the piece
     * @return Number of empty rows below
     */
    public int getBelowEmpty(int rotation) {
        for (int y = dimension - 1; y >= 0; y--) {
            for (int x = 0; x < dimension; x++) {
                if(isOccupied(x, y, rotation))
                    return dimension - y;
            }
        }
        return -1;
    }
}
