import java.awt.*;
import java.awt.geom.AffineTransform;
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

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform backup = g2d.getTransform();
        AffineTransform tx = AffineTransform.getRotateInstance(phi, pos.x, pos.y);
        g2d.setTransform(tx);

        g2d.drawImage(ballImage, (int) (pos.x-r-1), (int) (pos.y-r-1),
                (int) (2*r + 2),
                (int) (2*r + 2), null);

        g2d.setTransform(backup);
    }

}
