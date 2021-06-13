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
    BufferedImage zakat;
    boolean mousepressed = false;
    int mouseX = 0;
    int mouseY = 0;
    long time1 = System.currentTimeMillis();
    private Font f1 = new Font("TimesRoman", Font.BOLD, 20),
            f2 = new Font("Courier", Font.ITALIC, 30),
            f3 = new Font("Arial", Font.BOLD + Font.ITALIC, 16);



    public Panel() throws IOException {
        world = new World();
        this.pauseImage= ImageIO.read(new File("imgs\\pause4.png"));
        this.winMenu= ImageIO.read(new File("imgs\\winMenu.png"));
        this.loseMenu= ImageIO.read(new File("imgs\\loseMenu.png"));
        this.pauseMenu= ImageIO.read(new File("imgs\\pauseMenu.png"));
        this.mainMenu= ImageIO.read(new File("imgs\\mainMenu.png"));
        this.zakat= ImageIO.read(new File("imgs\\zakat.png"));
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
        addMouseListener(this);
        time = System.currentTimeMillis();
    }


    @Override
    public void paintComponent(Graphics g) {
        //System.out.println((time-time1)/1000);
        long tmp = (time-time1)/1000;
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

        g.setColor(new Color(0));

        g.setFont(f2);
        g.drawString("TIME: "+tmp, 50,50);
        if (world.sphere.pos.y > Main.height && world.sphere.pos.x <= world.sphere.xPos1) {
            g.drawImage(zakat, 0, 0, Main.width, Main.height, null);
            g.setFont(f2);
            g.setColor(new Color(0));
            g.drawString("YOU LOSE!", Main.width/2, (int) (Main.height*0.2));
            g.drawString("PLAY AGAIN", Main.width/2, (int) (Main.height*0.5));
        }
        System.out.println(f1.getSize());
        if (world.sphere.pos.y > Main.height && world.sphere.pos.x >= world.sphere.xPos2) {
            g.drawImage(zakat, 0, 0, Main.width, Main.height, null);
            g.setFont(f2);
            g.setColor(new Color(0));

            g.drawString("YOU WIN!", Main.width/2, (int) (Main.height*0.2));
            g.drawString("YOUR RECORD: "+tmp, Main.width/2, (int) (Main.height*0.35));
            g.drawString("PLAY AGAIN", Main.width/2, (int) (Main.height*0.5));
        }
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            if (e.getKeyChar() == 'w' || e.getKeyChar() == 'ц' || e.getKeyChar() == 'W' || e.getKeyChar() == 'Ц') {
                world.sphere.w += 0.5;
            }
            if (e.getKeyChar() == 's' || e.getKeyChar() == 'ы' || e.getKeyChar() == 'S' || e.getKeyChar() == 'Ы') {
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
