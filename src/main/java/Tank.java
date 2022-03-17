

import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.awt.*;
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
    int x=200,y=200;//坦克出生的位置
    public static int WIDTH=ResourceMgr.tankD.getWidth(),HEIGHT=ResourceMgr.tankD.getHeight();
    Direction dri=Direction.LEFT;
    final static int speed=5;
    private boolean moving=false;
    private tankFrame tankFrame;//持有主界面的引用
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
        if(random.nextInt(10)>8) this.fire();
    }

    public void fire() {
        int dx=x+Tank.WIDTH/2-bullet.WIDTH/2;
        int dy=y+Tank.HEIGHT/2-bullet.HEIGHT/2;
        tankFrame.bullets.add(new bullet(dx,dy,dri,tankFrame,this.group));
    }

    public void die() {
        living=false;
        tankFrame.explodes.add(new Explode(x,y,tankFrame));
    }
}
