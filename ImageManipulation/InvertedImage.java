public class InvertedImage extends CustomImage {
    public InvertedImage(String fileName, int x, int y) {
        super(fileName, x, y);
        invert();
    }

    public void invert(){
        Pixel[][] img = getPixels();
        for (Pixel[] row: img) {
            for (Pixel pixel: row) {
                pixel.setRed(255-pixel.getRed());
                pixel.setGreen(255-pixel.getGreen());
                pixel.setBlue(255-pixel.getBlue());
            }
        }
        setImage(img);
    }
}
