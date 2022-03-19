package tank;

import M.GameModel;

public class T {
    public static void main(String[] args) throws InterruptedException {
         tankFrame t=new tankFrame();
        new Thread(()->new Audio("audio/war1.wav").loop()).start();
         while(true){
             Thread.sleep(50);
             t.repaint();
         }
    }
}
