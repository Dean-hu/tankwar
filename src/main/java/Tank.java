

import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Tank {
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    Random random=new Random();
    private Group group=Group.BAD;
    private boolean  living =true;

    public boolean isLiving() {
        return living;
    }
    //开火策略
    Firestrategies f;
    //坦克出生的位置
    int x=200,y=200;
    Rectangle rect=new Rectangle();
    public static int WIDTH=ResourceMgr.tankD.getWidth(),HEIGHT=ResourceMgr.tankD.getHeight();
    Direction dri=Direction.LEFT;
   final static int speed=5;
    private boolean moving=false;
    private tankFrame tankFrame;//持有主界面的引用

    public tankFrame getTankFrame() {
        return tankFrame;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setDri(Direction dri) {
        this.dri = dri;
    }

    public Direction getDri() {
        return dri;
    }

    public Tank(int x, int y, Direction dri,tankFrame tankFrame,Group group) {
        this.x = x;
        this.y = y;
        this.dri = dri;
        this.tankFrame=tankFrame;
        this.group=group;
        rect.x=x;
        rect.y=y;
        rect.width=WIDTH;
        rect.height=HEIGHT;
//        if(group==Group.BAD)
//           f=DefaultFireStrategy.getInstance();
//        else
//            f=FourDriFireStrategy.GetInstance();

        if(group==Group.GOOD) {
            String s = (String) PropertyMrg.props.get("goodFS");
                try {
                    f=(Firestrategies)Class.forName(s).getDeclaredMethod("getInstance").invoke(null,null);
                } catch (Exception e) {
                    e.printStackTrace();}
        }
        else {
            String s = (String) PropertyMrg.props.get("goodFS");
            try {
                f=(Firestrategies)Class.forName(s).getDeclaredMethod("getInstance").invoke(null,null);
            } catch (Exception e) {
                e.printStackTrace();}
        }
    }

    void paint(Graphics g) {
        if(!living) tankFrame.enemies.remove(this);
        switch (dri){
            case LEFT:g.drawImage(ResourceMgr.tankL,x,y,null); break;
            case RIGHT:g.drawImage(ResourceMgr.tankR,x,y,null); break;
            case UP:g.drawImage(ResourceMgr.tankU,x,y,null); break;
            case DOWN:g.drawImage(ResourceMgr.tankD,x,y,null); break;
        }
       move();
    }

    public Group getGroup() {
        return group;
    }

    private void move() {
        if(!moving)  return;
        switch (dri){
            case LEFT : {x-=speed;break;}
            case RIGHT:  {x+=speed;break;}
            case UP: {y-=speed;break;}
            case DOWN:  {y+=speed;break;}
            default:
                break;
        }
        //update rect
        rect.x=x;
        rect.y=y;
        if(x<2) x=2;
        if(y<30) y=30;
        if(x> tankFrame.Game_Width-WIDTH) x=tankFrame.Game_Width-WIDTH;
        if(y> tankFrame.Game_Height-HEIGHT) y=tankFrame.Game_Height-HEIGHT;
        if(this.group==Group.BAD &&random.nextInt(200)>195)
               randomDri();
        if(random.nextInt(10)>8&&this.group==Group.BAD) this.fire();
    }

    private void randomDri() {
        this.dri=Direction.values()[random.nextInt(4)];
    }

    public void fire() {
       f.fire(this);
    }

    public void die() {
        living=false;
        int ex=x+Tank.WIDTH/2-Explode.WIDTH/2;
        int ey=y+Tank.HEIGHT/2-Explode.HEIGHT/2;
        tankFrame.explodes.add(new Explode(ex,ey,tankFrame));
    }
}
