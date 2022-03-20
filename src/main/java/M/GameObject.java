package M;

import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
        int x ,y;
      public abstract void paint(Graphics g);
      public abstract int getWidth();
      public abstract int getHeight();
}
