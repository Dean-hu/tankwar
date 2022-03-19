package tank;

import M.GameModel;

import java.awt.*;

public class Explode {

        public static int WIDTH=ResourceMgr.explodes[0].getWidth(),
                          HEIGHT=ResourceMgr.explodes[0].getHeight();

       GameModel gm=null;//持有主界面的引用
        private  int x ,y;
        private int step=0;
        public Explode(int x, int y, GameModel gm) {
            this.x = x;
            this.y = y;
            this.gm=gm;
            new Thread(()->new Audio("audio/explode.wav").play()).start();
        }

        public void paint(Graphics g) {
            g.drawImage(ResourceMgr.explodes[step++],x,y,null);
           if(step>=ResourceMgr.explodes.length)
                 gm.explodes.remove(this);
        }

}
