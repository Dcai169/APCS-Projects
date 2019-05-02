public class PixelatedImage extends CustomImage {
    public PixelatedImage(String fileName, int x, int y) {
        super(fileName, x, y);
        pixelate();
    }

    public void pixelate(){
        Pixel[][] img = getPixels();
        Pixel[][] blur = new Pixel[img.length][img[0].length];
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[0].length; j++) {
                blur[i][j] = new Pixel(0); //, 0, 0, 255);
            }
        }
        int blurSize = 10;
        for (int i = 0; i < img.length; i+=blurSize) {
            for (int j = 0; j < img[0].length; j+=blurSize) {
                int red = 0;
                int green = 0;
                int blue = 0;
                for (int k = i; k < blurSize; k++) {
                    for (int l = j; l < blurSize; l++) {
                        Pixel pixel = img[k][l];
                        red += pixel.getRed();
                        green += pixel.getGreen();
                        blue += pixel.getBlue();
                    }
                }
                red = red/(blurSize*blurSize);
                green = green/(blurSize*blurSize);
                blue = blue/(blurSize*blurSize);
                for (int i1 = 0; i1 < blurSize; i1++) {
                    for (int i2 = 0; i2 < blurSize; i2++) {
                        Pixel pixel = blur[i1][i2];
                        pixel.setRed(red);
                        pixel.setGreen(green);
                        pixel.setBlue(blue);
                    }
                }
            }
        }
        setImage(img);
    }
}
