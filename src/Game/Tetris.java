package Game;

import Pieces.Piece;
import Panels.GamePanel;
import Panels.SidePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * This class contains the main method, starts the game and reads user input.
 */

public class Tetris extends JFrame {
    // The number of milliseconds per frame
    private static final long FRAME_TIME = 1000L / 50L;
    // The number of possible pieces
    private static final int TYPE_COUNT = Piece.values().length;

    // GamePanel instance
    private GamePanel game;
    // SidePanel instance
    private SidePanel side;

    // If game is paused or not
    private boolean isPaused;
    // If we have new game is started or not
    private boolean isNewGame;
    // If game is over or not
    private boolean isGameOver;

    // Current level
    private int level;
    // Current score
    private int score;

    // Random number generator, used to get random pieces to spawn
    private Random random;

    // The clock of the game
    private Clock logicTimer;

    // The current piece
    private Piece currentPiece;
    // The next piece
    private Piece nextPiece;

    // The current column of the piece
    private int currentCol;
    // The current row of the piece
    private int currentRow;
    // The curretn rotation of the piece
    private int currentRotation;

    // Cooldown until the user can drop the piece
    private int dropCooldown;

    // Speed of the game
    private float gameSpeed;

    /**
     * Creates a new Tetris instance. Sets up the window's properties,
     * and adds a controller listener
     */
    private Tetris() {
        /*
         * Set the basic properties of the window
         */
        super("Tetris");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Initialize GamePanel and SidePanel
        this.game = new GamePanel(this);
        this.side = new SidePanel(this);

        // Add GamePanel and SidePanel to game frame
        add(game, BorderLayout.CENTER);
        add(side, BorderLayout.EAST);

        // Key listener of the frame
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {

                    // When drop is pressed, change timer to 25 cycles per second
                    case KeyEvent.VK_S:
                        if (!isPaused && dropCooldown == 0) {
                            logicTimer.setCyclesPerSec(25.0f);
                        }
                        break;

                    // Move left
                    case KeyEvent.VK_A:
                        if (!isPaused && game.isValidAndEmpty(currentPiece, currentCol - 1, currentRow, currentRotation)) {
                            currentCol--;
                        }
                        break;

                    // Move Right
                    case KeyEvent.VK_D:
                        if (!isPaused && game.isValidAndEmpty(currentPiece, currentCol + 1, currentRow, currentRotation)) {
                            currentCol++;
                        }
                        break;

                    // Rotate anti-clockwise
                    case KeyEvent.VK_Q:
                        if (!isPaused) {
                            rotatePiece((currentRotation == 0) ? 3 : currentRotation - 1);
                        }
                        break;

                    // Rotate clockwise
                    case KeyEvent.VK_E:
                        if (!isPaused) {
                            rotatePiece((currentRotation == 3) ? 0 : currentRotation + 1);
                        }
                        break;

                    // Pause or unpause game
                    case KeyEvent.VK_ESCAPE:
                        if (!isGameOver && !isNewGame) {
                            isPaused = !isPaused;
                            logicTimer.setPaused(isPaused);
                        }
                        break;

                    // Start game
                    case KeyEvent.VK_ENTER:
                        if (isGameOver || isNewGame) {
                            resetGame();
                        }
                        break;

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    // When drop is released, set the timer back to initial gameSpeed
                        logicTimer.setCyclesPerSec(gameSpeed);
                        logicTimer.reset();
                }

            }
        });

        this.pack();
        this.setLocationRelativeTo(null); //center of screen
        this.setVisible(true);
    }

    /**
     * Starts the game
     */
    private void startGame() {
        // Initialization
        this.random = new Random();
        this.isNewGame = true;
        this.gameSpeed = 1.0f;

        // Setup timer
        this.logicTimer = new Clock(gameSpeed);
        logicTimer.setPaused(true);

        while(true) {
            //Get the time that the frame started
            long start = System.nanoTime();

            //Update the logic timer
            logicTimer.update();

            // If a cycle has elapsed on the timer, we can update the game and move our current piece down
            if(logicTimer.hasElapsedCycle()) {
                updateGame();
            }

            //Decrement the drop cool down
            if(dropCooldown > 0) {
                dropCooldown--;
            }

            // Display the window to the user
            renderGame();

            // Cap frame rate
            long delta = (System.nanoTime() - start) / 1000000L;
            if(delta < FRAME_TIME) {
                try {
                    Thread.sleep(FRAME_TIME - delta);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Updates the game
     */
    private void updateGame() {
        // Check to see if the piece's position can move down to the next row.
        if(game.isValidAndEmpty(currentPiece, currentCol, currentRow + 1, currentRotation)) {
            //Increment the current row if it's safe to do so.
            currentRow++;
        }
        else {
            /*
             * We've either reached the bottom of the board, or landed on another piece, so
             * we need to add the piece to the board.
             */
            game.addPiece(currentPiece, currentCol, currentRow, currentRotation);

            /*
             * Check to see if adding the new piece resulted in any cleared lines. If so,
             * increase the player's score. (Up to 4 lines can be cleared in a single go;
             * [1 = 100pts, 2 = 200pts, 3 = 400pts, 4 = 800pts]).
             */
            int cleared = game.checkLines();
            if(cleared > 0) {
                score += 50 << cleared;
            }

            // As the game goes on, the speed of the game will increase
            gameSpeed += 0.035f;
            logicTimer.setCyclesPerSec(gameSpeed);
            logicTimer.reset();


            // Set the drop cooldown so the next piece doesn't automatically right after the user placed a piece
            dropCooldown = 25;

            /*
             * Update the difficulty level. This has no effect on the game, and is only
             * used in the "Level" string in the SidePanel.
             */
            level = (int)(gameSpeed * 1.70f);

            /*
             * Spawn a new piece to control.
             */
            spawnPiece();
        }
    }


    // Repaints GamePanel and SidePanel
    private void renderGame() {
        game.repaint();
        side.repaint();
    }


     // Resets the game
    private void resetGame() {
        this.level = 1;
        this.score = 0;
        this.gameSpeed = 1.0f;
        this.nextPiece = Piece.values()[random.nextInt(TYPE_COUNT)];
        this.isNewGame = false;
        this.isGameOver = false;
        game.clear();
        logicTimer.reset();
        logicTimer.setCyclesPerSec(gameSpeed);
        spawnPiece();
    }

    //Spawns a new piece
    private void spawnPiece() {
        this.currentPiece = nextPiece;
        this.currentCol = currentPiece.getSpawnColumn();
        this.currentRow = currentPiece.getSpawnRow();
        this.currentRotation = 0;
        this.nextPiece =  Piece.values()[random.nextInt(TYPE_COUNT)];

        // If the player has lost, end game and pause it
        if(!game.isValidAndEmpty(currentPiece, currentCol, currentRow, currentRotation)) {
            this.isGameOver = true;
            logicTimer.setPaused(true);
        }
    }

    /**
     * Rotates a piece
     * @param newRotation The rotation of the new piece.
     */
    private void rotatePiece(int newRotation) {
        /*
         * Sometimes pieces will need to be moved when rotated to avoid clipping
         * out of the board (the I piece is a good example of this). Here we store
         * a temporary row and column in case we need to move the tile as well.
         */
        int newColumn = currentCol;
        int newRow = currentRow;

        int left = currentPiece.getLeftEmpty(newRotation);
        int right = currentPiece.getRightEmpty(newRotation);
        int top = currentPiece.getAboveEmpty(newRotation);
        int bottom = currentPiece.getBelowEmpty(newRotation);

        // If the current piece is too far to the left or right, move the piece away from the edges
        if(currentCol < -left) {
            newColumn -= currentCol - left;
        }
        else if(currentCol + currentPiece.getDimension() - right >= GamePanel.COLS) {
            newColumn -= (currentCol + currentPiece.getDimension() - right) - GamePanel.COLS + 1;
        }

        // If the current piece is too far to the top or bottom, move the piece away from the edges
        if(currentRow < -top) {
            newRow -= currentRow - top;
        } else if(currentRow + currentPiece.getDimension() - bottom >= GamePanel.ROWS) {
            newRow -= (currentRow + currentPiece.getDimension() - bottom) - GamePanel.ROWS + 1;
        }

        // Check to see if the new position is acceptable valid
        if(game.isValidAndEmpty(currentPiece, newColumn, newRow, newRotation)) {
            currentRotation = newRotation;
            currentRow = newRow;
            currentCol = newColumn;
        }
    }

    /**
     * Checks to see if game is paused or not
     * @return If game is paused or not
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Checks to see if game is over or not
     * @return If game is over or not
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * Checks to see if player has started a new game or not
     * @return If new game started or not
     */
    public boolean isNewGame() {
        return isNewGame;
    }

    /**
     * Gets the current score
     * @return The score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the current level
     * @return The level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the current piece type
     * @return The piece type
     */
    public Piece getPieceType() {
        return currentPiece;
    }

    /**
     * Gets the next piece type
     * @return The next piece
     */
    public Piece getNextPieceType() {
        return nextPiece;
    }

    /**
     * Gets the column of the current piece
     * @return The column
     */
    public int getPieceCol() {
        return currentCol;
    }

    /**
     * Gets the row of the current piece
     * @return The row
     */
    public int getPieceRow() {
        return currentRow;
    }

    /**
     * Gets the rotation of the current piece
     * @return The rotation
     */
    public int getPieceRotation() {
        return currentRotation;
    }

    /**
     * Starts game :D
     * @param args Unused
     */
    public static void main(String[] args) {
        Tetris tetris = new Tetris();
        tetris.startGame();
    }
}