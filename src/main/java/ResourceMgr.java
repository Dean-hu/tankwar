
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage tankL,tankR,tankU,tankD
    ,bulletL,bulletR,bulletU,bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];
    static {
        try {
            tankL=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankR=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankU=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankD=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
          bulletL=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
          bulletR=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
          bulletU=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
          bulletD=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
          for(int i=1;i<=16;i++){
              explodes[i-1]=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+i+".gif"));
          }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
