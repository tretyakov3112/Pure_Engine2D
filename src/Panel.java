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


    public Panel() {
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
        world.update(dt);
        //System.out.println(dt/1000);
        time = time1;

        world.sphere.draw(g);
        world.testWall.draw(g);
//        for (int i = 0; i < 99; i++) {
//            world.walls[i].draw(g);
//        }


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
        }
        return false;
    }
}
