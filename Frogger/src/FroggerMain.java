import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

//TODO: PUT YOUR NAME HERE

public class FroggerMain extends JPanel {

    //instance fields for the general environment
    public static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 600;
    private Timer timer;
    private boolean[] keys;

    //instance fields for frogger.
    private Frog frog;
    private ArrayList<Sprite> obstacles;
    private ArrayList<Rideable> rideables;
    private Goal goal;
    private int fps = 20;

    public FroggerMain() {
        keys = new boolean[512]; //should be enough to hold any key code.
        //TODO: initialize the instance fields.
        obstacles = new ArrayList<Sprite>();
        rideables = new ArrayList<Rideable>();
        frog = new Frog(500, 550);
        //TODO: init obstacles arraylist
        //TODO: add obstacles - cars and stuff
        // Westbound slow lane
        for (int i = 0; i < 6; i++) {
            Vehicle vehicle = new Vehicle(i*100, 325, Sprite.WEST, 1);
            vehicle.setSpeed(5);
            obstacles.add(vehicle);
        }
        // Eastbound slow lane
        for (int i = 0; i < 7; i++) {
            Vehicle vehicle = new Vehicle(i*40, 450, Sprite.EAST, 4);
            vehicle.setSpeed(5);
            obstacles.add(vehicle);
        }
        // Westbound fast lane
        for (int i = 0; i < 4; i++){
            Vehicle vehicle = new Vehicle(i*50+50, 350, Sprite.WEST, 2);
            vehicle.setSpeed(10);
            obstacles.add(vehicle);
        }
        // Westbound faster lane
        for (int i = 0; i < 3; i++) {
            Vehicle vehicle = new Vehicle(i*125, 375, Sprite.WEST, 4);
            vehicle.setSpeed(15);
            vehicle.setPic("truck.png", Sprite.WEST);
            obstacles.add(vehicle);
        }
        // Eastbound fast lane
        for (int i = 0; i < 5; i++){
            Vehicle vehicle = new Vehicle(i*50+100, 425, Sprite.EAST, 3);
            vehicle.setSpeed(10);
            obstacles.add(vehicle);
        }
        // Eastbound faster lane
        for (int i = 0; i < 1; i++) {
            Vehicle vehicle = new Vehicle(i*75, 400, Sprite.EAST, 1);
            vehicle.setSpeed(15);
            obstacles.add(vehicle);
        }
        final Water water = new Water(0,75, FRAME_WIDTH,125);
        obstacles.add(water);

        // Rideables
        for (int i = 0; i < 6; i++) {
            Turtle turtle = new Turtle(i*50,75, Sprite.WEST);
            turtle.setSpeed(11);
            rideables.add(turtle);
        }
        for (int i = 0; i < 3; i++) {
            rideables.add(new Log(i*150, 100, Sprite.EAST, 1));
        }
        for (int i = 0; i < 3; i++) {
            Log log = new Log(i*150+25, 150, Sprite.EAST, 0);
            log.setSpeed(10);
            rideables.add(log);
        }
        for (int i = 0; i < 4; i++) {
            Turtle turtle = new Turtle(i*50, 125, Sprite.WEST);
            rideables.add(turtle);
        }
        for (int i = 0; i < 2; i++) {
            Log log = new Log(i*200, 175, Sprite.EAST, 2);
            log.setSpeed(8);
            rideables.add(log);
        }

        // Goal
        goal = new Goal(500,0);

        timer = new Timer(1000/fps, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                timer.setDelay(1000/fps);
                if(frog.isAlive()) {
                    //move the frog
                    moveTheFrog();
                    for (Sprite obj : obstacles) {
                        obj.update();
                        if (obj instanceof Water && frog.intersects(obj)) {
                            frog.setAlive(false);
                            for (Rideable rideable : rideables) {
                                if (frog.intersects((Sprite) rideable)) {
                                    Point loc = frog.getLoc();
                                    loc.translate(rideable.getUpdateX(), rideable.getUpdateY());
                                    frog.setLoc(loc);
                                    frog.setAlive(true);
                                }
                            }
                        } else if (frog.intersects(obj)) {
                            frog.setAlive(false);
                        }
                    }
                    for (Rideable rideable : rideables) {
                        ((Sprite) rideable).update();
                    }
                    if (frog.intersects(goal)){
                        fps += 10;
                        timer.setDelay(1000/fps);
                        frog.reset();
                    }
                } else {
                    frog.reset();
                }

                repaint(); //always the last line.  after updating, refresh the graphics.
            }
        });
        timer.start();

        setKeyListener();

    }

    //Our paint method.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,325, FRAME_WIDTH, 450);
        g2.setColor(new Color(15, 117, 24));
        g2.fillRect(0, 475, FRAME_WIDTH, FRAME_HEIGHT -475);
        g2.fillRect(0,200, FRAME_WIDTH,125);
        g2.fillRect(0,0, FRAME_WIDTH, 75);
        for (Sprite obj:obstacles) {
            obj.draw(g2);
        }
        for (Rideable rideable : rideables){
            ((Sprite) rideable).draw(g2);
        }
        frog.draw(g2);
        goal.draw(g2);
        //TODO: draw all the obstacles.
    }

    public void moveTheFrog() {
        if (keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) {
            frog.setDir(Sprite.NORTH);
            frog.update();
            keys[KeyEvent.VK_W] = false;
            keys[KeyEvent.VK_UP] = false;
        }
        if (keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) {
            frog.setDir(Sprite.WEST);
            frog.update();
            keys[KeyEvent.VK_A] = false;
            keys[KeyEvent.VK_LEFT] = false;
        }
        if (keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]) {
            frog.setDir(Sprite.SOUTH);
            frog.update();
            keys[KeyEvent.VK_S] = false;
            keys[KeyEvent.VK_DOWN] = false;
        }
        if (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) {
            frog.setDir(Sprite.EAST);
            frog.update();
            keys[KeyEvent.VK_D] = false;
            keys[KeyEvent.VK_RIGHT] = false;
        }
        if (keys[KeyEvent.VK_ESCAPE]){
            System.exit(0);
        }
        if (keys[KeyEvent.VK_R]){
            frog.reset();
        }
        if (keys[KeyEvent.VK_SHIFT]){
            fps += 10;
        }
        if (keys[KeyEvent.VK_CONTROL]){
            if (fps > 10) {
                fps -= 10;
            }
        }
    }

    /*
      You probably don't need to modify this keyListener code.
       */
    public void setKeyListener() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {/*intentionally left blank*/ }

            //when a key is pressed, its boolean is switch to true.
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = true;
            }

            //when a key is released, its boolean is switched to false.
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = false;
            }
        });
    }
    //sets ups the panel and frame.  Probably not much to modify here.
    public static void main(String[] args) {
        JFrame window = new JFrame("Frogger!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT + 22); //(x, y, w, h) 22 due to title bar.

        FroggerMain panel = new FroggerMain();
        panel.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}