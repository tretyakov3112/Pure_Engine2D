import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Booster {
    Vector2 pos;
    double r = 50;
    Color color = new Color(0xF60707);
    BufferedImage boosterImage;
    public Booster(Vector2 pos) throws IOException {
        this.pos = pos;
        //this.boosterImage = ImageIO.read(new File("imgs\\booster2.png"));
        this.boosterImage = ImageIO.read(new File("imgs\\booster2.png"));
    }
    public void boost(Sphere sphere){
    }
    public void draw(Graphics g){
        /*g.setColor(color);
        g.drawOval((int) (pos.x-r), (int)(pos.y-r), (int) (2*r), (int) (2*r));
        g.fillOval((int) (pos.x-r), (int)(pos.y-r), (int) (2*r), (int) (2*r));*/
        g.drawImage(boosterImage, (int) (pos.x-r), (int) (pos.y-r), (int)(2*r), (int) (2*r), null);
    }
    public boolean update(Sphere sphere){
        if (Vector2.segmentVector(sphere.pos, pos).len()<= sphere.r+r){
            boost(sphere);
            return true;
        }
        return false;
    }
}
