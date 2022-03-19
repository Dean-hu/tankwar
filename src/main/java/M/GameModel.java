package M;
import tank.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class GameModel {

     public Tank MyTank=new Tank(200,400, Direction.LEFT,this, Group.GOOD);
     public  List<bullet> bullets = new ArrayList<>();
     public List<Tank> enemies = new ArrayList<>();
     public List<Explode> explodes=new ArrayList<>();

    public static GameModel getInstance(){
         return gm;
    }
    private  GameModel() {
            int initTankCount = Integer.parseInt((String) PropertyMrg.props.get("initTankCount"));
            for (int i = 0; i < initTankCount; i++) {
                enemies.add(new Tank(50 + i * 100, 100, Direction.DOWN, this, Group.BAD));
                enemies.get(i).setMoving(true);
            }
    }
   static GameModel gm=new GameModel();
        public void paint(Graphics g) {
        Color c=g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量为"+bullets.size(),10,60);
        g.drawString("敌人的数量为"+enemies.size(),10,80);
        g.setColor(c);
        if(MyTank.isLiving())
            MyTank.paint(g);
        for(int i=0;i<bullets.size();i++)
            bullets.get(i).paint(g);
        for(int i=0;i<enemies.size();i++)
            enemies.get(i).paint(g);
        for(int i=0;i<explodes.size();i++)
            explodes.get(i).paint(g);
        //判断是否相撞
        for(int i=0;i<bullets.size();i++){
            if(MyTank.isLiving()) bullets.get(i).collideWith(MyTank);
            for(int j=0;j<enemies.size();j++){
                bullets.get(i).collideWith(enemies.get(j));
            }
        }
    }
}
