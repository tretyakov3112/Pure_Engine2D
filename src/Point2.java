public class Point2 {

    public int x, y;

    public Point2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void rotate(Point2 rotatePoint, float angle) {
        int sx = x;
        x =  (int) ((sx - rotatePoint.x) * Math.cos(angle) - (this.y - rotatePoint.y) * Math.sin(angle) + rotatePoint.x);
        y =  (int) ((sx - rotatePoint.x) * Math.sin(angle) + (this.y - rotatePoint.y) * Math.cos(angle) + rotatePoint.y);

    }

    @Override
    public String toString() {
        return x + "; " + y;
    }
}
