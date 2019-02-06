import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class F18F {
    private int z;
    private BufferedImage img;
    public F18F(int z){
        this.z = z;
        try {
            img = ImageIO.read(new File("./src/f18f.png"));
            System.out.println("Image loaded");
        } catch (IOException e) {
            System.out.println("Image failed to load");
        }
    }
    public void draw(Graphics2D g2, int x, int y){
        g2.drawImage(img,x,y,848/(101-z)+x,490/(101-z)+y,0,0,848,490,null);
    }
    public void drawVic(Graphics2D g2, int x, int y){
        g2.drawImage(img,x,y,848/(101-z)+x,490/(101-z)+y,0,0,848,490,null);
        g2.drawImage(img,x+150,y-35,848/(101-z)+(x+150),490/(101-z)+(y-35),0,0,848,490,null);
        g2.drawImage(img,x+125,y+50,848/(101-z)+(x+125),490/(101-z)+(y+50),0,0,848,490,null);
    }
    public void drawMis(Graphics2D g2,int x, int y){
        g2.drawImage(img,x,y,848/(101-z)+x,490/(101-z)+y,0,0,848,490,null);
        g2.drawImage(img,x+150,y-35 ,848/(101-z)+(x+150),490/(101-z)+(y-35 ),0,0,848,490,null);

        g2.drawImage(img,x+250,y+125,848/(101-z)+(x+250),490/(101-z)+(y+125),0,0,848,490, null);
    }
    public void drawLec(Graphics2D g2,int x, int y){
        g2.drawImage(img,x,y,848/(101-z)+x,490/(101-z)+y,0,0,848,490,null);
        g2.drawImage(img,x+150,y+45 ,848/(101-z)+(x+150),490/(101-z)+(y+45 ),0,0,848,490,null);
        g2.drawImage(img,x+300,y+90 ,848/(101-z)+(x+300),490/(101-z)+(y+90 ),0,0,848,490,null);
        g2.drawImage(img,x+450,y+135,848/(101-z)+(x+450),490/(101-z)+(y+135),0,0,848,490,null);
    }
    public void drawRec(Graphics2D g2,int x, int y){
        g2.drawImage(img,x,y,848/(101-z)+x,490/(101-z)+y,0,0,848,490,null);
        g2.drawImage(img,x+150,y-45 ,848/(101-z)+(x+150),490/(101-z)+(y-45 ),0,0,848,490,null);
        g2.drawImage(img,x+300,y-90 ,848/(101-z)+(x+300),490/(101-z)+(y-90 ),0,0,848,490,null);
        g2.drawImage(img,x+450,y-135,848/(101-z)+(x+450),490/(101-z)+(y-135),0,0,848,490,null);
    }
}
