public class World {
    Sphere sphere = new Sphere();
    Wall[] walls = new Wall[99];
    Vector2 g = new Vector2(0, 10);

    Wall testWall;

    public World() {
        addWalls();
    }

    Point2[] points = new Point2[100];

    public void addWalls() {
        /*for (int i = 0; i < 100; i++) {
            //points[i] = new Point2((int) (i * 50 * (1 + Math.sqrt(2))), (int) (800 + (Math.random() - 0.5) * 2 * 50 * 0.5 * (1 + Math.sqrt(2))));
            points[i] = new Point2((int) (i * 50 * (1 + Math.sqrt(2))), (int) (800));
        }
        for (int i = 1; i < 100; i++) {
            walls[i - 1] = new Wall(points[i - 1], points[i], 0.5);
        }*/
        //Wall wall = new Wall(new Point2(100,600), new Point2(1000,600), 0.5);

        testWall = new Wall(new Vector2(50, 500), new Vector2(1500, 1000), 0.5);
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
        Vector2 intersection = sphere.checkCollision(testWall);
        if (intersection != null) {
            System.out.println(intersection.toString());
            Wall wall = testWall;
            Vector2 wallVector = new Vector2(wall.pos2.x-wall.pos1.x, wall.pos2.y-wall.pos1.y);
            double A = 10*(wall.k*Math.cos(Vector2.phi(wallVector))-Math.sin(Vector2.phi(wallVector)));
            Vector2 a = new Vector2(A*Math.cos(Vector2.phi(wallVector)), A*Math.sin(Vector2.phi(wallVector)));

            if (Vector2.DotProduct(sphere.v, wallVector.ort()) == 0){
//                sphere.pos.x = sphere.pos.x+sphere.v.x*dt+0.5*A*Math.cos(Vector2.phi(wallVector))*dt*dt;
//                sphere.pos.y = sphere.pos.y+sphere.v.y*dt+0.5*A*Math.sin(Vector2.phi(wallVector))*dt*dt;
                sphere.pos.plus(Vector2.mult(sphere.v, dt).sum(Vector2.mult(a, 0.001*dt*dt)));
                sphere.w = sphere.w+ wall.k*sphere.m*g.len()*Math.cos(Vector2.phi(wallVector))*dt/sphere.J;
                sphere.v = wallVector.norm().mult(sphere.w*sphere.r+g.len()*(wall.k*Math.cos(Vector2.phi(wallVector))-Math.sin(Vector2.phi(wallVector))));
                //sphere.v.plus(Vector2.mult(a, dt));
            } else {
                sphere.pos.plus(Vector2.mult(sphere.v, dt).sum(Vector2.mult(g, 0.001*dt*dt)));
                sphere.v.rotate(-2*Vector2.phi(wallVector)*Vector2.phi(sphere.v));
                //sphere.v.plus(Vector2.mult(a, dt));
            }
        } else {
            //sphere.pos.y = sphere.pos.y - 0.001 * g.y * dt * dt;
            sphere.pos.plus(Vector2.mult(sphere.v, dt).sum(Vector2.mult(g, 0.001*dt*dt)));
            sphere.v.plus(Vector2.mult(g, 0.00001*dt));
            //System.out.println("Nope");
        }

        /*
        public void update(float dt){
        int wallIndex = findWall();
        if (wallIndex == -1){
            //sphere.pos.y = sphere.pos.y-0.001*g.y*dt*dt;
            sphere.pos.plus(Vector2.mult(sphere.v, dt).sum(Vector2.mult(g, 0.001*dt*dt)));
            sphere.v.plus(Vector2.mult(g, dt));
        } else {
            Wall wall = walls[wallIndex];
            Vector2 wallVector = new Vector2(wall.pos2.x-wall.pos1.x, wall.pos2.y-wall.pos1.y);
            double A = 10*(wall.k*Math.cos(Vector2.phi(wallVector))-Math.sin(Vector2.phi(wallVector)));
            Vector2 a = new Vector2(A*Math.cos(Vector2.phi(wallVector)), A*Math.sin(Vector2.phi(wallVector)));

            if (Vector2.DotProduct(sphere.v, wallVector.ort()) == 0){
//                sphere.pos.x = sphere.pos.x+sphere.v.x*dt+0.5*A*Math.cos(Vector2.phi(wallVector))*dt*dt;
//                sphere.pos.y = sphere.pos.y+sphere.v.y*dt+0.5*A*Math.sin(Vector2.phi(wallVector))*dt*dt;
                sphere.pos.plus(Vector2.mult(sphere.v, dt).sum(Vector2.mult(a, 0.001*dt*dt)));
                sphere.w = sphere.w+ wall.k*sphere.m*g.len()*Math.cos(Vector2.phi(wallVector))*dt/sphere.J;
                sphere.v = wallVector.norm().mult(sphere.w*sphere.r+g.len()*(wall.k*Math.cos(Vector2.phi(wallVector))-Math.sin(Vector2.phi(wallVector))));
                //sphere.v.plus(Vector2.mult(a, dt));
            } else {
                sphere.pos.plus(Vector2.mult(sphere.v, dt).sum(Vector2.mult(g, 0.001*dt*dt)));
                sphere.v.rotate(-2*Vector2.phi(wallVector)*Vector2.phi(sphere.v));
                //sphere.v.plus(Vector2.mult(a, dt));
            }

        }
    }*/
    }
}