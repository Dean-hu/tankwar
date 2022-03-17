import java.awt.*;
import java.util.Random;

public class Explode {

        public static int WIDTH=ResourceMgr.explodes[0].getWidth(),
                          HEIGHT=ResourceMgr.explodes[0].getHeight();

        private tankFrame tankFrame=null;//持有主界面的引用
        private  int x ,y;
        private int step=0;
        public Explode(int x, int y, tankFrame tankFrame) {
            this.x = x;
            this.y = y;
            this.tankFrame=tankFrame;
            new Thread(()->new Audio("audio/explode.wav").play()).start();
        }

        void paint(Graphics g) {
            g.drawImage(ResourceMgr.explodes[step++],x,y,null);
           if(step>=ResourceMgr.explodes.length)
               tankFrame.explodes.remove(this);
        }

}
