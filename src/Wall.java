import java.awt.*;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Wall {
    Vector2 pos1;
    Vector2 pos2;
    double k;
    Color color = new Color(6, 245, 6);
    public Wall(Vector2 pos1, Vector2 pos2, double k){
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.k = k;
    }
    public void draw(Graphics g){
        g.setColor(color);
        g.drawLine((int) pos1.x,(int) pos1.y, (int) pos2.x, (int) pos2.y);
    }
}
