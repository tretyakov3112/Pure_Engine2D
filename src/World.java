import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class World {
    Background background;
    Sphere sphere = new Sphere();
    int wallCount = 100;
    Wall[] walls = new Wall[wallCount];
    Vector2 g = new Vector2(0, 500);
    LinkedList<Booster> boosterList = new LinkedList<>();

    public World() throws IOException {
        addWalls();
        background = new Background();
    }

    public Booster randomBooster(Vector2 pos) throws IOException {
        Random random = new Random(System.currentTimeMillis());
        int tmp = random.nextInt(3);

        if (tmp == 0){
            Accelerator accelerator = new Accelerator(pos);
            return accelerator;
        } else if (tmp == 1){
            Jumper jumper = new Jumper(pos);
            return jumper;
        } else if (tmp == 2){
            Stopper stopper = new Stopper(pos);
            return stopper;
        } else {
            Accelerator accelerator = new Accelerator(pos);
            return accelerator;
        }
    }

    Vector2[] points = new Vector2[wallCount+1];
    public void addWalls() throws IOException {
        for (int i = 0; i < wallCount+1; i++) {
            points[i] = new Vector2( (i * sphere.r * (1 + Math.sqrt(2))),  (Main.height*0.8 + (Math.random() - 0.5) * 2 * sphere.r * 0.5 * (1 + Math.sqrt(2))));
        }
        for (int i = 1; i < (wallCount+1)/10 - 1; i++) {
            boosterList.add(randomBooster(new Vector2(points[10*i].x, Main.height*0.8 - sphere.r * 0.5 * (1 + Math.sqrt(2)) - sphere.r)));
        }
        for (int i = 1; i < wallCount+1; i++) {
            walls[i - 1] = new Wall(points[i - 1], points[i], 1);
        }
    }

    public void moveWalls(){
        if (sphere.pos.x >= sphere.xPos2) {
            double dx = sphere.xPos2 - sphere.pos.x;
            for (int i = 0; i < wallCount; i++) {
                walls[i].pos1.x += dx;
                walls[i].pos2.x += dx;
            }
            for (Booster booster : boosterList) {
                booster.pos.x += 2*dx;
            }

            background.update(dx);
            sphere.pos.x = sphere.xPos2;
        }
        if (sphere.pos.x <= sphere.xPos1) {
            double dx = sphere.xPos1 - sphere.pos.x;
            for (int i = 0; i < wallCount; i++) {
                walls[i].pos1.x += dx;
                walls[i].pos2.x += dx;
            }
            for (Booster booster : boosterList) {
                booster.pos.x += 2*dx;
            }

            background.update(dx);
            sphere.pos.x = sphere.xPos1;
        }
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
        sphere.v.x = sphere.w*sphere.r;
        sphere.v.rotate(Vector2.phi(wallVector));
        sphere.pos.plus(Vector2.mult(sphere.v, dt).sum(Vector2.mult(g, dt*dt*0.5)));
    }


    public void update(float dt) {
        Vector2 intersection = null;
        for (int i = 1; i < wallCount-1; i++) {
            intersection = sphere.checkCollision(walls[i]);
            if (intersection != null){
                new Thread(() -> {
                    new MakeSound().playSound("sounds\\collision.wav");
                }).start();
               Collision(dt, walls[i]);
            }
        }
        sphere.v.plus(Vector2.mult(g, dt));
        if (sphere.w > 5){
            sphere.w = 5;
        }
        if (sphere.w < -5){
            sphere.w = -5;
        }
        sphere.phi += sphere.w*dt;
        sphere.pos.plus(Vector2.mult(sphere.v, dt));

    }
}