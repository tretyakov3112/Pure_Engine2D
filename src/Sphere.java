import java.awt.*;
import lombok.Getter;
import lombok.Setter;


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
    double phi;
    double xPos = 500;

    public  Sphere(){
        r = 50;
        pos = new Vector2(xPos, 200);
        orientationVector = new Vector2(1,0);
        v = new Vector2(0,0);
        a = new Vector2(0,0);
        w = 0.01;
        m = 100;
        J = 10;
        phi = 0;
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

    /*public boolean checkCollision(Wall wall){
        Vector2 tmp = new Vector2(wall.pos2.x-wall.pos1.x, wall.pos2.y-wall.pos1.y);

        double d = (Math.abs(pos.x/tmp.x-pos.y/tmp.y-(wall.pos1.x/tmp.x-wall.pos1.y/tmp.y)))/Math.sqrt(1/tmp.x*tmp.x+1/tmp.y*tmp.y);
        if ( (pos.x > wall.pos1.x+tmp.ort().x) && (pos.x < wall.pos2.x+tmp.ort().x) && d<r) {
            return true;} else {
            return false;}
        }*/
    public Vector2 checkCollision(Wall wall){
        Vector2 wallVector = Vector2.segmentVector(wall.pos1, wall.pos2);
        Vector2 sphereToWallStartVector = Vector2.segmentVector(wall.pos1, pos);

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
        orientationVector.rotate(phi);

    }

    public void draw(Graphics g){
        g.setColor(new Color(231, 128, 128));
        g.drawOval((int) (pos.x-r), (int)(pos.y-r), (int) (2*r), (int) (2*r));
        g.fillOval((int) (pos.x-r), (int)(pos.y-r), (int) (2*r), (int) (2*r));
        g.setColor(new Color(255, 4, 4));
        g.drawLine((int) (pos.x), (int) (pos.y), (int) (pos.x + r*orientationVector.x), (int) (pos.y + r*orientationVector.y));
    }

}
