package tank;

import M.GameModel;
import M.GameObject;

import java.awt.*;
import java.util.UUID;

public class Explode extends GameObject {

        public static int WIDTH=ResourceMgr.explodes[0].getWidth(),
                          HEIGHT=ResourceMgr.explodes[0].getHeight();

        private  int x ,y;
        private int step=0;
        public UUID id=UUID.randomUUID();
        public Explode(int x, int y) {
            this.x = x;
            this.y = y;
            new Thread(()->new Audio("audio/explode.wav").play()).start();
        }

        public void paint(Graphics g) {
            g.drawImage(ResourceMgr.explodes[step++],x,y,null);
           if(step>=ResourceMgr.explodes.length)
                 GameModel.getInstance().objects.remove(id);
        }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

}
