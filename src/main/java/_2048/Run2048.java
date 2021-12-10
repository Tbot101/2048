package _2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Run2048 implements Runnable{
    public static final JFrame FRAME = new JFrame("2048");
    public static final GridBagConstraints GBC = new GridBagConstraints();
    private int size = 800;

    @Override
    public void run() {
        FRAME.setLocation(300, 300);
        FRAME.setLayout(new GridBagLayout());
        FRAME.setSize(size,size);
        FRAME.setPreferredSize(new Dimension(800,800));
        FRAME.setVisible(true);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setBackground(Color.WHITE);

        screenHome();
    }

    private void screenHome() {
        FRAME.getContentPane().removeAll();
        GBC.insets = new Insets(3, 3, 3, 3);
        GBC.ipady = 10;

        JLabel title = new JLabel("2048");
        title.setFont(new Font(Font.DIALOG,  Font.BOLD, 25));
        GBC.gridx = 0;
        GBC.gridy = -5;
        GBC.gridwidth = 2;
        FRAME.add(title, GBC);

        JButton beginGame = new JButton("Begin Game");
        beginGame.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        beginGame.addActionListener(e -> {
            try {
                beginGame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        GBC.gridx = 0;
        GBC.gridy = 5;
        FRAME.add(beginGame, GBC);


        JButton instructions = new JButton("Instructions");
        instructions.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        instructions.addActionListener(e -> loadInstructions());
        GBC.gridx = 0;
        GBC.gridy = 7;
        FRAME.add(instructions, GBC);
    }

    private void beginGame() throws IOException {
        FRAME.getContentPane().removeAll();

        // Status panel
        JPanel status_panel = new JPanel();
        FRAME.add(status_panel, GBC);
        JLabel status = new JLabel("Setting up...");
        status_panel.add(status);

        GameBoard board = new GameBoard(status);
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.gridwidth = 6;
        FRAME.add(board, GBC);

        // Reset button
        /*JPanel control_panel = new JPanel();
        GBC.gridx = 5;
        GBC.gridy = -5;
        FRAME.add(control_panel, GBC);


        JButton reset = new JButton("Reset");
        reset.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.reset();
            }
        });
        control_panel.add(reset);*/

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        backButton.addActionListener(e -> {
            FRAME.getContentPane().removeAll();
            screenHome();
        });
        GBC.gridx = 8;
        GBC.gridy = -8;
        status_panel.add(backButton, GBC);


        // Put the frame on the screen
        FRAME.pack();
        FRAME.validate();
        FRAME.repaint();
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setVisible(true);

        // Start the game
        board.reset();
    }

    private void loadInstructions() {
        JPanel panel = new JPanel();

        String html = "<html><div style=\"font-size: 14; text-align: justify\">" +
                "2048 is a sensational and addictive game, prepared to be hooked! If you haven't <br>" +
                "played before, don't worry. It's really easy. The goal of the game is to reach <br>" +
                "the 2048 tile. <br><br>" +
                "Use the arrow keys to move the tiles up, down, left or right or WSAD. If the tiles are <br>" +
                "the same they will add together and double in value. At the end of each turn <br>" +
                "the board will shift and a new tile will be added! Make sure you clear tiles <br>" +
                "otherwise the board will be full of tiles and you will lose! Try and get a tile <br>" +
                "to reach 2048 by adding tiles of the same value. There will be a popup when the <br>" +
                "game is over. <br><br>" +
                "You can click the delete button if you think you made a mistake and want to <br>" +
                "change your move. If you want to challenge yourself, don't use this button! <br>" +
                "Check the homepage to find users who have reached the 2048 tile! <br><br>" +
                "Have fun playing!";

        JLabel textArea = new JLabel(html, JLabel.CENTER);
        panel.add(textArea);

        UIManager.put("OptionPane.minimumSize",new Dimension(600, 200));
        JOptionPane.showMessageDialog(null, panel, "Instructions",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Run 2048
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Run2048());
    }
}
