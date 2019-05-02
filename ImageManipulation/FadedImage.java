public class FadedImage extends CustomImage {
    public FadedImage(String fileName, int x, int y) {
        super(fileName, x, y);
        fadeToWhite();
    }

    public void fadeToWhite(){
        Pixel[][] img = getPixels();
        for (int i = 0; i < img.length; i++) {
            Pixel[] row = img[i];
            for (int i1 = 0; i1 < row.length; i1++) {
                Pixel pixel = row[i1];
                double dist = Math.sqrt((row.length/2-i1)*(row.length/2-i1)+(img.length/2-i)*(img.length/2-i));
                int alpha = 255 - (int) (dist / row.length * 255);
                pixel.setAlpha(alpha);
            }
        }
        setImage(img);
    }

    public void fadeToBlack(){
        Pixel[][] img = getPixels();
        for (int i = 0; i < img.length; i++) {
            Pixel[] row = img[i];
            for (int i1 = 0; i1 < row.length; i1++) {
                Pixel pixel = row[i1];
                double dist = Math.sqrt((row.length/2-i1)*(row.length/2-i1)+(img.length/2-i)*(img.length/2-i));
                int alpha = 255 - (int) (dist / row.length * 255);
                pixel.setAlpha(alpha);
                pixel.setRed(0);
                pixel.setGreen(0);
                pixel.setBlue(0);
            }
        }
        setImage(img);
    }
}
