import java.awt.*;

public class Vector2 {
    public double x;
    public double y;

    public Vector2() {
        x = 1;
        y = 0;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public double len() {
        return Math.sqrt(x * x + y * y);
    }
    public void xx(double a){
        x=a*x;
        y=a*y;
    }
    public void plus(Vector2 v){
        x=x+v.x;
        y=y+v.y;
    }
    public void minus(Vector2 v){
        x=x-v.x;
        y=y-v.y;
    }
    public Vector2 sum(Vector2 v){
        Vector2 tmp = new Vector2(x+v.x,y+v.y);
        return tmp;
    }
    public static Vector2 sum(Vector2 v1, Vector2 v2){
        Vector2 tmp = new Vector2(v2.x+v1.x,v1.y+v2.y);
        return tmp;
    }
    public Vector2 mult(double a){
        Vector2 tmp = new Vector2(a*x,a*y);
        return tmp;
    }
    public double DotProduct(Vector2 v){
        return x*v.x+y*v.y;
    }
    public static double DotProduct(Vector2 v1, Vector2 v2){
        return v2.x*v1.x+v2.y*v1.y;
    }
    public Vector2 CrossProduct(float c){
        return new Vector2(-c*y, c*x);
    }
    public double angle(Vector2 v1, Vector2 v2){
        return Math.acos(DotProduct(v1, v2)/(v1.len()*v2.len()));
    }
    public static Vector2 mult(Vector2 v,double a){
        Vector2 tmp = new Vector2(a*v.x,a*v.y);
        return tmp;
    }
    public void normalize(){
        if (len()!=0) {
            xx(1.0/len());
        }
    }
    public Vector2 norm(){
        Vector2 v = new Vector2(this);
        if (len()!=0) {
            v.xx(1.0/v.len());
        }
        return v;
    }
    public void rotate(double a){
        double x2 = x*Math.cos(a)-y*Math.sin(a);
        double y2 = y*Math.cos(a)+x*Math.sin(a);
        x = x2;
        y = y2;
    }

    public Vector2 rotated(double a){
        Vector2 v = new Vector2(this);
        v.rotate(a);
        return v;
    }
    public Vector2 ort() {
        if (len()!=0) {
            Vector2 tmp = new Vector2(-y/len(),x/len());
            return tmp;
        } else {
            Vector2 tmp = new Vector2(0, 0);
            return tmp;
        }
    }

    public static double phi(Vector2 v){
        return Math.asin(v.y/v.len());
    }

    public static Vector2 segmentVector(Vector2 pos1, Vector2 pos2) {
        return new Vector2(pos2.x - pos1.x, pos2.y - pos1.y);
    }

    public int getQuarte(){
        if (x>0 && y>0){
            int n=1;
            return n;
        } else if (x<0 && y>0){
            int n=2;
            return n;
        } else if (x<0 && y<0){
            int n=3;
            return n;
        } else if (x>0 && y<0){
            int n=4;
            return n;
        } else {
            int n=0;
            return n;
        }
    }

    public boolean equals(Vector2 v){
        return x == v.x && y == v.y;
    }

    public String toString(){
        return "("+(int) x+","+(int) y+")";


    }


}