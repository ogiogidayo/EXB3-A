package labo;

import java.util.Date;
import java.util.Scanner;

public class Labo2Task02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Double z1_re = sc.nextDouble();
        Double z1_im = sc.nextDouble();
        Double z2_re = sc.nextDouble();
        Double z2_im = sc.nextDouble();
        Complex z1 = new Complex(z1_re, z1_im);
        Complex z2 = new Complex(z2_re, z2_im);
        System.out.println(z1.plus(z2).toString());
        System.out.println(z1.minus(z2).toString());
        System.out.println(z1.mul(z2).toString());
        System.out.println(z1.div(z2).toString());
    }
}

class Complex {
    public double re;
    public double im;

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