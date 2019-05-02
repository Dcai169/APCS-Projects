public class GrayScaleImage extends CustomImage {
    public GrayScaleImage(String fileName, int x, int y) {
        super(fileName, x, y);
        makeGray();
    }

    public void makeGray(){
        Pixel[][] img = getPixels();
        for (Pixel[] row: img) {
            for (Pixel pixel: row) {
                int gray = (pixel.getRed()+pixel.getGreen()+pixel.getBlue())/3;
                pixel.setRed(gray);
                pixel.setGreen(gray);
                pixel.setBlue(gray);
            }
        }
        setImage(img);
    }
}
