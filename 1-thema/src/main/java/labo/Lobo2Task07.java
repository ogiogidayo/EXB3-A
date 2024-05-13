package labo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Random;

public class Lobo2Task07 {

    static HashMap<Integer,Color> hm = new HashMap<Integer,Color>();
    static Random rand = new Random();

    public static void main(String[] args) {
        var image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < 1000; x++){
            for (int y = 0; y < 1000; y++){
                Complex co = Complex.getComplexAt(x, y);
                int n = Complex.mandelCount(co);
                Color c = nToColor(n);
                image.setRGB(x, y, c.getRGB());
            }
        }
        File file = new File("mandelbrot.png");
        try{
            ImageIO.write(image, "png", file);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static Color nToColor(int n){
        if (n >= 300) return Color.BLACK;
        else if (hm.containsKey(n)) return hm.get(n);
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        Color c = new Color(r, g, b);
        hm.put(n, c);
        return hm.get(n);
    }
}

class Complex {
    public double re;
    public double im;
    private static final double reMin = -1.1;
    private static final double reMax = 0.7;
    private static final double imMin = -1.1;
    private static final double imMax = 0.7;

    public static Complex getComplexAt(int x, int y) {
        x += 1;
        y += 1;
        double imag = imMin + (imMax - imMin) * y / 1000;
        double real = reMin + (reMax - reMin) * x / 1000;
        return new Complex(real, imag);
    }

    public static int mandelCount(Complex c){
        var z = new Complex(0, 0);
        for (int i = 2; i <= 300; i++) {
            if (z.abs() > 2) return i;
            z =  (z.mul(z)).plus(c);
        }
        return 300;
    }

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double abs(){
        return Math.sqrt(re*re + im*im);
    }

    public String toString() {
        return "(" + this.re + "," + this.im + ")";
    }

    public Complex plus(Complex other){
        double new_re = this.re + other.re;
        double new_im = this.im + other.im;
        return new Complex(new_re, new_im);
    }

    public Complex minus(Complex other){
        double new_re = this.re - other.re;
        double new_im = this.im - other.im;
        return new Complex(new_re, new_im);
    }

    public Complex mul(Complex other){
        double new_re = (this.re * other.re) - (this.im * this.im);
        double new_im = (this.re * other.im) + (this.im * this.re);
        return new Complex(new_re, new_im);
    }

    public Complex div(Complex other){
        double new_re = ((this.re * other.re) + (this.im * this.im))/(other.abs()* other.abs());
        double new_im = ((this.im * other.re) - (this.re * this.im))/(other.abs()* other.abs());
        return new Complex(new_re, new_im);
    }
}
