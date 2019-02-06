import java.awt.*;

public class Cell {
    private boolean isAlive;
    private int r, c;
    private Color color;

    public static final int SIZE = 20;

    public Cell(int row, int col){
        isAlive = false;
        r = row;
        c = col;
        color = new Color(0,0,0);
    }
    public void kill(){
        isAlive = false;
        color = new Color(0,0,0);
    }
    public void spawn(){
        isAlive = true;
    }

    /*
    returns the number of living cells around this cell.
     */
    public int numNeighbors(Cell[][] cells){
        int neighbors = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i+r >= 0 && j+c >= 0 && i+r < cells.length && j+c < cells[0].length){
                    if (cells[r+i][c+j].isAlive){
                        neighbors++;
                    }
                }
            }
        }
        if (cells[r][c].isAlive){
            neighbors--;
        }
        return neighbors;
    }

    /*
    returns true if this Cell should be alive next generation.
    returns false if this Cell should not be alive next generation.
     */
    public boolean nextFate(Cell[][] cells){
        if (numNeighbors(cells) <= 1 || numNeighbors(cells) >= 4) {
            // Cell dies
            return false;
        } else if (numNeighbors(cells) == 3 && !isAlive){
            // Cell is born
            return true;
        } else {
            return isAlive;
        }
    }

    public void draw(Graphics2D g2){
        if(isAlive){
            g2.setColor(color);
            g2.fillRect(c * SIZE, r * SIZE, SIZE, SIZE);
        }
    }

    public boolean getIsAlive(){
        return isAlive;
    }

    @Override
    public boolean equals(Object otherCell) {
        //override so that if this and otherCell have the same row and col, true.
        if(!(otherCell instanceof Cell))
            return false;
        else if(((Cell) otherCell).getIsAlive() == isAlive){
            if (((Cell) otherCell).getR() == r && ((Cell) otherCell).getC() == c){
                return true;
            }
        }
        return false;
    }

    public void changeColor(int dr, int dg, int db){
        boolean redRange = color.getRed() + dr >= 0 && color.getRed() + dr <= 255;
        boolean greenRange = color.getGreen() + dg >= 0 && color.getGreen() + dg <= 255;
        boolean blueRange = color.getBlue() + db >= 0 && color.getBlue() + db <= 255;
        if (redRange && greenRange && blueRange){
            color = new Color(color.getRed() + dr, color.getGreen() + dg, color.getBlue() + db);
        }
    }

    @Override
    public String toString() {
        return "Cell [" +
                "isAlive = " + isAlive +
                ", row = " + r +
                ", col = " + c +
                ", color = " + color +
                ']';
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
