package tank;

import Client.Client;
import M.GameModel;
import Server.TankDirChangedMsg;
import Server.TankStartMovingMsg;
import Server.TankStopMsg;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class tankFrame extends  Frame {


   public static  final  int Game_Width=1000,Game_Height=1000;
   // 主战坦克

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

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(Game_Width, Game_Height);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, Game_Width, Game_Height);//从frame的（0,0）位置重画一张黑色的图
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        GameModel.getInstance().paint(g);
    }

    boolean BL=false;
    boolean BR=false;
    boolean BU=false;
    boolean BD=false;
    class MyKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
           int key=e.getKeyCode();
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

       Tank MyTank=GameModel.getInstance().MyTank;
        @Override
        public void keyReleased(KeyEvent e) {
            int key=e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT: BL=false;break;
                case KeyEvent.VK_RIGHT: BR=false;break;
                case KeyEvent.VK_UP: BU=false;break;
                case KeyEvent.VK_DOWN:BD=false;break;
                case KeyEvent.VK_CONTROL:MyTank.fire();break;
                default:
                    break;
            }
            setMainTankDri();
        }

        private void setMainTankDri() {
            if(!BL&&!BR&&!BU&&!BD)
            {
                Client.INSTANCE.send(new TankStopMsg(MyTank));
                MyTank.setMoving(false);
            }
            else {
                Direction d=MyTank.getDri();
                if (BL) MyTank.setDri(Direction.LEFT);
                if (BR) MyTank.setDri(Direction.RIGHT);
                if (BU) MyTank.setDri(Direction.UP);
                if (BD) MyTank.setDri(Direction.DOWN);
                if(BD&&BU) MyTank.setMoving(false);
                if(BD&&BL) MyTank.setDri(Direction.LD);
                if(BU&&BL) MyTank.setDri(Direction.LU);
                if(BD&&BR) MyTank.setDri(Direction.RD);
                if(BU&&BR) MyTank.setDri(Direction.RU);
                if(!MyTank.isMoving())
                    Client.INSTANCE.send(new TankStartMovingMsg(MyTank));
                MyTank.setMoving(true);

                if(d != MyTank.getDri()) {
                    Client.INSTANCE.send(new TankDirChangedMsg(MyTank));
                }
            }
        }
    }//只给tankFrame用，所以写在内部
}
