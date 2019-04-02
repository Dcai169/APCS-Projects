import javax.sound.sampled.*;
import java.awt.*;
import java.io.*;

public class Frog extends Sprite {

    private boolean isAlive, audioLoaded;
    private Clip hopAudio;

    public Frog(int x, int y){
        super(x, y, NORTH);
        setPic("frog1.png", NORTH); //overrides the default "blank.png"
        setSpeed(25);
        this.isAlive = true;
        this.audioLoaded = true;
        File f = new File("res/hop.wav");
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f);
            this.hopAudio = AudioSystem.getClip();
            this.hopAudio.open(audioIn);
        } catch (UnsupportedAudioFileException e){
            this.audioLoaded = false;
            e.printStackTrace();
        } catch (IOException e){
            this.audioLoaded = false;
            e.printStackTrace();
        } catch (LineUnavailableException e){
            this.audioLoaded = false;
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        int dx = (int) (Math.cos(Math.toRadians(getDir())) * getSpeed());
        int dy = -(int) (Math.sin(Math.toRadians(getDir())) * getSpeed());
        Point loc = getLoc();
        loc.translate(dx, dy);
        setLoc(loc);
        if (getLoc().x > FroggerMain.FRAME_WIDTH) {
            reset();
        }
        if (getLoc().x < 0) {
            reset();
        }
        //Audio for hop.wav
        if (audioLoaded) {
            hopAudio.start();
            hopAudio.setFramePosition(0);
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void reset(){
        setLoc(new Point(500, 550));
        setDir(Sprite.NORTH);
        setAlive(true);
    }
}
