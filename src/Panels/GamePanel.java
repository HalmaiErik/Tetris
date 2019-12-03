package Panels;

import Pieces.Piece;
import Game.Tetris;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.Graphics;

/**
 * Displays game panel and handles things related to the game board
 */

public class GamePanel extends JPanel {
    // Width of border around game board
    private static final int BORDER_WIDTH = 5;
    // Number of columns on game board
    public static final int COLS = 10;
    // Number of visible rows
    private static final int VISIBLE_ROWS = 20;
    // Number of invisible rows
    private static final int HIDDEN_ROWS = 2;
    // Total number of rows on game board
    public static final int ROWS = VISIBLE_ROWS + HIDDEN_ROWS;
    // Size of tiles (pixels)
    public static final int TILE_SIZE = 24;
    // X coordinate of center
    private static final int CENTER_X = COLS * TILE_SIZE / 2;
    // Y coordinate of center
    private static final int CENTER_Y = VISIBLE_ROWS * TILE_SIZE / 2;
    // Total width of panel
    public static final int PANEL_WIDTH = COLS * TILE_SIZE + BORDER_WIDTH * 2;
    // Total height of panel
    public static final int PANEL_HEIGHT = VISIBLE_ROWS * TILE_SIZE + BORDER_WIDTH * 2;
    // Large font
    private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 16);
    //Small font
    private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 12);

    private Tetris tetris;
    private Piece[][] pieces;

    /**
     * Creates new GamePanel
     * @param tetris The tetris game to use
     */
    public GamePanel(Tetris tetris) {
        this.tetris = tetris;
        this.pieces = new Piece[ROWS][COLS];

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.BLACK);
    }

    /**
     * Resets the board and clears every tile
     */
    public void clear() {
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                pieces[i][j] = null;
            }
        }
    }

    /**
     * Checks to see if the tile is already occupied
     * @param x The x coordinate to check
     * @param y The y coordinate to check
     * @return Whether or not the tile is occupied
     */
    private boolean isOccupied(int x, int y) {
        return pieces[y][x] != null;
    }

    /**
     * Determines whether or not a piece can be placed at the coordinates.
     * @param piece The piece
     * @param x The x coordinate of the piece
     * @param y The y coordinate of the piece
     * @param rotation The rotation of the piece
     * @return Whether or not the position is valid
     */
    public boolean isValidAndEmpty(Piece piece, int x, int y, int rotation) {

        // Ensure the piece is in a valid column.
        if(x < -piece.getLeftEmpty(rotation) || x + piece.getDimension() - piece.getRightEmpty(rotation) >= COLS) {
            return false;
        }

        // Ensure the piece is in a valid row.
        if(y < -piece.getAboveEmpty(rotation) || y + piece.getDimension() - piece.getBelowEmpty(rotation) >= ROWS) {
            return false;
        }

        // Checks if collides with any other tile
        for(int col = 0; col < piece.getDimension(); col++) {
            for(int row = 0; row < piece.getDimension(); row++) {
                if(piece.isTile(col, row, rotation) && isOccupied(x + col, y + row)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Puts a tile to desired x and y coordinate
     * @param x The column
     * @param y The row
     * @param type The type of tile you want to create
     */
    private void setTile(int  x, int y, Piece type) {
        pieces[y][x] = type;
    }

    /**
     * Adds a piece to the game board
     * Note: Doesn't check for existing pieces, and will overwrite them if they exist
     * @param piece The piece to add
     * @param x The x coordinate where to add
     * @param y The y coordinate where to add
     * @param rotation The rotation of the piece
     */
    public void addPiece(Piece piece, int x, int y, int rotation) {
        for(int col = 0; col < piece.getDimension(); col++) {
            for(int row = 0; row < piece.getDimension(); row++) {
                if(piece.isTile(col, row, rotation)) {
                    setTile(col + x, row + y, piece);
                }
            }
        }
    }

    /**
     * Checks if line is complete or not. If complete, remove line
     * @param line The row to check
     * @return Whether or not this row is full
     */
    private boolean checkLine(int line) {
        for(int col = 0; col < COLS; col++) {
            if(!isOccupied(col, line)) {
                return false;
            }
        }

        // move every line, above complete line, one down
        for(int row = line - 1; row >= 0; row--) {
            for(int col = 0; col < COLS; col++) {
                setTile(col, row + 1,  pieces[row][col]);
            }
        }
        return true;
    }

    /**
     * Checks the board to see if any lines have been completed, and
     * removes them from the game
     * @return The number of lines that were cleared
     */
    public int checkLines() {
        int completedLines = 0;
        for(int row = 0; row < ROWS; row++) {
            if(checkLine(row)) {
                completedLines++;
            }
        }
        return completedLines;
    }

    /**
     * Draws a tile onto the board
     * @param color The color of tile.
     * @param x The column
     * @param y The row
     * @param g The graphics object
     */
    private void drawTile(Color color, int x, int y, Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //This helps simplify the positioning of things.
        g.translate(BORDER_WIDTH, BORDER_WIDTH);


        // Draw the board differently depending on the current game state.
        if(tetris.isPaused()) {
            g.setFont(LARGE_FONT);
            g.setColor(Color.WHITE);
            String msg = "PAUSED";
            g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2, CENTER_Y);
        } else if(tetris.isNewGame() || tetris.isGameOver()) {
            g.setFont(LARGE_FONT);
            g.setColor(Color.WHITE);

            String msg = tetris.isNewGame() ? "TETRIS" : "GAME OVER";
            g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2, 150);
            g.setFont(SMALL_FONT);
            msg = "Press Enter to Play" + (tetris.isNewGame() ? "" : " Again");
            g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2, 300);
        } else {
            // Draw tiles
            for(int x = 0; x < COLS; x++) {
                for(int y = HIDDEN_ROWS; y < ROWS; y++) {
                    Piece tile = pieces[y][x];
                    if(tile != null) {
                        drawTile(tile.getColorPiece(), x * TILE_SIZE, (y - HIDDEN_ROWS) * TILE_SIZE, g);
                    }
                }
            }


            // Draw the current piece
            Piece piece = tetris.getPieceType();
            int pieceCol = tetris.getPieceCol();
            int pieceRow = tetris.getPieceRow();
            int rotation = tetris.getPieceRotation();

            // Draw the piece onto the board.
            for(int col = 0; col < piece.getDimension(); col++) {
                for(int row = 0; row < piece.getDimension(); row++) {
                    if(pieceRow + row >= 2 && piece.isTile(col, row, rotation)) {
                        drawTile(piece.getColorPiece(), (pieceCol + col) * TILE_SIZE, (pieceRow + row - HIDDEN_ROWS) * TILE_SIZE, g);
                    }
                }
            }

            // Draw the ghost (semi-transparent piece that shows where the current piece will land)
            Color base = piece.getColorPiece();
            base = new Color(base.getRed(), base.getGreen(), base.getBlue(), 20);
            for(int lowest = pieceRow; lowest < ROWS; lowest++) {
                //If no collision is detected, try the next row.
                if(isValidAndEmpty(piece, pieceCol, lowest, rotation)) {
                    continue;
                }

                // Draw the ghost one row higher than the one the collision took place at.
                lowest--;

                // Draw the ghost piece.
                for(int col = 0; col < piece.getDimension(); col++) {
                    for(int row = 0; row < piece.getDimension(); row++) {
                        if(lowest + row >= 2 && piece.isTile(col, row, rotation)) {
                            drawTile(base, (pieceCol + col) * TILE_SIZE, (lowest + row - HIDDEN_ROWS) * TILE_SIZE, g);
                        }
                    }
                }

                break;
            }

            // Draw the background grid above the pieces.
            g.setColor(Color.DARK_GRAY);
            for(int x = 0; x < COLS; x++) {
                for(int y = 0; y < VISIBLE_ROWS; y++) {
                    g.drawLine(0, y * TILE_SIZE, COLS * TILE_SIZE, y * TILE_SIZE);
                    g.drawLine(x * TILE_SIZE, 0, x * TILE_SIZE, VISIBLE_ROWS * TILE_SIZE);
                }
            }
        }

        /*
         * Draw the outline.
         */
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, TILE_SIZE * COLS, TILE_SIZE * VISIBLE_ROWS);
    }
}
