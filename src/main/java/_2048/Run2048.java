package _2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Take user to home screen
        screenHome();
    }

    private void screenHome() {
        GBC.insets = new Insets(3, 3, 3, 3);
        GBC.ipady = 10;

        JLabel title = new JLabel("2048");
        title.setFont(new Font(Font.DIALOG,  Font.BOLD, 25));
        GBC.gridx = 0;
        GBC.gridy = -5;
        GBC.gridwidth = 2;
        FRAME.add(title, GBC);


        JButton instructions = new JButton("Instructions");
        instructions.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        instructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadInstructions();
            }
        });
        GBC.gridx = 0;
        GBC.gridy = 5;
        FRAME.add(instructions, GBC);

        JButton highScore = new JButton("High Scores");
        highScore.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        highScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadHighScore();
            }
        });
        GBC.gridx = 0;
        GBC.gridy = 8;
        FRAME.add(highScore, GBC);

    }

    private void loadHighScore() {
        JOptionPane.showMessageDialog(FRAME,"High Scores","Instructions",JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadInstructions() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(246, 163, 109));
        panel.setSize(new Dimension(size, 300));
        panel.setLayout(null);

        String instructions = "Welcome to 2048. \n Simply use the arrow keys on the key board to shift the tiles \n" +
                "one side to the other. If the tiles are the same number they will add. The goal of \n" +
                "the game is to get the 2048 tile. You will lose if it is not possible to move any tiles.";

        JTextArea label = new JTextArea(instructions);
        label.setBounds(0, 0, 600, 200);
        label.setFont(new Font(Font.DIALOG, Font.BOLD, 11));
//        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        UIManager.put("OptionPane.minimumSize",new Dimension(600, 200));
        JOptionPane.showMessageDialog(null, panel, "Customized Message Dialog",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Run 2048
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Run2048());
    }
}
