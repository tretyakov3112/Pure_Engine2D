public class Jumper extends Booster {
    @Override
    public void boost(Sphere sphere) {
        super.boost(sphere);
        sphere.v.y -= 300;
    }
    public Jumper(Vector2 pos){
        super(pos);
        this.pos = pos;
    }
}
