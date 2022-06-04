/**
 *
 * Tester class for executing the whole program
 */


import javax.swing.*;

public class Launcher extends Sort {

    public static void main (String[] args) {
        JFrame frame = new JFrame ("Sorting Simulator");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Sort());
        frame.pack();
        frame.setVisible (true);
        frame.setLocationRelativeTo(null);
    }
}
