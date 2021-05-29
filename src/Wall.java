import java.awt.*;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Wall {
    Point2 pos1;
    Point2 pos2;
    double k;
    Color color = new Color(6, 245, 6);
    public Wall(Point2 pos1, Point2 pos2, double k){
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.k = k;
    }
    public void draw(Graphics g){
        g.setColor(color);
        g.drawLine(pos1.x, pos1.y, pos2.x, pos2.y);
    }
}
