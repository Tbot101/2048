package _2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel {
    private _2048 game2048;
    private JLabel status; // current status text

    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 500;

    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        game2048 = new _2048(); // initializes model for the game
        status = statusInit; // initializes the status JLabel

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ( e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP )
                {
                    game2048.up();
                    //                    gameBoard = game2048.toString();
                    repaint();
                }
                else if ( e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN )
                {
                    game2048.down();
                    //                    gameBoard = game2048.toString();
                    repaint();
                }
                else if ( e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT )
                {
                    game2048.left();
                    //                    gameBoard = game2048.toString();
                    repaint();
                }
                else if ( e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT )
                {
                    game2048.right();
                    //                    gameBoard = game2048.toString();
                    repaint();
                }
                else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE )
                {
                    if(game2048.gameStateLength() != 0){
                        game2048.previousMove();
                        //                    gameBoard = game2048.toString();
                        repaint();
                    }
                    return;
                }
                else if ( e.getKeyCode() == KeyEvent.VK_ENTER )
                {
                    game2048 = new _2048();
                    repaint();
                }
            }
        });
    }


    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        game2048.reset();
        status.setText("Player's Turn");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        if (game2048.getGameStatus()) {
            status.setText("Running");
        } else {
            status.setText("Game Over");
        }

        int winner = game2048.getHighTile();
        if (winner == 2048) {
            status.setText("Player wins");
        }
    }

    /**
     * Draws the game board.
     *
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(100, 0, 100, 400);
        g.drawLine(200, 0, 200, 400);
        g.drawLine(300, 0, 300, 400);
        g.drawLine(0, 100, 400, 100);
        g.drawLine(0, 200, 400, 200);
        g.drawLine(0, 300, 400, 300);
        g.drawLine(0, 400, 400, 400);

        if (game2048.getGameStatus()) {

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    drawTile(g,game2048.getBoard()[i][j],i * 100,j * 100 );
                }
            }
        } else {
            g.setColor(new Color(0xFFEBCD));
            g.fillRoundRect(10, 10, 10, 10, 2, 2);

            g.setColor(new Color(0xBBADA0).darker());
            g.setFont(new Font("SansSerif", Font.BOLD, 10));
            g.drawString("2048", 10, 10);

            g.setFont(new Font("SansSerif", Font.BOLD, 20));

            /*if (gamestate == State.won) {
                g.drawString("you made it!", 390, 350);

            } else if (gamestate == State.over)
                g.drawString("game over", 400, 350);*/


        }
        int score = game2048.getScore();
        int highestTile = game2048.getHighTile();

        g.setColor(new Color(0xBBADA0));
        g.drawString("Score: " + score, 10, 450);
        g.drawString("Highest Tile: " + highestTile, 10, 475);
    }

    public void drawTile(Graphics g, Tile tile, int x, int y){
        int tileValue = tile.getValue();
        int length = String.valueOf( tileValue ).length();
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor( Color.lightGray );
        g2.fillRoundRect( x, y, 98, 98, 5, 5 );
        g2.setColor( Color.black );
        if ( tileValue > 0 )
        {
            g2.setColor( tile.getColor() );
            g2.fillRoundRect( x, y, 98, 98, 5, 5 );
            g2.setColor( Color.black );
            g2.setFont(new Font("SansSerif", Font.BOLD, 25));
            g2.drawString( "" + tileValue, x + 45 - 3 * length, y + 55 );
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }

}
