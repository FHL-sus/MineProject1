import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame6 extends JFrame {//游戏规则2
    private MidBackground mid;
    private JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));//返回按钮
    private JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));//下一页按钮
    private JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));//上一页按钮
    public GameFrame6(){
        super("扫雷");
        this.setSize(1012,700);//界面的大小
        this.setLocationRelativeTo(null);//界面的位置
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        setBackground();

        JButton btnReturn = new JButton("返回菜单");
        JButton btnNext = new JButton("下一页");
        JButton btnLast = new JButton("上一页");

        btnReturn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameFrame2();
                dispose();
            }
        });
        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameFrame7();
                dispose();
            }
        });
        btnLast.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameFrame5();
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
        btnNext.setFont(new Font("楷书",Font.BOLD,20));
        btnNext.setMargin(new Insets(0,0,0,0));
        btnNext.setPreferredSize(new Dimension(80,45));
        btnNext.setBorder(BorderFactory.createRaisedBevelBorder());
        btnNext.setFocusPainted(false);
        GameFrame2.setProperty(panel1);
        GameFrame2.setProperty(panel2);
        GameFrame2.setProperty(panel3);
        panel1.add(btnReturn);
        panel2.add(btnNext);
        panel3.add(btnLast);
        //设置按钮的位置
        panel1.setLocation(430,544);
        panel2.setLocation(861,544);
        panel3.setLocation(-53,544);

        mid.add(panel1);
        mid.add(panel2);
        mid.add(panel3);
        add(mid);
        add(GameFrame2.getPanel3());
        this.setVisible(true);
    }

    private void setBackground(){
        ImageIcon pic = new ImageIcon(getClass().getResource("pic\\pic4.png"));
        mid = new MidBackground(null,pic);
        mid.setSize(1600,850);
        mid.setLocation(-5,43);//图
    }

}
