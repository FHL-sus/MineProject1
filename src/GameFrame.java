import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JFrame {//游戏背景
    private MidBackground mid;
    private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));//按钮
    public GameFrame(){
        super("扫雷");
        this.setSize(1012,700);//界面的大小
        this.setLocationRelativeTo(null);//界面的位置
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        setBackground();
        JButton button = new JButton();
        button.setIcon(new ImageIcon(getClass().getResource("pic\\button.png")));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameFrame2();
                dispose();
            }
        });
        panel.add(button);
        GameFrame2.setProperty(panel);
        panel.setLocation(755,586);
        mid.add(panel);
        add(mid);
        this.setVisible(true);
    }
    private void setBackground(){
        //导入背景图
        ImageIcon bg = new ImageIcon(getClass().getResource("pic\\pic6.png"));
        mid = new MidBackground(null,bg);
        mid.setSize(1000,666);
        mid.setLocation(0,0);//图
    }

}
