import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BeginGameFrame extends JFrame implements ActionListener {//开始游戏的选项
    private MidBackground mid;
    private JButton btnNew = new JButton("新游戏");
    private JButton btnContinue = new JButton("继续游戏");
    private JButton btnTrain = new JButton("训练模式");
    private JButton btnReturn = new JButton("返回菜单");
    private JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));//按钮
    public BeginGameFrame(){
        super("扫雷");
        this.setSize(1012,700);//界面的大小
        this.setLocationRelativeTo(null);//界面的位置
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        btnNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ChooseFrame();
                dispose();
            }
        });
        btnTrain.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SingleMode();
                SingleMode.CountDown cd = new SingleMode.CountDown();
                cd.start();
                dispose();
            }
        });
        btnContinue.addActionListener(this);
        btnReturn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameFrame2();
                dispose();
            }
        });
        GameFrame2.setProperty(panel1);
        GameFrame2.setButton(btnTrain);
        GameFrame2.setButton(btnNew);
        GameFrame2.setButton(btnContinue);
        GameFrame2.setButton(btnReturn);
        panel1.add(btnNew);
        panel1.add(btnContinue);
        panel1.add(btnTrain);
        panel1.add(btnReturn);
        panel1.setLocation(430,160);//按钮

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

    @Override
    public void actionPerformed(ActionEvent e){
        new ReadFrame(this);
    }
}
