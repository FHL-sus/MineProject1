import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame7 extends JFrame{//游戏规则3
    private MidBackground mid;
    private JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));//返回按钮
    private JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));//上一页按钮

    public GameFrame7(){
        super("扫雷");
        this.setSize(1012,700);//界面的大小
        this.setLocationRelativeTo(null);//界面的位置
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        setBackground();
        JButton btnReturn = new JButton("返回菜单");
        JButton btnLast = new JButton("上一页");

        btnReturn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameFrame2();
                dispose();
            }
        });
        btnLast.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameFrame6();
                dispose();
            }
        });

        btnLast.setFont(new Font("楷书",Font.BOLD,20));
        btnLast.setMargin(new Insets(0,0,0,0));
        btnLast.setPreferredSize(new Dimension(80,45));
        btnLast.setBorder(BorderFactory.createRaisedBevelBorder());
        btnLast.setFocusPainted(false);
        btnReturn.setFont(new Font("楷书",Font.BOLD,20));
        btnReturn.setMargin(new Insets(0,0,0,0));
        btnReturn.setPreferredSize(new Dimension(115,45));
        btnReturn.setBorder(BorderFactory.createRaisedBevelBorder());
        btnReturn.setFocusPainted(false);
        GameFrame2.setProperty(panel1);
        GameFrame2.setProperty(panel3);
        panel1.add(btnReturn);
        panel3.add(btnLast);
        panel1.setLocation(430,544);
        panel3.setLocation(-53,544);

        mid.add(panel1);
        mid.add(panel3);
        add(mid);
        add(GameFrame2.getPanel3());
        this.setVisible(true);
    }

    private void setBackground(){
        ImageIcon pic = new ImageIcon(getClass().getResource("pic\\pic5.png"));
        mid = new MidBackground(null,pic);
        mid.setSize(1600,850);
        mid.setLocation(-5,43);//图
    }
}
