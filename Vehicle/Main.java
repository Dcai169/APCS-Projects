import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        int width = 1440;
        int height = 850;
        frame.setPreferredSize(new Dimension(width, height+24));

        JPanel panel = new GraphicsPanel(width, height);
        panel.setFocusable(true);
        panel.grabFocus();

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
