package M;
import cor.ColliderChain;
import cor.TankBulletCollide;
import tank.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class GameModel {
     static GameModel Instance =new GameModel();
     public Tank MyTank;
      ColliderChain colliderChain=ColliderChain.getInstance();
     public List<GameObject> objects=new ArrayList<>();
    TankBulletCollide tankBulletCollide;
    public static GameModel getInstance(){
         return Instance;
    }
    static {
          Instance.initialazation();
    }

    private void initialazation() {
        int initTankCount = Integer.parseInt((String) PropertyMrg.props.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            Tank t= new Tank(50 + i * 100, 100, Direction.DOWN, this, Group.BAD);
            t.setMoving(true);
        }
        MyTank=new Tank(200,400, Direction.LEFT,this, Group.GOOD);
        tankBulletCollide=new TankBulletCollide();
    }

    private  GameModel() {
    }

        public void paint(Graphics g) {
//        Color c=g.getColor();
//        g.setColor(Color.WHITE);
//       g.drawString("子弹的数量为"+bullets.size(),10,60);
//        g.drawString("敌人的数量为"+enemies.size(),10,80);
//        g.setColor(c);
        if(MyTank.isLiving())
            MyTank.paint(g);

        for(int i=0;i<objects.size();i++)
             objects.get(i).paint(g);
        //判断是否相撞
//        for(int i=0;i<bullets.size();i++){
//            if(MyTank.isLiving()) bullets.get(i).collideWith(MyTank);
//            for(int j=0;j<enemies.size();j++){
//                bullets.get(i).collideWith(enemies.get(j));
//            }
//        }

            for(int i=0;i<objects.size();i++)
                for(int j=i+1;j<objects.size();j++) {
                    colliderChain.Collide(objects.get(i),objects.get(j));
                }

    }
}
