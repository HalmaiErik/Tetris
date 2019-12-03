package Panels;

import Pieces.Piece;
import Panels.GamePanel;
import Game.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

/**
 * Displays general information about the current game, such as score, level, next piece preview, controls
 */

public class SidePanel extends JPanel {
    //small font properties
    private static final Font TEXT_SMALL_FONT = new Font("Tahoma", Font.BOLD, 11);
    //large font properties
    private static final Font TEXT_LARGE_FONT = new Font("Tahoma", Font.BOLD, 13);
    //offset between each string (number of pixels)
    private static final int TEXT_OFFSET = 25;
    //draw color: text and drawing colors
    private static final Color DRAW_COLOR = new Color(128, 192, 128); //greenish
    //nr pixels for small inset
    private static final int SMALL_INSET = 20;
    //nr pixels for large inset
    private static int LARGE_INSET = 40;

    //dimension of each tile on the next piece preview
    private static final int PREVIEW_TILE_SIZE = GamePanel.TILE_SIZE >> 1;
    //nr of rows and columns of preview window
    private static final int PREVIEW_TILE_COUNT = 5;
    //center x of preview window
    private static final int PREVIEW_CENTER_X = 130;
    //center y of preview window
    private static final int PREVIEW_CENTER_Y = 65;
    //size of preview window
    private static final int PREVIEW_WINDOW_SIZE = (PREVIEW_TILE_SIZE * PREVIEW_TILE_COUNT >> 1);

    //y coordinate of stats category
    private static final int STATS_COORDINATE_Y = 175;

    //y coordinate of controls category
    private static final int CONTROLS_COORDINATE_Y = 300;

    private Tetris tetris;

    /**
     * Creates new side panel and sets it's dimension and background color
     * @param tetris The tetris game to use
     */
    public SidePanel(Tetris tetris) {
        this.tetris = tetris;
        setPreferredSize(new Dimension(200, GamePanel.PANEL_HEIGHT));
        setBackground(Color.BLACK);
    }

    /**
     * Draws a tile at given x, y coordinates of piece's color
     * @param piece The tetris piece
     * @param x The x coordinate
     * @param y The y coordinate
     * @param g The graphics object
     */
    private void drawTile(Piece piece, int x, int y, Graphics g) {
        // Create Tile of piece's color
        g.setColor(piece.getColorPiece());
        g.fillRect(x, y, PREVIEW_TILE_SIZE, PREVIEW_TILE_SIZE);
    }

    /**
     * Paints the SidePanel
     * @param g The graphics object
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set color for text/drawing
        g.setColor(DRAW_COLOR);

        // Current y coordinate of string
        int offset;

        // Draw Stats
        g.setFont(TEXT_LARGE_FONT);
        g.drawString("Stats", SMALL_INSET, offset = STATS_COORDINATE_Y);
        g.setFont(TEXT_SMALL_FONT);
        g.drawString("Level: " + tetris.getLevel(), 30, offset += TEXT_OFFSET);
        g.drawString("Score: " + tetris.getScore(), 30, offset += TEXT_OFFSET);

        // Draw Controls
        g.setFont(TEXT_LARGE_FONT);
        g.drawString("Controls", SMALL_INSET, offset = CONTROLS_COORDINATE_Y);
        g.setFont(TEXT_SMALL_FONT);
        g.drawString("A - Move Left", LARGE_INSET, offset += TEXT_OFFSET);
        g.drawString("D - Move Right", LARGE_INSET, offset += TEXT_OFFSET);
        g.drawString("Q - Rotate Anticlockwise", LARGE_INSET, offset += TEXT_OFFSET);
        g.drawString("E - Rotate Clockwise", LARGE_INSET, offset += TEXT_OFFSET);
        g.drawString("S - Drop", LARGE_INSET, offset += TEXT_OFFSET);
        g.drawString("P - Pause Game", LARGE_INSET, offset += TEXT_OFFSET);

        // Draw Preview Window
        g.setFont(TEXT_LARGE_FONT);
        g.drawString("Next Piece:", SMALL_INSET, 70);
        g.drawRect(PREVIEW_CENTER_X - PREVIEW_WINDOW_SIZE, PREVIEW_CENTER_Y - PREVIEW_WINDOW_SIZE, PREVIEW_WINDOW_SIZE * 2, PREVIEW_WINDOW_SIZE * 2);

        // Draw Preview of next piece
        Piece piece = tetris.getNextPieceType();
        if(!tetris.isGameOver() && piece != null) {
            // Get size properties
            int col = piece.getCol();
            int row = piece.getRow();
            int dimension = piece.getDimension();

            // Calculate starting point for drawing
            // x coordinate starting point
            int startX = (PREVIEW_CENTER_X - (col * PREVIEW_TILE_SIZE / 2));
            //y coordinate starting point
            int startY = (PREVIEW_CENTER_Y - (row * PREVIEW_TILE_SIZE / 2));

            // Get top, left empty rows, columns
            int top = piece.getAboveEmpty(0);
            int left = piece.getLeftEmpty(0);

            // Draw piece's tiles
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (piece.isTile(i, j, 0)) {
                        drawTile(piece, startX + ((j - left) * PREVIEW_TILE_SIZE), startY + ((i - top) * PREVIEW_TILE_SIZE), g);
                    }
                }
            }

        }
    }

}
