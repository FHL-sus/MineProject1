import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RestartFrame extends JFrame {
    private JLabel label = new JLabel("确认是否重启游戏：");
    private JButton btnYes = new JButton("Yes");
    private JButton btnNo = new JButton("No");
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private GameWindow gameWindow;
    public RestartFrame(GameWindow gameWindow){
        super("扫雷");
        this.setSize(500,250);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(null);

        panel1.add(btnYes);
        panel2.add(btnNo);
        this.gameWindow = gameWindow;
        btnYes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null,"游戏已经重新开始","提示：",JOptionPane.INFORMATION_MESSAGE);
                new GameWindow(GameWindow.anRow, GameWindow.anCol, GameWindow.beginningNum,GameWindow.turns);
                gameWindow.dispose();
                GameWindow.midTime1 = 3600;
                GameWindow.midTime2 = 3600;
                GameWindow.label1.setText("魏薛涛-剩余时间:      " +
                        (1 + ":" + 0 + ":" + 0));
                GameWindow.label2.setText("贺铭鑫-剩余时间:      " +
                        (1 + ":" + 0 + ":" + 0));
                dispose();
            }
        });
        btnNo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        add(panel1);
        add(panel2);
        add(label);
        label.setFont(GameWindow.font);
        label.setBounds(150,50,500,35);
        panel1.setBackground(new Color(0, 0, 0, 0));
        panel2.setBackground(new Color(0, 0, 0, 0));
        panel1.setBounds(100,90,100,200);
        panel2.setBounds(260,90,100,200);
        GameFrame2.setButton(btnYes);
        GameFrame2.setButton(btnNo);
        this.setVisible(true);
    }
}
