import java.awt.*;

public class Booster {
    Vector2 pos;
    double r = 50;
    Color color = new Color(0xF60707);
    public Booster(Vector2 pos){
        this.pos = pos;
    }
    public void boost(Sphere sphere){
    }
    public void draw(Graphics g){
        g.setColor(color);
        g.drawOval((int) (pos.x-r), (int)(pos.y-r), (int) (2*r), (int) (2*r));
        g.fillOval((int) (pos.x-r), (int)(pos.y-r), (int) (2*r), (int) (2*r));
    }
    public boolean update(Sphere sphere){
        if (Vector2.segmentVector(sphere.pos, pos).len()<= sphere.r+r){
            boost(sphere);
            return true;
        }
        return false;
    }
}
