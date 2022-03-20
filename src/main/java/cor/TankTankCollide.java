package cor;

import M.GameObject;
import tank.Tank;
import tank.bullet;

public class TankTankCollide implements Collider{
    @Override
    public boolean Collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank&&o2 instanceof Tank)
        {
             Tank tank1=(Tank)o1;
             Tank tank2=(Tank)o2;
             if(tank1.rect.intersects(tank2.rect)){
                 tank1.setX(tank1.OldX);
                 tank2.setX(tank2.OldX);
                 tank1.setY(tank1.OldY);
                 tank2.setY(tank2.OldY);
                 return true;
             }
        }
        return false;
    }
}
