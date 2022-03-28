package tank;

import Client.Client;
import M.GameModel;

public class T {
    public static void main(String[] args) throws InterruptedException {
         tankFrame t=new tankFrame();

        new Thread(()->new Audio("audio/war1.wav").loop()).start();
        new Thread(()->{
            while(true){
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t.repaint();
            }
        }).start();

         Client.INSTANCE.connect();
    }
}
