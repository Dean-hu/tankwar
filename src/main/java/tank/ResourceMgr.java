package tank;

import tools.ImageUtil;
import tools.RotateImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage goodTank1,goodTank2,badTank1,badTank2
    ,bulletL,bulletR,bulletU,bulletD,bulletLU,bulletRU,bulletLD,bulletRD;
    public static BufferedImage[] explodes = new BufferedImage[16];
    static {
        try {
            goodTank1=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTank2=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank2.png"));

            badTank1=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTank2=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank2.png"));


            bulletU=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL= ImageUtil.rotateImage(bulletU,270);
            bulletR= ImageUtil.rotateImage(bulletU,90);
            bulletD=ImageUtil.rotateImage(bulletU,180);
            bulletLU= ImageUtil.rotateImage(bulletU,315);
            bulletRU= ImageUtil.rotateImage(bulletU,45);
            bulletRD=ImageUtil.rotateImage(bulletU,135);
            bulletLD= ImageUtil.rotateImage(bulletU,225);
          for(int i=1;i<=16;i++){
              explodes[i-1]=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+i+".gif"));
          }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
