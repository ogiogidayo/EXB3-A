package labo;

import java.awt.image.BufferedImage;

public class Lobo2Task07 {
    public static void main(String[] args) {
        var image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
    }
}

class Complex {
    public double re;
    public double im;
    private static final double reMin = -2.1;
    private static final double reMax = 0.5;
    private static final double imMin = -1.3;
    private static final double imMax = 1.3;



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
