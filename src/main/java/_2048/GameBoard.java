package _2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GameBoard extends JPanel {
    private _2048 game2048;
    private JLabel status; // current status text
    _2048.State gameState = _2048.State.RUN;

    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 500;

    public GameBoard(JLabel statusInit) throws IOException {
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
                if ( e.getKeyCode() == KeyEvent.VK_LEFT )
                {
                    game2048.up(true);
                    gameState = game2048.getGameStatus();
                    updateStatus();
                    repaint();
                }
                else if ( e.getKeyCode() == KeyEvent.VK_RIGHT )
                {
                    game2048.down(true);
                    gameState = game2048.getGameStatus();
                    updateStatus();
                    repaint();
                }
                else if ( e.getKeyCode() == KeyEvent.VK_UP )
                {
                    game2048.left(true);
                    gameState = game2048.getGameStatus();
                    updateStatus();
                    repaint();
                }
                else if ( e.getKeyCode() == KeyEvent.VK_DOWN )
                {
                    game2048.right(true);
                    gameState = game2048.getGameStatus();
                    updateStatus();
                    repaint();
                }
                else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE )
                {
                    if(game2048.gameStateLength() != 0){
                        game2048.previousMove();
                        updateStatus();
                        repaint();
                    }
                    return;
                }
                else if ( e.getKeyCode() == KeyEvent.VK_ENTER )
                {
                    try {
                        game2048 = new _2048();
                        updateStatus();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    repaint();
                } else if ( e.getKeyChar() == 's' )
                {
                    game2048.saveGameFile();
                } else if ( e.getKeyChar() == 'r' )
                {
                    try {
                        game2048.loadGameFile();
                        updateStatus();
                        repaint();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }


    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() throws IOException {
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
        if (gameState == _2048.State.RUN) {
            status.setText("Running");
        } else if (gameState == _2048.State.WIN) {
            status.setText("You Win!");
        } else if (gameState == _2048.State.LOSE) {
            status.setText("Game Over");
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

        if (gameState == _2048.State.RUN || gameState == _2048.State.WIN || gameState == _2048.State.LOSE) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    drawTile(g,game2048.getBoard()[i][j],i * 100,j * 100 );
                }
            }
        }
        if (gameState != _2048.State.RUN) {

            if (gameState == _2048.State.WIN) {
                g.drawString("You Win!, ENTER to restart", 390, 350);

            } else if (gameState == _2048.State.LOSE)
                g.drawString("Game Over, ENTER to restart", 400, 350);


        }
        int score = game2048.getScore();
        int highestTile = game2048.getHighTile();

        g.setColor(new Color(0,0,0));
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 440);
        g.drawString("Highest Tile: " + highestTile, 10, 470);
        g.setFont(new Font("SansSerif", Font.BOLD, 12));
        g.drawString("S - save, R - reload, Enter - restart, Delete - Go back", 10,490);

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
