import java.io.IOException;
import java.util.LinkedList;

public class World {
    Background background;
    Sphere sphere = new Sphere();
    int wallCount = 100;
    Wall[] walls = new Wall[wallCount];
    Vector2 g = new Vector2(0, 100);
    int currentWallIndex = -1;
    LinkedList<Booster> boosterList = new LinkedList<>();


    //Wall testWall;

    public World() throws IOException {
        addWalls();
        background = new Background();

    }

    Vector2[] points = new Vector2[wallCount+1];

    public void addWalls() {
        for (int i = 0; i < wallCount+1; i++) {
            points[i] = new Vector2( (i * sphere.r * (1 + Math.sqrt(2))),  (Main.height*0.5 + (Math.random() - 0.5) * 2 * sphere.r * 0.5 * (1 + Math.sqrt(2))));

        }
        for (int i = 0; i < (wallCount+1)/10; i++) {
            Accelerator accelerator = new Accelerator();
            accelerator.pos = new Vector2(points[10*i].x, points[10*i].y-accelerator.r*2);
            boosterList.add(accelerator);
        }
        for (int i = 1; i < wallCount+1; i++) {
            walls[i - 1] = new Wall(points[i - 1], points[i], 1);

        }
        //Wall wall = new Wall(new Point2(100,600), new Point2(1000,600), 0.5);

        //testWall = new Wall(new Vector2(0, 700), new Vector2(3000, 1000), 0.5);
    }

    public void moveWalls(){
        double dx = sphere.xPos-sphere.pos.x;
        for (int i = 0; i < wallCount; i++) {
            walls[i].pos1.x += dx;
            walls[i].pos2.x += dx;
        }
        for (Booster booster: boosterList) {
            booster.pos.x += dx;
        }

        background.update(dx);
        sphere.pos.x = sphere.xPos;
    }

    public int findWall() {
        /*for (int i = 0; i < 99; i++) {
            if (sphere.checkCollision(walls[i])){
                return i;
            }
        }*/
        return -1;
    }
    public void Collision(float dt, Wall wall){
        Vector2 wallVector = new Vector2(wall.pos2.x-wall.pos1.x, wall.pos2.y-wall.pos1.y).norm();
        Vector2 sphereToWallStartVector = Vector2.segmentVector(wall.pos1, sphere.pos);
        double t = Vector2.DotProduct(wallVector, sphereToWallStartVector) / Vector2.DotProduct(wallVector, wallVector);
        Vector2 intersectionVector = wallVector.mult(t);
        intersectionVector.plus(wall.pos1);
        wall.k = (Math.abs(sphere.w)*sphere.r+Math.abs(sphere.v.x))/(6*Math.abs(sphere.v.y))+1;
        Vector2 projectionFromSphere = Vector2.segmentVector(sphere.pos, intersectionVector);
        System.out.println(sphere.w);
        double tmp = (-Math.abs(Vector2.DotProduct(wallVector, sphere.v)/sphere.v.len()) + 10*0.5*sphere.w*sphere.r)/(1.5*sphere.r);
        if (tmp<5 && tmp>-5) {
            sphere.w = tmp;
        } else if (tmp>0){
            sphere.w = 5;
        } else {
            sphere.w = -5;
        }
        sphere.v.y = -Math.abs(Vector2.DotProduct(projectionFromSphere, sphere.v)/projectionFromSphere.len());
        //sphere.v.x = (-Math.abs(Vector2.DotProduct(wallVector, sphere.v)/wallVector.len()) + 10*0.5*sphere.w*sphere.r)/(1.5);
        sphere.v.x = sphere.w*sphere.r;
        //System.out.println(-Math.abs(Vector2.DotProduct(wallVector, sphere.v)/wallVector.len()));
        //System.out.println(0.5*sphere.w*sphere.r);
        sphere.v.rotate(Vector2.phi(wallVector));
        sphere.pos.plus(Vector2.mult(sphere.v, dt).sum(Vector2.mult(g, dt*dt*0.5)));


    }

    public void update(float dt) {
        Vector2 intersection = null;
        for (int i = 1; i < wallCount-1; i++) {
            intersection = sphere.checkCollision(walls[i]);
            if (intersection != null){
               Collision(dt, walls[i]);
            }
        }
        sphere.v.plus(Vector2.mult(g, dt));
        sphere.phi = sphere.phi+sphere.w*dt;
        sphere.pos.plus(Vector2.mult(sphere.v, dt));
        sphere.update();
    }
}