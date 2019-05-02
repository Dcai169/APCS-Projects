public class SandboxImage extends CustomImage {
    public SandboxImage(String fileName, int x, int y) {
        super(fileName, x, y);
        drawGrid(6);
    }

    public void drawGrid(int size){
        Pixel[][] img = getPixels();
        for (int r = 0; r < img.length; r+=size) {
            for (int c = 0; c < img[0].length; c++) {
                // Venetian Blinds
//                img[r][c].setRed(255);
//                img[r][c].setGreen(255);
//                img[r][c].setBlue(255);
                img[r][c].setAlpha(128);
            }
        }
        setImage(img);
    }
}
