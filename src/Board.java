import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.util.Random;

public class Board extends JPanel {
    int x;//行格子数
    int y;//列格子数
    int mineNum;//雷数
    int gridSize;//格子的大小
    public static boolean isMusic = true;
    public static boolean isCheated = false;
    public static boolean isRecursion = true;

    int[][] amount;//1 - 8代表格子周围雷的数目，9代表该格是雷
    JButton[][] buttons;
    State[][] states;
    JLabel[][] labels;
    static ImageIcon bomb = new ImageIcon("src/pic/雷.png");
    static ImageIcon m1 = new ImageIcon("src/pic/m1.png");
    static ImageIcon m2 = new ImageIcon("src/pic/m2.png");
    static ImageIcon m3 = new ImageIcon("src/pic/m3.png");
    static ImageIcon m4 = new ImageIcon("src/pic/m4.png");
    static ImageIcon m5 = new ImageIcon("src/pic/m5.png");
    static ImageIcon m6 = new ImageIcon("src/pic/m6.png");
    static ImageIcon m7 = new ImageIcon("src/pic/m7.png");
    static ImageIcon m8 = new ImageIcon("src/pic/m8.png");

    public Board(int x, int y, int xLong, int yLong, int mineNum) {//x代表的是有几列，y代表的是有几行，即xy直角坐标系
        //初始化上面声明的数组
        setLayout(null);
        setSize(xLong, yLong);
        this.x = x;
        this.y = y;
        this.mineNum = mineNum;
        if (x > y) this.gridSize = xLong / x;
        else this.gridSize = yLong / y;
        iniData();
        iniGrid();
        iniPic();
        iniButton();
        this.setVisible(true);
    }

    //以下方法使用时应按照顺序
    public void iniData() {//只用于设置amount数据，不用于贴图
        amount = new int[x][y];
        states = new State[x][y];
        do {
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    amount[i][j] = 0;
                }
            }
            for (int i = 0; i < mineNum; i++) {
                int rr = (int) (Math.random() * x);
                int cc = (int) (Math.random() * y);
                if (amount[rr][cc] != 9) amount[rr][cc] = 9;
                else i--;
            }
        } while (isDense());
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (amount[i][j] == 9) {
                    continue;
                }
                int bombNum = 0;
                if (i > 0 && j > 0 && amount[i - 1][j - 1] == 9) bombNum++;
                if (i > 0 && amount[i - 1][j] == 9) bombNum++;
                if (i > 0 && j < y - 1 && amount[i - 1][j + 1] == 9) bombNum++;
                if (j > 0 && amount[i][j - 1] == 9) bombNum++;
                if (j < y - 1 && amount[i][j + 1] == 9) bombNum++;
                if (i < x - 1 && j > 0 && amount[i + 1][j - 1] == 9) bombNum++;
                if (i < x - 1 && amount[i + 1][j] == 9) bombNum++;
                if (i < x - 1 && j < y - 1 && amount[i + 1][j + 1] == 9) bombNum++;
                amount[i][j] = bombNum;
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                states[i][j] = State.covered;//设置初始的格子状态全为covered
            }
        }
    }

    public boolean isDense() {//判断是否密集置雷
        boolean dense = false;
        if (x > 2 && y > 2) {
            for (int i = 0; i < x - 2; i++) {
                for (int j = 0; j < y - 2; j++) {
                    if (amount[i][j] == 9 && amount[i + 1][j] == 9 && amount[i + 2][j] == 9
                            && amount[i + 1][j] == 9 && amount[i + 1][j + 1] == 9 && amount[i + 1][j + 2] == 9
                            && amount[i + 2][j] == 9 && amount[i + 2][j + 1] == 9 && amount[i + 2][j + 2] == 9) {
                        dense = true;
                        break;
                    }
                }
            }
        }
        return dense;
    }

    public void iniGrid() {//初始化按钮之下的格子
        labels = new JLabel[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                JLabel l = new JLabel("", JLabel.CENTER);
                l.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                l.setBackground(Color.LIGHT_GRAY);
                labels[i][j] = l;
                l.setBounds(i * gridSize, j * gridSize, gridSize, gridSize);
                this.add(l);
            }
        }
    }

    public static ImageIcon findIcon(int i) {
        switch (i) {
            case 1:
                return m1;
            case 2:
                return m2;
            case 3:
                return m3;
            case 4:
                return m4;
            case 5:
                return m5;
            case 6:
                return m6;
            case 7:
                return m7;
            case 8:
                return m8;
            case 9:
                return bomb;
        }
        return null;
    }

    public void iniPic() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                labels[i][j].setIcon(findIcon(amount[i][j]));
                labels[i][j].setVisible(false);
            }
        }
    }

    public void iniButton(){//初始化按钮的信息，添加按钮触发的点击事件
        buttons = new JButton[x][y];
        for(int i = 0;i < x;i++){
            for(int j = 0;j < y;j++){
                buttons[i][j] = new JButton();
                buttons[i][j].setBounds(i * gridSize, j * gridSize, gridSize, gridSize);
                ButtonClick click = new ButtonClick();
                buttons[i][j].addMouseListener(click);
                int finalI = i;
                int finalJ = j;
                Color color = buttons[i][j].getBackground();
                buttons[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        buttons[finalI][finalJ].setBackground(Color.CYAN);
                        if(isCheated){
                            buttons[finalI][finalJ].setIcon(findIcon(amount[finalI][finalJ]));
                        }
                    }
                    public void  mouseExited(MouseEvent e){
                        buttons[finalI][finalJ].setBackground(color);
                        buttons[finalI][finalJ].setIcon(null);
                    }
                });
                add(buttons[i][j]);
                buttons[i][j].setVisible(true);
            }
        }
    }

    class ButtonClick extends MouseAdapter{//内部类，声明鼠标点击事件之后的连接作用
        @Override
        public void mouseClicked(MouseEvent e){
            JButton button = (JButton) e.getSource();//得到鼠标点击的是哪一个按钮
            int click = e.getButton();//得到鼠标点击的是右键还是左键
            int rr = 0;//该按钮的x坐标
            int cc = 0;//该按钮的y坐标
            for(int i = 0;i < x;i++){
                for(int j = 0;j < y;j++){
                    if(buttons[i][j] == button){
                        rr = i;
                        cc = j;
                    }
                }
            }
            if(states[rr][cc] == State.covered){
                if (click == 1){//左键单击
                    if(GameWindow.scoreboard.initial && amount[rr][cc] == 9){//避免首次触雷
                        if(isMusic) {
                            Music mac = new Music();
                            try {
                                mac.setAudioClip(Applet
                                        .newAudioClip((new File("src/Music/boom.wav")).toURL()));
                            } catch (MalformedURLException d) {
                                d.printStackTrace();
                            }
                            mac.play();
                        }
                        JOptionPane.showMessageDialog(null,
                                "运气真差，第一次就触雷啦！游戏已经重新开始！","提示：",JOptionPane.INFORMATION_MESSAGE);
                        GameWindow.gameWindow.dispose();
                        new GameWindow(x,y,mineNum,GameWindow.turns);
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
                        //首发触雷关闭原界面未解决
                    }
                    else {//非首发触雷情况
                        if (amount[rr][cc] == 9 && states[rr][cc] == State.covered){//是雷的情况
                            buttons[rr][cc].setVisible(false);
                            buttons[rr][cc].setEnabled(false);
                            labels[rr][cc].setVisible(true);
                            GameWindow.scoreboard.onTurn.deductScore();
                            if(GameWindow.scoreboard.onTurn == GameWindow.scoreboard.player1) InfoFrame.weiScore--;
                            else if (GameWindow.scoreboard.onTurn == GameWindow.scoreboard.player2) InfoFrame.heScore--;
                            GameWindow.board.mineNum--;
                            GameWindow.mineNum--;
                            states[rr][cc] = State.open;
                            if(isMusic){
                                Music music = new Music();
                                try {
                                    music.setAudioClip(Applet
                                            .newAudioClip((new File("src/Music/boom.wav")).toURL()));//踩雷的音效
                                } catch (MalformedURLException d) {
                                    d.printStackTrace();
                                }
                                music.play();
                            }
                        }
                        else{//不是雷的情况递归打开格子
                            subOpen(rr,cc);
                        }
                        GameWindow.scoreboard.clickOnce();
                        GameWindow.scoreboard.initial = false;
                    }
                } else if (click == 3){//右键单击
                    if(amount[rr][cc] == 9 && states[rr][cc] == State.covered){//是雷的情况
                        buttons[rr][cc].setVisible(false);
                        buttons[rr][cc].setEnabled(false);
                        labels[rr][cc].setVisible(true);
                        GameWindow.scoreboard.onTurn.addScore();
                        if(GameWindow.scoreboard.onTurn == GameWindow.scoreboard.player1) InfoFrame.weiScore++;
                        else if (GameWindow.scoreboard.onTurn == GameWindow.scoreboard.player2) InfoFrame.heScore++;
                        GameWindow.board.mineNum--;
                        GameWindow.mineNum--;
                        if(isMusic) {
                            Music mac = new Music();
                            try {
                                mac.setAudioClip(Applet
                                        .newAudioClip((new File("src/Music/score.wav")).toURL()));
                            } catch (MalformedURLException d) {
                                d.printStackTrace();
                            }
                            mac.play();
                        }
                        states[rr][cc] = State.flag;
                    }
                    else{//不是雷的情况增加失误数
                        GameWindow.scoreboard.onTurn.addMistake();
                        if(GameWindow.scoreboard.onTurn == GameWindow.scoreboard.player1) InfoFrame.weiMis++;
                        else if (GameWindow.scoreboard.onTurn == GameWindow.scoreboard.player2) InfoFrame.heMis++;
                        subOpen(rr,cc);
                        if(isMusic) {
                            Music mac = new Music();
                            try {
                                mac.setAudioClip(Applet
                                        .newAudioClip((new File("src/Music/deduct.wav")).toURL()));
                            } catch (MalformedURLException d) {
                                d.printStackTrace();
                            }
                            mac.play();
                        }
                        JOptionPane.showMessageDialog(null,
                                "可惜！这里不是雷！","错误提醒：",JOptionPane.ERROR_MESSAGE);
                    }
                    GameWindow.scoreboard.clickOnce();
                    GameWindow.scoreboard.initial = false;
                }
            }
            GameWindow.Victory();
        }

        void subOpen(int i, int j){
            if(!buttons[i][j].isEnabled()) return;
            if(amount[i][j] == 0 && isRecursion  && i<x  && j<y){//递归算法！！！
                buttons[i][j].setEnabled(false);
                if(i>0 && j>0 && amount[i-1][j-1]!=9) {
                    subOpen(i-1,j-1);
                    buttons[i-1][j-1].setEnabled(false);
                }
                if(i>0 && amount[i-1][j]!=9){
                    subOpen(i-1,j);
                    buttons[i-1][j].setEnabled(false);
                }
                if(i>0 && j+1<y && amount[i-1][j+1]!=9) {
                    subOpen(i-1,j+1);
                    buttons[i-1][j+1].setEnabled(false);
                }
                if(j>0 && amount[i][j-1]!=9) {
                    subOpen(i,j-1);
                    buttons[i][j-1].setEnabled(false);
                }
                if(j<y-1 && amount[i][j+1]!=9){
                    subOpen(i,j+1);
                    buttons[i][j+1].setEnabled(false);
                }
                if(i<x-1 && j>0 && amount[i+1][j-1]!=9) {
                    subOpen(i+1,j-1);
                    buttons[i+1][j-1].setEnabled(false);
                }
                if(i+1<x && amount[i+1][j]!=9) {
                    subOpen(i+1,j);
                    buttons[i+1][j].setEnabled(false);
                }
                if(i+1<x && j+1<y && amount[i+1][j+1]!=9){
                    subOpen(i+1,j+1);
                    buttons[i+1][j+1].setEnabled(false);
                }
            }
            buttons[i][j].setVisible(false);
            buttons[i][j].setEnabled(false);
            labels[i][j].setVisible(true);
            states[i][j] = State.open;
        }
    }

}

enum State {//枚举类，表示了一个格子的三种状态：打开（引爆也为打开）、未打开、插旗
    open("1"),
    covered("0"),
    flag("2");

    State(String s) {}
}