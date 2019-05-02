import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {

    private CustomImage myImage;

    public Main(int w, int h){
        setSize(w, h);

        myImage = new PixelatedImage("img2.jpg", 0, 0);

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        myImage.draw(g2);
    }

    public static void main(String[] args) {
        //The JFrame class represents the window that holds the graphics
        JFrame window = new JFrame("Images!");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 1200, 800 + 22); //(x, y, w, h) 22 due to title bar.

        //This puts an object of this JPanel into the JFrame.
        Main panel = new Main(1200, 800);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
//        window.setResizable(false);
    }

}