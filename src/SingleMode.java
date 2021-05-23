import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;

public class SingleMode {
    static private int midTime = 3600, mineNum = 0;
    private static ImageIcon face = new ImageIcon("src/pic/face.png");
    static private JLabel label1, label2;
    static private GamePanel gp;

    SingleMode() {
        JFrame f = new JFrame("扫雷");
        f.setBounds(600, 200, 500, 600);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(null);
        label1 = new JLabel("剩余时间：" + (midTime / 60 / 60 % 60) + ":" + (midTime / 60 % 60) + ":" + (midTime % 60));
        label1.setBounds(10, 20, 120, 20);
        f.add(label1);
        label2 = new JLabel("剩余:" + mineNum);
        label2.setBounds(400, 20, 120, 20);
        f.add(label2);
        JButton bt = new JButton(face);
        bt.setBounds(230, 15, 30, 30);
        bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                midTime = 3600;
                new SingleMode();
            }
        });
        f.add(bt);
        JButton button = new JButton("返回菜单");
        button.setFocusPainted(false);
        button.setFont(GameWindow.font);
        button.setBounds(180, 510, 120, 40);
        button.setVisible(true);
        button.setBackground(Color.CYAN);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(true);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GameFrame2();
                f.dispose();
            }
        });
        f.add(button);
        gp = new GamePanel(20, 20);
        gp.setBounds(40, 100, 400, 400);
        f.add(gp);
        f.setVisible(true);
    }

    static class CountDown extends Thread {
        public void run() {
            while (midTime > 0) {
                try {
                    --midTime;
                    label1.setText("剩余时间：" + (midTime / 60 / 60 % 60) + ":" + (midTime / 60 % 60) + ":" + (midTime % 60));
                    this.sleep(1000);
                } catch (Exception e) {
                    System.out.println("错误：" + e.toString());
                }
            }
            if (midTime == 0) {
                gp.showBomb();
                JOptionPane.showMessageDialog(null, "时间已到", "游戏结束", JOptionPane.PLAIN_MESSAGE);
            }
        }

    }

    public static void setMineNum(int i) {
        mineNum = i;
        label2.setText("剩余:" + mineNum);
    }
}

class GamePanel extends JPanel {
    private int rows, cols, bombCount, flagNum;
    private final int BLOCKWIDTH = 20;
    private final int BLOCKHEIGHT = 20;
    private JLabel[][] label;
    private boolean[][] state;
    private Btn[][] btns;
    private byte[][] click;
    private static ImageIcon flag = new ImageIcon("src/pic/flag.png");
    private static ImageIcon bomb = new ImageIcon("src/pic/雷.png");

    public GamePanel(int row, int col) {
        rows = row;
        cols = col;
        bombCount = rows * cols / 10;
        flagNum = bombCount;
        label = new JLabel[rows][cols];
        state = new boolean[rows][cols];
        btns = new Btn[rows][cols];
        click = new byte[rows][cols];
        SingleMode.setMineNum(flagNum);
        setLayout(null);
        initLabel();
        randomBomb();
        writeNumber();
        randomBtn();
    }

    public void initLabel() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JLabel l = new JLabel("", JLabel.CENTER);
                l.setBounds(j * BLOCKWIDTH, i * BLOCKHEIGHT, BLOCKWIDTH, BLOCKHEIGHT);
                l.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                l.setOpaque(true);
                l.setBackground(Color.lightGray);
                this.add(l);
                label[i][j] = l;
                label[i][j].setVisible(false);
            }
        }
    }

    private void randomBomb() {
        for (int i = 0; i < bombCount; i++) {
            int rRow = (int) (Math.random() * rows);
            int rCol = (int) (Math.random() * cols);
            label[rRow][rCol].setIcon(bomb);
            state[rRow][rCol] = true;
        }
    }

    private void writeNumber() {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (state[i][j]) {
                    continue;
                }
                int bombCount = 0;
                for (int r = -1; (r + i < rows) && (r < 2); ++r) {
                    if (r + i < 0) continue;
                    for (int c = -1; (c + j < cols) && (c < 2); ++c) {
                        if (c + j < 0) continue;
                        if (state[r + i][c + j]) ++bombCount;
                    }
                }
                if (bombCount > 0) {
                    click[i][j] = 2;
                    label[i][j].setText(String.valueOf(bombCount));
                }
            }
        }
    }

    private void randomBtn() {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                Btn btn = new Btn();
                btn.i = i;
                btn.j = j;
                btn.setBounds(j * BLOCKWIDTH, i * BLOCKHEIGHT, BLOCKWIDTH, BLOCKHEIGHT);
                this.add(btn);
                btns[i][j] = btn;
                btn.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) open(btn);
                        if (e.getButton() == MouseEvent.BUTTON3) placeFlag(btn);
                    }});
            }
        }

    }

    private void open(Btn b) {
        if (state[b.i][b.j]) {
            for (int r = 0; r < rows; ++r) {
                for (int c = 0; c < cols; ++c) {
                    btns[r][c].setVisible(false);
                    label[r][c].setVisible(true);
                }
            }
            if(Board.isMusic) {
                Music mac = new Music();
                try {
                    mac.setAudioClip(Applet
                            .newAudioClip((new File("src/Music/defeat.wav")).toURL()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                mac.play();
            }
            JOptionPane.showMessageDialog(null, "您失败了", "游戏结束", JOptionPane.PLAIN_MESSAGE);
        } else  {
            subOpen(b);
        }
    }

    private void subOpen(Btn b) {
        if (state[b.i][b.j]) return;
        if (click[b.i][b.j] == 1 || click[b.i][b.j] == 4) return;
        if (click[b.i][b.j] == 2) {
            b.setVisible(false);
            label[b.i][b.j].setVisible(true);
            click[b.i][b.j] = 1;
            return;
        }
        b.setVisible(false);
        label[b.i][b.j].setVisible(true);
        click[b.i][b.j] = 1;
        for (int r = -1; (r + b.i < rows) && (r < 2); ++r) {
            if (r + b.i < 0) continue;
            for (int c = -1; (c + b.j < cols) && (c < 2); ++c) {
                if (c + b.j < 0) continue;
                if (r == 0 && c == 0) continue;
                Btn newBtn = btns[r + b.i][c + b.j];
                subOpen(newBtn);
            }
        }
    }

    private void placeFlag(Btn b) {
        if (flagNum > 0) {
            if (click[b.i][b.j] == 3) {
                if (label[b.i][b.j].getText().equals("[0-9]")) click[b.i][b.j] = 2;
                else click[b.i][b.j] = 0;
                b.setIcon(null);
                ++flagNum;
            } else  {
                b.setIcon(flag);
                click[b.i][b.j] = 3;
                --flagNum;
            }
            SingleMode.setMineNum(flagNum);
            if (flagNum == 0) {
                boolean flagState = true;
                for (int i = 0; i < rows; ++i) {
                    for (int j = 0; j < cols; ++j) {
                        if (click[i][j] != 3 && state[i][j]) flagState = false;
                    }
                }
                if (flagState) JOptionPane.showMessageDialog(null, "您成功了", "游戏结束", JOptionPane.PLAIN_MESSAGE);
                if(Board.isMusic) {
                    Music mac = new Music();
                    try {
                        mac.setAudioClip(Applet
                                .newAudioClip((new File("src/Music/Victory.wav")).toURL()));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    mac.play();
                }
            }
        } else  {
            JOptionPane.showMessageDialog(null, "标记已用尽", "错误操作", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void showBomb() {
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                btns[r][c].setVisible(false);
                label[r][c].setVisible(true);
            }
        }
    }
}

class Btn extends JButton {
    public int i, j;
}