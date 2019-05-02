import java.awt.*;


public class Pixel {

    private int argb, red, green, blue, alpha;
    private Color color;

    public Pixel(int argb){
        this.argb = argb;
        red = 0xFF & ( argb >> 16);
        alpha = 0xFF & (argb >> 24);
        blue = 0xFF & (argb >> 0 );
        green = 0xFF & (argb >> 8 );
        color = new Color(red, green, blue, alpha);
    }

    public Pixel(int r, int g, int b, int a){
        argb += ((a & 0xff) << 24); // alpha
        argb += (b & 0xff); // blue
        argb += ((g & 0xff) << 8); // green
        argb += ((r & 0xff) << 16); // red

        alpha = a;
        red = r;
        green = g;
        blue = b;

        color = new Color(red, green, blue, alpha);
    }


    public void setRed(int red) {
        this.red = red;
        this.argb = (alpha << 24) | (red << 16 ) | (green<<8) | blue;

    }

    public void setGreen(int green) {
        this.green = green;
        this.argb = (alpha << 24) | (red << 16 ) | (green<<8) | blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
        this.argb = (alpha << 24) | (red << 16 ) | (green<<8) | blue;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
        this.argb = (alpha << 24) | (red << 16 ) | (green<<8) | blue;
    }

    public Color getColor() {
        color = new Color(red, green, blue, alpha);
        return color;
    }

    public int getArgb() {
        return argb;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getAlpha() {
        return alpha;
    }
}