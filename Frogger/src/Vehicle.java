import java.awt.*;

/**
 * Created by michael_hopps on 2/5/18.
 */
public class Vehicle extends Sprite {

    public Vehicle(int x, int y, int direction, int texture) {
        super(x, y, direction);
        if (texture >= 1 && texture <= 4) {
            setPic("car" + texture + ".png", direction);
        }
        setSpeed(10); //GUESS?!
    }
}
