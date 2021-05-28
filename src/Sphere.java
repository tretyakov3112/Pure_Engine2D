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

    public  Sphere(){
        r = 50;
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

    public boolean checkCollision(Wall wall){
        Vector2 tmp = new Vector2(wall.pos2.x-wall.pos1.x, wall.pos2.y-wall.pos1.y);
        double d = (Math.abs(pos.x/(wall.pos2.x-wall.pos1.x)-pos.y/(wall.pos2.y-wall.pos1.y)-(wall.pos1.x/(wall.pos2.x-wall.pos1.x)-wall.pos1.y/(wall.pos2.y-wall.pos1.y))))/Math.sqrt(1/(wall.pos2.x-wall.pos1.x)*(wall.pos2.x-wall.pos1.x)+1/(wall.pos2.y-wall.pos1.y)*(wall.pos2.y-wall.pos1.y));
        if ( (pos.x >wall.pos1.x+tmp.ort().x) && (pos.x <wall.pos2.x+tmp.ort().x) && d<r) {
            return true;} else {
            return false;}
        }
    

    public void update(){
        orientationVector.rotate(phi);

    }

    public void draw(Graphics g){
        g.setColor(new Color(231, 128, 128));
        g.drawOval((int) (pos.x-r), (int)(pos.y-r), (int) (2*r), (int) (2*r));
        g.fillOval((int) (pos.x-r), (int)(pos.y-r), (int) (2*r), (int) (2*r));
        g.setColor(new Color(255, 4, 4));
        g.drawLine((int) (pos.x), (int) (pos.y), (int) (pos.x + orientationVector.x), (int) (pos.y + orientationVector.y));
    }

}
