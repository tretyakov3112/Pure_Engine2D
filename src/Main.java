import javax.swing.*;

import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
//import static sun.jvm.hotspot.runtime.PerfMemory.start;

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
            Vector2 intersection = null;

            for (int i = 1; i < panel.world.wallCount - 1; i++) {
                intersection = panel.world.sphere.checkCollision(panel.world.walls[i]);
                if (intersection != null) {
                    new Thread(() -> {
                        new MakeSound().playSound("sounds\\collision.wav");
                        //System.out.println("audio file finished!");
                    }).start();
                }
            }

            /*for (Booster booster : panel.world.boosterList) {
                System.out.println(Vector2.segmentVector(panel.world.sphere.pos, booster.pos).len()<= panel.world.sphere.r + booster.r);
                if (Vector2.segmentVector(panel.world.sphere.pos, booster.pos).len() <= panel.world.sphere.r + booster.r) {
                    if (booster.getClass() == Accelerator.class) {
                        new Thread(() -> {
                            new MakeSound().playSound("sounds\\accelerate.wav");
                        }).start();
                    } else if (booster.getClass() == Jumper.class) {
                        new Thread(() -> {
                            new MakeSound().playSound("sounds\\jump.wav");
                        }).start();
                    } else if (booster.getClass() == Stopper.class) {
                        new Thread(() -> {
                            new MakeSound().playSound("sounds\\stop.wav");
                        }).start();
                    }
                }
            }*/

            if (panel.world.sphere.pos.y > Main.height){
                new Thread(() -> {
                    new MakeSound().playSound("sounds\\dimon.wav");
                }).start();
                break;
            }
            frame.repaint();
            Thread.sleep(10);
        }
    }
}

