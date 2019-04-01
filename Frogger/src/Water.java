import java.awt.*;

public class Water extends Sprite {
    private int x, y, width, height;
    private static final Color WATER = new Color(33, 152, 255);
    public Water(int x, int y , int w, int h){
        super(x, y, 0);
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    @Override
    public void draw(Graphics2D g2){
        g2.setColor(WATER);
        g2.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getBoundingRectangle(){
        return new Rectangle(x ,y, width, height);
    }
}
