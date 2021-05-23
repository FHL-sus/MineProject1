import javax.swing.*;
import java.awt.*;

public class Scoreboard extends JPanel {
    Player player1 = new Player("魏薛涛");
    Player player2 = new Player("贺铭鑫");
    public Player onTurn = player1;
    JPanel numText = new JPanel();
    JPanel players = new JPanel();
    public static int numbs;//判断雷数
    JLabel number = new JLabel("剩余地雷个数       " + numbs);
    JLabel on = new JLabel("当前玩家      " + onTurn.name);
    boolean initial = true;//判断是否为游戏刚开始
    int n = 0;
    int turns;

    //判断游戏是否结束
    boolean end = false;
    boolean same = false;//判断是否平局
    Player winner;//判断哪位玩家胜出

    public Scoreboard(int turns){
        this.setSize(300,500);
        this.setOpaque(false);
        this.setBackground(Color.WHITE);
        this.turns = turns;
        add(players);
        add(numText);
        add(on);
        players.setLayout(new GridLayout(2,1));
        players.setBackground(Color.WHITE);
        players.setOpaque(false);
        numText.setBackground(Color.WHITE);
        numText.setOpaque(false);
        numText.setBounds(0,20,200,200);
        on.setBackground(Color.WHITE);
        on.setOpaque(false);
        on.setBounds(80,200,200,200);
        on.setFont(Player.font);
        players.add(player1);
        players.add(player2);
        number.setFont(Player.font);
        numText.add(number);
        players.setBounds(-200,30,200,700);
        this.setVisible(true);
    }

    public void refresh(){
        onTurn.refreshData();
        numbs = GameWindow.getMineNum();
        number.setText("剩余地雷个数       " + numbs);
        finalJudge();
    }

    public void conRefresh(){
        player1.label1.setText(player1.name +"-分数:     "+player1.score);
        player1.label2.setText(player1.name +"-失误数:   "+player1.mistake);
        player2.label1.setText(player2.name +"-分数:     "+player2.score);
        player2.label2.setText(player2.name +"-失误数:   "+player2.mistake);
        number.setText("剩余地雷个数       " + numbs);
        finalJudge();
    }

    public void takeTurn(){
        if(n >= turns){
            if(onTurn == player1){
                onTurn = player2;
            }
            else{
                onTurn = player1;
            }
            on.setText("当前玩家      " + onTurn.name);
            this.n = 0;
            turnJudge();
        }
    }

    public void turnJudge(){
        int score1 = player1.score;
        int score2 = player2.score;
        if(Math.abs(score1-score2) > numbs){
            if(score1 - score2 > numbs) winner = player1;
            else winner = player2;
            end = true;//游戏结束
        }
        if(score1 <= 0 || score2 <= 0){
            if(score1 <= 0) winner = player2;
            if(score2 <= 0) winner = player1;
            end = true;
        }
    }

    public void finalJudge(){
        int score1 = player1.score;
        int score2 = player2.score;
        int mistake1 = player1.mistake;
        int mistake2 = player2.mistake;
        if(numbs == 0){
            end = true;//游戏结束
            if(score1 - score2 > 0) winner = player1;
            else if(score1 - score2 < 0) winner = player2;
            else {
                if (mistake1 < mistake2) winner = player1;
                else if (mistake1 > mistake2) winner = player2;
                else same = true;
            }
        }
    }

    public void clickOnce(){//鼠标点击事件发生一次
        n++;
        refresh();
        takeTurn();
    }
}
