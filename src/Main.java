import javax.swing.*;

import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static int width = 2000;
    public static int height = 1500;
    public static void main(String[] args) throws InterruptedException, IOException {
        JFrame frame = new JFrame();
        frame.setTitle("xxx");
        frame.setSize(width,height);
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
