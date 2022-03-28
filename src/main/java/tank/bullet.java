package tank;

import Client.Client;
import M.GameModel;
import M.GameObject;
import Server.BulletNewMsg;
import com.sun.media.jfxmedia.events.PlayerEvent;

import java.awt.*;
import java.util.UUID;

public class bullet extends GameObject {
    private int speed=20;
    public   int x,y;
    public Direction dri;
    private boolean living =true;
    private Group group=Group.BAD;
    public UUID id=UUID.randomUUID();
    public UUID playerId=null;
    public  Rectangle rect=new Rectangle();//优化部分，这样一个子弹只对应一个rect，而不会new出来很多rect，节省内存
    public   static  final  int WIDTH=ResourceMgr.bulletL.getWidth(),HEIGHT=ResourceMgr.bulletL.getHeight();
    public bullet(int x, int y, Direction dri,Group group,UUID p) {
        this.x = x;
        this.y = y;
        this.dri = dri;
        this.group=group;
        playerId=p;
        rect.x=x;
        rect.y=y;
        rect.width=WIDTH;
        rect.height=HEIGHT;
       GameModel.getInstance().objects.put(id,this);

    }

    public void paint(Graphics g){
        if(!living) GameModel.getInstance().objects.remove(this.id);
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

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
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

    public Group getGroup() {
        return group;
    }

}
