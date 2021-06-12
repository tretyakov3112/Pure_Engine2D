import javax.swing.*;
import java.io.IOException;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static int width = (int) (1024*1.5);
    public static int height = (int) (576*1.5);
    public static void main(String[] args) throws InterruptedException, IOException {
        JFrame frame = new JFrame();
        frame.setTitle("xxx");
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Panel panel = new Panel();
        frame.add(panel);
        frame.setVisible(true);


        while (true) {
            Vector2 intersection = null;

            for (int i = 1; i < panel.world.wallCount - 1; i++) {
                intersection = panel.world.sphere.checkCollision(panel.world.walls[i]);
                if (intersection != null) {
                    new Thread(() -> {
                        new MakeSound().playSound("sounds\\collision.wav");
                    }).start();
                }
            }

            if (panel.world.sphere.pos.y > Main.height && panel.world.sphere.pos.x <= panel.world.sphere.xPos1){
                new Thread(() -> {
                    new MakeSound().playSound("sounds\\dimon.wav");
                }).start();
                break;
            }

            if (panel.world.sphere.pos.y > Main.height && panel.world.sphere.pos.x >= panel.world.sphere.xPos2){
                new Thread(() -> {
                    new MakeSound().playSound("sounds\\win.wav");
                }).start();
                break;
            }
            frame.repaint();
            Thread.sleep(10);
        }
    }
}

