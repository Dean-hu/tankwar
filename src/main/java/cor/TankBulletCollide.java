package cor;

import M.GameObject;
import tank.Tank;
import tank.bullet;

public class TankBulletCollide implements Collider{
    @Override
    public boolean Collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank&&o2 instanceof bullet)
        {
             Tank tank=(Tank)o1;
             bullet b=(bullet)o2;
             if(tank.getGroup()!=b.getGroup())
             if(tank.rect.intersects(b.rect)){
                 tank.setLiving(false);
                 tank.die();
                 return true;
             }
        }
        else if(o1 instanceof bullet&&o2 instanceof Tank){
             return  Collide(o2,o1);
        }
        return false;
    }
}
