import java.awt.*;

public class World {
    private Cell[][] cells;
    private boolean linesToggle;
    private boolean colorDelta;
    /*
    Creates a world of cells, devoid of life.
     */
    public World(int r, int c) {
        cells = new Cell[r][c];
        for (int i = 0; i < cells[0].length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[j][i] = new Cell(j, i);
            }
        }
        this.linesToggle = false;
        this.colorDelta = true;
    }

    //randomize the world
    public void randomizeWorld(){
        for (int i = 0; i < cells[0].length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if ((int)(Math.random()*2) == 1){
                    cells[j][i].spawn();
                } else {
                    cells[j][i].kill();
                }
                cells[j][i].setColor(new Color(0,0,0));
            }
        }
    }

    public void clearWorld(){
        for (int i = 0; i < cells[0].length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[j][i].kill();
            }
        }
    }

    public void drawWorld(Graphics2D g2){
        //g2.setColor(new Color(51,153,51));
        g2.setColor(new Color(204,204,204));
        g2.fillRect(0,0,1440,900);
        for (int k = 0; k < cells[0].length; k++) {
            for (int l = 0; l < cells.length; l++) {
                if (cells[l][k].getIsAlive()) {
                    if (colorDelta) {
                        cells[l][k].changeColor(10, 10, 10);
                    }
                    cells[l][k].draw(g2);
                }
            }
        }
        if (linesToggle) {
            g2.setColor(Color.black);
            for (int i = 0; i < 900; i++) {
                g2.drawLine(0, i*20, 1440, i*20);
            }
            for (int j = 0; j < 1440; j++) {
                g2.drawLine(j*20, 0, j*20, 900);
            }
        }
    }

    public void toggleLines(){
        linesToggle = !linesToggle;
    }

    public void nextGeneration(){
        //nextGenFate holds if a cell will be alive in the next generation.
        //this is needed so we don't modify the cells while we are
        //calculating the next gen.
        boolean[][] nextGenFate = new boolean[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].nextFate(cells)/* == true*/){
                    nextGenFate[i][j] = true;
                } else {
                    nextGenFate[i][j] = false;
                }
            }
        }
        for (int i = 0; i < nextGenFate.length; i++) {
            for (int j = 0; j < nextGenFate[0].length; j++) {
                if (nextGenFate[i][j]){
                    cells[i][j].spawn();
                } else {
                    cells[i][j].kill();
                }
            }
        }
    }

    public void spawnShape(boolean[][] shape, int x, int y){
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[0].length; c++) {
                if (shape[r][c]) {
                    if (cells.length > y+r && cells[0].length > x+c) {
                        cells[y + r][x + c].spawn();
                    }
                }
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public String toString(){
        return "World: [" +
                "length = " + cells[0].length +
                ", height = " + cells.length + "]";
    }
}
