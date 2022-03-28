import tank.ResourceMgr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        try {
            BufferedImage goodTank1= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
