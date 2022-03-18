public class DefaultFireStrategy implements Firestrategies{

    private static final DefaultFireStrategy Instance=new DefaultFireStrategy();

    private DefaultFireStrategy(){}

    public static  DefaultFireStrategy getInstance(){
        return Instance;
    }
    @Override
    public void fire(Tank tank) {
        int dx=tank.getX()+Tank.WIDTH/2-bullet.WIDTH/2;
        int dy=tank.getY()+Tank.HEIGHT/2-bullet.HEIGHT/2;
        new bullet(dx,dy,tank.getDri(),tank.getTankFrame(),tank.getGroup());
    }
}
