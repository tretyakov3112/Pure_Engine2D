import java.awt.*;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Wall {
    Vector2 pos1;
    Vector2 pos2;
    double k;
    int[] xPoint = new int[4];
    int[] yPoint = new int[4];
    Color color1 = new Color(6, 245, 6);
    Color color2 = new Color(0xB15D00);
    public Wall(Vector2 pos1, Vector2 pos2, double k){
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.k = k;
    }
    public void draw(Graphics g){
        xPoint[0] = (int) pos1.x;
        yPoint[0] = (int) pos1.y;
        xPoint[1] = (int) pos2.x;
        yPoint[1] = (int) pos2.y;
        xPoint[2] = (int) pos2.x;
        yPoint[2] = (int) Main.height;
        xPoint[3] = (int) pos1.x;
        yPoint[3] = (int) Main.height;
        g.setColor(color1);
        g.drawPolygon(xPoint, yPoint, 4);
        g.fillPolygon(xPoint, yPoint,4);
        g.setColor(color2);
        g.drawLine((int) pos1.x,(int) pos1.y-1, (int) pos2.x, (int) pos2.y-1);
    }
}
