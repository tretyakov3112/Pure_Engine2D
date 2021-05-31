public class World {
    Sphere sphere = new Sphere();
    int wallCount = 100;
    Wall[] walls = new Wall[wallCount];
    Vector2 g = new Vector2(0, 100);
    int currentWallIndex = -1;


    //Wall testWall;

    public World() {
        addWalls();
    }

    Vector2[] points = new Vector2[wallCount+1];

    public void addWalls() {
        for (int i = 0; i < wallCount+1; i++) {
            points[i] = new Vector2( (i * sphere.r * (1 + Math.sqrt(2))),  (800 + (Math.random() - 0.5) * 2 * sphere.r * 0.5 * (1 + Math.sqrt(2))));
            //points[i] = new Vector2((int) (i * 50 * (1 + Math.sqrt(2))), (int) (800));
            //System.out.println(points[i]);
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
        System.out.println(dx);
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

    public void update(float dt) {
        for (int i = 0; i < wallCount; i++) {
            walls[i].k = (Math.abs(sphere.w)*sphere.r+Math.abs(sphere.v.x))/(6*sphere.v.y) +1;
        }
        Vector2 intersection = null;
        int wallIndex = 0;
        for (int i = 0; i < wallCount-1; i++) {
            if (currentWallIndex == i ){
                continue;
            }
            intersection = sphere.checkCollision(walls[i]);
            if (intersection != null){
                wallIndex = i;
                currentWallIndex = i;
                //System.out.println(i);
                //System.out.println(walls[wallIndex].pos1+" "+walls[wallIndex].pos2);
                break;
            }
        }
        if (intersection == null){
            currentWallIndex = -1;
        }




        if (intersection != null) {//если коллизия
            //System.out.println(intersection.toString());
            Wall wall = walls[wallIndex];
            Vector2 wallVector = new Vector2(wall.pos2.x-wall.pos1.x, wall.pos2.y-wall.pos1.y).norm();
            double A = 10*(wall.k*Math.cos(Vector2.phi(wallVector))-Math.sin(Vector2.phi(wallVector)));
            Vector2 a = new Vector2(A*Math.cos(Vector2.phi(wallVector)), A*Math.sin(Vector2.phi(wallVector)));

            /*if (Vector2.DotProduct(sphere.v, wallVector.ort()) == 0){//если качение
//                sphere.pos.x = sphere.pos.x+sphere.v.x*dt+0.5*A*Math.cos(Vector2.phi(wallVector))*dt*dt;
//                sphere.pos.y = sphere.pos.y+sphere.v.y*dt+0.5*A*Math.sin(Vector2.phi(wallVector))*dt*dt;
                sphere.w = sphere.w+ wall.k*sphere.m*g.len()*Math.cos(Vector2.phi(wallVector))*dt/sphere.J;
                sphere.phi = sphere.phi+sphere.w*dt;
                sphere.v = wallVector.norm().mult(sphere.w*sphere.r+g.len()*(wall.k*Math.cos(Vector2.phi(wallVector))-Math.abs(Math.sin(Vector2.phi(wallVector)))));
                sphere.pos.plus(Vector2.mult(sphere.v, dt).sum(Vector2.mult(a, dt*dt)));
                //sphere.v.plus(Vector2.mult(a, dt));
            } else {//если удар
                //sphere.phi = sphere.phi+sphere.w*dt;

             */
                Vector2 sphereToWallStartVector = Vector2.segmentVector(wall.pos1, sphere.pos);
                double t = Vector2.DotProduct(wallVector, sphereToWallStartVector) / Vector2.DotProduct(wallVector, wallVector);
                Vector2 intersectionVector = wallVector.mult(t);
                intersectionVector.plus(wall.pos1);
                Vector2 projectionFromSphere = Vector2.segmentVector(sphere.pos, intersectionVector);
                sphere.w = (-Math.abs(Vector2.DotProduct(wallVector, sphere.v)/sphere.v.len()) + 0.5*sphere.w*sphere.r)/(1.5*sphere.r);
                sphere.v.x = -(-Math.abs(Vector2.DotProduct(wallVector, sphere.v)/wallVector.len()) + 0.5*sphere.w*sphere.r)/(1.5);
                sphere.v.y = -Math.abs(Vector2.DotProduct(projectionFromSphere, sphere.v)/projectionFromSphere.len());
                sphere.v.rotate(Math.abs(Vector2.phi(wallVector)));
                //System.out.println(2*(Vector2.phi(wallVector)+Math.abs(Vector2.phi(sphere.v))));
                //sphere.v.rotate(2*(Vector2.phi(wallVector)+Math.abs(Vector2.phi(sphere.v))));
                sphere.pos.plus(Vector2.mult(sphere.v, dt));
                //sphere.v.plus(Vector2.mult(a, dt));
            //}
        } else {//если полет
            //sphere.pos.y = sphere.pos.y - 0.001 * g.y * dt * dt;
            sphere.v.plus(Vector2.mult(g, dt));
            sphere.phi = sphere.phi+sphere.w*dt;
            sphere.pos.plus(Vector2.mult(sphere.v, dt));
            //System.out.println("Nope");
        }
        sphere.update();
    }
}