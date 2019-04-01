public class Log extends Sprite implements Rideable {
    public Log(int x, int y, int dir, int size){
        super(x, y, dir);
        if (size == 0){
            setPic("logShort.png", dir);
        } else if (size == 1){
            setPic("logMedium.png", dir);
        } else if (size == 2){
            setPic("logLarge.png", dir);
        } else {
            setPic("missing.png", dir);
        }
        setSpeed(7);
    }
    public int getUpdateX() {
        return (int)(Math.cos(Math.toRadians(getDir())) * getSpeed());
    }

    public int getUpdateY() {
        return  -(int) (Math.sin(Math.toRadians(getDir())) * getSpeed());
    }
}
