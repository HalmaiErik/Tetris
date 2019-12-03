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

    // BoardPanel instance
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
    private Piece nextType;

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
     * and adds a controller listener.
     */
    private Tetris() {
        /*
         * Set the basic properties of the window.
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

                switch(e.getKeyCode()) {

                    // When drop is pressed, change timer to 25 cycles per second
                    case KeyEvent.VK_S:
                        if(!isPaused && dropCooldown == 0) {
                            logicTimer.setCyclesPerSec(25.0f);
                        }
                        break;

                    // Move left
                    case KeyEvent.VK_A:
                        if(!isPaused && game.isValidAndEmpty(currentPiece, currentCol - 1, currentRow, currentRotation)) {
                            currentCol--;
                        }
                        break;

                    // Move Right
                    case KeyEvent.VK_D:
                        if(!isPaused && game.isValidAndEmpty(currentPiece, currentCol + 1, currentRow, currentRotation)) {
                            currentCol++;
                        }
                        break;

                    // Rotate anti-clockwise
                    case KeyEvent.VK_Q:
                        if(!isPaused) {
                            rotatePiece((currentRotation == 0) ? 3 : currentRotation - 1);
                        }
                        break;

                    // Rotate clockwise
                    case KeyEvent.VK_E:
                        if(!isPaused) {
                            rotatePiece((currentRotation == 3) ? 0 : currentRotation + 1);
                        }
                        break;

                    // Pause or unpause game
                    case KeyEvent.VK_ESCAPE:
                        if(!isGameOver && !isNewGame) {
                            isPaused = !isPaused;
                            logicTimer.setPaused(isPaused);
                        }
                        break;

                    // Start game
                    case KeyEvent.VK_ENTER:
                        if(isGameOver || isNewGame) {
                            resetGame();
                        }
                        break;

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                switch(e.getKeyCode()) {

                    // When drop is released, set the timer back to initial gameSpeed
                    case KeyEvent.VK_S:
                        logicTimer.setCyclesPerSecond(gameSpeed);
                        logicTimer.reset();
                        break;
                }

            }
        });
        
}
