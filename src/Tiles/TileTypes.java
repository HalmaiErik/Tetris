package Tiles;

import java.awt.Color;

public enum TileTypes {
    /**
     * Piece TypeI.
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
     * Piece TypeJ.
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
     * Piece TypeL.
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
     * Piece TypeO.
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
     * Piece TypeS.
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
     * Piece TypeT.
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
     * Piece TypeZ.
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
}
