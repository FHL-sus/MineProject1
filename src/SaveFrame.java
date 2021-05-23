import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SaveFrame extends JFrame {
    JTextField text = new JTextField();
    JLabel label = new JLabel("请输入存档的名称:");
    JButton button = new JButton("确认");
    static Board board;
    static Scoreboard scoreboard;
    static int midTime1;
    static int midTime2;
    ImageIcon wei;
    ImageIcon he;
    public SaveFrame(Board board, Scoreboard scoreboard,int midTime1,int midTime2,ImageIcon wei,ImageIcon he,int turns){
        super("存档窗口");
        this.setLocationRelativeTo(null);
        this.setSize(250,250);
        SaveFrame.board = board;
        SaveFrame.scoreboard = scoreboard;
        SaveFrame.midTime1 = midTime1;
        SaveFrame.midTime2 = midTime2;
        this.wei = wei;
        this.he = he;
        text.setFont(GameWindow.font);
        text.setBounds(20,150,100,40);
        label.setFont(new Font("宋体", Font.BOLD,20));
        label.setBounds(30,-20,300,20);
        button.setVisible(true);
        button.setFont(new Font("宋体", Font.BOLD,12));
        button.setFocusPainted(false);
        button.setBounds(135,150,60,40);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Save save = new Save(board,scoreboard,midTime1,midTime2,wei,he,turns);
                save.newSave(text.getText());
                dispose();
            }
        });
        //设置按钮连接事件得到存档txt文件
        add(button);
        add(text);
        add(label);
        this.setVisible(true);
    }
}
