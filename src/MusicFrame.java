import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MusicFrame extends JFrame implements ItemListener {
    private MidBackground mid;
    JPanel panel1 = new JPanel(new FlowLayout());
    JPanel panel2 = new JPanel(new FlowLayout());
    JPanel panel7 = new JPanel(new FlowLayout());
    JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
    JRadioButton btnPlay = new JRadioButton("播放音乐");
    JRadioButton btnStop = new JRadioButton("暂停音乐");
    JRadioButton btnTrue = new JRadioButton("打开音效");
    JRadioButton btnFalse = new JRadioButton("关闭音效");
    JRadioButton btnRec = new JRadioButton("开启递归");
    JRadioButton btnNotRec = new JRadioButton("关闭递归");
    JButton btnReturn = new JButton("返回菜单");
    JPanel panel4 = new JPanel(new FlowLayout());//wei的头像
    JPanel panel5 = new JPanel(new FlowLayout());//he的头像
    JPanel panel6 = new JPanel(new FlowLayout());
    private static ImageIcon icon1 = new ImageIcon("src/pic/Portrait1.png");
    private static ImageIcon icon2 = new ImageIcon("src/pic/Portrait2.png");
    private static ImageIcon icon3 = new ImageIcon("src/pic/Portrait3.png");
    private static ImageIcon icon4 = new ImageIcon("src/pic/Portrait4.png");
    private static ImageIcon icon5 = new ImageIcon("src/pic/Portrait5.png");
    private static ImageIcon icon6 = new ImageIcon("src/pic/Portrait6.png");
    private static ImageIcon icon7 = new ImageIcon("src/pic/Portrait7.png");
    private static ImageIcon icon8 = new ImageIcon("src/pic/Portrait8.png");
    private static ImageIcon icon9 = new ImageIcon("src/pic/Portrait9.png");
    private static ImageIcon icon10 = new ImageIcon("src/pic/Portrait10.png");

    public MusicFrame(){
        super("扫雷");
        this.setSize(1012,700);//界面的大小
        this.setLocationRelativeTo(null);//界面的位置设置到中间
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);


        ButtonGroup group1 = new ButtonGroup();
        ButtonGroup group2 = new ButtonGroup();
        ButtonGroup group3 = new ButtonGroup();
        Font font = new Font("微软雅黑",Font.BOLD,16);
        btnPlay.setFont(font);
        btnStop.setFont(font);
        btnTrue.setFont(font);
        btnFalse.setFont(font);
        btnRec.setFont(font);
        btnNotRec.setFont(font);
        group1.add(btnPlay);
        group1.add(btnStop);
        group2.add(btnTrue);
        group2.add(btnFalse);
        group3.add(btnRec);
        group3.add(btnNotRec);
        btnPlay.addItemListener(this);
        btnStop.addItemListener(this);
        btnTrue.addItemListener(this);
        btnFalse.addItemListener(this);
        btnRec.addItemListener(this);
        btnNotRec.addItemListener(this);
        btnPlay.setFocusPainted(false);
        btnStop.setFocusPainted(false);
        btnTrue.setFocusPainted(false);
        btnFalse.setFocusPainted(false);
        btnRec.setFocusPainted(false);
        btnNotRec.setFocusPainted(false);
        btnPlay.setOpaque(false);
        btnStop.setOpaque(false);
        btnTrue.setOpaque(false);
        btnFalse.setOpaque(false);
        btnRec.setOpaque(false);
        btnNotRec.setOpaque(false);

        btnReturn.setFont(new Font("楷书",Font.BOLD,20));
        btnReturn.setMargin(new Insets(0,0,0,0));
        btnReturn.setPreferredSize(new Dimension(115,45));
        btnReturn.setBorder(BorderFactory.createRaisedBevelBorder());
        btnReturn.setFocusPainted(false);
        Color color = btnReturn.getBackground();
        GameFrame2.setProperty(panel3);
        panel3.add(btnReturn);
        panel3.setLocation(422,480);
        btnReturn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameFrame2();
                dispose();
            }
        });

        panel1.add(btnPlay);
        panel1.add(btnStop);
        panel1.setBounds(422,20,200,40);
        panel1.setOpaque(true);
        panel2.add(btnTrue);
        panel2.add(btnFalse);
        panel2.setBounds(422,60,200,40);
        panel2.setOpaque(true);
        panel7.add(btnRec);
        panel7.add(btnNotRec);
        panel7.setBounds(422,100,200,40);
        panel7.setOpaque(true);

        JLabel label = new JLabel("请选择魏薛涛和贺铭鑫的人物形象(上方为魏薛涛，下方为贺铭鑫):");
        label.setFont(new Font("华文新魏",Font.BOLD,22));
        JButton btn1 = new JButton(icon1);
        JButton btn2 = new JButton(icon2);
        JButton btn3 = new JButton(icon3);
        JButton btn4 = new JButton(icon4);
        JButton btn5 = new JButton(icon5);
        JButton btn6 = new JButton(icon6);
        JButton btn7 = new JButton(icon7);
        JButton btn8 = new JButton(icon8);
        JButton btn9 = new JButton(icon9);
        JButton btn10 = new JButton(icon10);
        btn1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameWindow.setWei(icon1);
                btn1.setBackground(Color.PINK);
                btn2.setBackground(color);
                btn3.setBackground(color);
                btn4.setBackground(color);
                btn5.setBackground(color);
            }
        });
        btn2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameWindow.setWei(icon2);
                btn2.setBackground(Color.PINK);
                btn1.setBackground(color);
                btn3.setBackground(color);
                btn4.setBackground(color);
                btn5.setBackground(color);
            }
        });
        btn3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameWindow.setWei(icon3);
                btn3.setBackground(Color.PINK);
                btn1.setBackground(color);
                btn2.setBackground(color);
                btn4.setBackground(color);
                btn5.setBackground(color);
            }
        });
        btn4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameWindow.setWei(icon4);
                btn4.setBackground(Color.PINK);
                btn1.setBackground(color);
                btn2.setBackground(color);
                btn3.setBackground(color);
                btn5.setBackground(color);
            }
        });
        btn5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameWindow.setWei(icon5);
                btn5.setBackground(Color.PINK);
                btn1.setBackground(color);
                btn2.setBackground(color);
                btn3.setBackground(color);
                btn4.setBackground(color);
            }
        });
        btn6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameWindow.setHe(icon6);
                btn6.setBackground(Color.PINK);
                btn7.setBackground(color);
                btn8.setBackground(color);
                btn9.setBackground(color);
                btn10.setBackground(color);
            }
        });
        btn7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameWindow.setHe(icon7);
                btn7.setBackground(Color.PINK);
                btn6.setBackground(color);
                btn8.setBackground(color);
                btn9.setBackground(color);
                btn10.setBackground(color);
            }
        });
        btn8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameWindow.setHe(icon8);
                btn8.setBackground(Color.PINK);
                btn6.setBackground(color);
                btn7.setBackground(color);
                btn9.setBackground(color);
                btn10.setBackground(color);
            }
        });
        btn9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameWindow.setHe(icon9);
                btn9.setBackground(Color.PINK);
                btn6.setBackground(color);
                btn7.setBackground(color);
                btn8.setBackground(color);
                btn10.setBackground(color);
            }
        });
        btn10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameWindow.setHe(icon10);
                btn10.setBackground(Color.PINK);
                btn6.setBackground(color);
                btn7.setBackground(color);
                btn8.setBackground(color);
                btn9.setBackground(color);
            }
        });
        panel4.add(btn1);
        panel4.add(btn2);
        panel4.add(btn3);
        panel4.add(btn4);
        panel4.add(btn5);
        panel5.add(btn6);
        panel5.add(btn7);
        panel5.add(btn8);
        panel5.add(btn9);
        panel5.add(btn10);
        panel4.setOpaque(true);
        panel5.setOpaque(true);
        panel4.setBounds(82,180,880,150);
        panel5.setBounds(82,330,880,150);
        panel6.add(label);
        panel6.setBounds(190,152,700,28);

        setBackground();
        mid.add(panel1);
        mid.add(panel2);
        mid.add(panel3);
        mid.add(panel4);
        mid.add(panel5);
        mid.add(panel6);
        mid.add(panel7);
        add(mid);
        add(GameFrame2.getPanel3());
        this.setVisible(true);
    }

    private void setBackground(){
        ImageIcon pic = new ImageIcon(getClass().getResource("pic\\pic2.png"));
        mid = new MidBackground(null,pic);
        mid.setSize(1600,850);
        mid.setLocation(-18,65);//图
    }

    @Override
    public void itemStateChanged(ItemEvent e){
        if(e.getSource() == btnPlay){
            Music.mac.loop();
        }
        else if(e.getSource() == btnStop){
            Music.mac.stop();
        }
        else if(e.getSource() == btnTrue){
            Board.isMusic = true;
        }
        else if(e.getSource() == btnFalse){
            Board.isMusic = false;
        }
        else if(e.getSource() == btnRec){
            Board.isRecursion = true;
        }
        else if(e.getSource() == btnNotRec){
            Board.isRecursion = false;
        }
    }
}
