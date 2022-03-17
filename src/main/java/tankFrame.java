import com.sun.deploy.net.MessageHeader;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class tankFrame extends  Frame {
    List<bullet> bullets = new ArrayList<>();
    static  List<Tank>   enemies = new ArrayList<>();
    List<Explode> explodes=new ArrayList<>();
    Explode explode=new Explode(100,100,this);

   public static  final  int Game_Width=800,Game_Height=800;
   Tank MyTank=new Tank(200,400,Direction.LEFT,this,Group.GOOD);
    public tankFrame(){
        setVisible(true);
        setSize(Game_Width,Game_Height);
        setTitle("tank war");
        setResizable(false);
        this.addKeyListener(new MyKeyListener());
      addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
              System.exit(0);
          }});
       }
       Image offScreenImage =null;

    public void update(Graphics g){
        if(offScreenImage==null){
            offScreenImage=this.createImage(Game_Width,Game_Height);
        }
        Graphics gOffScreen=offScreenImage.getGraphics();
        Color c=gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,Game_Width,Game_Height);//从frame的（0,0）位置重画一张黑色的图
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {

        Color c=g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量为"+bullets.size(),10,60);
        g.drawString("敌人的数量为"+enemies.size(),10,80);
        g.setColor(c);
        MyTank.paint(g);
       for(int i=0;i<bullets.size();i++)
           bullets.get(i).paint(g);
        for(int i=0;i<enemies.size();i++)
            enemies.get(i).paint(g);
        for(int i=0;i<explodes.size();i++)
            explodes.get(i).paint(g);
        for(int i=0;i<bullets.size();i++){
            for(int j=0;j<enemies.size();j++){
                bullets.get(i).collideWith(enemies.get(j));
            }
        }
    }

    class MyKeyListener extends KeyAdapter{
        boolean BL=false;
        boolean BR=false;
        boolean BU=false;
        boolean BD=false;
        @Override
        public void keyPressed(KeyEvent e) {
           int key=e.getKeyCode();
//           if(key==KeyEvent.VK_LEFT) BL=true;
//            if(key==KeyEvent.VK_RIGHT) BR=true;
//            if(key==KeyEvent.VK_UP) BU=true;
//            if(key==KeyEvent.VK_DOWN) BD=true;
           switch (key){
               case KeyEvent.VK_LEFT: {BL=true; break;}
               case KeyEvent.VK_RIGHT: {BR=true;break;}
               case KeyEvent.VK_UP: {BU=true; break;}
               case KeyEvent.VK_DOWN: {BD=true; break;}
               default:
                   break;
           }
          setMainTankDri();
        }



        @Override
        public void keyReleased(KeyEvent e) {
            int key=e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    BL=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    BR=false;
                    break;
                case KeyEvent.VK_UP:
                     BU=false;
                     break;
                case KeyEvent.VK_DOWN:
                    BD=false;
                     break;
                case KeyEvent.VK_CONTROL:
                    MyTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDri();
        }

        private void setMainTankDri() {
            if(!BL&&!BR&&!BU&&!BD)
            {
//                System.out.println("按键释放，坦克禁止");
                MyTank.setMoving(false);}
            else {
//                System.out.println("按压按键，坦克移动");
                MyTank.setMoving(true);
                if (BL) MyTank.setDri(Direction.LEFT);
                if (BR) MyTank.setDri(Direction.RIGHT);
                if (BU) MyTank.setDri(Direction.UP);
                if (BD) MyTank.setDri(Direction.DOWN);
            }
        }
    }//只给tankFrame用，所以写在内部
}
