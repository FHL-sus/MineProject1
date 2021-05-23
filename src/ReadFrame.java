import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReadFrame extends JFrame {
    static BeginGameFrame beginGameFrame;
    JTextField text = new JTextField();
    JLabel label = new JLabel("请输入存档的名称:");
    JButton button = new JButton("确认");

    public ReadFrame(BeginGameFrame beginGameFrame) {
        super("读档窗口");
        this.setLocationRelativeTo(null);
        this.setSize(250, 240);
        text.setFont(GameWindow.font);
        text.setBounds(20, 150, 100, 40);
        label.setFont(new Font("宋体", Font.BOLD, 20));
        label.setBounds(30, -20, 300, 20);
        button.setVisible(true);
        button.setFont(new Font("宋体", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBounds(135, 150, 60, 40);
        //设置按钮连接事件得到存档txt文件

        add(button);
        add(text);
        add(label);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Save readSave = new Save();
                    readSave.getSave(text.getText());
                    new GameWindow(readSave.getBoard(), readSave.getScoreboard(), readSave.midTime1,readSave.midTime2,readSave.wei,readSave.he);
                    GameWindow.scoreboard.initial = false;
                    ReadFrame.beginGameFrame = beginGameFrame;
                    beginGameFrame.dispose();
                    dispose();
                }catch (Exception d){
                    JOptionPane.showMessageDialog(null,"请输入正确的存档名！","Warning",JOptionPane.ERROR_MESSAGE);
                }
            }});
        setVisible(true);
    }
}