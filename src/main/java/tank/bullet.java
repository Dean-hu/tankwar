package tank;

import M.GameModel;

import java.awt.*;

public class bullet {
    private int speed=10;
    private  int x,y;
    private Direction dri;
    private boolean living =true;
   private GameModel gm;
    private Group group=Group.BAD;
    Rectangle rect=new Rectangle();//优化部分，这样一个子弹只对应一个rect，而不会new出来很多rect，节省内存
    public   static  final  int WIDTH=ResourceMgr.bulletL.getWidth(),HEIGHT=ResourceMgr.bulletL.getHeight();
    public bullet(int x, int y, Direction dri,GameModel gm,Group group) {
        this.x = x;
        this.y = y;
        this.dri = dri;
        this.gm=gm;
        this.group=group;
        rect.x=x;
        rect.y=y;
        rect.width=WIDTH;
        rect.height=HEIGHT;
       gm.bullets.add(this);
    }

    public void paint(Graphics g){
        if(!living) gm.bullets.remove(this);
       switch (dri){
           case LEFT:g.drawImage(ResourceMgr.bulletL,x,y,null);break;
           case RIGHT:g.drawImage(ResourceMgr.bulletR,x,y,null);break;
           case UP:g.drawImage(ResourceMgr.bulletU,x,y,null);break;
           case DOWN:g.drawImage(ResourceMgr.bulletD,x,y,null);break;
           case LU:g.drawImage(ResourceMgr.bulletLU,x,y,null);break;
           case LD:g.drawImage(ResourceMgr.bulletLD,x,y,null);break;
           case RU:g.drawImage(ResourceMgr.bulletRU,x,y,null);break;
           case RD:g.drawImage(ResourceMgr.bulletRD,x,y,null);break;
       }
        move();
    }

    private void move() {
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
        if(x<0||y<0||x>tankFrame.Game_Width||y>tankFrame.Game_Height)
            living =false;
    }

    public void collideWith(Tank tank) {
        if(this.group==tank.getGroup())  return;
        if(rect.intersects(tank.rect)) {
            living = false;
            tank.die();
        }
    }
}
