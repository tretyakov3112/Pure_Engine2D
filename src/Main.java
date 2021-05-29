import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        frame.setTitle("xxx");
        frame.setSize(1500,1500);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Panel panel = new Panel();
        frame.add(panel);
        frame.setVisible(true);

        while (true) {
            frame.repaint();
            Thread.sleep(10);
        }

    }
}
