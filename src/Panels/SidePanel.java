package Panels;

import javax.swing.*;
import java.awt.*;

/**
 * Displays general information about the current game, such as score, next piece preview, controls
 */
// TODO: 25-Nov-19 check if I wrote everything in class desc.

public class SidePanel extends JPanel {
    //small font properties
    private static final Font TEXT_SMALL_FONT = new Font("Tahoma", Font.BOLD, 11);
    //large font properties
    private static final Font TEXT_LARGE_FONT = new Font("Tahoma", Font.BOLD, 13);
    //offset between each string (number of pixels)
    private static final int TEXT_OFFSET = 25;
    //draw color: text and drawing colors
    private static final Color DRAW_COLOR = new Color(128, 192, 128); //greenish

    //dimension of each tile on the next piece preview
    private static final int PREVIEW_TILE_SIZE = BoardPanel.TILE_SIZE >> 1;
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
        setPreferredSize(new Dimension(200, BoardPanel.PANEL_HEIGHT));
        setBackground(Color.BLACK);
    }



}
