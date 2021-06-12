import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel implements KeyEventDispatcher, MouseListener {
    public World world;
    long time;
    BufferedImage pauseImage;
    BufferedImage winMenu;
    BufferedImage loseMenu;
    BufferedImage pauseMenu;
    BufferedImage mainMenu;
    boolean mousepressed = false;
    int mouseX = 0;
    int mouseY = 0;



    public Panel() throws IOException {
        world = new World();
        this.pauseImage= ImageIO.read(new File("imgs\\pause4.png"));
        this.winMenu= ImageIO.read(new File("imgs\\winMenu.png"));
        this.loseMenu= ImageIO.read(new File("imgs\\loseMenu.png"));
        this.pauseMenu= ImageIO.read(new File("imgs\\pauseMenu.png"));
        this.mainMenu= ImageIO.read(new File("imgs\\mainMenu.png"));
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
        addMouseListener(this);
        time = System.currentTimeMillis();
    }


    @Override
    public void paintComponent(Graphics g) {
        boolean shouldPlay = false;
        shouldPlay = mousepressed && (mouseX>442*1.5 && mouseX<582*1.5) && (mouseY>209*1.5 && mouseY<250*1.5);
        boolean shouldPause = false;
        shouldPause = mousepressed && (mouseX>Main.width/2 && mouseX<Main.width/2 + 50) && (mouseY>Main.height/20 && mouseY<Main.height/20+50);
            long time1 = System.currentTimeMillis();
            float dt = (time1 - time);
            world.update(dt / 1000);
            world.moveWalls();
            time = time1;
            world.background.draw(g);
            world.sphere.draw(g);
            g.drawImage(pauseImage, Main.width/2, Main.height/20, 50, 50, null);
            for (int i = 1; i < world.wallCount - 1; i++) {
                world.walls[i].draw(g);
            }
            Booster toRemove = null;
            for (Booster booster : world.boosterList) {
                if (booster.update(world.sphere)) {
                    toRemove = booster;
                } else {
                    booster.draw(g);
                }
            }
            if (toRemove != null) {
                world.boosterList.remove(toRemove);
            }

            if (world.sphere.pos.y > Main.height && world.sphere.pos.x <= world.sphere.xPos1) {
                g.drawImage(loseMenu, 0, 0, Main.width, Main.height, null);
            }

            if (world.sphere.pos.y > Main.height && world.sphere.pos.x >= world.sphere.xPos2) {
                g.drawImage(winMenu, 0, 0, Main.width, Main.height, null);
            }
            System.out.println(mousepressed);

    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            if (e.getKeyChar() == 'w' || e.getKeyChar() == 'ц') {
                world.sphere.w += 0.5;
            }
            if (e.getKeyChar() == 's' || e.getKeyChar() == 'ы') {
                world.sphere.w -= 0.5;
            }
            if (e.getKeyChar() == ' ') {
                System.out.println(e.getKeyChar());
                world.sphere.v.y += -300;
            }
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousepressed = true;
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousepressed = false;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
