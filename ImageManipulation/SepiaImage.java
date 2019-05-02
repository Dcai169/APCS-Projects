public class SepiaImage extends CustomImage {
    public SepiaImage(String fileName, int x, int y) {
        super(fileName, x, y);
        makeSepia();
    }

    public void makeSepia(){
        Pixel[][] img = getPixels();
        for (Pixel[] row: img) {
            for (Pixel pixel: row) {
                int red = (int)((pixel.getRed() * .393) + (pixel.getGreen() *.769) + (pixel.getBlue() * .189));
                int green = (int)((pixel.getRed() * .349) + (pixel.getGreen() *.686) + (pixel.getBlue() * .168));
                int blue = (int)((pixel.getRed() * .272) + (pixel.getGreen() *.534) + (pixel.getBlue() * .131));

                if (red < 255) {
                    pixel.setRed(red);
                } else {
                    pixel.setRed(255);
                }

                if (green < 255) {
                    pixel.setGreen(green);
                } else {
                    pixel.setGreen(255);
                }

                if (blue < 255) {
                    pixel.setBlue(blue);
                } else {
                    pixel.setBlue(255);
                }
            }
        }
        setImage(img);
    }
}
