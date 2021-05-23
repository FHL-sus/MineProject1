import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;

public class GameWindow extends JFrame implements ActionListener {
    public static CountDown cd = new CountDown();
    public static Font font;//$
    public static int midTime1 = 3600;
    public static int midTime2 = 3600;
    public static JLabel label3,label4,label5,label9;
    //1,2为记录倒计时，3为重新启动提醒，5为存储游戏，9为重开游戏
    private static ImageIcon back = new ImageIcon("src/pic/back.png");
    private static ImageIcon check = new ImageIcon("src/pic/check.png");
    private static ImageIcon save = new ImageIcon("src/pic/save.png");
    private static ImageIcon restart = new ImageIcon("src/pic/restart.png");
    private static JLabel label6 = new JLabel("魏薛涛");
    private static JLabel label7 = new JLabel("贺铭鑫");
    private static JLabel label8 = new JLabel("V S");
    private static ImageIcon por1 = new ImageIcon("src/pic/魏薛涛.png");
    private static ImageIcon por2 = new ImageIcon("src/pic/贺铭鑫.png");
    private static ImageIcon wei = por1;
    private static ImageIcon he = por2;
    public static int anRow,anCol,mineNum,beginningNum,turns;
    public static Scoreboard scoreboard;
    public static Board board;
    public static JLabel label1 = new JLabel("魏薛涛-剩余时间:      " +
            (midTime1 / 60 / 60 % 60) + ":" + (midTime1 / 60 % 60) + ":" + (midTime1 % 60));
    public static JLabel label2 = new JLabel("贺铭鑫-剩余时间:      " +
            (midTime2 / 60 / 60 % 60) + ":" + (midTime2 / 60 % 60) + ":" + (midTime2 % 60));
    static GameWindow gameWindow;
    private static boolean first = true;//是否为第一次进入游戏

    static {
        try {
            font = Font.createFont( Font.TRUETYPE_FONT,
                    new FileInputStream("src/font/STKAITI.TTF") );
        } catch(Exception e) {
            e.printStackTrace();
        }
        font = font.deriveFont(Font.PLAIN, 20);
    }

    public GameWindow(int row, int col, int mineNum, int turns) {//row表示行数，col表示列数
        super("扫雷");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1275,950);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setFont(font);
        anRow = row;
        anCol = col;
        GameWindow.turns = turns;
        GameWindow.mineNum = mineNum;
        Scoreboard.numbs = mineNum;
        beginningNum = mineNum;
        gameWindow = this;

        //魏薛涛和贺铭鑫对决的图层
        JButton btnWei = new JButton(wei);
        JButton btnHe = new JButton(he);
        btnWei.setBounds(1320,90,130,130);
        btnWei.setFocusPainted(false);
        btnHe.setBounds(1320,340,130,130);
        btnHe.setFocusPainted(false);
        add(btnWei);
        add(btnHe);

        label3 = new JLabel("←点此返回菜单");
        label4 = new JLabel("点此查看分布→");
        label5 = new JLabel("←点此存储游戏");
        label9 = new JLabel("点此↓重开游戏");
        label3.setFont(new Font("宋体",Font.TRUETYPE_FONT,17));
        label4.setFont(new Font("宋体",Font.TRUETYPE_FONT,17));
        label5.setFont(new Font("宋体",Font.TRUETYPE_FONT,17));
        label9.setFont(new Font("宋体",Font.TRUETYPE_FONT,17));
        label6.setFont(font);
        label7.setFont(font);
        label8.setFont(new Font("楷书",Font.HANGING_BASELINE,20));
        label3.setBounds(150,520,200,120);
        label4.setBounds(30,660,200,120);
        label5.setBounds(150,800,200,120);
        label6.setBounds(1350,40,60,40);
        label7.setBounds(1350,480,60,40);
        label8.setBounds(1365,260,60,40);
        label9.setBounds(1324,681,200,120);
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(label7);
        add(label8);
        add(label9);

        scoreboard = new Scoreboard(turns);
        scoreboard.setLocation(-20,30);
        add(scoreboard);
        board = new Board(col,row,900,900,mineNum);
        add(board);
        board.setOpaque(false);
        board.setBackground(Color.WHITE);
        if(row > col) {
            board.setLocation(400 + (row - col) * 8, 20);
        }
        if(row < col){
            board.setLocation(400, 20 + (col - row) * 8);
        }
        if(row == col){
            board.setLocation(400, 20);
        }

        //倒计时线程
        label1.setBounds(32,196,240,400);
        label2.setBounds(32,246,240,400);
        label1.setFont(font);
        label2.setFont(font);

        JButton button1 = new JButton(back);
        JButton button2 = new JButton(check);
        JButton button3 = new JButton(save);
        JButton button4 = new JButton(restart);
        JButton btnNotCheated = new JButton("关闭作弊模式");
        btnNotCheated.setFont(new Font("微软雅黑",Font.HANGING_BASELINE,12));
        button1.setBounds(30,520,120,120);
        button2.setBounds(150,660,120,120);
        button3.setBounds(30,800,120,120);
        button4.setBounds(1325,760,120,120);
        btnNotCheated.setFocusPainted(false);
        btnNotCheated.setBackground(Color.pink);
        btnNotCheated.setBounds(30,740,110,20);
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new MouseAdapter(){
                };
                new GameFrame2();
                dispose();
            }
        });
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PasswordFrame(board);
            }
        });
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SaveFrame(board,scoreboard,midTime1,midTime2,wei,he,turns);
            }
        });
        button4.addActionListener(this);
        btnNotCheated.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Board.isCheated = false;
            }
        });
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(btnNotCheated);

        this.setVisible(true);
    }

    public GameWindow(Board board, Scoreboard scoreboard, int midTime1, int midTime2, ImageIcon wei, ImageIcon he){
        super("扫雷");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1275,950);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setFont(font);

        anRow = board.y;
        anCol = board.x;
        turns = scoreboard.turns;
        mineNum = board.mineNum;
        GameWindow.midTime1 = midTime1;
        GameWindow.midTime2 = midTime2;
        GameWindow.wei = wei;
        GameWindow.he = he;
        Scoreboard.numbs = board.mineNum;
        beginningNum = board.mineNum;
        gameWindow = this;
        GameWindow.board = board;
        GameWindow.scoreboard = scoreboard;
        GameWindow.first = first;

        //魏薛涛和贺铭鑫对决的图层
        JButton btnWei = new JButton(wei);
        JButton btnHe = new JButton(he);
        btnWei.setBounds(1320,90,130,130);
        btnWei.setFocusPainted(false);
        btnHe.setBounds(1320,340,130,130);
        btnHe.setFocusPainted(false);
        add(btnWei);
        add(btnHe);

        label3 = new JLabel("←点此返回菜单");
        label4 = new JLabel("点此查看分布→");
        label5 = new JLabel("←点此存储游戏");
        label9 = new JLabel("点此↓重开游戏");
        label3.setFont(new Font("宋体",Font.TRUETYPE_FONT,17));
        label4.setFont(new Font("宋体",Font.TRUETYPE_FONT,17));
        label5.setFont(new Font("宋体",Font.TRUETYPE_FONT,17));
        label9.setFont(new Font("宋体",Font.TRUETYPE_FONT,17));
        label6.setFont(font);
        label7.setFont(font);
        label8.setFont(new Font("楷书",Font.HANGING_BASELINE,20));
        label3.setBounds(150,520,200,120);
        label4.setBounds(30,660,200,120);
        label5.setBounds(150,800,200,120);
        label6.setBounds(1350,40,60,40);
        label7.setBounds(1350,480,60,40);
        label8.setBounds(1365,260,60,40);
        label9.setBounds(1324,681,200,120);
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(label7);
        add(label8);
        add(label9);

        scoreboard.setLocation(-20,30);
        add(scoreboard);
        add(board);
        board.setOpaque(false);
        board.setBackground(Color.WHITE);
        if(board.y > board.x) {
            board.setLocation(400 + (board.y - board.x) * 8, 20);
        }
        if(board.y < board.x){
            board.setLocation(400, 20 + (board.x - board.y) * 8);
        }
        if(board.x == board.y){
            board.setLocation(400, 20);
        }

        //倒计时线程
        label1.setBounds(32,196,240,400);
        label2.setBounds(32,246,240,400);
        label1.setFont(font);
        label2.setFont(font);

        JButton button1 = new JButton(back);
        JButton button2 = new JButton(check);
        JButton button3 = new JButton(save);
        JButton button4 = new JButton(restart);
        JButton btnNotCheated = new JButton("关闭作弊模式");
        btnNotCheated.setFont(new Font("微软雅黑",Font.HANGING_BASELINE,12));
        button1.setBounds(30,520,120,120);
        button2.setBounds(150,660,120,120);
        button3.setBounds(30,800,120,120);
        button4.setBounds(1325,760,120,120);
        btnNotCheated.setFocusPainted(false);
        btnNotCheated.setBackground(Color.pink);
        btnNotCheated.setBounds(30,740,110,20);
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new MouseAdapter(){
                };
                new GameFrame2();
                dispose();
            }
        });
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PasswordFrame(board);
            }
        });
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SaveFrame(board,scoreboard,midTime1,midTime2,wei,he,turns);
            }
        });
        button4.addActionListener(this);
        btnNotCheated.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Board.isCheated = false;
            }
        });
        add(button1);
        add(button2);
        add(button3);
        button4.setEnabled(false);
        add(button4);
        add(btnNotCheated);

        this.setVisible(true);
    }

    public static int getMineNum() {
        return mineNum;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        new RestartFrame(this);
    }

    public static void main(String[] args) {
        new GameWindow(5,6,6,3);
        if(isFirst()) {
            CountDown cd = new CountDown();
            cd.start();
            setFirst(false);
        }
    }

    public static ImageIcon getWei() {
        return wei;
    }

    public static void setWei(ImageIcon wei) {
        GameWindow.wei = wei;
    }

    public static ImageIcon getHe() {
        return he;
    }

    public static void setHe(ImageIcon he) {
        GameWindow.he = he;
    }

    public static boolean isFirst() {
        return first;
    }

    public static void setFirst(boolean first) {
        GameWindow.first = first;
    }

    public static void Victory(){
        if(scoreboard.end && (!scoreboard.same)){
            String winnerName = scoreboard.winner.name;
            if(winnerName.equals("贺铭鑫")) new WinnerHeFrame();
            else if(winnerName.equals("魏薛涛")) new WinnerWeiFrame();
        }
        else if(scoreboard.end){
            JOptionPane.showMessageDialog(null,"平局啦！","Game Over",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static class CountDown extends Thread{
        public void run(){
            while(GameWindow.midTime1 > 0 && GameWindow.midTime2 > 0){
                try{
                    if (scoreboard.onTurn == scoreboard.player1){
                        --GameWindow.midTime1;
                        label1.setText("魏薛涛-剩余时间:      " +
                                (GameWindow.midTime1 / 60 / 60 % 60) + ":" + (GameWindow.midTime1 / 60 % 60) + ":" + (GameWindow.midTime1 % 60));
                    }
                    else if (scoreboard.onTurn == scoreboard.player2){
                        --GameWindow.midTime2;
                        label2.setText("贺铭鑫-剩余时间:      " +
                                (GameWindow.midTime2 / 60 / 60 % 60) + ":" + (GameWindow.midTime2 / 60 % 60) + ":" + (GameWindow.midTime2 % 60));
                    }
                    this.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(midTime1 == 0) new WinnerHeFrame();
            if(midTime2 == 0) new WinnerHeFrame();
        }
    }
}