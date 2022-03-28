package M;
import Client.Client;
import cor.ColliderChain;
import cor.TankBulletCollide;
import tank.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameModel {
     static GameModel Instance =new GameModel();
     public Tank MyTank;
      ColliderChain colliderChain=ColliderChain.getInstance();
     public Map<UUID,GameObject> objects=new HashMap<>();
    TankBulletCollide tankBulletCollide;
    public static GameModel getInstance(){
         return Instance;
    }
    static {
          Instance.initialazation();
    }

    private void initialazation() {
        MyTank=new Tank(200,400, Direction.LEFT, Group.GOOD);
        tankBulletCollide=new TankBulletCollide();
    }

    private  GameModel() {
    }

        public void paint(Graphics g) {

        if(MyTank.isLiving())
            MyTank.paint(g);

        for(GameObject t:objects.values()){
            t.paint(g);
        }

    }

    public Tank findTankByUUID(UUID id) {
        return (Tank)objects.get(id);
    }

    public bullet findBulletByUUID(UUID bulletId) {
        return (bullet) objects.get(bulletId);
    }
}
