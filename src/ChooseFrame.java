import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChooseFrame extends JFrame {//难度选择
    private MidBackground mid;
    private JButton btnSimple = new JButton("初级");
    private JButton btnMid = new JButton("中级");
    private JButton btnDifficult = new JButton("高级");
    private JButton btnSetting = new JButton("自定义");
    private JButton btnReturn = new JButton("返回菜单");
    private JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));//按钮


    public  ChooseFrame(){
        super("扫雷");
        this.setSize(1012,700);//界面的大小
        this.setLocationRelativeTo(null);//界面的位置设置到中间
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        btnSimple.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameWindow(9,9,10,5);
                GameWindow.midTime1 = 3600;
                GameWindow.midTime2 = 3600;
                GameWindow.label1.setText("魏薛涛-剩余时间:      " +
                        (1 + ":" + 0 + ":" + 0));
                GameWindow.label2.setText("贺铭鑫-剩余时间:      " +
                        (1 + ":" + 0 + ":" + 0));
                if(GameWindow.isFirst()){
                    GameWindow.CountDown cd = new GameWindow.CountDown();
                    cd.start();
                    GameWindow.setFirst(false);
                }
                dispose();
            }
        });
        btnMid.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameWindow(16,16,40,5);
                GameWindow.midTime1 = 3600;
                GameWindow.midTime2 = 3600;
                GameWindow.label1.setText("魏薛涛-剩余时间:      " +
                        (1 + ":" + 0 + ":" + 0));
                GameWindow.label2.setText("贺铭鑫-剩余时间:      " +
                        (1 + ":" + 0 + ":" + 0));
                if(GameWindow.isFirst()){
                    GameWindow.CountDown cd = new GameWindow.CountDown();
                    cd.start();
                    GameWindow.setFirst(false);
                }
                dispose();
            }
        });
        btnDifficult.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameWindow(16,30,99,5);
                GameWindow.midTime1 = 3600;
                GameWindow.midTime2 = 3600;
                GameWindow.label1.setText("魏薛涛-剩余时间:      " +
                        (1 + ":" + 0 + ":" + 0));
                GameWindow.label2.setText("贺铭鑫-剩余时间:      " +
                        (1 + ":" + 0 + ":" + 0));
                if(GameWindow.isFirst()){
                    GameWindow.CountDown cd = new GameWindow.CountDown();
                    cd.start();
                    GameWindow.setFirst(false);
                }
                dispose();
            }
        });
        btnReturn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameFrame2();
                dispose();
            }
        });
        btnSetting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SelfFrame();
                dispose();
            }
        });
        GameFrame2.setProperty(panel1);
        GameFrame2.setButton(btnSimple);
        GameFrame2.setButton(btnMid);
        GameFrame2.setButton(btnDifficult);
        GameFrame2.setButton(btnSetting);
        GameFrame2.setButton(btnReturn);
        panel1.add(btnSimple);
        panel1.add(btnMid);
        panel1.add(btnDifficult);
        panel1.add(btnSetting);
        panel1.add(btnReturn);
        panel1.setLocation(430,130);//按钮


        setBackground();
        mid.add(panel1);
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