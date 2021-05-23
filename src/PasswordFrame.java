import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordFrame extends JFrame implements ActionListener {
    JPasswordField passwordField = new JPasswordField();
    JLabel label = new JLabel("请输入管理员密码：");
    JButton button = new JButton("确定进入");
    JPanel panel = new JPanel();
    private static final String password = "123456";
    private Board board;
    public PasswordFrame(Board board){
        super("扫雷");
        this.setSize(500,250);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(null);

        this.board = board;
        label.setFont(GameWindow.font);
        label.setBounds(50,60,500,35);
        GameFrame2.setButton(button);
        button.setBackground(null);
        button.setFocusPainted(true);
        button.addActionListener(this);
        button.setLocation(50,400);
        add(passwordField);
        passwordField.setFont(GameWindow.font);
        passwordField.setBounds(230,65,200,30);
        add(label);
        panel.add(button);
        panel.setBounds(270,120,100,100);
        add(panel);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(new String(passwordField.getPassword()).equals(password)){//密码正确的情况
            Board.isCheated = true;
            dispose();
        }
        else{
            passwordField.setText("");
            JOptionPane.showMessageDialog(null,"输入密码错误！请重新输入！","Warning",JOptionPane.ERROR_MESSAGE);
        }
    }

}
