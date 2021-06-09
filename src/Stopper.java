import java.io.IOException;

public class Stopper extends Booster {
    @Override
    public void boost(Sphere sphere) {
        super.boost(sphere);
        sphere.v = new Vector2(0,0);
    }
    public Stopper(Vector2 pos) throws IOException {
        super(pos);
        this.pos = pos;
    }
}
