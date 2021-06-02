import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Panel extends JPanel implements MouseListener, KeyEventDispatcher {
    public World world;
    long time;


    public Panel() throws IOException {
        world = new World();
        this.addMouseListener(this);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
        time = System.currentTimeMillis();
    }



    @Override
    public void paintComponent(Graphics g) {
        long time1 = System.currentTimeMillis();
        float dt =  (time1-time) ;
        world.update(dt/1000);
        //world.background.update();
        world.moveWalls();
        time = time1;
        world.background.draw(g);
        world.sphere.draw(g);
        for (int i = 1; i < world.wallCount-1; i++) {
            world.walls[i].draw(g);
        }
        Booster toRemove = null;
        for (Booster booster: world.boosterList) {
            if (booster.update(world.sphere)){
                toRemove = booster;
            } else{
                booster.draw(g);
            }
        }
        if (toRemove != null) {
           world.boosterList.remove(toRemove);
        }

    }


    ;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e1) {

        }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            if (e.getKeyChar() == 'w') {
                //System.out.println(e.getKeyChar());
                world.sphere.w += 0.01;
            }
            if (e.getKeyChar() == 's') {
                world.sphere.w -= 0.01;
            }
            if (e.getKeyChar() == ' ') {
                System.out.println(e.getKeyChar());
                world.sphere.v.y += -200;
            }
            if (e.getKeyChar() == 'q') {

            }


        }
        return false;
    }
}
