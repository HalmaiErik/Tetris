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
    //nr of coloumns that have true in tiles matrix (when rotation = 0)
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
        this.spawnRow =
    }

    /**
     * Gets color of piece.
     * @return Color of piece
     */
    public Color getColorPiece() {
        return colorPiece;
    }

    /**
     * Gets dimension of piece
     * @return Dimension of piece's matrix
     */
    public int getDimension() {
        return dimension
    }


    public int getSpawnRow() {
        return spawnRow;
    }

    public int getSpawnCol() {
        return spawnCol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
