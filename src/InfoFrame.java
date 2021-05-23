import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;

public class InfoFrame extends JFrame {
    private MidBackground mid;
    JPanel panel = new JPanel();
    JPanel rankP = new JPanel();
    JPanel btnP = new JPanel();
    static int weiScore = 0;
    static int heScore = 0;
    static int weiMis = 0;
    static int heMis = 0;
    JLabel label1 = new JLabel("魏薛涛的净得分为：" + weiScore);
    JLabel label2 = new JLabel("贺铭鑫的净得分为：" + heScore);
    JLabel label3 = new JLabel("魏薛涛的总失误数为：" + weiMis);
    JLabel label4 = new JLabel("贺铭鑫的总失误数为：" + heMis);
    JLabel label = new JLabel();
    ImageIcon icon = new ImageIcon("src/pic/rank.png");
    JButton button = new JButton("返回菜单");
    private static Font font;

    static {
        try {
            font = Font.createFont( Font.TRUETYPE_FONT,
                    new FileInputStream("src/font/YanShiXiaXingKai-2.ttf") );
        } catch(Exception e) {
            e.printStackTrace();
        }
        font = font.deriveFont(Font.PLAIN, 24);
    }

    public InfoFrame(){
        super("扫雷");
        this.setSize(1012,700);//界面的大小
        this.setLocationRelativeTo(null);//界面的位置设置到中间
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        label.setIcon(icon);
        rankP.add(label);
        label1.setText("魏薛涛的净得分为：" + weiScore);
        label2.setText("贺铭鑫的净得分为：" + heScore);
        label3.setText("魏薛涛的总失误数为：" + weiMis);
        label4.setText("贺铭鑫的总失误数为：" + heMis);
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        rankP.setOpaque(false);
        panel.setOpaque(false);
        rankP.setBounds(-52,-4,400,400);
        panel.setBounds(650,20,320,200);

        GameFrame2.setButton(button);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameFrame2();
                dispose();
            }
        });
        btnP.add(button);
        btnP.setOpaque(false);
        btnP.setBounds(70,270,150,100);

        setBackground();
        mid.add(panel);
        mid.add(rankP);
        mid.add(btnP);
        add(mid);
        add(GameFrame2.getPanel3());
        this.setVisible(true);
    }

    private void setBackground(){
        //导入背景图
        ImageIcon bg = new ImageIcon(getClass().getResource("pic\\pic2.png"));
        mid = new MidBackground(null,bg);
        mid.setSize(1600,850);
        mid.setLocation(-18,65);//图
    }

}
