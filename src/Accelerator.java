public class Accelerator extends Booster {
    @Override
    public void boost(Sphere sphere) {
        super.boost(sphere);
        sphere.v.xx(3);
    }
}
