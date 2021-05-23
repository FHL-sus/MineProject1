import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelfFrame extends JFrame implements ActionListener {
    private MidBackground mid;
    private JPanel panel = new JPanel();//用来最终放置第一段的值
    private JPanel numOfMine = new JPanel();
    private JPanel turnNum = new JPanel();
    private JLabel label1 = new JLabel("请输入你想要设定的行数、列数和雷数");
    private JLabel label2 = new JLabel("行数↓不得大于24  列数↓不得大于30");
    private JLabel label3 = new JLabel("雷数不得超过↓总格子数的一半");
    private JLabel label4 = new JLabel("回合数：");
    JButton button = new JButton("进入游戏");
    JButton btnReturn = new JButton("返回菜单");
    JTextField row = new JTextField();
    JTextField col = new JTextField();
    JTextField numMine = new JTextField();
    JTextField turns = new JTextField();

    public SelfFrame(){
        super("扫雷");
        this.setSize(1012,700);//界面的大小
        this.setLocationRelativeTo(null);//界面的位置
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        panel.add(label1);
        panel.add(label2);
        numOfMine.add(label3);
        turnNum.add(label4);
        add(row);
        add(col);
        add(numMine);
        add(turns);
        add(button);
        add(btnReturn);
        label1.setFont(GameWindow.font);
        label2.setFont(GameWindow.font);
        label3.setFont(GameWindow.font);
        label4.setFont(GameWindow.font);
        label1.setBounds(200,200,120,60);
        label2.setBounds(200,260,120,60);
        label3.setBounds(200,260,120,60);
        label4.setBounds(200,260,120,60);
        row.setBounds(360,255,100,40);
        col.setBounds(548,255,100,40);
        numMine.setBounds(449,329,100,40);
        turns.setBounds(490,381,60,40);
        row.setFont(GameWindow.font);
        col.setFont(GameWindow.font);
        numMine.setFont(GameWindow.font);
        turns.setFont(GameWindow.font);
        GameFrame2.setButton(button);
        button.setBounds(450,460,100,50);
        button.addActionListener(this);
        GameFrame2.setButton(btnReturn);
        btnReturn.setBounds(450,530,100,50);
        btnReturn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameFrame2();
                dispose();
            }
        });
        panel.setBounds(335,180,342,75);
        numOfMine.setBounds(335,294,342,35);
        turnNum.setBounds(415,381,75,40);
        setBackground();
        add(panel);
        add(numOfMine);
        add(turnNum);
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
        try {
            if (Integer.parseInt(row.getText()) > 24 || Integer.parseInt(row.getText()) < 0 ||
                    Integer.parseInt(col.getText()) > 30 || Integer.parseInt(col.getText()) < 0 || Integer.parseInt(numMine.getText()) < 0 ||
                    Integer.parseInt(numMine.getText()) > Integer.parseInt(row.getText()) * Integer.parseInt(col.getText()) || Integer.parseInt(turns.getText()) <= 0) {
                row.setText("");
                col.setText("");
                numMine.setText("");
                turns.setText("");
                JOptionPane.showMessageDialog(null, "输入的行、列的值和雷数、回合数是无效的！", "Warning", JOptionPane.ERROR_MESSAGE);
            } else {//需要添加改变雷数的操作
                new GameWindow(Integer.parseInt(row.getText()), Integer.parseInt(col.getText()), Integer.parseInt(numMine.getText()), Integer.parseInt(turns.getText()));
                dispose();
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
            }
        }catch (Exception d){
            JOptionPane.showMessageDialog(null, "输入的行、列的值和雷数、回合数是无效的！", "Warning", JOptionPane.ERROR_MESSAGE);
            row.setText("");
            col.setText("");
            numMine.setText("");
            turns.setText("");
        }
    }

    public static void main(String[] args) {
        new SelfFrame();
    }
}
