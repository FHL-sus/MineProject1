import javax.swing.*;
import java.awt.*;

public class MidBackground extends JPanel {
    private ImageIcon bg;

    public MidBackground(LayoutManager layout, ImageIcon bg) {
        super(layout);
        this.bg = bg;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        bg.paintIcon(this, g, 0, 0);
        printComponents(g);
    }
}