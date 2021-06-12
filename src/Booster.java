import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Booster {
    Vector2 pos;
    double r = 50;
    BufferedImage boosterImage;

    public Booster(Vector2 pos) throws IOException {
        this.pos = pos;
        this.boosterImage = ImageIO.read(new File("imgs\\booster2.png"));
    }
    public void boost(Sphere sphere){
    }
    public void draw(Graphics g){
        g.drawImage(boosterImage, (int) (pos.x-r), (int) (pos.y-r), (int)(2*r), (int) (2*r), null);
    }
    public boolean update(Sphere sphere){
        if (Vector2.segmentVector(sphere.pos, pos).len()<= sphere.r+r){
            if (getClass() == Accelerator.class) {
                new Thread(() -> {
                    new MakeSound().playSound("sounds\\accelerate.wav");
                }).start();
            } else if (getClass() == Jumper.class) {
                new Thread(() -> {
                    new MakeSound().playSound("sounds\\jump.wav");
                }).start();
            } else if (getClass() == Stopper.class) {
                new Thread(() -> {
                    new MakeSound().playSound("sounds\\stop.wav");
                }).start();
            }
            boost(sphere);
            return true;
        }
        return false;
    }
}
