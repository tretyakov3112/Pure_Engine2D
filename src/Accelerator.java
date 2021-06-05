public class Accelerator extends Booster {
    @Override
    public void boost(Sphere sphere) {
        super.boost(sphere);
        sphere.v.x += 200;
    }
    public Accelerator(Vector2 pos){
        super(pos);
        this.pos = pos;
    }
}
