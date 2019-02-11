import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;

public class MineSweeper extends JPanel {

    private Square[][] board;
    private boolean gameState;
    private int width, height, totalMines;
    private int mX, mY;
    private int r, c;

    public static final int SIZE = 50;

    public MineSweeper(int width, int height) {
        this.width = width;
        this.height = height;
        this.totalMines = Settings.TOTAL_MINES; //((width/SIZE)*(height/SIZE))/5;
        this.gameState = true;
        setSize(width, height);
        setupBoard();
        setupMouseListener();
        setupKeyboardListener();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c].draw(g2, gameState);
            }
        }
        if (winDetect()) {
            drawWin(g2);
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
                    if (key == 81) {
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
                    } else if (key == 27) {
                        System.exit(0);
                    }
                } else if (key == 27) {
                    System.exit(0);
                } else {
                    setupBoard();
                    hideBoard();
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
        int minesPlanted = 0;
        board = new Square[height/SIZE][width/SIZE];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Square(i, j, board);
            }
        }

        while (minesPlanted <= totalMines-1){
            int random = (int)(Math.random() * 100);
            int randR = (int)(Math.random() * (height/SIZE));
            int randC = (int)(Math.random() * (width/SIZE));
            if (random > 90 && totalMines >= minesPlanted) {
                board[randR][randC].setMine(true);
                minesPlanted++;
            }
        }

        for (int k = 0; k < board.length; k++) {
            for (int l = 0; l < board[0].length; l++) {
                board[k][l].setNeighborMines(board[k][l].numNeighborMines(board));
            }
        }
    }

    public void drawWin(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(width/8,height/6,width/5*4,height/3+SIZE/2);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.PLAIN,(SIZE/2)));
        g2.drawString("Game Over", width/8+(SIZE/4), (height/6)-(SIZE/8)+SIZE);
        g2.drawString("Press any key to continue", width/8+(SIZE/4), (height/6)-(SIZE/8)+SIZE*2);
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

    public boolean winDetect(){
        int correctFlags = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Square current = board[i][j];
                if (current.isMine() == current.isFlagged() && current.isFlagged()){
                    correctFlags++;
                }
            }
        }
        return totalMines == correctFlags;
    }

    public static void main(String[] args) {
        int width = Settings.WIDTH;
        int height = Settings.HEIGHT;

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
