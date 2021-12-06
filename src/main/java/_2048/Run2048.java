package _2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Run2048 implements Runnable{
    public static final JFrame FRAME = new JFrame("2048");
    public static final GridBagConstraints GBC = new GridBagConstraints();

    @Override
    public void run() {
        FRAME.setLocation(300, 300);
        FRAME.setLayout(new GridBagLayout());
        FRAME.setResizable(true);
        FRAME.setSize(800,800);
        FRAME.setVisible(true);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setBackground(Color.WHITE);

        // Take user to home screen
        screenHome();
    }

    private void screenHome() {
        /*
        final Lightbulb bulb = new LightBulb()'
        JButton button = new JButton("on/Off");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
           });
        */
        JOptionPane.showMessageDialog(FRAME,"Hello","Computer",JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Run 2048
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Run2048());
    }
}
