import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GraphicsPanel extends JPanel {
    private MouseListener Listener;
    public GraphicsPanel(int width, int height) {
        setSize(width,height);
        Listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("redraw ordered");
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        };
        addMouseListener(Listener);
    }
    public int randint(int min, int max){
        return (int)(Math.random() * max-min) + min;
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Background back = new Background(getWidth(), getHeight());
        int randint0 = randint(0, 10);
        int randint1 = randint(0, 5);
        if (randint0 > 5) {
            System.out.println("CVN-65");
            back.laMer(g2, 1000);
            CVN65 cvn65 = new CVN65();
            F18F f18f = new F18F(95);
            cvn65.drawCV(g2);
            if (randint1 == 0) {
                f18f.draw(g2, randint(0, getWidth() - 848), randint(0, getHeight() - 490));
            } else if (randint1 == 1) {
                f18f.drawVic(g2, randint(0, getWidth() - 623), randint(35, getHeight() - 540));
            } else if (randint1 == 2) {
                f18f.drawMis(g2, randint(0, getWidth() - 1098), randint(35, getHeight() - 615));
            } else if (randint1 == 3) {
                f18f.drawLec(g2, randint(0, getWidth() - 1298), randint(0, getHeight() - 635));
            } else if (randint1 == 4) {
                f18f.drawRec(g2, randint(0, getWidth() - 1298), randint(625, getHeight()));
            }
        } else if (randint0 < 5) {
            System.out.println("NCC-1701-A");
            back.SPACE(g2, 100);
            NCC1701A ncc1701a = new NCC1701A();
            ncc1701a.drawEA(g2);
        } else {
            repaint();
            System.out.println("5");
        }
    }
}