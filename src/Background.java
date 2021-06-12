import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background {

    public double speed = 1;
    private static double XMove;
    private static double XMove1 = Main.width;
    private static double XMove2 = -Main.width;
    BufferedImage backImage;

    public Background() throws IOException {
        this.backImage= ImageIO.read(new File("imgs\\Background.png"));
    }

    public void draw(Graphics g) {
        g.drawImage(backImage,(int) XMove,0, Main.width, Main.height,null);
        g.drawImage(backImage,(int) XMove1,0,Main.width, Main.height,null);
        g.drawImage(backImage,(int) XMove2,0,Main.width, Main.height,null);

    }
    public void update(double dx) {
        XMove-=-speed*dx;
        XMove1-=-speed*dx;
        XMove2-=-speed*dx;

        if (XMove2>0 || XMove1<0){
            XMove = 0;
            XMove1 = Main.width;
            XMove2 = -(Main.width);
        }
    }
}
