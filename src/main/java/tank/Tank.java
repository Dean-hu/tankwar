package tank;

import M.GameModel;
import M.GameObject;
import tools.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tank extends GameObject {
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    Random random=new Random();
    private Group group=Group.BAD;
    private boolean  living =true;
    public int OldX,OldY;
    public void setLiving(boolean living) {
        this.living = living;
    }

    public boolean isLiving() {
        return living;
    }
    //开火策略
    Firestrategies f;
    //坦克出生的位置
    int x=500,y=500;
    public Rectangle rect=new Rectangle();
    public static int WIDTH=ResourceMgr.goodTank1.getWidth(),HEIGHT=ResourceMgr.goodTank1.getHeight();
    Direction dri=Direction.LEFT;
   final static int speed=5;
    private boolean moving=false;

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

    public Tank(int x, int y, Direction dri, GameModel gm, Group group) {
        this.x = x;
        this.y = y;
        this.dri = dri;
        this.group=group;
        rect.x=x;
        rect.y=y;
        rect.width=WIDTH;
        rect.height=HEIGHT;
        if(group==Group.GOOD) {
            String s = (String) PropertyMrg.props.get("goodFS");
                try {
                    f=(Firestrategies)Class.forName(s).getDeclaredMethod("getInstance").invoke(null,null);
                } catch (Exception e) {
                    e.printStackTrace();}
        }
        else {
            String s = (String) PropertyMrg.props.get("badFS");
            try {
                f=(Firestrategies)Class.forName(s).getDeclaredMethod("getInstance").invoke(null,null);
            } catch (Exception e) {
                e.printStackTrace();}
        }
        GameModel.getInstance().objects.add(this);
    }

    BufferedImage t;
    boolean s=true;
    int step=0;
    public void paint(Graphics g) {
        if(!isLiving()) GameModel.getInstance().objects.remove(this);
        if(step<10)
        {
            if(group==Group.GOOD)
              t=ResourceMgr.goodTank1;
            else
                t=ResourceMgr.badTank1;
        }
        else if(step>20) step=0;
        else
        {
            if(group==Group.GOOD)
                t=ResourceMgr.goodTank2;
            else
                t=ResourceMgr.badTank2;
        }
            step++;
        switch (dri){
            case LEFT:g.drawImage(ImageUtil.rotateImage(t,270),x,y,null); break;
            case RIGHT:g.drawImage(ImageUtil.rotateImage(t,90),x,y,null); break;
            case UP:g.drawImage(t,x,y,null); break;
            case DOWN: g.drawImage(ImageUtil.rotateImage(t,180),x,y,null); break;
            case LU:g.drawImage(ImageUtil.rotateImage(t,315),x,y,null); break;
            case RU:g.drawImage(ImageUtil.rotateImage(t,45),x,y,null); break;
            case LD:g.drawImage(ImageUtil.rotateImage(t,225),x,y,null); break;
            case RD:g.drawImage(ImageUtil.rotateImage(t,135),x,y,null); break;

        }
       move();
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    public Group getGroup() {
        return group;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private void move() {
        if(!moving)  return;
        if(group==Group.GOOD)
        new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        OldX=x;
        OldY=y;
        switch (dri){
            case LEFT : {x-=speed;break;}
            case RIGHT:  {x+=speed;break;}
            case UP: {y-=speed;break;}
            case DOWN:  {y+=speed;break;}
            case LD:  {x-=speed;y+=speed;break;}
            case LU : {x-=speed;y-=speed;break;}
            case RU:  {x+=speed;y-=speed;break;}
            case RD: {x+=speed;y+=speed;break;}
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
        if(this.group==Group.BAD &&random.nextInt(100)>90)
               randomDri();
        if(random.nextInt(100)>95&&this.group==Group.BAD) this.fire();
    }

    private void randomDri() {
        this.dri=Direction.values()[random.nextInt(8)];
    }

    public void fire() {
       if(isLiving())
           f.fire(this);
    }

    public void die() {
        living=false;
        int ex=x+Tank.WIDTH/2-Explode.WIDTH/2;
        int ey=y+Tank.HEIGHT/2-Explode.HEIGHT/2;
       GameModel.getInstance().objects.add(new Explode(ex,ey));
    }
}
