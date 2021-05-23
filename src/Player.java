import javax.swing.*;
import java.awt.*;

public class Player extends JPanel {
    String name;
    int score = 10;
    int mistake = 0;
    static Font font = GameWindow.font;
    JLabel label1;
    JLabel label2;
    public Player(String name){
        this.name = name;
        label1 = new JLabel(name +"-分数:      "+score);
        label2 = new JLabel(name +"-失误数:    "+mistake);
        this.setLayout(new GridLayout(4,1));
        this.add(label1);
        this.add(label2);
        label1.setBounds(20,10,100,100);
        label2.setBounds(20,70,100,100);
        label1.setFont(font);
        label2.setFont(font);
        this.setOpaque(false);
    }
    public void addScore(){
        score++;
    }
    public void deductScore(){
        score--;
    }
    public void addMistake(){
        mistake++;
    }
    public void refreshData(){
        label1.setText(name +"-分数:     "+score);
        label2.setText(name +"-失误数:   "+mistake);
    }
}
