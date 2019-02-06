import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LifePanel extends JPanel implements ActionListener, KeyListener{
    private Timer timer;
    private World theWorld;
    private boolean timerState;
    private int timerSpeed;
    private boolean[][] shape;

    public LifePanel(int w, int h){
        setSize(w, h);
        timerState = true;
        timerSpeed = 10;
        shape = new boolean[1][1];
        shape[0][0] = true;
        //init theWorld.
        theWorld = new World(45,72);
        //theWorld = new World(3,3);
        timer = new Timer(1000/timerSpeed, this);
        theWorld.randomizeWorld();
        System.out.println("Press Space to Start");
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {}

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                int r = mouseEvent.getX() / Cell.SIZE;
                int c = mouseEvent.getY() / Cell.SIZE;
                if (mouseEvent.getButton() == 1) {
                    //Toggle Alive/Dead
                    if (theWorld.getCells()[c][r].getIsAlive()) {
                        theWorld.getCells()[c][r].kill();
                    } else {
                        theWorld.getCells()[c][r].spawn();
                    }
                } else if (mouseEvent.getButton() == 3) {
                    //print2DArray(shape);
                    theWorld.spawnShape(shape,r,c);
                }
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) { }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                //timer.start();
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                //timer.stop();
            }
        });
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
                if (timerSpeed + mouseWheelEvent.getWheelRotation() > 0) {
                    timerSpeed += mouseWheelEvent.getWheelRotation();
                }
                timer.setDelay(1000/timerSpeed);
                System.out.println("FPS: " + timerSpeed);
            }
        });
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        theWorld.drawWorld(g2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        theWorld.nextGeneration();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == ' '){
            if (timerState){
                timer.start();
                System.out.println("Start");
            } else {
                timer.stop();
                System.out.println("Stop");
            }
            timerState = !timerState;
        } else if (e.getKeyChar() == 'a'){
            // Toggle Lines
            theWorld.toggleLines();
            repaint();
            System.out.println("Toggled lines");
        } else if (e.getKeyChar() == 'w'){
            // Step Generation
            theWorld.nextGeneration();
            repaint();
            System.out.println("Step");
        } else if (e.getKeyChar() == 'd'){
            // Clear World
            theWorld.clearWorld();
            System.out.println("Clear");
            repaint();
        } else if (e.getKeyChar() == 's'){
            // Randomize World
            theWorld.clearWorld();
            theWorld.randomizeWorld();
            System.out.println("Regenerate");
            repaint();
        } else if (e.getKeyChar() == 'e'){
            // Repaint
            repaint();
            System.out.println("Repaint");
        } else if (e.getKeyChar() == 'r'){
            // Reset timer speed
            timerSpeed = 10;
            timer.setDelay(1000/timerSpeed);
            System.out.println("Speed reset");
        } else if (e.getKeyChar() == 'f'){
            // Sout toString
            System.out.println(this.toString());
        } else if (e.getKeyChar() == 'q'){
            // Quit
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            System.out.println("Quit");
            win.dispose();
            System.exit(0);
        } else if (e.getKeyChar() == '0'){
            shape = new boolean[1][1];
            shape[0][0] = true;
        } else if (e.getKeyChar() == '1'){
            shape = new boolean[2][2];
            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[0].length; j++) {
                    shape[j][i] = true;
                }
            }
            System.out.println("Shape 1 loaded");
        } else if (e.getKeyChar() == '2'){
            shape = new boolean[1][3];
            for (int i = 0; i < 3; i++) {
                shape[0][i] = true;
            }
            System.out.println("Shape 2 loaded");
        } else if (e.getKeyChar() == '3'){
            // doesnt work idk why
            shape = new boolean[3][3];
            shape[0][1] = true;
            shape[1][2] = true;
            for (int i = 0; i < 3; i++) {
                shape[2][i] = true;
            }
            System.out.println("Shape 3 loaded");
        }
    }

    public void print2DArray(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        int width = 1440;
        int height = 900;
        frame.setPreferredSize(new Dimension(width, height + 24));

        JPanel panel = new LifePanel(width, height);
        panel.setFocusable(true);
        panel.grabFocus();

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public String toString(){
        return "LifePanel: [" +
                " timerState = " + timerState +
                ", timerSpeed = " + timerSpeed +
                ", theWorld = " + theWorld.toString() +
                "]";
    }
}
