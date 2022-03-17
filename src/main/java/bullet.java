import java.awt.*;

public class bullet {
    private int speed=10;
    private  int x,y;
    private Direction dri;
    private boolean living =true;
    private tankFrame tankFrame;
    private Group group=Group.BAD;
    public   static  final  int WIDTH=ResourceMgr.bulletL.getWidth(),HEIGHT=ResourceMgr.bulletL.getHeight();
    public bullet(int x, int y, Direction dri,tankFrame tankFrame,Group group) {
        this.x = x;
        this.y = y;
        this.dri = dri;
        this.tankFrame=tankFrame;
        this.group=group;
    }

    public void paint(Graphics g){
        if(!living) tankFrame.bullets.remove(this);
       switch (dri){
           case LEFT:g.drawImage(ResourceMgr.bulletL,x,y,null);break;
           case RIGHT:g.drawImage(ResourceMgr.bulletR,x,y,null);break;
           case UP:g.drawImage(ResourceMgr.bulletU,x,y,null);break;
           case DOWN:g.drawImage(ResourceMgr.bulletD,x,y,null);break;
       }
        move();
    }

    private void move() {
        switch (dri){
            case LEFT : {x-=speed;break;}
            case RIGHT:  {x+=speed;break;}
            case UP: {y-=speed;break;}
            case DOWN:  {y+=speed;break;}
            default:
                break;
        }
        if(x<0||y<0||x>tankFrame.Game_Width||y>tankFrame.Game_Height)
            living =false;
    }

    public void collideWith(Tank tank) {
        if(this.group==tank.getGroup())  return;
        Rectangle rec1=new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rec2=new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if(rec1.intersects(rec2)) {
            living = false;
            tank.die();
        }
    }
}
