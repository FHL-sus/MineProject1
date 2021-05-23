import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Save {
    //board 部分：
    int x;//行格子数
    int y;//列格子数
    int mineNum;//总雷数
    int[][] amount;//1 - 8代表格子周围雷的数目，9代表该格是雷
    State[][] states;
    int turns;//固定回合数

    //scoreBoard 部分：
    Player player1 = new Player("魏薛涛");
    Player player2 = new Player("贺铭鑫");
    Player onTurn = new Player("魏薛涛");//TODO: 有所更改
    String OnTurn = " ";
    int nums;//剩余雷数
    int n;//回合数

    //人员部分：
    int midTime1;
    int midTime2;
    public ImageIcon wei;
    public ImageIcon he;

    //前置排行榜部分
    int weiNewScore = 0;
    int heNewScore = 0;
    int weiNewMis = 0;
    int heNewMis = 0;

    public Save(Board board, Scoreboard scoreboard, int midTime1, int midTime2, ImageIcon wei, ImageIcon he, int turns) {
        ArrayList<Integer> pureScore = getPureScore();
        this.x = board.x;
        this.y = board.y;
        this.mineNum = board.mineNum;
        this.amount = board.amount;
        this.states = board.states;
        this.player1 = scoreboard.player1;
        this.player2 = scoreboard.player2;
        this.onTurn = scoreboard.onTurn;
        this.midTime1 = midTime1;
        this.midTime2 = midTime2;
        this.wei = wei;
        this.he = he;
        this.turns = turns;
        this.weiNewScore = player1.score + pureScore.get(1);
        this.weiNewMis = player1.mistake + pureScore.get(2);
        this.heNewScore = player2.score + pureScore.get(3);
        this.heNewMis = player2.mistake + pureScore.get(4);
        if (player1.name.equals(onTurn.name)) {
            this.OnTurn = (player1.name);
        } else {
            this.OnTurn = (player2.name);
        }
        this.nums = Scoreboard.numbs;
        this.n = scoreboard.n;
    }

    public Save() {
//        if (!getSave(name)) {
//            System.out.println("没有相应存档！！！");//TODO： 写一个提示方格？
//        }
    }

    public void newSave(String name) {
        String Path = "src\\saveData\\" + name + ".txt";
        String ReadPath = "src\\saveData\\"+name+"Read.txt";
        File save = new File(Path);//创建文件
        File forRead = new File(ReadPath);
        File pureScore = new File("src\\saveData\\pureScore.txt");
        try {
            FileWriter saveWriter = new FileWriter(save);
            FileWriter writer = new FileWriter(forRead);
            FileWriter pureWriter = new FileWriter(pureScore);
            StringBuilder board = new StringBuilder();
            StringBuilder reBoard = new StringBuilder();
            StringBuilder pure = new StringBuilder();
            reBoard.append("height: ").append(y).append("  length: ").append(x).append("\r\n")
                    .append("leftMind: ").append(nums).append(" onTurn: ").append(onTurn.name).append(" HasStep: ").append(n).append("\r\n")
                    .append(player1.name).append(" 得分: ").append(player1.score).append(" 失误: ").append(player1.mistake).append(" 剩余时间: ").append(midTime1).append("s 头像：").append(wei).append("\r\n")
                    .append(player2.name).append(" 得分: ").append(player2.score).append(" 失误: ").append(player2.mistake).append(" 剩余时间: ").append(midTime2).append("s 头像：").append(he).append("\r\n=========================================================\n");
            board.append(y).append(" ").append(x).append(" ").append(mineNum).append(" ").append(OnTurn).append(" ").append(nums).append(" ").append(n).append(" ")
                    .append(player1.name).append(" ").append(player1.score).append(" ").append(player1.mistake).append(" ").append(midTime1).append(" ").append(wei).append(" ")
                    .append(player2.name).append(" ").append(player2.score).append(" ").append(player2.mistake).append(" ").append(midTime2).append(" ").append(he).append(" ").append(turns).append(" ");
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < x - 1; k++) {
                    board.append(this.amount[k][j]).append(" ");
                    reBoard.append(this.amount[k][j]).append(" ");
                }
                board.append(this.amount[x - 1][j]).append(" ");
                reBoard.append(this.amount[x - 1][j]).append("\r\n");
            }
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < x; k++) {
                    board.append(this.states[k][j]).append(" ");
                }
            }
            pure.append("1 ").append(weiNewScore).append(" ").append(weiNewMis).append(" ").append(heNewScore).append(" ").append(heNewMis).append(" -1");
            saveWriter.write(String.valueOf(board));
            writer.write(reBoard + "\n");
            pureWriter.write(String.valueOf(pure));
            saveWriter.flush();
            saveWriter.close();
            writer.flush();
            writer.close();
            pureWriter.flush();
            pureWriter.close();
        } catch (IOException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean getSave(String name) {
        String path = "src\\saveData\\" + name + ".txt";
        try {
            //connection
            InputStream is = new FileInputStream(path);
            byte[] b = new byte[5000];//读取至字节
            int i, k = 17;//存储每次数据//TODO: 修改序列值
            int index = 0;//循环读取每个数据

            while ((i = is.read()) != -1) {
                b[index] = (byte) i;
                index++;
            }
            String contains = new String(b);
            is.close();
            String[] SetB = contains.split(" ");
            this.y = Integer.parseInt(SetB[0]);
            this.x = Integer.parseInt(SetB[1]);
            this.mineNum = Integer.parseInt(SetB[2]);
            this.OnTurn = SetB[3];
            this.nums = Integer.parseInt(SetB[4]);
            this.n = Integer.parseInt(SetB[5]);
            this.player1.name = SetB[6];
            this.player1.score = Integer.parseInt(SetB[7]);
            this.player1.mistake = Integer.parseInt(SetB[8]);
            this.midTime1 = Integer.parseInt(SetB[9]);
            this.wei = new ImageIcon(SetB[10]);
            this.player2.name = SetB[11];
            this.player2.score = Integer.parseInt(SetB[12]);
            this.player2.mistake = Integer.parseInt(SetB[13]);
            this.midTime2 = Integer.parseInt(SetB[14]);
            this.he = new ImageIcon(SetB[15]);
            this.turns = Integer.parseInt(SetB[16]);
            if (OnTurn.equals(player1.name)) {
                this.onTurn = player1;
            } else {
                this.onTurn = player2;
            }
            amount = new int[x][y];
            states = new State[x][y];
            for (int m = 0; m < this.y; m++) {
                for (int n = 0; n < this.x; n++) {
                    this.amount[n][m] = Integer.parseInt(SetB[k]);
                    k++;
                }
            }
            for (int m = 0; m < this.y; m++) {
                for (int n = 0; n < this.x; n++) {
                    this.states[n][m] = State.valueOf(SetB[k]);
                    k++;
                }
            }

        } catch (FileNotFoundException e) {
            //TODO Auto-generated catch block
            return false;
        } catch (IOException e) {
            //TODO Auto-generated catch block
        }
        return true;
    }

    public Board getBoard() {
        Board board = new Board(x, y, 900, 900, mineNum);
        int gridSize;
        if(x > y) gridSize = 900 / x;
        else gridSize = 900 / y;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                board.amount[j][i] = this.amount[j][i];
                board.states[j][i] = this.states[j][i];
                JLabel l = new JLabel("", JLabel.CENTER);
                l.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                l.setBackground(Color.LIGHT_GRAY);
                board.labels[j][i] = l;
                l.setBounds(j * gridSize, i * gridSize, gridSize, gridSize);
                board.add(l);
                board.labels[j][i].setIcon(Board.findIcon(board.amount[j][i]));
                if(board.states[j][i] != State.covered){
                    board.buttons[j][i].setVisible(false);
                    board.buttons[j][i].setEnabled(false);
                    board.labels[j][i].setVisible(true);
                }
            }
        }
        return board;
    }

    public Scoreboard getScoreboard() {
        Scoreboard scoreboard = new Scoreboard(turns);
        scoreboard.player1.name = String.valueOf(this.player1.name);
        scoreboard.player1.score = this.player1.score;
        scoreboard.player1.mistake = this.player1.mistake;
        scoreboard.player2.name = String.valueOf(this.player2.name);
        scoreboard.player2.score = this.player2.score;
        scoreboard.player2.mistake = this.player2.mistake;
        scoreboard.onTurn.name = String.valueOf(this.onTurn.name);
        scoreboard.onTurn.score = this.onTurn.score;
        scoreboard.onTurn.mistake = this.onTurn.mistake;
        scoreboard.n = n;
        scoreboard.conRefresh();
        return scoreboard;
    }

    public ArrayList<Integer> getPureScore() {
        String path = "src\\saveData\\pureScore.txt";
        ArrayList<Integer> pureScore = new ArrayList<>();
        try {
            InputStream is = new FileInputStream(path);
            byte[] b = new byte[1024];//读取至字节
            int i;//存储每次数据
            int index = 0;//循环读取每个数据

            while ((i = is.read()) != -1) {
                b[index] = (byte) i;
                index++;
            }
            String contains = new String(b);
            is.close();
            String[] Set = contains.split(" ");

            for (int m = 0; m < 5; m++) {
                pureScore.add(Integer.parseInt(Set[m]));
            }
        } catch (FileNotFoundException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            //TODO Auto-generated catch block
        }
        return pureScore;
    }

}
