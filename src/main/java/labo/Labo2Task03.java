package labo;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

public class Labo2Task03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String filename1 = sc.nextLine();
        String filename2 = sc.nextLine();
        try {
            BufferedImage img1 = ImageIO.read(new File(filename1));
            BufferedImage img2 = ImageIO.read(new File(filename2));
            System.out.println("(" + img1.getWidth() + "," + img1.getHeight() + ")");
            System.out.println("(" + img2.getWidth() + "," + img2.getHeight() + ")");
            ImageHistogram hist1 = new ImageHistogram();
            ImageHistogram hist2 = new ImageHistogram();

            hist1.addImage(img1);
            hist2.addImage(img2);

            double distance = hist1.distance(hist2);

            System.out.println(distance);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}

class ImageHistogram {
    public int[] counts;

    public  ImageHistogram(){
        this.counts = new int[64];
    }

    public void addRGB(int rgb){
        Color color = new Color(rgb);
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int index = 16*(red /64) + 4*(green/64) +(blue/64);
        ++counts[index];
    }
    public void addImage(BufferedImage img){
        for (int x = 0; x < img.getWidth(); x++){
            for (int y = 0; y < img.getHeight(); y++){
                int rgb=img.getRGB(x, y);
                addRGB(rgb);
            }
        }
    }
    public double distance(ImageHistogram other) {
        int[] counts1 = this.counts;
        double[] p1 = new double[64];
        double distance = 0;

        double sum1 = 0;
        for (int i = 0; i < 64; i++){
            sum1 += counts1[i];
        }
        int[] counts2 = other.counts;
        double[] p2 = new double[64];
        double sum2 = 0;
        for (int i = 0; i < 64; i++){
            sum2 += counts2[i];
        }
        for (int i = 0; i < 64; i++) {
            p1[i] = counts1[i] / sum1;
            p2[i] = counts2[i] / sum2;
            distance += Math.abs(p2[i] - p1[i]);
        }
        return distance;
    }
}