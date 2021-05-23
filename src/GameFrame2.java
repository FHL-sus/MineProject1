import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame2 extends JFrame {//选择菜单界面

    private JButton btnStart = new JButton("开始游戏");
    private JButton btnIntro = new JButton("游戏介绍");
    private JButton btnSetting = new JButton("游戏设置");
    private JButton btnInfo = new JButton("玩家信息");
    private JButton btnExit = new JButton("退出游戏");
    private static boolean test = true;
    //创建五个按钮

    private MidBackground mid;
    private JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));//按钮
    public static JPanel panel3 = new JPanel();//上方label
    //菜单栏面板

    public GameFrame2() {
        super("扫雷");
        this.setSize(1012, 700);//界面的大小
        this.setLocationRelativeTo(null);//界面的位置
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        if (GameFrame2.isTest()) {
            setTopLabel();
        }
        setBackground();

        setProperty(panel1);
        setButton(btnStart);
        setButton(btnIntro);
        setButton(btnSetting);
        setButton(btnInfo);
        setButton(btnExit);
        btnStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new BeginGameFrame();
                dispose();
            }
        });
        btnIntro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameFrame5();
                dispose();
            }
        });
        btnSetting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new MusicFrame();
                dispose();
            }
        });
        btnInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new InfoFrame();
                dispose();
            }
        });
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        panel1.add(btnStart);
        panel1.add(btnIntro);
        panel1.add(btnSetting);
        panel1.add(btnInfo);
        panel1.add(btnExit);
        panel1.setLocation(430, 130);//按钮

        mid.add(panel1);
        add(mid);
        add(panel3);
        GameFrame2.setTest(false);
        this.setVisible(true);
    }

    public static JPanel getPanel3() {
        return panel3;
    }
    //******************************
    //选择界面

    public static void setTopLabel() {
        //制作上方的label
        JLabel title = new JLabel("MineSweeper 扫雷");
        title.setFont(new Font("", Font.ROMAN_BASELINE, 30));
        panel3.add(title);
        panel3.setSize(1012, 70);
        panel3.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(0x0000)));
        panel3.setOpaque(true);
    }

    private void setBackground() {
        //导入背景图
        ImageIcon bg = new ImageIcon(getClass().getResource("pic\\pic2.png"));
        mid = new MidBackground(null, bg);
        mid.setSize(1600, 850);
        mid.setLocation(-18, 65);//图
    }

    public static void setProperty(JPanel panel) {
        panel.setSize(200, 500);
        panel.setBackground(new Color(0, 0, 0, 0));
    }

    public static void setButton(JButton button) {
        button.setBackground(Color.LIGHT_GRAY);
        button.setFont(new Font("楷书", Font.HANGING_BASELINE, 20));
        button.setPreferredSize(new Dimension(180, 45));
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setFocusPainted(false);
    }

    public static void setTest(boolean b) {
        test = b;
    }

    public static boolean isTest() {
        return test;
    }

}