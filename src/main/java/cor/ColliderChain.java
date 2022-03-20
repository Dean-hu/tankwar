package cor;

import M.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{
     private static final ColliderChain Instance=new ColliderChain();
     public static ColliderChain getInstance(){
         return Instance;
     }

     private ColliderChain(){
             add(new TankBulletCollide());
             add(new TankTankCollide());
     }

     private void add(Collider collider){
          colliders.add(collider);
     }
    public  List<Collider> colliders =new LinkedList<>();
    @Override
    public boolean Collide(GameObject o1, GameObject o2) {
        for(int i=0;i<colliders.size();i++)
        {
            if(colliders.get(i).Collide(o1,o2))
                return true;//处理成功
        }
        return false;//未得到有效处理
    }
}
