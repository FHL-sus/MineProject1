import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FirstFrame extends JFrame {//开始第一张界面
    JPanel jp_right;
    MidBackground jp_mid;
    public FirstFrame(){//开始进入游戏封面`
        super("扫雷");
        this.setSize(1012,700);//界面的大小
        this.setLocationRelativeTo(null);//界面的位置
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        ImageIcon bg = new ImageIcon(getClass().getResource("pic\\pic1.png"));
        jp_mid = new MidBackground(null,bg);
        jp_mid.setSize(1000,666);//图片的大小
        jp_mid.setLocation(0,0);//图片的位置

        jp_right = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        jp_right.setLocation(400,450);
        jp_right.setSize(200,500);
        jp_right.setBackground(new Color(0,0,0,0));
        JButton btnStart = new JButton("点击进入游戏");

        btnStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameFrame();
                dispose();
            }
        });
        btnStart.setFont(new Font("宋体",Font.BOLD,26));
        btnStart.setPreferredSize(new Dimension(200,50));
        btnStart.setBackground(Color.cyan);
        btnStart.setBorderPainted(true);
        btnStart.setBorder(BorderFactory.createRaisedBevelBorder());
        btnStart.setFocusPainted(false);
        jp_right.add(btnStart);
        jp_mid.add(jp_right);
        add(jp_mid);
        this.setVisible(true);
    }

}


