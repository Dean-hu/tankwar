import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageTest {
    Firestrategies f;
    @Test
    void test(){
//        String s = (String) PropertyMrg.props.get("goodFS");
        try {
            f=(Firestrategies)Class.forName("FourDriFireStrategy").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();}
        assertNotNull(f);
    }

}
