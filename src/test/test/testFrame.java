import tools.RotateImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class testFrame<g> extends Frame
{
     public testFrame(){
         setSize(500,500);
         setResizable(false);
         setTitle("test");
         setVisible(true);
         addWindowListener(new WindowAdapter() {
             @Override
             public void windowClosing(WindowEvent e) {
                       System.exit(0);
             }
         });
     }
    BufferedImage a;
    {
        try {
            a = ImageIO.read(testFrame.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(a,100,100,null);
    }
}
