import java.io.IOException;

public class Accelerator extends Booster {
    @Override
    public void boost(Sphere sphere) {
        super.boost(sphere);
        sphere.v.x += 200;
    }
    public Accelerator(Vector2 pos) throws IOException {
        super(pos);
        this.pos = pos;
    }
}
