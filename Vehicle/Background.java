import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Background {
    private int wid, hei;
    public Background(int wid, int hei){
        this.wid=wid;
        this.hei=hei;
    }
    //Ocean Methods
    public void waves(Graphics2D g2, int x, int y){
        g2.setColor(new Color(40,70,150, 150));
        g2.fill(new Ellipse2D.Double(x, y, 20,10));
    }
    public void water(Graphics2D g2){
        g2.setColor(new Color(55,100,200));
        g2.fillRect(0,0,wid,hei);
    }
    public void laMer(Graphics2D g2, int n){
        water(g2);
        for (int i = 0; i < n; i++) {
            waves(g2, (int)(Math.random() * wid),(int)(Math.random() * hei));
        }
    }
    //Space Methods
    public void space(Graphics2D g2){
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0,wid,hei);
    }
    public void stars(Graphics2D g2, int x, int y, int z){
        Color stars = new Color((int)(Math.random()*100)+125,(int)(Math.random()*100)+125,(int)(Math.random()*100)+125,(int)(Math.random()*10)+90);
        g2.setColor(stars);
        g2.fill(new Ellipse2D.Double(x, y, z*2, z*2));
    }
    public void SPACE(Graphics2D g2, int n){
        space(g2);
        for (int i = 0; i < n; i++) {
            stars(g2,(int)(Math.random()*wid),(int)(Math.random()*hei),(int)(Math.random()*15));
        }
    }
}
