import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MineSweeper extends JPanel {

    private Square[][] board;
    private boolean gameState;
    private int width, height, totalMines, flagsPlanted;
    private int mX, mY;
    private int r, c;

    public static final int SIZE = 50;

    public MineSweeper(int width, int height) {
        this.width = width;
        this.height = height;
        this.totalMines = ((width/SIZE)*(height/SIZE))/5;
        this.gameState = true;
        this.flagsPlanted = 0;
        setSize(width, height);
        setupBoard();
        setupMouseListener();
        setupKeyboardListener();
        System.out.println(totalMines);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c].draw(g2, gameState);
            }
        }

        if (totalMines == flagsPlanted){
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[0].length; c++) {
                    if (board[r][c].isFlagged() == board[r][c].isMine()){
                        win(g2);
                    }
                }
            }
        }
    }

    public void setupMouseListener() {
        addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gameState) {
                    if (e.getButton() == 1) {
                        lClick();
                    } else if (e.getButton() == 2) {
                        System.out.println("MMB");
                    }else if (e.getButton() == 3) {
                        rClick();
                    }
                }
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                mX = mouseEvent.getX();
                mY = mouseEvent.getY();

                r = mY / SIZE;
                c = mX / SIZE;

            }
        });
    }

    public void setupKeyboardListener() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                int key = keyEvent.getKeyCode();
                if (gameState) {
                    if (key == 27) {
                        System.exit(0);
                    } else if (key == 81) {
                        revealBoard();
                    } else if (key == 69) {
                        hideBoard();
                    } else if (key == 87) {
                        setupBoard();
                        hideBoard();
                    } else if (key == 32) {
                        rClick();
                    } else if (key == 16) {
                        lClick();
                    }
                } else {
                    gameState = true;
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
    }

    public void setupBoard() {
        int mineChance = 0;
        int minesPlanted = 0;
        flagsPlanted = 0;
        board = new Square[height/SIZE][width/SIZE];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Square(i, j, board);
                board[i][j].setFlagged(false);
                int random = (int)(Math.random() * 100);
                if (random > 90 && totalMines >= minesPlanted) {
                    board[i][j].setMine(true);
                    minesPlanted++;
                }
            }
        }

        for (int k = 0; k < board.length; k++) {
            for (int l = 0; l < board[0].length; l++) {
                board[k][l].setNeighborMines(board[k][l].numNeighbors(board));
            }
        }
        //System.out.println(totalMines);
    }

    public void win(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(width/3,height/3,width/3,height/3);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.PLAIN, SIZE));
        g2.drawString("Game Over", width/3, height/3);
        g2.drawString("Press any key to continue", width/3, height/3 + SIZE);
        gameState = false;
    }

    public void revealBoard() {
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++) {
                board[i][j].setRevealed(true);
            }
        }
    }

    public void lClick() {
        if (!board[r][c].isFlagged()) {
            board[r][c].lClick();
        }
        if (board[r][c].isMine()) {
            gameState = false;
            revealBoard();
        }
    }

    public void rClick() {
        boolean initial = board[r][c].isFlagged();
        if (initial){
            flagsPlanted--;
        } else {
            flagsPlanted++;
        }
        board[r][c].rClick();
    }

    public void hideBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j].setRevealed(false);
                board[i][j].setFlagged(false);
            }
        }
        gameState = true;
    }

    public static void main(String[] args) {
        int width = 16;
        int height = 14;

        JFrame window = new JFrame("Minesweeper");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, SIZE*width, SIZE*height + 22); //(x, y, w, h) 22 due to title bar.

        MineSweeper panel = new MineSweeper(SIZE*width, SIZE*height);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}
