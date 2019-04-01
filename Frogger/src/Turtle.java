public class Turtle extends Sprite implements Rideable {
    public Turtle(int x, int y, int dir){
        super(x, y, dir);
        setPic("turtle.png", dir);
        setSpeed(9);
    }
    public int getUpdateX() {
        return (int)(Math.cos(Math.toRadians(getDir())) * getSpeed());
    }

    public int getUpdateY() {
        return  -(int) (Math.sin(Math.toRadians(getDir())) * getSpeed());
    }
}
