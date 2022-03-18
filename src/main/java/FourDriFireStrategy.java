import sun.rmi.runtime.NewThreadAction;
import sun.security.jca.GetInstance;

public class FourDriFireStrategy implements Firestrategies{
    private  static  FourDriFireStrategy Instance=new FourDriFireStrategy();
    private     FourDriFireStrategy(){}

    public static FourDriFireStrategy getInstance(){
        return Instance;
    }

    @Override
    public void fire(Tank tank) {
        int dx=tank.getX()+Tank.WIDTH/2-bullet.WIDTH/2;
        int dy=tank.getY()+Tank.HEIGHT/2-bullet.HEIGHT/2;
        Direction[] dirs=Direction.values();
        for(Direction dir:dirs)
          new bullet(dx,dy,dir,tank.getTankFrame(),tank.getGroup());
    }
}
