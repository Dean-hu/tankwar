import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class T {
    public static void main(String[] args) throws InterruptedException {
         tankFrame t=new tankFrame();
         //初始化敌方坦克
        for(int i=0;i<5;i++){
               tankFrame.enemies.add(new Tank(50+i*100,100,Direction.DOWN,t,Group.BAD));
               tankFrame.enemies.get(i).setMoving(true);
        }
         while(true){
             Thread.sleep(50);
             t.repaint();
         }
    }
}
