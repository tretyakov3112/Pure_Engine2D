import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;


@Getter @Setter
public class Sphere {
    Vector2 pos;
    Vector2 v;
    Vector2 a;
    Vector2 orientationVector;
    double r;
    double w;
    double m;
    double J;
    double phi;//угол orientationVector с горизонталью
    double xPos2 = Main.width*3/4;
    double xPos1 = Main.width/4;
    BufferedImage ballImage;

    public  Sphere() throws IOException {
        r = 50;
        pos = new Vector2(Main.width/2, Main.height/4);
        orientationVector = new Vector2(1,0);
        v = new Vector2(0,0);
        a = new Vector2(0,0);
        w = 0.0;
        m = 100;
        J = 0.5*m*r*r;
        phi = 0;
        this.ballImage = ImageIO.read(new File("imgs\\ball4.png"));
    }
    public Sphere(Vector2 pos, Vector2 v, Vector2 a, Vector2 orientationVector, double r, double w, double m, double J, double phi){
        this.pos = pos;
        this.v = v;
        this.a = a;
        this.orientationVector = orientationVector;
        this.r = r;
        this.w = w;
        this.m = m;
        this.J = J;
        this.phi = phi;
    }

    public static BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    public BufferedImage rotateImage(BufferedImage buffImage, double angle) {
        double radian = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(radian));
        double cos = Math.abs(Math.cos(radian));
        int width = buffImage.getWidth();
        int height = buffImage.getHeight();
        int nWidth = (int) Math.floor((double) width * cos + (double) height * sin);
        int nHeight = (int) Math.floor((double) height * cos + (double) width * sin);
        BufferedImage rotatedImage = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = rotatedImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, nWidth, nHeight);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics.translate((nWidth - width) / 2, (nHeight - height) / 2);
        graphics.rotate(radian, (double) (width / 2), (double) (height / 2));
        graphics.drawImage(buffImage, 0, 0,null);
        graphics.dispose();
        return rotatedImage;
    }

    public Vector2 checkCollision(Wall wall){

            Vector2 wallVector = Vector2.segmentVector(wall.pos1, wall.pos2);
            Vector2 sphereToWallStartVector = Vector2.segmentVector(wall.pos1, pos);
            Vector2 sphereToWallStartVector2 = Vector2.segmentVector(wall.pos2, pos);
            if (sphereToWallStartVector.len()<=r || sphereToWallStartVector2.len()<=r){
                return sphereToWallStartVector;
            }

            double t = Vector2.DotProduct(wallVector, sphereToWallStartVector) / Vector2.DotProduct(wallVector, wallVector);
            if (t < 0 || t > 1) {
                return null;
            }

            Vector2 intersectionVector = wallVector.mult(t);
            intersectionVector.plus(wall.pos1);

            Vector2 projectionFromSphere = Vector2.segmentVector(pos, intersectionVector);
            if (projectionFromSphere.len() <= r) {
                return intersectionVector;
            } else {
                return null;
            }

    }

    public void update(){
        orientationVector.rotate(phi/1000);
        ballImage = rotate(ballImage, phi/1000);
    }

    public void draw(Graphics g){
        /*g.setColor(new Color(231, 128, 128));
        g.drawOval((int) (pos.x-r), (int)(pos.y-r), (int) (2*r), (int) (2*r));
        g.fillOval((int) (pos.x-r), (int)(pos.y-r), (int) (2*r), (int) (2*r));
        g.setColor(new Color(255, 4, 4));
        g.drawLine((int) (pos.x), (int) (pos.y), (int) (pos.x + r*orientationVector.x), (int) (pos.y + r*orientationVector.y));*/
        g.drawImage(ballImage, (int) (pos.x-r), (int) (pos.y-r), (int)(2*r), (int) (2*r), null);
    }

}
