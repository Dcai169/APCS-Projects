public class ColoredImage extends CustomImage {
    public ColoredImage(String fileName, int x, int y) {
        super(fileName, x, y);
    }

    public void removeRed(){
        Pixel[][] img = getPixels();
        for (Pixel[] row: img) {
            for (Pixel pixel: row) {
                pixel.setRed(0);
            }
        }
        setImage(img);
    }

    public void removeGreen(){
        Pixel[][] img = getPixels();
        for (Pixel[] row: img) {
            for (Pixel pixel: row) {
                pixel.setGreen(0);
            }
        }
        setImage(img);
    }

    public void removeBlue(){
        Pixel[][] img = getPixels();
        for (Pixel[] row: img) {
            for (Pixel pixel: row) {
                pixel.setBlue(0);
            }
        }
        setImage(img);
    }
}
