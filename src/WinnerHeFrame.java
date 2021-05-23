import javax.swing.*;
import java.applet.Applet;
import java.io.File;
import java.net.MalformedURLException;

public class WinnerHeFrame extends JFrame {
    MidBackground mid;
    Music mac = new Music();
    public WinnerHeFrame(){
        super("Victory");
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(null);
        setBackground();
        add(mid);
        this.setVisible(true);
        try {
            mac.setAudioClip(Applet
                    .newAudioClip((new File("src/Music/Victory.wav")).toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        mac.play();
    }

    private void setBackground(){
        ImageIcon bg = new ImageIcon("src/pic/WinHe.png");
        mid = new MidBackground(null,bg);
        mid.setSize(400,400);
        mid.setLocation(-10,0);
    }

}
